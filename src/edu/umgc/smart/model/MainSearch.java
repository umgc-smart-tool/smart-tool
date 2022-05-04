package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for a search against any field
 */
public class MainSearch extends Search {

  public MainSearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm);
  }

  @Override
  public Record[] getResults() {
    return dataAccessor.getRecordsByMainSearch(searchTerm);
  }

}
