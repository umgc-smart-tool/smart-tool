package edu.umgc.smart.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

/**
 * Advanced Search Card Panel.
 *
 * This class creates the panel used for performing searches based on a specific
 * field. Only searches against a single field can be performed at a time.
 */
class AdvancedSearchCardPanel extends CardPanel {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final String[] fieldNames = Record.getHeaders().split(",");

  private JButton mainSearchButton = new JButton("Main Menu Search");
  private JLabel[] labels = new JLabel[fieldNames.length];
  private JTextField[] searchFields = new JTextField[fieldNames.length];
  private JButton[] searchButtons = new JButton[fieldNames.length];
  private CardView cardView;

  public AdvancedSearchCardPanel(CardView cardView) {
    this.cardView = cardView;
    this.setLayout(new BorderLayout());
    addSearchPanel();
    addNavPanel();
  }

  private void addSearchPanel() {
    JPanel searchPanel;
    searchPanel = new JPanel();
    searchPanel.setLayout(new GridBagLayout());
    initializeComponentsWithALoop();
    addActionListenersToButtons();
    addComponentsToSearchPanel(searchPanel);
    this.add(searchPanel, BorderLayout.CENTER);
  }

  private void initializeComponentsWithALoop() {
    for (int i = 0; i < fieldNames.length; i++) {
      labels[i] = new JLabel(fieldNames[i]);
      searchFields[i] = new JTextField();
      searchButtons[i] = new JButton("Search");
    }
  }

  private void addActionListenersToButtons() {
    searchButtons[0].addActionListener(e -> searchByReferenceNumber(0));
    searchButtons[1].addActionListener(e -> searchByTitle(1));
    searchButtons[2].addActionListener(e -> searchByRecordType(2));
    searchButtons[3].addActionListener(e -> searchByAuthorLastName(3));
    searchButtons[4].addActionListener(e -> searchByAuthorFirstName(4));
    searchButtons[5].addActionListener(e -> searchByDate(5));
    searchButtons[6].addActionListener(e -> searchByCategory(6));
    searchButtons[7].addActionListener(e -> searchBySummary(7));
    mainSearchButton.addActionListener(e -> cardView.setPanel(new SearchCardPanel(cardView)));
  }

  private void searchByReferenceNumber(int index) {
    String logMessage = String.format("Searching by reference number: %s", searchFields[index].getText());
    LOGGER.log(Level.INFO, logMessage);
    if (searchFields[index].getText().isEmpty()) {
      showSearchError(fieldNames[index]);
    } else {
      Record[] records = cardView.dataAccessor.getRecordsByReferenceNum(searchFields[index].getText());
      cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[index].getText(), fieldNames[index]));
    }
  }

  private void searchByTitle(int index) {
    String logMessage = String.format("Searching by title: %s", searchFields[index].getText());
    LOGGER.log(Level.INFO, logMessage);
    if (searchFields[index].getText().isEmpty())
      showSearchError(fieldNames[index]);
    else {
      Record[] records = cardView.dataAccessor.getRecordsByTitle(searchFields[index].getText());
      cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[index].getText(), fieldNames[index]));
    }
  }

  private void searchByRecordType(int index) {
    String logMessage = String.format("Searching by record type: %s", searchFields[index].getText());
    LOGGER.log(Level.INFO, logMessage);
    if (searchFields[index].getText().isEmpty())
      showSearchError(fieldNames[index]);
    else {
      try {
        RecordType type = RecordType.valueOf(searchFields[index].getText().toUpperCase(Locale.ROOT));
        Record[] records = cardView.dataAccessor.getRecordsByRecordType(type);
        cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[2].getText(), fieldNames[index]));
      } catch (IllegalArgumentException error) {
        JOptionPane.showMessageDialog(null,
            "Please search for a valid Record Type: \n" +
                Arrays.toString(RecordType.values()),
            "Invalid Record Type Search", JOptionPane.ERROR_MESSAGE);
      } // End try-catch
    }
  }

  private void searchByAuthorLastName(int index) {
    String logMessage = String.format("Searching by author last name: %s", searchFields[index].getText());
    LOGGER.log(Level.INFO, logMessage);
    if (searchFields[index].getText().isEmpty())
      showSearchError(fieldNames[index]);
    else {
      Record[] records = cardView.dataAccessor.getRecordsByAuthorLastName(searchFields[index].getText());
      cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[index].getText(), fieldNames[index]));
    }
  }

  private void searchByAuthorFirstName(int index) {
    String logMessage = String.format("Searching by author first name: %s", searchFields[index].getText());
    LOGGER.log(Level.INFO, logMessage);
    if (searchFields[index].getText().isEmpty())
      showSearchError(fieldNames[index]);
    else {
      Record[] records = cardView.dataAccessor.getRecordsByAuthorFirstName(searchFields[index].getText());
      cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[index].getText(), fieldNames[index]));
    }
  }

  private void searchByDate(int index) {
    String logMessage = String.format("Searching by date: %s", searchFields[index].getText());
    LOGGER.log(Level.INFO, logMessage);
    if (searchFields[index].getText().isEmpty())
      showSearchError(fieldNames[index]);
    else {
      Record[] records = cardView.dataAccessor.getRecordsByDate(searchFields[index].getText());
      cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[index].getText(), fieldNames[index]));
    }
  }

  private void searchByCategory(int index) {
    String logMessage = String.format("Searching by category: %s", searchFields[index].getText());
    LOGGER.log(Level.INFO, logMessage);
    if (searchFields[index].getText().isEmpty())
      showSearchError(fieldNames[index]);
    else {
      Record[] records = cardView.dataAccessor.getRecordsByCategory(searchFields[index].getText());
      cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[index].getText(), fieldNames[index]));
    }
  }

  private void searchBySummary(int index) {
    String logMessage = String.format("Searching by summary: %s", searchFields[index].getText());
    LOGGER.log(Level.INFO, logMessage);
    if (searchFields[7].getText().isEmpty())
      showSearchError(fieldNames[index]);
    else {
      Record[] records = cardView.dataAccessor.getRecordsBySummary(searchFields[7].getText());
      cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[7].getText(), fieldNames[7]));
    }
  }

  private void addComponentsToSearchPanel(JPanel searchPanel) {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weighty = 0.15;
    constraints.insets = new Insets(10, 10, 10, 10);
    for (int i = 0; i < fieldNames.length; i++) {
      if (!fieldNames[i].equals("Location")) {
        // Skip adding "Location" to advanced search panel - not to be added here
        constraints.gridy = i;
        constraints.gridx = 0;
        constraints.ipadx = 25;
        searchPanel.add(labels[i], constraints);
        constraints.gridwidth = 2;
        constraints.ipadx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.2;
        constraints.gridx = 1;
        searchPanel.add(searchFields[i], constraints);
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.gridx = 3;
        searchPanel.add(searchButtons[i], constraints);
      }
    }
  }

  private void addNavPanel() {
    JPanel navPanel = new JPanel();
    navPanel.setLayout(new BorderLayout());
    mainSearchButton.setBorderPainted(false);
    mainSearchButton.setForeground(Color.BLUE);
    navPanel.add(mainSearchButton, BorderLayout.LINE_START);
    this.add(navPanel, BorderLayout.PAGE_END);
  }

  private void showSearchError(String fieldName) {
    JOptionPane.showMessageDialog(null,
        "Please enter a valid search term for " + fieldName + ".",
        "Invalid Search", JOptionPane.ERROR_MESSAGE);
  }

  public String getName() {
    return "Advanced Search";
  }

}
