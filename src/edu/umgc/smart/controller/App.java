package edu.umgc.smart.controller;

import edu.umgc.smart.model.DataAccessor;
import edu.umgc.smart.view.View;

public class App {
  private View view;

  public void setView(View view) {
    this.view = view;
    view.setDataAccessor(dataAccessor);
  }

  public void start() {
    this.view.start();
  }
}
