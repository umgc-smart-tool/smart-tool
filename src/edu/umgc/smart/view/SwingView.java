package edu.umgc.smart.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.Objects;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

/**
 * File: SwingView.java
 * Coder: Joshua Longo
 * Project: SMART Tool - For CMSC 495
 * Version: 0.1.0
 * Last Modified: 4/16/22
 * Class Description: Generates the GUI for the SMART Tool.
 */

public class SwingView extends View {
  /**
   *
   */
  private static final String SEARCH = "Search";
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final Dimension STANDARD_WINDOW_SIZE = new Dimension(750, 500);
  String[] fieldNames = {"Reference Number", "Title", "Record Type", "Author", "Date", "Category", "Summary"};

  Record[] records = makeTestRecords(10); //DELETE after MemoryDataAccessor methods are implemented

  /**
   * mainWindow(Point location): This method is called first after the start() method. This is
   * the 'main menu' for the SMART Tool GUI. This menu houses a general/simplified search box,
   * a search button, a button to the advanced search window and a button to the create records
   * window.
   * @param location - This parameter defines the location of the window on the screen.
   */
  private void mainWindow(Point location){
    //mainWindow() Swing fields
    JLabel searchBoxLabel = new JLabel("Search for Company Records", SwingConstants.CENTER);
    JLabel emptyLabel = new JLabel("  "); //Empty label used for spacing.
    JTextField searchBoxField = new JTextField();
    JButton searchButton = new JButton(SEARCH);
    JButton advancedSearchButton = new JButton("Advanced Search");
    JButton createRecordButton = new JButton("Create Record");

    JFrame mainFrame = new JFrame();
    mainFrame.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // ---------- Add action listeners (functionality) to buttons ----------
    //Search Button
    searchButton.addActionListener(e -> {
      LOGGER.info("Search Button Pressed. Searching for: " + searchBoxField.getText());
      //Temporary call to result window for testing
      resultsWindow(mainFrame.getLocationOnScreen(), searchBoxField.getText(), records);
      mainFrame.setVisible(false);
    });

    //Create Record Button
    createRecordButton.addActionListener(e -> {
      viewRecordWindow(mainFrame.getLocationOnScreen(), ViewType.CREATE, null);
      mainFrame.setVisible(false);
    });

    //Advanced Search Button
    advancedSearchButton.addActionListener(e -> {
      advancedSearchWindow(mainFrame.getLocationOnScreen());
      mainFrame.setVisible(false);
    });


    // ---------- Add Swing fields to mainFrame with GridBagLayout constraints ----------
    //Empty field for spacing - Bottom right to allow for components to center the frame
    constraints.anchor = GridBagConstraints.LAST_LINE_END; //Anchor to bottom right corner
    constraints.weighty = 0.5;
    constraints.gridwidth = 1;
    constraints.ipadx = 50;
    constraints.gridx = 4;
    constraints.gridy = 4;
    mainFrame.add(emptyLabel, constraints);

    //createRecordButton
    createRecordButton.setBorderPainted(false);
    createRecordButton.setForeground(Color.BLUE);
    constraints.anchor = GridBagConstraints.FIRST_LINE_START; //Anchor at top left corner
    constraints.weighty = 0.5;
    constraints.ipadx = 0;
    constraints.gridwidth = 1;
    constraints.gridx = 0;
    constraints.gridy = 0;
    mainFrame.add(createRecordButton, constraints);

    //searchBoxField
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weighty = 0;
    constraints.weightx = 0.5;
    constraints.gridwidth = 2;
    constraints.gridx = 1;
    constraints.gridy = 2;
    mainFrame.add(searchBoxField, constraints);

    //searchBoxLabel
    constraints.weightx = 0;
    constraints.gridwidth = 2;
    constraints.gridx = 1;
    constraints.gridy = 1;
    mainFrame.add(searchBoxLabel, constraints);

    //searchButton
    constraints.weightx = 0.0;
    constraints.gridwidth = 1;
    constraints.gridx = 3;
    constraints.gridy = 2;
    mainFrame.add(searchButton, constraints);

    //advancedSearchButton
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
      mainFrame.setLocationRelativeTo(null); //If first time opening mainFrame, set location as center.
    else
      mainFrame.setLocation(location); //Else set location as last location.
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mainFrame.setVisible(true);
  }//End mainWindow() method

  /**
   * advancedSearchWindow(Point location): This method holds the window for the advanced search.
   * This search has many fields the user can search with, being a more specific search than the
   * general search field within the main menu window. A button to return to the main menu is also
   * present in this window.
   * @param location - This parameter defines the location of the window on the screen.
   */
  private void advancedSearchWindow(Point location){
    //advancedSearchWindow() Swing fields
    JButton mainSearchButton = new JButton("Main Menu Search");

    JLabel[] labels = new JLabel[fieldNames.length];
    JTextField[] searchFields = new JTextField[fieldNames.length];
    JButton[] searchButtons = new JButton[fieldNames.length];

    JFrame advancedSearchFrame = new JFrame();
    advancedSearchFrame.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    //Create labels, search fields and search buttons with a loop
    for (int i = 0; i < fieldNames.length; i++){
      labels[i] = new JLabel(fieldNames[i]);
      searchFields[i] = new JTextField();
      searchButtons[i] = new JButton(SEARCH);
    }

    // ---------- Add action listeners (functionality) to buttons ----------

    //Reference Number field search button
    searchButtons[0].addActionListener(e ->
      LOGGER.info("Searching by reference number: " + searchFields[0].getText())
    );

    //Title field search button
    searchButtons[1].addActionListener(e ->
      LOGGER.info("Searching by title: " + searchFields[1].getText())
    );

    //Record Type field search button
    searchButtons[2].addActionListener(e ->
      LOGGER.info("Searching by record type: " + searchFields[2].getText())
    );

    //Author field search button
    searchButtons[3].addActionListener(e ->
      LOGGER.info("Searching by author: " + searchFields[3].getText())
    );

    //Date field search button
    searchButtons[4].addActionListener(e ->
      LOGGER.info("Searching by date: " + searchFields[4].getText())
    );

    //Category field search button
    searchButtons[5].addActionListener(e ->
      LOGGER.info("Searching by category: " + searchFields[5].getText())
    );

    //Summary field search button
    searchButtons[6].addActionListener(e ->
      LOGGER.info("Searching by summary: " + searchFields[6].getText())
    );

    //mainSearchButton - Return to simple search / main window.
    mainSearchButton.addActionListener(e -> {
      mainWindow(advancedSearchFrame.getLocationOnScreen());
      advancedSearchFrame.setVisible(false);
    });


    // ---------- Add labels, search fields and search buttons to frame ----------
    constraints.weighty = 0.15;
    constraints.insets = new Insets(10, 10, 10, 10);
    for (int i = 0; i < fieldNames.length; i++){
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
  }//End advancedSearchWindow() method

  /**
   * viewRecordWindow(Point location, ViewType viewType): This method generates the window for
   * viewing and creating/modifying records. The same types of fields used in the advanced
   * search window are present here, but used to view, create/modify rather than search.
   * @param location - This parameter defines the location of the window on the screen.
   * @param viewType - This parameter helps identify which view of this window should be shown.
   * @param currentRecord - This is the record that is to be displayed under the 'VIEW' view type.
   */
  private void viewRecordWindow(Point location, ViewType viewType, Record currentRecord){
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton mainMenuButton = new JButton("Main Menu Search");
    JButton modifyButton = new JButton("Modify");
    JLabel[] fieldLabels = new JLabel[fieldNames.length];
    JTextField[] textFields = new JTextField[fieldNames.length];
    JComboBox<RecordType> recordTypeComboBox = new JComboBox<>(RecordType.values());

    JFrame viewRecordFrame = new JFrame();
    viewRecordFrame.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    JFrame modifyRecordFrame = new JFrame();
    modifyRecordFrame.setLayout(new GridBagLayout());

    //Create labels, text fields and buttons with loop.
    for (int i = 0; i < fieldNames.length; i++){
      fieldLabels[i] = new JLabel(fieldNames[i]);
      textFields[i] = new JTextField();
    }

    // ---------- Add action listeners (functionality) to buttons ----------
    //Main Menu Button
    mainMenuButton.addActionListener(e -> {
      mainWindow(viewRecordFrame.getLocationOnScreen());
      viewRecordFrame.setVisible(false);
    });

    //Save Button
    saveButton.addActionListener(e -> {
      try {
        if (viewType == ViewType.CREATE){
          LOGGER.info("Save - Create");
        } else if (viewType == ViewType.MODIFY){
          LOGGER.info("Save - Modify");
        } else {
          throw new SwingViewException("Invalid Save Button Type.");
        }
      } catch (SwingViewException error){
        JOptionPane.showMessageDialog(null, "Invalid Save Button Type. \n" + error,
                "GUI Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    //Delete Button
    deleteButton.addActionListener(e -> LOGGER.info("Delete button pressed"));

    //Modify Button
    modifyButton.addActionListener(e -> {
      viewRecordWindow(viewRecordFrame.getLocationOnScreen(), ViewType.MODIFY, currentRecord);
      viewRecordFrame.setVisible(false);
    });

    // ---------- Add labels, text fields, and buttons to frame ----------
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


    // ---------- Set editable fields and add buttons based on the ViewType ----------
    try {
      switch (viewType) {
        case CREATE: //When creating record, all fields but date are editable
          constraints.insets = new Insets(10, 20, 10, 5);
          constraints.gridx = 1;
          viewRecordFrame.add(saveButton, constraints);
          constraints.insets = new Insets(10, 5, 10, 50);
          constraints.gridx = 2;
          viewRecordFrame.add(deleteButton, constraints);
          for (int i = 0; i < fieldNames.length; i++) {
            textFields[i].setEditable(true);
            textFields[i].setBackground(Color.white);
            recordTypeComboBox.setEnabled(true);
          }
          textFields[4].setEditable(false); //Date text field.
          textFields[4].setBackground(Color.LIGHT_GRAY);
          viewRecordFrame.setTitle("SMART Tool - Create Record");
          break;
        case MODIFY: //When modifying, all fields but date are editable, and have text within
          constraints.insets = new Insets(10, 20, 10, 5);
          constraints.gridx = 1;
          viewRecordFrame.add(saveButton, constraints);
          constraints.insets = new Insets(10, 5, 10, 50);
          constraints.gridx = 2;
          viewRecordFrame.add(deleteButton, constraints);
          for (int i = 0; i < fieldNames.length; i++) {
            textFields[i].setEditable(true);
            textFields[i].setBackground(Color.white);
            recordTypeComboBox.setEnabled(true);
          }
          textFields[4].setEditable(false); //Date text field.
          textFields[4].setBackground(Color.LIGHT_GRAY);
          viewRecordFrame.setTitle("SMART Tool - Modify Record");

          //Display contents of record to editable fields
          textFields[0].setText(currentRecord.getReferenceNumber());
          textFields[1].setText(currentRecord.getTitle());
          recordTypeComboBox.setSelectedItem(currentRecord.getDocumentType());
          textFields[3].setText(currentRecord.getAuthorLastName() + ", " + currentRecord.getAuthorFirstName());
          textFields[4].setText(currentRecord.getDate().toString());
          textFields[5].setText(currentRecord.getCategory());
          textFields[6].setText(currentRecord.getSummary());
          break;
        case VIEW:  //When viewing, no fields are editable
          constraints.insets = new Insets(10, 20, 10, 5);
          constraints.gridx = 1;
          viewRecordFrame.add(modifyButton, constraints);
          constraints.insets = new Insets(10, 5, 10, 50);
          constraints.gridx = 2;
          viewRecordFrame.add(deleteButton, constraints);
          for (int i = 0; i < fieldNames.length; i++) {
            textFields[i].setEditable(false);
            textFields[i].setBackground(Color.LIGHT_GRAY);
          }
          recordTypeComboBox.setEnabled(false);

          //Show contents of record.
          textFields[0].setText(currentRecord.getReferenceNumber());
          textFields[1].setText(currentRecord.getTitle());
          recordTypeComboBox.setSelectedItem(currentRecord.getDocumentType());
          textFields[3].setText(currentRecord.getAuthorLastName() + ", " + currentRecord.getAuthorFirstName());
          textFields[4].setText(currentRecord.getDate().toString());
          textFields[5].setText(currentRecord.getCategory());
          textFields[6].setText(currentRecord.getSummary());
          viewRecordFrame.setTitle("SMART Tool - View Record");
          break;
        default:
          throw new SwingViewException("Invalid RecordType for viewRecordWindow()");
      }
    } catch (SwingViewException error){
      JOptionPane.showMessageDialog(null, "Invalid RecordType. \n" + error,
              "GUI Error", JOptionPane.ERROR_MESSAGE);
    }


    // ---------- Setup and show viewRecordFrame ----------
    viewRecordFrame.setSize(STANDARD_WINDOW_SIZE);
    viewRecordFrame.setMaximumSize(STANDARD_WINDOW_SIZE);
    viewRecordFrame.setMinimumSize(STANDARD_WINDOW_SIZE);
    viewRecordFrame.setLocation(location);
    viewRecordFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    viewRecordFrame.setVisible(true);
  }//End viewRecordWindow() method

  /**
   * resultsWindow(Point location, String searchTerm, Record[] records): This method is the GUI for
   * displaying the results of a search. The results are stored in the records array, and are
   * formatted for output within the GUI. Select buttons allow users to select the records to
   * see them in more detail using the viewRecordWindow() method.
   * @param location - Location of the window on the screen.
   * @param searchTerm - The term used to search resulting in this window, used for telling user the term entered.
   * @param records - The array of Record objects. These are the actual search results, used to create results in GUI.
   */
  private void resultsWindow(Point location, String searchTerm, Record[] records){
    JTextField searchField = new JTextField();
    JButton searchButton = new JButton(SEARCH);
    JButton advancedSearchButton = new JButton("Advanced Search");
    JButton[] selectButtons = new JButton[records.length]; //Select buttons for all the displayed records
    JLabel searchLabel = new JLabel("General Search: ");

    JFrame resultsFrame = new JFrame();
    resultsFrame.setLayout(new BorderLayout());
    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    JPanel resultsPanel = new JPanel();
    resultsPanel.setLayout(new BorderLayout());
    JScrollPane resultScrollPane = new JScrollPane(resultsPanel);

    //Initialize the Select buttons for each record.
    for (int i = 0; i < selectButtons.length; i++){
      selectButtons[i] = new JButton("Select");
    }

    // ---------- Add action listeners (functionality) to buttons ----------
    //Advanced Search Button
    advancedSearchButton.addActionListener(e -> {
      advancedSearchWindow(resultsFrame.getLocationOnScreen());
      resultsFrame.setVisible(false);
    });

    //Main Search Button
    searchButton.addActionListener(e -> LOGGER.info("Search button pressed: " + searchField.getText()));

    //Select Buttons for each Record
    for (int i = 0; i < selectButtons.length; i++){
      int finalI = i;
      selectButtons[i].addActionListener(e -> {
        viewRecordWindow(resultsFrame.getLocationOnScreen(), ViewType.VIEW, records[finalI]);
        resultsFrame.setVisible(false);
      });
    }

    // ---------- Add text field, label and buttons to search panel, and panel to frame ----------
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.LINE_START;
    constraints.insets = new Insets(10, 10, 10, 10);
    constraints.gridx = 0;
    searchPanel.add(searchLabel, constraints);
    constraints.insets = new Insets(10, 0, 10, 0);
    constraints.weightx = 0.15;
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 3;
    searchPanel.add(searchField, constraints);
    constraints.weightx = 0;
    constraints.gridx = 4;
    constraints.gridwidth = 1;
    searchPanel.add(searchButton, constraints);
    constraints.anchor = GridBagConstraints.LINE_END;
    constraints.gridx = 5;
    advancedSearchButton.setBorderPainted(false);
    advancedSearchButton.setForeground(Color.BLUE);
    searchPanel.add(advancedSearchButton, constraints);
    resultsFrame.add(searchPanel, BorderLayout.NORTH);

    // ---------- Setup elements for resultsPanel ----------
    resultsPanel.setLayout(new GridLayout(records.length, 1));
    resultsPanel.setBorder(BorderFactory.createTitledBorder("Results for: " + searchTerm));

    //Add results to resultsPanel
    if (records.length == 0){
      JLabel noResultLabel = new JLabel("There are no results for: \"" + searchTerm + "\"", SwingConstants.CENTER);
      resultsPanel.add(noResultLabel);
    } else {
      for (int i = 0; i < records.length; i++){
        JPanel recordPanel = new JPanel();
        recordPanel.setLayout(new GridLayout(1, 4));
        recordPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        selectButtons[i].setBorderPainted(false);
        selectButtons[i].setForeground(Color.gray);
        recordPanel.add(selectButtons[i]);
        recordPanel.add(new JLabel(records[i].getReferenceNumber()));
        recordPanel.add(new JLabel(records[i].getTitle()));
        recordPanel.add(new JLabel(records[i].getDate().toString()));
        resultsPanel.add(recordPanel);
      }
    }//End if-else
    resultsFrame.add(resultScrollPane, BorderLayout.CENTER);

    // ---------- Setup and show resultsFrame ----------
    resultsFrame.setSize(STANDARD_WINDOW_SIZE);
    resultsFrame.setMaximumSize(STANDARD_WINDOW_SIZE);
    resultsFrame.setMinimumSize(STANDARD_WINDOW_SIZE);
    resultsFrame.setLocation(location);
    resultsFrame.setTitle("SMART Tool - Search Results");
    resultsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    resultsFrame.setVisible(true);
  }//End resultsWindow() method

  /**
   * Method to create a test array of Record objects. ONLY for testing, can be deleted once the
   * DataAccessor methods are properly implemented.
   * @return records - Returns array of Record Objects
   */
  private Record[] makeTestRecords(int numberOfRecords){
    Record[] testRecords = new Record[numberOfRecords];

    for (int i = 0; i < numberOfRecords; i++){
      testRecords[i] = new Record("R-22-" + i, "Title " + i, "MEMO",
              "Longo", "Josh", "2022-04-16",
              "Finance", "Summary " + i, "Location");
    }
    return testRecords;
  }//End of makeTestRecords() method

  public void start() {
    mainWindow(new Point());
  }//End start() method
}//End SwingView class
