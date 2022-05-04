package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Abstract class for defining search queries
 *
 * Used for returning to the ResultsCardPanel from ViewCardPanel.
 */
public abstract class Search {
  final DataAccessor dataAccessor;
  final String searchTerm;
  final String field;

  Search(DataAccessor dataAccessor, String searchTerm) {
    this(dataAccessor, searchTerm, "All Fields");
  }

  Search(DataAccessor dataAccessor, String searchTerm, String field) {
    this.dataAccessor = dataAccessor;
    this.searchTerm = searchTerm;
    this.field = field;
  }

  public String getSearchTerm() {
    return this.searchTerm;
  }

  public String getSearchField() {
    return this.field;
  }

  public abstract Record[] getResults();
}
