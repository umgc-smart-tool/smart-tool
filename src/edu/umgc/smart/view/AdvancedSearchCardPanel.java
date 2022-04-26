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

import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public class AdvancedSearchCardPanel extends CardPanel {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private String[] fieldNames = Record.getHeaders().split(",");

  public AdvancedSearchCardPanel(CardView cardView) {
    JButton mainSearchButton = new JButton("Main Menu Search");

    JLabel[] labels = new JLabel[fieldNames.length];
    JTextField[] searchFields = new JTextField[fieldNames.length];
    JButton[] searchButtons = new JButton[fieldNames.length];

    this.setLayout(new BorderLayout());
    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // Create labels, search fields and search buttons with a loop
    for (int i = 0; i < fieldNames.length; i++) {
      labels[i] = new JLabel(fieldNames[i]);
      searchFields[i] = new JTextField();
      searchButtons[i] = new JButton("Search");
    }

    // ---------- Add action listeners (functionality) to buttons ----------

    // Reference Number field search button
    searchButtons[0].addActionListener(e ->{
          LOGGER.log(Level.INFO,
                  String.format("Searching by reference number: %s", searchFields[0].getText()));
          if (searchFields[0].getText().isEmpty()){
            showSearchError(fieldNames[0]);
          } else {
            Record[] records = cardView.dataAccessor.getRecordsByReferenceNum(searchFields[0].getText());
            cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[0].getText(), fieldNames[0]));
          }
        });

    // Title field search button
    searchButtons[1].addActionListener(e -> {
      LOGGER.log(Level.INFO,
              String.format("Searching by title: %s", searchFields[1].getText()));
      if (searchFields[1].getText().isEmpty())
        showSearchError(fieldNames[1]);
      else {
        Record[] records = cardView.dataAccessor.getRecordsByTitle(searchFields[1].getText());
        cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[1].getText(), fieldNames[1]));
      }
    });

    // Record Type field search button
    searchButtons[2]
        .addActionListener(e -> {
          LOGGER.log(Level.INFO,
                  String.format("Searching by record type: %s", searchFields[2].getText()));
          if (searchFields[2].getText().isEmpty())
            showSearchError(fieldNames[2]);
          else {
            try{
              RecordType type = RecordType.valueOf(searchFields[2].getText().toUpperCase(Locale.ROOT));
              Record[] records = cardView.dataAccessor.getRecordsByRecordType(type);
              cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[2].getText(), fieldNames[2]));
            } catch (IllegalArgumentException error){
              JOptionPane.showMessageDialog(null,
                      "Please search for a valid Record Type: \n" +
                              Arrays.toString(RecordType.values()),
                      "Invalid Record Type Search", JOptionPane.ERROR_MESSAGE);
            }// End try-catch
          }
        });

    // Author last name field search button
    searchButtons[3].addActionListener(e -> {
      LOGGER.log(Level.INFO,
              String.format("Searching by author last name: %s", searchFields[3].getText()));
      if (searchFields[3].getText().isEmpty())
        showSearchError(fieldNames[3]);
      else {
        Record[] records = cardView.dataAccessor.getRecordsByAuthorLastName(searchFields[3].getText());
        cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[3].getText(), fieldNames[3]));
      }
    });

    // Author first name field search button
    searchButtons[4].addActionListener(e -> {
      LOGGER.log(Level.INFO,
              String.format("Searching by author first name: %s", searchFields[4].getText()));
      if (searchFields[4].getText().isEmpty())
        showSearchError(fieldNames[4]);
      else {
        Record[] records = cardView.dataAccessor.getRecordsByAuthorFirstName(searchFields[4].getText());
        cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[4].getText(), fieldNames[4]));
      }
    });

    // Date field search button
    searchButtons[5].addActionListener(e -> {
      LOGGER.log(Level.INFO,
              String.format("Searching by date: %s", searchFields[5].getText()));
      if (searchFields[5].getText().isEmpty())
        showSearchError(fieldNames[5]);
      else {
        // TODO: Get Date search properly working - remove use of Date object altogether?
        Record[] records = cardView.dataAccessor.getRecordsByDate(Date.valueOf(searchFields[5].getText()));
        cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[5].getText(), fieldNames[5]));
      }
    });

    // Category field search button
    searchButtons[6].addActionListener(e -> {
      LOGGER.log(Level.INFO,
              String.format("Searching by category: %s", searchFields[6].getText()));
      if (searchFields[6].getText().isEmpty())
        showSearchError(fieldNames[6]);
      else {
        Record[] records = cardView.dataAccessor.getRecordsByCategory(searchFields[6].getText());
        cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[6].getText(), fieldNames[6]));
      }
    });

    // Summary field search button
    searchButtons[7].addActionListener(e -> {
      LOGGER.log(Level.INFO,
              String.format("Searching by summary: %s", searchFields[7].getText()));
      if (searchFields[7].getText().isEmpty())
        showSearchError(fieldNames[7]);
      else {
        Record[] records = cardView.dataAccessor.getRecordsBySummary(searchFields[7].getText());
        cardView.setPanel(new ResultsCardPanel(cardView, records, searchFields[7].getText(), fieldNames[7]));
      }
    });

    // mainSearchButton - Return to simple search / main window.
    mainSearchButton.addActionListener(e -> cardView.setPanel(new SearchCardPanel(cardView)));

    // ---------- Add labels, search fields and search buttons to frame ----------
    constraints.weighty = 0.15;
    constraints.insets = new Insets(10, 10, 10, 10);
    for (int i = 0; i < fieldNames.length; i++) {
      if (fieldNames[i].equals("Location")){
        // Skip adding "Location" to advanced search panel - not to be added here
      } else {
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
    this.add(searchPanel, BorderLayout.CENTER);

    JPanel navPanel = new JPanel();
    navPanel.setLayout(new BorderLayout());
    mainSearchButton.setBorderPainted(false);
    mainSearchButton.setForeground(Color.BLUE);
    navPanel.add(mainSearchButton, BorderLayout.LINE_START);
    this.add(navPanel, BorderLayout.PAGE_END);
  }

  private void showSearchError(String fieldName){
    JOptionPane.showMessageDialog(null,
            "Please enter a valid search term for " + fieldName + ".",
            "Invalid Search", JOptionPane.ERROR_MESSAGE);
  }

  public String getName() {
    return "Advanced Search";
  }

}
