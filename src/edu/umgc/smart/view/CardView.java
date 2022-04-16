package edu.umgc.smart.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class CardView extends View {

  static final String SEARCH_PANEL = "Search";
  static final String ADVANCED_SEARCH_PANEL = "Advanced Search";
  static final String VIEW_RECORD_PANEL = "View Record";
  static final String CREATE_RECORD_PANEL = "Create Record";

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private JFrame frame = new JFrame();
  private CardLayout cardLayout = new CardLayout();

  public CardView() {
    Container mainPane = frame.getContentPane();
    mainPane.setLayout(cardLayout);
    mainPane.add(SEARCH_PANEL, new SearchPanel(this));
    mainPane.add(ADVANCED_SEARCH_PANEL, new AdvancedSearchPanel(this));
    mainPane.add(VIEW_RECORD_PANEL, new ViewRecordPanel(this, ViewType.VIEW));
    mainPane.add(CREATE_RECORD_PANEL, new ViewRecordPanel(this, ViewType.CREATE));

    frame.setSize(new Dimension(750, 500));
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent windowEvent) {
        LOGGER.log(Level.INFO, "Exiting Program");
      }
    });
    frame.setTitle(String.format("SMART Tool - %s", SEARCH_PANEL));
    frame.setVisible(true);
  }

  public void setPanel(String panelName) {
    frame.setTitle(String.format("SMART Tool - %s", panelName));
    cardLayout.show(frame.getContentPane(), panelName);
  }

  public void setTitle(String title) {
    frame.setTitle(title);
  }

  public void start() {
    SwingUtilities.invokeLater(() -> {
      // Do something
    });
  }
}
