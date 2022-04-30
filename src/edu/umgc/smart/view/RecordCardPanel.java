package edu.umgc.smart.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import edu.umgc.smart.model.InputValidator;
import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public abstract class RecordCardPanel extends CardPanel {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final String[] fieldNames = Record.getHeaders().split(",");

  private CardView cardView;
  private Record currentRecord;

  private JButton activeSaveButton = new JButton("SAVE");
  private JButton deleteButton = new JButton("Delete");
  private JButton mainMenuButton = new JButton("Main Menu Search");
  private JButton backToResultsButton = new JButton("Return to Results");
  private JLabel[] fieldLabels = new JLabel[fieldNames.length];
  private JTextField[] textFields = new JTextField[fieldNames.length];
  private JComboBox<RecordType> recordTypeComboBox = new JComboBox<>(RecordType.values());
  private GridBagConstraints constraints = new GridBagConstraints();

  RecordCardPanel(CardView cardView) {
    this.cardView = cardView;
    this.setLayout(new GridBagLayout());
    initializeLabelsAndFields();
    initializeButtonActions();
    addPanelComponents();
  }

  RecordCardPanel(CardView cardView, Record r) {
    this(cardView);
    currentRecord = r;
    textFields[0].setText(currentRecord.getReferenceNumber());
    textFields[1].setText(currentRecord.getTitle());
    textFields[2].setText(currentRecord.getDocumentType().toString());
    textFields[3].setText(currentRecord.getAuthorLastName());
    textFields[4].setText(currentRecord.getAuthorFirstName());
    textFields[5].setText(currentRecord.getDate());
    textFields[6].setText(currentRecord.getCategory());
    textFields[7].setText(currentRecord.getSummary());
    textFields[8].setText(currentRecord.getLocation());
  }

  private void initializeLabelsAndFields() {
    for (int i = 0; i < fieldNames.length; i++) {
      fieldLabels[i] = new JLabel(fieldNames[i]);
      textFields[i] = new JTextField();
    }
  }

  private void initializeButtonActions() {
    setButtonAction(mainMenuButton, e -> cardView.setPanel(new SearchCardPanel(cardView)));
    setButtonAction(deleteButton, e -> {
      int deleteOption = JOptionPane.showConfirmDialog(null,
          "Are you sure you want to delete this record?");
      if (deleteOption == JOptionPane.YES_OPTION) {
        LOGGER.info("Delete confirmed");
        JOptionPane.showMessageDialog(null, "Deleting records is not yet implemented.");
      } else {
        LOGGER.info("Delete cancelled");
      }
    });
    setButtonAction(backToResultsButton, e -> {
      LOGGER.info("Back to results button pressed");
      cardView.setPanel(new ResultsCardPanel(cardView));
    });
  }

  void setSaveButton(JButton button, ActionListener actionListener) {
    constraints.insets = new Insets(10, 20, 10, 5);
    constraints.gridx = 2;
    button.addActionListener(actionListener);
    this.remove(activeSaveButton);
    this.add(button, constraints);
    activeSaveButton = button;
  }

  private void setButtonAction(JButton button, ActionListener actionListener) {
    ActionListener[] current = button.getActionListeners();
    for (int i = 0; i < current.length; i++) {
      button.removeActionListener(current[i]);
    }
    button.addActionListener(actionListener);
  }

  private void addPanelComponents() {
    constraints.weighty = 0.15;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    for (int i = 0; i < fieldNames.length; i++) {
      constraints.weightx = 0;
      constraints.gridy = i;
      constraints.gridx = 0;
      constraints.insets = new Insets(10, 50, 10, 5);
      this.add(fieldLabels[i], constraints);
      constraints.gridx = 1;
      constraints.gridwidth = 3;
      constraints.insets = new Insets(10, 5, 10, 50);
      constraints.weightx = 0.2;
      if (fieldNames[i].equals("Record Type"))
        this.add(recordTypeComboBox, constraints);
      else
        this.add(textFields[i], constraints);
    }
    constraints.insets = new Insets(10, 50, 10, 5);
    constraints.gridy = fieldLabels.length;
    constraints.gridx = 0;
    constraints.gridwidth = 1;
    mainMenuButton.setBorderPainted(false);
    mainMenuButton.setForeground(Color.BLUE);
    this.add(mainMenuButton, constraints);

    constraints.insets = new Insets(10, 5, 10, 50);
    constraints.gridx = 3;
    this.add(deleteButton, constraints);
  }

  void disableAllFields() {
    for (int i = 0; i < fieldNames.length; i++) {
      textFields[i].setEditable(false);
      textFields[i].setBackground(Color.LIGHT_GRAY);
    }
  }

  void enableModifyFields() {
    for (int i = 0; i < fieldNames.length; i++) {
      textFields[i].setEditable(true);
      textFields[i].setBackground(Color.white);
    }
    // Disable Reference Number Field
    textFields[0].setEditable(false);
    textFields[0].setBackground(Color.LIGHT_GRAY);
    // Disable Date Field
    textFields[5].setEditable(false);
    textFields[5].setBackground(Color.LIGHT_GRAY);
  }

  void enableReturnButton(boolean enable) {
    if (enable) {
      constraints.insets = new Insets(10, 10, 10, 10);
      constraints.gridx = 1;
      this.add(backToResultsButton, constraints);
    } else {
      this.remove(backToResultsButton);
    }
  }

  Record buildRecord() {
    String[] input = extractInputData();
    if (InputValidator.isValidReferenceNumber(input[0])) {
      return buildValidatedRecord(input);
    }
    return null;
  }

  private Record buildValidatedRecord(String[] input) {
    return new Record.Builder(input[0])
        .title(input[1].isBlank() ? "" : input[1])
        .type(InputValidator.isValidRecordType(input[2]) ? input[2] : null)
        .lastName(InputValidator.isValidName(input[3]) ? input[3] : "")
        .firstName(InputValidator.isValidName(input[4]) ? input[4] : "")
        .date(InputValidator.isValidDate(input[5]) ? input[5] : "")
        .category(input[6].isBlank() ? "" : input[6])
        .summary(InputValidator.isValidSummaryLength(input[7]) ? input[7] : input[7].substring(0, 500))
        .location(input[8].isBlank() ? "" : input[8])
        .build();
  }

  private String[] extractInputData() {
    String[] input = new String[textFields.length];
    for (int i = 0; i < input.length; i++) {
      input[i] = textFields[i].getText();
    }
    return input;
  }

  public abstract String getName();
}
