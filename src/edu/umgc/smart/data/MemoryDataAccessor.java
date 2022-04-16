package edu.umgc.smart.data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import edu.umgc.smart.model.Record;

public class MemoryDataAccessor implements DataAccessor {
  private List<Record> records = new ArrayList<>();

  public MemoryDataAccessor() {
    Record r = new Record();
    r.setReferenceNumber("DOC-R-21-01234");
    r.setTitle("Finance Report for FY21 Q3");
    r.setDocumentType("Record");
    r.setAuthorLastName("Lastname");
    r.setAuthorFirstName("Firstname");
    r.setDate(Date.valueOf("2021-10-15"));
    r.setCategory("Finance");
    r.setSummary("This report covers the finances for the 3rd quarter of 2021.");
    r.setLocation("/network/folder/reports/Q3");
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
  public void save(Record r) {
    this.records.add(r);
  }

  @Override
  public void update(String referenceNumber, Record r) {
    Record temp = this.get(referenceNumber);
    if (temp != null) {
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
}
