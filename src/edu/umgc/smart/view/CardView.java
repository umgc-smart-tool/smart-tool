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

/**
 * Javax Swing GUI Implementation.
 *
 * This version of a GUI uses the CardLayout in order to swap panels in and out
 * without needing to recreate the main frame/window. While not the fastest, as
 * panels are created each time they are needed, the delay is not noticable.
 *
 * Since the frame is made visible when the start() method is called, it runs in
 * its own event thread.
 *
 * When the window is closed, the application also closes, but not before invoking
 * the save() method of its DataAccessor.
 */
public class CardView extends View {
  private static final long serialVersionUID = 479582524176873805L;
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private JFrame frame = new JFrame();

  private Container mainPane;

  private CardPanel currentPanel;

  public CardView() {
    mainPane = frame.getContentPane();
    mainPane.setLayout(new CardLayout());
    currentPanel = new SearchCardPanel(this);
    mainPane.add(currentPanel);

    frame.setSize(new Dimension(750, 500));
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent windowEvent) {
        dataAccessor.save();
        LOGGER.log(Level.INFO, "Exiting Program");
      }
    });
    frame.setTitle(String.format("SMART Tool - %s", "SEARCH"));
  }

  public void setPanel(CardPanel panel) {
    mainPane.add(panel);
    mainPane.remove(currentPanel);
    currentPanel = panel;
    frame.setTitle(String.format("SMART Tool - %s", panel.getName()));
  }

  public void setTitle(String title) {
    frame.setTitle(title);
  }

  public void start() {
    SwingUtilities.invokeLater(() -> frame.setVisible(true));
  }
}
