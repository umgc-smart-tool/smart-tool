package edu.umgc.smart.app;

import edu.umgc.smart.data.DataAccessor;
import edu.umgc.smart.data.MemoryDataAccessor;
import edu.umgc.smart.view.*;

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
    View view = initializeUI(args);
    DataAccessor da = initializeDataAccessor();
    App smartApp = new App(view, da);
    smartApp.start();
  }

  private static View initializeUI(String... args) {
    if (args.length > 0 && args[0].equalsIgnoreCase("console")) {
      return new ConsoleView();
    } else {
      return new CardView();
    }
  }

  private static DataAccessor initializeDataAccessor() {
    return new MemoryDataAccessor();
  }

}
