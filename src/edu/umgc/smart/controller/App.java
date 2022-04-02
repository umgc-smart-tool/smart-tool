package edu.umgc.smart.controller;

import edu.umgc.smart.model.DataAccessor;
import edu.umgc.smart.view.View;

public class App {
  private View view;
  private DataAccessor dataAccessor;

  public void setView(View view) {
    this.view = view;
  }

  public void setDataAccessor(DataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  public void start() {
    this.view.start();
  }
}
