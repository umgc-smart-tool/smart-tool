package edu.umgc.smart.model;

import edu.umgc.smart.data.DataAccessor;

/**
 * Stores search information for a record type search
 *
 * Since all Search classes use a String for the searchTerm, a check to
 * validate the RecordType was necessary.  In the event of an invalid type, an
 * empty Record array is returned.
 */
public class TypeSearch extends Search {

  public TypeSearch(DataAccessor dataAccessor, String searchTerm) {
    super(dataAccessor, searchTerm, "Type");
  }

  @Override
  public Record[] getResults() {
    if (InputValidator.isValidRecordType(searchTerm)) {
      return dataAccessor.getRecordsByRecordType(RecordType.valueOf(searchTerm));
    }
    return new Record[0];
  }

}
