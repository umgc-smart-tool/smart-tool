package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for a summary search
 */
public class SummarySearch extends Search {

  public SummarySearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm, "Summary");
  }

  @Override
  public Record[] getResults() {
    return dataAccessor.getRecordsBySummary(searchTerm);
  }

}
