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

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public abstract class RecordCardPanel extends CardPanel {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final String[] fieldNames = Record.getHeaders().split(",");

  private CardView cardView;
  private Record currentRecord;

  private JButton saveModifiedRecordButton = new JButton("Save Changes");
  private JButton saveNewRecordButton = new JButton("Save New Record");
  private JButton deleteButton = new JButton("Delete");
  private JButton modifyButton = new JButton("Modify");
  private JButton mainMenuButton = new JButton("Main Menu Search");
  private JButton backToResultsButton = new JButton("Return to Results");
  private JLabel[] fieldLabels = new JLabel[fieldNames.length];
  private JTextField[] textFields = new JTextField[fieldNames.length];
  private JComboBox<RecordType> recordTypeComboBox = new JComboBox<>(RecordType.values());
  private GridBagConstraints constraints = new GridBagConstraints();

  RecordCardPanel(CardView cardView, String viewType) {
    this.cardView = cardView;
    this.setLayout(new GridBagLayout());
    initializeLabelsAndFields();
    initializeButtonActions();
    addPanelComponents(viewType);
  }

  RecordCardPanel(CardView cardView, Record r, String viewType) {
    this(cardView, viewType);
    currentRecord = r;
    textFields[0].setText(currentRecord.getReferenceNumber());
    textFields[1].setText(currentRecord.getTitle());
    textFields[2].setText(currentRecord.getDocumentType().toString());
    textFields[3].setText(currentRecord.getAuthorLastName());
    textFields[4].setText(currentRecord.getAuthorFirstName());
    textFields[5].setText(currentRecord.getDate().toString());
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
    setDeleteButtonAction(e -> {
      int deleteOption = JOptionPane.showConfirmDialog(null,
              "Are you sure you want to delete this record?");
      // TODO: Implement the delete button functionality
      if (deleteOption == 0){ // A 0 returned from showConfirmDialog is yes. 1 = no, 2 = cancel.
        LOGGER.info("Delete confirmed");
        JOptionPane.showMessageDialog(null, "Deleting records is not yet implemented.");
      } else {
        LOGGER.info("Delete cancelled");
      }
    });
    setBackResultsButtonAction(e -> {
      LOGGER.info("Back to results button pressed");
      cardView.setPanel(new ResultsCardPanel(cardView));
    });
  }

  void setMainMenuButtonAction(ActionListener actionListener) {
    setButtonAction(mainMenuButton, actionListener);
  }

  void setSaveNewRecordButtonAction(ActionListener actionListener) {
    setButtonAction(saveNewRecordButton, actionListener);
  }

  void setSaveModifiedRecordButtonAction(ActionListener actionListener){
    setButtonAction(saveModifiedRecordButton, actionListener);
  }

  void setModifyButtonAction(ActionListener actionListener){
    setButtonAction(modifyButton, actionListener);
  }

  void setDeleteButtonAction(ActionListener actionListener) {
    setButtonAction(deleteButton, actionListener);
  }

  void setBackResultsButtonAction(ActionListener actionListener){
    setButtonAction(backToResultsButton, actionListener);
  }

  private void setButtonAction(JButton button, ActionListener actionListener) {
    ActionListener[] current = button.getActionListeners();
    for (int i = 0; i < current.length; i++) {
      button.removeActionListener(current[i]);
    }
    button.addActionListener(actionListener);
  }

  private void addPanelComponents(String viewType) {
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
    if (viewType.equals("view") || viewType.equals("modify")){
      constraints.insets = new Insets(10, 10, 10, 10);
      constraints.gridx = 1;
      this.add(backToResultsButton, constraints);
    }

    constraints.insets = new Insets(10, 20, 10, 5);
    constraints.gridx = 2;

    // TODO Move to respective RecordCardPanel implementations
    // Display modify or save button based on the view
    switch (viewType) {
      case "create":
        this.add(saveNewRecordButton, constraints);
        break;
      case "modify":
        this.add(saveModifiedRecordButton, constraints);
        break;
      case "view":
        this.add(modifyButton, constraints);
        break;
      default:
        LOGGER.info("view type error");
    }

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

  void enableModifyFields(){
    for (int i = 0; i < fieldNames.length; i++) {
      textFields[i].setEditable(true);
      textFields[i].setBackground(Color.white);
    }
    textFields[5].setEditable(false);
    textFields[5].setBackground(Color.LIGHT_GRAY);
  }

  public abstract String getName();
}
