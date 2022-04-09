package edu.umgc.smart.view;

import edu.umgc.smart.data.DataAccessor;

public abstract class View {

  protected DataAccessor dataAccessor;

  public void setDataAccessor(DataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }
  public abstract void start();
}
