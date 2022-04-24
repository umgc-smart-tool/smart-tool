package edu.umgc.smart.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public abstract class RecordCardPanel extends CardPanel {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final String[] fieldNames = Record.getHeaders().split(",");

  private CardView cardView;

  private JButton saveButton = new JButton("Save");
  private JButton deleteButton = new JButton("Delete");
  private JButton mainMenuButton = new JButton("Main Menu Search");
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

  RecordCardPanel(CardView cardView, Record currentRecord) {
    this(cardView);
    textFields[0].setText(currentRecord.getReferenceNumber());
    textFields[1].setText(currentRecord.getTitle());
    textFields[2].setText(currentRecord.getDocumentType().toString());
    textFields[3].setText(currentRecord.getAuthorLastName());
    textFields[4].setText(currentRecord.getAuthorFirstName());
    textFields[5].setText(currentRecord.getDocumentType().toString());
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
    setMainMenuButtonAction(e -> cardView.setPanel(new SearchCardPanel(cardView)));
    setSaveButtonAction(e -> LOGGER.info("Save Button pressed"));
    setDeleteButtonAction(e -> LOGGER.info("Delete button pressed"));
  }

  void setMainMenuButtonAction(ActionListener actionListener) {
    setButtonAction(mainMenuButton, actionListener);
  }

  void setSaveButtonAction(ActionListener actionListener) {
    setButtonAction(saveButton, actionListener);
  }

  void setDeleteButtonAction(ActionListener actionListener) {
    setButtonAction(deleteButton, actionListener);
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
      constraints.gridwidth = 2;
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

    // CREATE
    constraints.insets = new Insets(10, 20, 10, 5);
    constraints.gridx = 1;
    this.add(saveButton, constraints);
    constraints.insets = new Insets(10, 5, 10, 50);
    constraints.gridx = 2;
    this.add(deleteButton, constraints);
  }

  void disableAllFields() {
    for (int i = 0; i < fieldNames.length; i++) {
      textFields[i].setEditable(false);
      textFields[i].setBackground(Color.LIGHT_GRAY);
    }
  }

  public abstract String getName();
}
