package edu.umgc.smart.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.logging.Logger;

import javax.swing.*;

import edu.umgc.smart.model.Record;

public class AdvancedSearchCardPanel extends CardPanel {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private static final String[] fieldNames = Record.getHeaders().split(",");

  private JButton mainSearchButton = new JButton("Main Menu Search");
  private JLabel[] labels = new JLabel[fieldNames.length];
  private JTextField[] searchFields = new JTextField[fieldNames.length];
  private JButton[] searchButtons = new JButton[fieldNames.length];
  private transient ActionListener searchButtonListener;

  public AdvancedSearchCardPanel(CardView cardView) {
    searchButtonListener = new SearchButtonListener(this);
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

    for (int i = 0; i < searchButtons.length; i++) {
      searchButtons[i].addActionListener(searchButtonListener);
    }

    // mainSearchButton - Return to simple search / main window.
    mainSearchButton.addActionListener(e -> cardView.setPanel(new SearchCardPanel(cardView)));

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

  public String getName() {
    return "Advanced Search";
  }

  private class SearchButtonListener implements ActionListener {
    private JComponent parent;

    SearchButtonListener(JComponent parent) {
      this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      int i = getButtonIndex((JButton) e.getSource());
      String message = String.format("Searching by %s: %s", labels[i].getText(), searchFields[i].getText());
      LOGGER.info(message);
      String optionMessage = "This feature has not yet been implemented.\n"
          + "It will be available in the final version.\n"
          + message;
          JOptionPane.showMessageDialog(parent, optionMessage,
              "Feature Not Implemented", JOptionPane.WARNING_MESSAGE);
    }

    private int getButtonIndex(JButton button) {
      for (int i = 0; i < searchButtons.length; i++) {
        if (searchButtons[i].equals(button)) {
          return i;
        }
      }
      return 0;
    }
  }



}
