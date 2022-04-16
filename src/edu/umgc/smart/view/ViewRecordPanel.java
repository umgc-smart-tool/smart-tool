package edu.umgc.smart.view;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public class ViewRecordPanel extends JPanel {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private String[] fieldNames = Record.getHeaders().split(",");

  public ViewRecordPanel(CardView cardView, ViewType viewType) {
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton mainMenuButton = new JButton("Main Menu Search");
    JLabel[] fieldLabels = new JLabel[fieldNames.length];
    JTextField[] textFields = new JTextField[fieldNames.length];
    JComboBox<RecordType> recordTypeComboBox = new JComboBox<>(RecordType.values());

    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // Create labels, text fields and buttons with loop.
    for (int i = 0; i < fieldNames.length; i++) {
      fieldLabels[i] = new JLabel(fieldNames[i]);
      textFields[i] = new JTextField();
    }

    // ---------- Add action listeners (functionality) to buttons ----------
    // Main Menu Button
    mainMenuButton.addActionListener(e -> cardView.setPanel(CardView.SEARCH_PANEL));

    // Save Button
    saveButton.addActionListener(e -> {
      if (viewType == ViewType.CREATE) {
        LOGGER.log(Level.INFO, "Save - Create/Modify");
      } else if (viewType == ViewType.VIEW) {
        LOGGER.log(Level.INFO, "Save - View");
      } else {
        LOGGER.log(Level.INFO, "error");
      }
    });

    // Delete Button
    deleteButton.addActionListener(e -> LOGGER.log(Level.INFO, "Delete button pressed"));

    // ---------- Add labels, text fields, buttons and frame ----------
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

    // ---------- Set editable fields based on the ViewType ----------
    switch (viewType) {
      case CREATE: // When creating/modifying, all fields but date are editable
        for (int i = 0; i < fieldNames.length; i++) {
          textFields[i].setEditable(true);
          textFields[i].setBackground(Color.white);
        }
        textFields[5].setEditable(false); // Date text field.
        textFields[5].setBackground(Color.LIGHT_GRAY);
        break;
      case VIEW: // When viewing, no fields are editable
        for (int i = 0; i < fieldNames.length; i++) {
          textFields[i].setEditable(false);
          textFields[i].setBackground(Color.LIGHT_GRAY);
        }
        break;
      default:
        LOGGER.log(Level.SEVERE, "Invalid View");
    }
  }

}
