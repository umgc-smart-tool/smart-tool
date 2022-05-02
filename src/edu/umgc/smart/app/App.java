package edu.umgc.smart.app;

import edu.umgc.smart.data.CsvDataAccessor;
import edu.umgc.smart.data.DataAccessor;
import edu.umgc.smart.view.CardView;
import edu.umgc.smart.view.View;

/**
 * The application runner.
 *
 * This class instantiates the View and DataAccessor objects to be used in the application.
 * The instantiated objects are then used to instantiate the actual application.
 * Finally, the application is started along with the appropriate view.
 *
 */
public class App {
  private View view;

  public App(View view, DataAccessor dataAccessor) {
    this.view = view;
    view.setDataAccessor(dataAccessor);
  }

  public void start() {
    this.view.start();
  }

  public static void main(String... args) {
    View view = initializeUI();
    DataAccessor da = initializeDataAccessor();
    App smartApp = new App(view, da);
    smartApp.start();
  }

  private static View initializeUI() {
    return new CardView();
  }

  private static DataAccessor initializeDataAccessor() {
    return new CsvDataAccessor();
  }

}
