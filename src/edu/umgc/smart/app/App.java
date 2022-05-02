package edu.umgc.smart.app;

import edu.umgc.smart.data.CsvDataAccessor;
import edu.umgc.smart.data.DataAccessor;
import edu.umgc.smart.view.CardView;
import edu.umgc.smart.view.View;

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
