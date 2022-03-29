package edu.umgc.smart;

import edu.umgc.smart.controller.App;
import edu.umgc.smart.model.DataAccessor;
import edu.umgc.smart.model.MemoryDataAccessor;
import edu.umgc.smart.view.*;

/**
 * The entry point for the program.
 * This class should only be used to instantiate necessary portions of the
 * program, such as a view and storage.
 */
public class Main {
  private static View view;
  private static DataAccessor dataAccessor;

  public static void main(String... args) {
    initializeUI(args);
    initializeDataAccessor();
    startProgram();
  }

  private static void initializeUI(String... args) {
    if (args.length > 0 && args[0].equalsIgnoreCase("console")) {
      view = new ConsoleView();
    } else {
      view = new SwingView();
    }
  }

  private static void initializeDataAccessor() {
    dataAccessor = new MemoryDataAccessor();
  }

  private static void startProgram() {
    App smartApp = new App(view, dataAccessor);
    smartApp.start();
  }
}
