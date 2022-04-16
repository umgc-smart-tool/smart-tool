package edu.umgc.smart.data;

import edu.umgc.smart.model.Record;

public interface DataAccessor {
  public Record get(String referenceNumber);

  public Record[] getAll();

  public void save(Record r);

  public void update(String referenceNumber, Record r);

  public void delete(Record t);
}
