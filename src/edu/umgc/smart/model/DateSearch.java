package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for a date search
 */
public class DateSearch extends Search {

  public DateSearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm, "Date");
  }

  @Override
  public Record[] getResults() {
    return dataAccessor.getRecordsByDate(searchTerm);
  }

}
