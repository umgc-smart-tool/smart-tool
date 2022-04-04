package edu.umgc.smart.model;

import java.sql.Date;

/**
 * Java Bean for passing information between Model, View, and Controller.
 */
public class Record {

  private String referenceNumber;
  private String title;
  private String documentType;
  private String authorLastName;
  private String authorFirstName;
  private Date date;
  private String category;
  private String summary;
  private String location;

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
  public String getDocumentType() {
    return documentType;
  }
  public void setDocumentType(String documentType) {
    this.documentType = documentType;
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

}
