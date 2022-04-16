package edu.umgc.smart.data;

import java.util.List;

import edu.umgc.smart.model.Record;

public interface DataAccessor {
  public Record get(int id);

  public List<Record> getAll();

  public void save(Record r);

  public void update(int id, Record r);

  public void delete(Record t);
}
