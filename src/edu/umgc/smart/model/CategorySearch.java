package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for a category search
 */
public class CategorySearch extends Search {

  public CategorySearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm, "Category");
  }

  @Override
  public Record[] getResults() {
    return dataAccessor.getRecordsByCategory(searchTerm);
  }

}
