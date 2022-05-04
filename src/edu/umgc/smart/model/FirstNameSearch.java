package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for an author first name search
 */
public class FirstNameSearch extends Search {

  public FirstNameSearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm, "Author Firstname");
  }

  @Override
  public Record[] getResults() {
    return dataAccessor.getRecordsByAuthorFirstName(searchTerm);
  }

}
