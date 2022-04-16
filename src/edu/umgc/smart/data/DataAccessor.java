package edu.umgc.smart.data;

import java.sql.Date;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public interface DataAccessor {
  public Record get(String referenceNumber);

  public Record[] getAll();

  public void save(Record r);

  public void update(String referenceNumber, Record r);

  public void delete(Record t);

  public Record[] getRecordsByMainSearch(String searchTerm);

  public Record[] getRecordsByReferenceNum(String referenceNumber);

  public Record[] getRecordsByTitle(String title);

  public Record[] getRecordsByRecordType(RecordType recordType);

  public Record[] getRecordsByAuthor(String author);

  public Record[] getRecordsByDate(Date date);

  public Record[] getRecordsByCategory(String category);

  public Record[] getRecordsBySummary(String summary);

}
