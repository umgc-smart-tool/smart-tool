package edu.umgc.smart.model;

import java.util.List;

public interface DataAccessor {
  public Record get(int id);

  public List<Record> getAll();

  public void save(Record record);

  public void update(int id, Record record);

  public void delete(Record t);
}
