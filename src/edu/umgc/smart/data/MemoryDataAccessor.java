package edu.umgc.smart.data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import edu.umgc.smart.model.Record;

public class MemoryDataAccessor implements DataAccessor {
  private List<Record> records = new ArrayList<>();

  public MemoryDataAccessor() {
    Record record = new Record();
    record.setReferenceNumber("DOC-R-21-01234");
    record.setTitle("Finance Report for FY21 Q3");
    record.setDocumentType("Record");
    record.setAuthorLastName("Lastname");
    record.setAuthorFirstName("Firstname");
    record.setDate(Date.valueOf("2021-10-15"));
    record.setCategory("Finance");
    record.setSummary("This report covers the finances for the 3rd quarter of 2021.");
    record.setLocation("/network/folder/reports/Q3");
    records.add(record);
  }

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
