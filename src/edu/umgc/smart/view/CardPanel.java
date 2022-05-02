package edu.umgc.smart.view;

import javax.swing.JPanel;

/**
 * Abstract Class for All Card Panels.
 *
 * This class ensures that all card panels implement an overridden getName()
 * function. It also allows the CardView to not care about what specific panel
 * is being displayed.
 */
abstract class CardPanel extends JPanel {
  @Override
  public abstract String getName();
}
