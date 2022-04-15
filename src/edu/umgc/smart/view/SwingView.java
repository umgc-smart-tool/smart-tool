package edu.umgc.smart.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import edu.umgc.smart.model.RecordType;

/**
 * File: SwingView.java
 * Coder: Joshua Longo
 * Project: SMART Tool - For CMSC 495
 * Version: 0.0.3
 * Last Modified: 4/8/22
 * Class Description: Generates the GUI for the SMART Tool
 */

public class SwingView extends View {
  // Some global stuff for the GUI windows
  static final Dimension STANDARD_WINDOW_SIZE = new Dimension(750, 500);
  static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  enum ViewType {
    VIEW, CREATE
  } // Types of views for the viewRecordWindow() window

  String[] fieldNames = { "Reference Number", "Title", "Record Type", "Author", "Date", "Category", "Summary" };

  /**
   * mainWindow(Point location): This method is called first after the start()
   * method. This is
   * the 'main menu' for the SMART Tool GUI. This menu houses a general/simplified
   * search box,
   * a search button, a button to the advanced search window and a button to the
   * create records
   * window.
   *
   * @param location - This parameter defines the location of the window on the
   *                 screen.
   */
  private void mainWindow(Point location) {
    // mainWindow() Swing fields
    JLabel searchBoxLabel = new JLabel("Search for Company Records", SwingConstants.CENTER);
    JLabel emptyLabel = new JLabel("  "); // Empty label used for spacing.
    JTextField searchBoxField = new JTextField();
    JButton searchButton = new JButton("Search");
    JButton advancedSearchButton = new JButton("Advanced Search");
    JButton createRecordButton = new JButton("Create Record");

    JFrame mainFrame = new JFrame();
    mainFrame.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // ---------- Add action listeners (functionality) to buttons ----------
    // Search Button
    searchButton.addActionListener(
        e -> LOGGER.log(Level.INFO,
            String.format("Search Button Pressed. Searching for: %s", searchBoxField.getText())));

    // Create Record Button
    createRecordButton.addActionListener(e -> {
      viewRecordWindow(mainFrame.getLocationOnScreen(), ViewType.CREATE);
      mainFrame.setVisible(false);
    });

    // Advanced Search Button
    advancedSearchButton.addActionListener(e -> {
      advancedSearchWindow(mainFrame.getLocationOnScreen());
      mainFrame.setVisible(false);
    });

    // ---------- Add Swing fields to mainFrame with GridBagLayout constraints
    // ----------
    // Empty field for spacing - Bottom right to allow for components to center the
    // frame
    constraints.anchor = GridBagConstraints.LAST_LINE_END; // Anchor to bottom right corner
    constraints.weighty = 0.5;
    constraints.gridwidth = 1;
    constraints.ipadx = 50;
    constraints.gridx = 4;
    constraints.gridy = 4;
    mainFrame.add(emptyLabel, constraints);

    // createRecordButton
    createRecordButton.setBorderPainted(false);
    createRecordButton.setForeground(Color.BLUE);
    constraints.anchor = GridBagConstraints.FIRST_LINE_START; // Anchor at top left corner
    constraints.weighty = 0.5;
    constraints.ipadx = 0;
    constraints.gridwidth = 1;
    constraints.gridx = 0;
    constraints.gridy = 0;
    mainFrame.add(createRecordButton, constraints);

    // searchBoxField
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weighty = 0;
    constraints.weightx = 0.5;
    constraints.gridwidth = 2;
    constraints.gridx = 1;
    constraints.gridy = 2;
    mainFrame.add(searchBoxField, constraints);

    // searchBoxLabel
    constraints.weightx = 0;
    constraints.gridwidth = 2;
    constraints.gridx = 1;
    constraints.gridy = 1;
    mainFrame.add(searchBoxLabel, constraints);

    // searchButton
    constraints.weightx = 0.0;
    constraints.gridwidth = 1;
    constraints.gridx = 3;
    constraints.gridy = 2;
    mainFrame.add(searchButton, constraints);

    // advancedSearchButton
    advancedSearchButton.setBorderPainted(false);
    advancedSearchButton.setForeground(Color.BLUE);
    constraints.gridwidth = 1;
    constraints.gridx = 2;
    constraints.gridy = 3;
    mainFrame.add(advancedSearchButton, constraints);

    // ---------- Setup and show mainFrame ----------
    mainFrame.setSize(STANDARD_WINDOW_SIZE);
    mainFrame.setMaximumSize(STANDARD_WINDOW_SIZE);
    mainFrame.setMinimumSize(STANDARD_WINDOW_SIZE);
    mainFrame.setTitle("SMART Tool - Main Menu");
    if (Objects.equals(location, new Point(0, 0)))
      mainFrame.setLocationRelativeTo(null); // If first time opening mainFrame, set location as center.
    else
      mainFrame.setLocation(location); // Else set location as last location.
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mainFrame.setVisible(true);
  }// End mainWindow() method

  /**
   * advancedSearchWindow(Point location): This method holds the window for the
   * advanced search.
   * This search has many fields the user can search with, being a more specific
   * search than the
   * general search field within the main menu window. A button to return to the
   * main menu is also
   * present in this window.
   *
   * @param location - This parameter defines the location of the window on the
   *                 screen.
   */
  private void advancedSearchWindow(Point location) {
    // advancedSearchWindow() Swing fields
    JButton mainSearchButton = new JButton("Main Menu Search");

    JLabel[] labels = new JLabel[fieldNames.length];
    JTextField[] searchFields = new JTextField[fieldNames.length];
    JButton[] searchButtons = new JButton[fieldNames.length];

    JFrame advancedSearchFrame = new JFrame();
    advancedSearchFrame.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // Create labels, search fields and search buttons with a loop
    for (int i = 0; i < fieldNames.length; i++) {
      labels[i] = new JLabel(fieldNames[i]);
      searchFields[i] = new JTextField();
      searchButtons[i] = new JButton("Search");
    }

    // ---------- Add action listeners (functionality) to buttons ----------

    // Reference Number field search button
    searchButtons[0]
        .addActionListener(e -> LOGGER.log(Level.INFO,
        String.format("Searching by reference number: %s", searchFields[0].getText())));

    // Title field search button
    searchButtons[1].addActionListener(e -> LOGGER.log(Level.INFO,
    String.format("Searching by title: %s", searchFields[1].getText())));

    // Record Type field search button
    searchButtons[2]
        .addActionListener(e -> LOGGER.log(Level.INFO,
        String.format("Searching by record type: %s", searchFields[2].getText())));

    // Author field search button
    searchButtons[3].addActionListener(e -> LOGGER.log(Level.INFO,
    String.format("Searching by author: %s", searchFields[3].getText())));

    // Date field search button
    searchButtons[4].addActionListener(e -> LOGGER.log(Level.INFO,
    String.format("Searching by date: %s", searchFields[4].getText())));

    // Category field search button
    searchButtons[5].addActionListener(e -> LOGGER.log(Level.INFO,
    String.format("Searching by category: %s", searchFields[5].getText())));

    // Summary field search button
    searchButtons[6].addActionListener(e -> LOGGER.log(Level.INFO,
    String.format("Searching by summary: %s", searchFields[6].getText())));

    // mainSearchButton - Return to simple search / main window.
    mainSearchButton.addActionListener(e -> {
      mainWindow(advancedSearchFrame.getLocationOnScreen());
      advancedSearchFrame.setVisible(false);
    });

    // ---------- Add labels, search fields and search buttons to frame ----------
    constraints.weighty = 0.15;
    constraints.insets = new Insets(10, 10, 10, 10);
    for (int i = 0; i < fieldNames.length; i++) {
      constraints.gridy = i;
      constraints.gridx = 0;
      constraints.ipadx = 25;
      advancedSearchFrame.add(labels[i], constraints);
      constraints.gridwidth = 2;
      constraints.ipadx = 0;
      constraints.fill = GridBagConstraints.HORIZONTAL;
      constraints.weightx = 0.2;
      constraints.gridx = 1;
      advancedSearchFrame.add(searchFields[i], constraints);
      constraints.gridwidth = 1;
      constraints.weightx = 0;
      constraints.gridx = 3;
      advancedSearchFrame.add(searchButtons[i], constraints);
    }
    constraints.gridx = 0;
    constraints.gridy = 7;
    mainSearchButton.setBorderPainted(false);
    mainSearchButton.setForeground(Color.BLUE);
    advancedSearchFrame.add(mainSearchButton, constraints);

    // ---------- Setup and show advancedSearchFrame ----------
    advancedSearchFrame.setSize(STANDARD_WINDOW_SIZE);
    advancedSearchFrame.setMaximumSize(STANDARD_WINDOW_SIZE);
    advancedSearchFrame.setMinimumSize(STANDARD_WINDOW_SIZE);
    advancedSearchFrame.setTitle("SMART Tool - Advanced Search");
    advancedSearchFrame.setLocation(location);
    advancedSearchFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    advancedSearchFrame.setVisible(true);
  }// End advancedSearchWindow() method

  /**
   * viewRecordWindow(Point location, ViewType viewType): This method generates
   * the window for
   * viewing and creating/modifying records. The same types of fields used in the
   * advanced
   * search window are present here, but used to view, create/modify rather than
   * search.
   *
   * @param location - This parameter defines the location of the window on the
   *                 screen.
   * @param viewType - This parameter helps identify which view of this window
   *                 should be shown.
   */
  private void viewRecordWindow(Point location, ViewType viewType) {
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton mainMenuButton = new JButton("Main Menu Search");
    JLabel[] fieldLabels = new JLabel[fieldNames.length];
    JTextField[] textFields = new JTextField[fieldNames.length];
    JComboBox<RecordType> recordTypeComboBox = new JComboBox<>(RecordType.values());

    JFrame viewRecordFrame = new JFrame();
    viewRecordFrame.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // Create labels, text fields and buttons with loop.
    for (int i = 0; i < fieldNames.length; i++) {
      fieldLabels[i] = new JLabel(fieldNames[i]);
      textFields[i] = new JTextField();
    }

    // ---------- Add action listeners (functionality) to buttons ----------
    // Main Menu Button
    mainMenuButton.addActionListener(e -> {
      mainWindow(viewRecordFrame.getLocationOnScreen());
      viewRecordFrame.setVisible(false);
    });

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
      viewRecordFrame.add(fieldLabels[i], constraints);
      constraints.gridx = 1;
      constraints.gridwidth = 2;
      constraints.insets = new Insets(10, 5, 10, 50);
      constraints.weightx = 0.2;
      if (fieldNames[i].equals("Record Type"))
        viewRecordFrame.add(recordTypeComboBox, constraints);
      else
        viewRecordFrame.add(textFields[i], constraints);
    }
    constraints.insets = new Insets(10, 50, 10, 5);
    constraints.gridy = fieldLabels.length;
    constraints.gridx = 0;
    constraints.gridwidth = 1;
    mainMenuButton.setBorderPainted(false);
    mainMenuButton.setForeground(Color.BLUE);
    viewRecordFrame.add(mainMenuButton, constraints);

    // CREATE
    constraints.insets = new Insets(10, 20, 10, 5);
    constraints.gridx = 1;
    viewRecordFrame.add(saveButton, constraints);
    constraints.insets = new Insets(10, 5, 10, 50);
    constraints.gridx = 2;
    viewRecordFrame.add(deleteButton, constraints);

    // ---------- Set editable fields based on the ViewType ----------
    switch (viewType) {
      case CREATE: // When creating/modifying, all fields but date are editable
        for (int i = 0; i < fieldNames.length; i++) {
          textFields[i].setEditable(true);
          textFields[i].setBackground(Color.white);
        }
        textFields[4].setEditable(false); // Date text field.
        textFields[4].setBackground(Color.LIGHT_GRAY);
        viewRecordFrame.setTitle("SMART Tool - Create / Modify Record");
        break;
      case VIEW: // When viewing, no fields are editable
        for (int i = 0; i < fieldNames.length; i++) {
          textFields[i].setEditable(false);
          textFields[i].setBackground(Color.LIGHT_GRAY);
        }
        viewRecordFrame.setTitle("SMART Tool - View Record");
        break;
      default:
        LOGGER.log(Level.SEVERE, "Invalid View");
    }

    // ---------- Setup and show viewRecordFrame ----------
    viewRecordFrame.setSize(STANDARD_WINDOW_SIZE);
    viewRecordFrame.setMaximumSize(STANDARD_WINDOW_SIZE);
    viewRecordFrame.setMinimumSize(STANDARD_WINDOW_SIZE);
    viewRecordFrame.setLocation(location);
    viewRecordFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    viewRecordFrame.setVisible(true);
  }// End viewRecordWindow() method

  public void start() {
    mainWindow(new Point());
  }// End start() method
}// End SwingView class
