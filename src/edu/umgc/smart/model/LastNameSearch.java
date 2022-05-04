package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for an author last name search
 */
public class LastNameSearch extends Search {

  public LastNameSearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm, "Author Lastname");
  }

  @Override
  public Record[] getResults() {
    return dataAccessor.getRecordsByAuthorLastName(searchTerm);
  }

}
