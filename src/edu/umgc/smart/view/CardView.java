package edu.umgc.smart.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class CardView extends View {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private JFrame frame = new JFrame();

  private Container mainPane;

  private JPanel currentPanel;

  public CardView() {
    mainPane = frame.getContentPane();
    mainPane.add(new SearchPanel(this));

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
    frame.setTitle(String.format("SMART Tool - %s", "SEARCH"));
    frame.setVisible(true);
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
    SwingUtilities.invokeLater(() -> {
      // Do something
    });
  }
}
