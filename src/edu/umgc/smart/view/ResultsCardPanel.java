package edu.umgc.smart.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import edu.umgc.smart.model.MainSearch;
import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.Search;

/**
 * Results Card Panel
 *
 * Displays results of a search, if any. In the event that no results are
 * returned, the user is notified as such.  Further searches can be run from
 * this panel, as well.
 *
 * Each returned search result has an associated button to view the record details.
 */
class ResultsCardPanel extends CardPanel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Search search;

    private final JLabel searchLabel = new JLabel("General Search: ");
    private final JTextField searchField = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JButton advancedSearchButton = new JButton("Advanced Search");
    private JButton[] selectButtons;
    private Record[] records;

    public ResultsCardPanel(CardView cardView) {
        initializeResultsCardPanel(cardView);
    }

    public ResultsCardPanel(CardView cardView, Search search) {
        setSearch(search);
        initializeResultsCardPanel(cardView);
    }

    private static void setSearch(Search search) {
        ResultsCardPanel.search = search;
    }

    private void initializeResultsCardPanel(CardView cardView) {
        this.setLayout(new BorderLayout());
        initializeRecords();
        initializeSearchPanel(cardView);
        initializeResultsPanel();
    }

    private void initializeRecords() {
        if (null != search) {
            records = search.getResults();
        }
    }

    private void initializeSearchPanel(CardView cardView) {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        initializeSelectButtonsWithLoop();
        addActionListeners(cardView);
        addComponentsToSearchPanel(searchPanel);
        this.add(searchPanel, BorderLayout.NORTH);
    }

    private void initializeSelectButtonsWithLoop() {
        selectButtons = new JButton[records.length];
        for (int i = 0; i < selectButtons.length; i++) {
            selectButtons[i] = new JButton("Select");
        }
    }

    private void addActionListeners(CardView cardView) {
        ActionListener searchAction = e -> {
            LOGGER.info("Search button pressed: " + searchField.getText());
            if (searchField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid search term.",
                        "Invalid Search", JOptionPane.ERROR_MESSAGE);
            } else {
                cardView.setPanel(new ResultsCardPanel(cardView, new MainSearch(cardView.dataAccessor, searchField.getText())));
            }
        };

        searchButton.addActionListener(searchAction);
        searchField.addActionListener(searchAction);
        advancedSearchButton.addActionListener(e -> cardView.setPanel(new AdvancedSearchCardPanel(cardView)));
        addSelectButtonListenersWithLoop(cardView);
    }

    private void addSelectButtonListenersWithLoop(CardView cardView) {
        for (int i = 0; i < selectButtons.length; i++) {
            final int finalI = i;
            selectButtons[i]
                    .addActionListener(e -> cardView.setPanel(new ViewRecordCardPanel(cardView, records[finalI], true)));
        }
    }

    private void addComponentsToSearchPanel(JPanel searchPanel) {
        GridBagConstraints constraints = new GridBagConstraints();
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
    }

    private void initializeResultsPanel() {
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BorderLayout());
        JScrollPane resultScrollPane = new JScrollPane(resultsPanel);
        resultsPanel.setLayout(new GridLayout(records.length, 1));
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results for: \"" + search.getSearchTerm() + "\" in field: " +
                search.getSearchField()));
        addResultsToResultsPanel(resultsPanel);
        this.add(resultScrollPane, BorderLayout.CENTER);
    }

    private void addResultsToResultsPanel(JPanel resultsPanel) {
        if (records.length == 0) {
            JLabel noResultLabel = new JLabel("There are no results for: \"" + search.getSearchTerm() + "\" in the field: " +
                search.getSearchField());
            resultsPanel.add(noResultLabel);
        } else {
            addResultsWithLoop(resultsPanel);
        }
    }

    private void addResultsWithLoop(JPanel resultsPanel) {
        for (int i = 0; i < records.length; i++) {
            JPanel recordPanel = new JPanel();
            recordPanel.setLayout(new BorderLayout(20,0));
            JPanel centerPanel = new JPanel(); // Inner panel, used to vary size of columns
            centerPanel.setLayout(new BorderLayout(20,0));
            recordPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            selectButtons[i].setBorderPainted(false);
            selectButtons[i].setForeground(Color.gray);
            recordPanel.add(selectButtons[i], BorderLayout.WEST);
            centerPanel.add(new JLabel(records[i].getReferenceNumber()), BorderLayout.WEST);
            centerPanel.add(new JLabel(records[i].getTitle()), BorderLayout.CENTER);
            recordPanel.add(centerPanel, BorderLayout.CENTER);
            JLabel date = new JLabel(records[i].getDate());
            date.setBorder(new EmptyBorder(0,0,0,10));
            recordPanel.add(date, BorderLayout.EAST);
            resultsPanel.add(recordPanel);
        }
    }

    @Override
    public String getName() {
        return "Search Results";
    }
}
