package edu.umgc.smart.view;

import java.io.Serializable;

import edu.umgc.smart.data.DataAccessor;
import edu.umgc.smart.model.Record;

/**
 * Abstract View Class for the Application
 *
 * Any view to be used in the application must extend this class. While normally
 * an interface could be used, we wanted to ensure that any View object had a
 * DataAccessor and interfaces don't have instance variables.
 */
public abstract class View implements Serializable {

  DataAccessor dataAccessor;

  public void setDataAccessor(DataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  public Record[] searchFor(String searchTerm) {
    return dataAccessor.getRecordsByMainSearch(searchTerm);
  }

  public abstract void start();
}
