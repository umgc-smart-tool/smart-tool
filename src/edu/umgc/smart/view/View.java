package edu.umgc.smart.view;

import java.io.Serializable;

import edu.umgc.smart.data.DataAccessor;
import edu.umgc.smart.model.Record;

public abstract class View implements Serializable {

  protected DataAccessor dataAccessor;

  public void setDataAccessor(DataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  public Record[] searchFor(String searchTerm) {
    return dataAccessor.getRecordsByMainSearch(searchTerm);
  }

  public abstract void start();
}
