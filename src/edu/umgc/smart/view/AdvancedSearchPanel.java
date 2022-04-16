package edu.umgc.smart.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.umgc.smart.model.Record;

public class AdvancedSearchPanel extends JPanel {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private String[] fieldNames = Record.getHeaders().split(",");

  public AdvancedSearchPanel(CardView cardView) {
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
    mainSearchButton.addActionListener(e -> cardView.setPanel(CardView.SEARCH_PANEL));

    // ---------- Add labels, search fields and search buttons to frame ----------
    constraints.weighty = 0.15;
    constraints.insets = new Insets(10, 10, 10, 10);
    for (int i = 0; i < fieldNames.length; i++) {
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
    this.add(searchPanel, BorderLayout.CENTER);

    JPanel navPanel = new JPanel();
    navPanel.setLayout(new BorderLayout());
    mainSearchButton.setBorderPainted(false);
    mainSearchButton.setForeground(Color.BLUE);
    navPanel.add(mainSearchButton, BorderLayout.LINE_START);
    this.add(navPanel, BorderLayout.PAGE_END);
  }

}
