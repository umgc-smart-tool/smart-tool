package edu.umgc.smart.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import edu.umgc.smart.model.Record;

public class SearchCardPanel extends CardPanel {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public SearchCardPanel(CardView cardView) {
    JLabel searchBoxLabel = new JLabel("Search for Company Records", SwingConstants.CENTER);
    JLabel emptyLabel = new JLabel("  ");
    JTextField searchBoxField = new JTextField();
    JButton searchButton = new JButton("Search");
    JButton advancedSearchButton = new JButton("Advanced Search");
    JButton createRecordButton = new JButton("Create Record");

    this.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    // ---------- Add action listeners (functionality) to buttons ----------

    searchButton.addActionListener(
        e -> {
          LOGGER.log(Level.INFO, String.format("Search Button Pressed. Searching for: %s", searchBoxField.getText()));
          if (searchBoxField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a valid search term.",
                    "Invalid Search", JOptionPane.ERROR_MESSAGE);
          } else {
            Record[] records = cardView.dataAccessor.getRecordsByMainSearch(searchBoxField.getText());
            cardView.setPanel(new ResultsCardPanel(cardView, records, searchBoxField.getText(),
                    "All Fields"));
          }
        });
    createRecordButton.addActionListener(e -> cardView.setPanel(new CreateRecordPanel(cardView, "create")));
    advancedSearchButton.addActionListener(e -> cardView.setPanel(new AdvancedSearchCardPanel(cardView)));

    // ---------- Add labels, text field and buttons to frame ----------

    constraints.anchor = GridBagConstraints.LAST_LINE_END; // Anchor to bottom right corner
    constraints.weighty = 0.5;
    constraints.gridwidth = 1;
    constraints.ipadx = 50;
    constraints.gridx = 4;
    constraints.gridy = 4;
    this.add(emptyLabel, constraints);

    createRecordButton.setBorderPainted(false);
    createRecordButton.setForeground(Color.BLUE);
    constraints.anchor = GridBagConstraints.FIRST_LINE_START; // Anchor at top left corner
    constraints.weighty = 0.5;
    constraints.ipadx = 0;
    constraints.gridwidth = 1;
    constraints.gridx = 0;
    constraints.gridy = 0;
    this.add(createRecordButton, constraints);

    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weighty = 0;
    constraints.weightx = 0.5;
    constraints.gridwidth = 2;
    constraints.gridx = 1;
    constraints.gridy = 2;
    this.add(searchBoxField, constraints);

    constraints.weightx = 0;
    constraints.gridwidth = 2;
    constraints.gridx = 1;
    constraints.gridy = 1;
    this.add(searchBoxLabel, constraints);

    constraints.weightx = 0.0;
    constraints.gridwidth = 1;
    constraints.gridx = 3;
    constraints.gridy = 2;
    this.add(searchButton, constraints);

    advancedSearchButton.setBorderPainted(false);
    advancedSearchButton.setForeground(Color.BLUE);
    constraints.gridwidth = 1;
    constraints.gridx = 2;
    constraints.gridy = 3;
    this.add(advancedSearchButton, constraints);
  }

  public String getName() {
    return "Search";
  }

}
