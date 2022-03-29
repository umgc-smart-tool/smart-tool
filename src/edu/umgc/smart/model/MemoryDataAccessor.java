package edu.umgc.smart.model;

import java.util.ArrayList;
import java.util.List;

public class MemoryDataAccessor implements DataAccessor {
  private List<Record> records = new ArrayList<>();

  @Override
  public Record get(int id) {
    return this.records.get(id);
  }

  @Override
  public List<Record> getAll() {
    return this.records;
  }

  @Override
  public void save(Record record) {
    this.records.add(record);
  }

  @Override
  public void update(int id, Record record) {
    Record temp = this.get(id);
    this.records.remove(temp);
    records.add(id, record);
  }

  @Override
  public void delete(Record record) {
    this.records.remove(record);
  }
}
