package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for a reference number search
 */
public class ReferenceNumberSearch extends Search{

  public ReferenceNumberSearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm, "Reference Number");
  }

  @Override
  public Record[] getResults() {
    return dataAccessor.getRecordsByReferenceNum(searchTerm);
  }

}
