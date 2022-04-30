package edu.umgc.smart.data;

import java.io.Serializable;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public interface DataAccessor extends Serializable {
  public Record get(String referenceNumber);

  public Record[] getAll();

  public void save(Record r);

  public void update(String referenceNumber, Record r);

  public void delete(Record t);

  public Record[] getRecordsByMainSearch(String searchTerm);

  public Record[] getRecordsByReferenceNum(String referenceNumber);

  public Record[] getRecordsByTitle(String title);

  public Record[] getRecordsByRecordType(RecordType recordType);

  public Record[] getRecordsByAuthorFirstName(String author);

  public Record[] getRecordsByAuthorLastName(String author);

  public Record[] getRecordsByDate(String date);

  public Record[] getRecordsByCategory(String category);

  public Record[] getRecordsBySummary(String summary);

}
