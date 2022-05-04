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

/**
 * Record Card Panel
 *
 * Provides the base layout and components for all Record Card Panels, namely
 * create, modify, and view. Helper methods for enabling or disabling fields
 * based on view are provided so the respective classes can call as needed.
 */
abstract class RecordCardPanel extends CardPanel {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final String[] fieldNames = Record.getHeaders().split(",");

  private final JButton deleteButton = new JButton("Delete");
  private final JButton mainMenuButton = new JButton("Main Menu Search");
  private final JButton backToResultsButton = new JButton("Return to Results");
  private final JLabel[] fieldLabels = new JLabel[fieldNames.length];
  private final JTextField[] textFields = new JTextField[fieldNames.length];
  private final JComboBox<RecordType> recordTypeComboBox = new JComboBox<>(RecordType.values());
  private final GridBagConstraints constraints = new GridBagConstraints();

  private JButton activeSaveButton = new JButton("SAVE");
  private CardView cardView;
  private Record currentRecord;
  private boolean isFromResults = false;

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
      deleteRecord(deleteOption);
    });
    setButtonAction(backToResultsButton, e -> {
      LOGGER.info("Back to results button pressed");
      cardView.setPanel(new ResultsCardPanel(cardView));
    });
  }

  private void deleteRecord(int deleteOption) {
    if (deleteOption == JOptionPane.YES_OPTION) {
      LOGGER.info("Delete confirmed");
      cardView.dataAccessor.delete(currentRecord);
      if (isFromResults) {
        cardView.setPanel(new ResultsCardPanel(cardView));
      } else {
        cardView.setPanel(new SearchCardPanel(cardView));
      }
    } else {
      LOGGER.info("Delete cancelled");
    }
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
      return new Record.Builder(input[0])
          .title(input[1])
          .type(input[2])
          .lastName(input[3])
          .firstName(input[4])
          .date(input[5])
          .category(input[6])
          .summary(input[7])
          .location(input[8])
          .build();
    }
    return null;
  }

  private String[] extractInputData() {
    String[] input = new String[textFields.length];
    for (int i = 0; i < input.length; i++) {
      input[i] = textFields[i].getText();
    }
    return input;
  }

  public boolean isFromResults() {
    return isFromResults;
  }

  void setFromResults(boolean isFromResults) {
    this.isFromResults = isFromResults;
  }

  public abstract String getName();
}
