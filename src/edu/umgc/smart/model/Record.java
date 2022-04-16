package edu.umgc.smart.model;

import java.sql.Date;

/**
 * Java Bean for passing information between Model, View, and Controller.
 */
public class Record {

  /**
   *
   */
  private static final String HEADERS = "Reference Number,Title,Type,Author Lastname,Author Firstname,Date,Category,Summary,Location";
  private String referenceNumber;
  private String title;
  private RecordType documentType;
  private String authorLastName;
  private String authorFirstName;
  private Date date;
  private String category;
  private String summary;
  private String location;

  public Record() {

  }

  public Record(String referenceNumber, String title, String documentType, String authorLastName,
      String authorFirstName, String date,
      String category, String summary, String location) {
    this.referenceNumber = referenceNumber;
    this.title = title;
    this.documentType = RecordType.valueOf(documentType);
    this.authorLastName = authorLastName;
    this.authorFirstName = authorFirstName;
    this.date = Date.valueOf(date);
    this.category = category;
    this.summary = summary;
    this.location = location;
  }

  public static String getHeaders() {
    return HEADERS;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public RecordType getDocumentType() {
    return documentType;
  }

  public void setDocumentType(RecordType documentType) {
    this.documentType = documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = RecordType.valueOf(documentType);
  }

  public String getAuthorLastName() {
    return authorLastName;
  }

  public void setAuthorLastName(String authorLastName) {
    this.authorLastName = authorLastName;
  }

  public String getAuthorFirstName() {
    return authorFirstName;
  }

  public void setAuthorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setDate(String date) {
    this.date = Date.valueOf(date);
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(referenceNumber);
    sb.append(',');
    sb.append(title);
    sb.append(',');
    sb.append(documentType);
    sb.append(',');
    sb.append(authorLastName);
    sb.append(',');
    sb.append(authorFirstName);
    sb.append(',');
    sb.append(date.toString());
    sb.append(',');
    sb.append(category);
    sb.append(',');
    sb.append(summary);
    sb.append(',');
    sb.append(location);
    return sb.toString();
  }

}
