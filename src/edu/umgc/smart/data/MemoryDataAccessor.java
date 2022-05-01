package edu.umgc.smart.data;

import java.util.ArrayList;
import java.util.List;

import edu.umgc.smart.model.Record;
import edu.umgc.smart.model.RecordType;

public class MemoryDataAccessor implements DataAccessor {
  private static final long serialVersionUID = 3372578901821323067L;

  private List<Record> records = new ArrayList<>();

  public MemoryDataAccessor() {
    Record r = new Record.Builder("DOC-R-21-01234")
        .title("Finance Report for FY21 Q3")
        .type("Record")
        .lastName("Lastname")
        .firstName("Firstname")
        .date("2021-10-15")
        .category("Finance")
        .summary("This report covers the finances for the 3rd quarter of 2021.")
        .location("/network/folder/reports/Q3")
        .build();
    records.add(r);
  }

  @Override
  public Record get(String referenceNumber) {
    for (int i = 0; i < records.size(); i++) {
      Record r = records.get(i);
      if (r.getReferenceNumber().equals(referenceNumber)) {
        return r;
      }
    }
    return null;
  }

  @Override
  public Record[] getAll() {
    return this.records.toArray(new Record[0]);
  }

  @Override
  public void add(Record r) {
    this.records.add(r);
  }

  @Override
  public void save() {
    this.records.sort(null);
  }

  @Override
  public void update(String referenceNumber, Record r) {
    Record temp = this.get(referenceNumber);
    if (null != temp) {
      this.records.remove(temp);
    }
    records.add(r);
  }

  @Override
  public void delete(Record r) {
    try {
      this.records.remove(r);
    } catch (Exception e) {
      // If record doesn't already exist, don't delete anything
    }
  }

  @Override
  public Record[] getRecordsByMainSearch(String searchTerm) {
    return new Record[0];
  }

  @Override
  public Record[] getRecordsByReferenceNum(String referenceNumber) {
    return new Record[0];
  }

  @Override
  public Record[] getRecordsByTitle(String title) {
    return new Record[0];
  }

  @Override
  public Record[] getRecordsByRecordType(RecordType recordType) {
    return new Record[0];
  }

  @Override
  public Record[] getRecordsByAuthorFirstName(String author) {
    return new Record[0];
  }

  @Override
  public Record[] getRecordsByAuthorLastName(String author) {
    return new Record[0];
  }

  @Override
  public Record[] getRecordsByDate(String date) {
    return new Record[0];
  }

  @Override
  public Record[] getRecordsByCategory(String category) {
    return new Record[0];
  }

  @Override
  public Record[] getRecordsBySummary(String summary) {
    return new Record[0];
  }
}
