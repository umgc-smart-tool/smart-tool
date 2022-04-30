package edu.umgc.smart.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Insets;

import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import edu.umgc.smart.model.Record;

public class ResultsCardPanel extends CardPanel{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Record[] records;
    private static String searchTerm;
    private static String searchTermField;

    public ResultsCardPanel(CardView cardView){
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton advancedSearchButton = new JButton("Advanced Search");
        JButton[] selectButtons = new JButton[records.length]; // Select buttons for all the displayed records
        JLabel searchLabel = new JLabel("General Search: ");

        this.setLayout(new BorderLayout());
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BorderLayout());
        JScrollPane resultScrollPane = new JScrollPane(resultsPanel);

        // Initialize the Select buttons for each record.
        for (int i = 0; i < selectButtons.length; i++) {
            selectButtons[i] = new JButton("Select");
        }

        // ---------- Add action listeners (functionality) to buttons ----------
        // Advanced Search Button
        advancedSearchButton.addActionListener(e -> cardView.setPanel(new AdvancedSearchCardPanel(cardView)));

        // Main Search Button
        searchButton.addActionListener(e -> {
            LOGGER.info("Search button pressed: " + searchField.getText());
            if (searchField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a valid search term.",
                        "Invalid Search", JOptionPane.ERROR_MESSAGE);
            } else {
                Record[] recordsResult = cardView.dataAccessor.getRecordsByMainSearch(searchField.getText());
                cardView.setPanel(new ResultsCardPanel(cardView, recordsResult, searchField.getText(),
                        "All Fields"));
            }
        });

        // Select Buttons for each Record
        for (int i = 0; i < selectButtons.length; i++) {
            final int finalI = i;
            selectButtons[i].addActionListener(e -> cardView.setPanel(new ViewRecordCardPanel(cardView, records[finalI])));
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
        this.add(searchPanel, BorderLayout.NORTH);

        // ---------- Setup elements for resultsPanel ----------
        resultsPanel.setLayout(new GridLayout(records.length, 1));
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results for: \"" + searchTerm + "\" in field: " +
                searchTermField));

        // Add results to resultsPanel
        if (records.length == 0) {
            JLabel noResultLabel =
                    new JLabel("There are no results for: \"" + searchTerm + "\" in the field: " +
                            searchTermField, SwingConstants.CENTER);
            resultsPanel.add(noResultLabel);
        } else {
            for (int i = 0; i < records.length; i++) {
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
            }// End for loop
        } // End if-else
        this.add(resultScrollPane, BorderLayout.CENTER);
    }// End ResultsCardPanel(CardView cardView) constructor

    public ResultsCardPanel(CardView cardView, Record[] recordsArray, String searchTermItem, String searchFieldItem){
        ResultsCardPanel.setSearchTerm(searchTermItem);
        ResultsCardPanel.setRecordsArray(recordsArray);
        ResultsCardPanel.setSearchFieldItem(searchFieldItem);
        this.setLayout(new BorderLayout());
        this.add(new ResultsCardPanel(cardView), BorderLayout.CENTER);
    }// End ResultsCardPanel(CardView cardView, Record[] recordsArray, String searchTermItem) constructor

    @Override
    public String getName() {
        return "Search Results";
    }

    private static void setSearchTerm(String searchTerm) {
        ResultsCardPanel.searchTerm = searchTerm;
    }

    private static void setRecordsArray(Record[] records) {
        ResultsCardPanel.records = records.clone();
    }

    private static void setSearchFieldItem(String searchFieldItem) {
        ResultsCardPanel.searchTermField = searchFieldItem;
    }
}
