package edu.umgc.smart.view;

import java.io.Serializable;

import edu.umgc.smart.data.DataAccessor;

public abstract class View implements Serializable {

  protected DataAccessor dataAccessor;

  public void setDataAccessor(DataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  public abstract void start();
}
