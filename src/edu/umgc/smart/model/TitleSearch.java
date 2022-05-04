package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for a title search
 */
public class TitleSearch extends Search {

  public TitleSearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm, "Title");
  }

  @Override
  public Record[] getResults() {
    return dataAccessor.getRecordsByTitle(searchTerm);
  }

}
