package edu.umgc.smart.controller;

import edu.umgc.smart.model.DataAccessor;
import edu.umgc.smart.view.View;

public class App {
  private View view;
  private DataAccessor dataAccessor;

  public App(View view, DataAccessor dataAccessor) {
    this.view = view;
    this.dataAccessor = dataAccessor;
  }

  public void start() {
    this.view.start();
  }
}
