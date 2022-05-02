package edu.umgc.smart.model;

import java.io.Serializable;

/**
 * Java Class solely used for passing information between Model, View, and Controller.
 *
 * An internal Builder class is used for constructing Records. This Builder class
 * must be used as the Record constructor is private only. The Builder class
 * allows for "partial" data to be submitted for creation while providing default
 * data for a functioning Record.
 */
public class Record implements Serializable {
  private static final long serialVersionUID = 600597679460063835L;
  private static final String HEADERS = "Reference Number,Title,Type,Author Lastname,Author Firstname,Date,Category,Summary,Location";

  private final String referenceNumber;
  private final String title;
  private final RecordType documentType;
  private final String authorLastName;
  private final String authorFirstName;
  private final String date;
  private final String category;
  private final String summary;
  private final String location;

  public static String getHeaders() {
    return HEADERS;
  }

  private Record(final Builder builder) {
    this.referenceNumber = builder.referenceNumber;
    this.title = builder.title;
    this.documentType = builder.documentType;
    this.authorLastName = builder.authorLastName;
    this.authorFirstName = builder.authorFirstName;
    this.date = builder.date;
    this.category = builder.category;
    this.summary = builder.summary;
    this.location = builder.location;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public String getTitle() {
    return title;
  }

  public RecordType getDocumentType() {
    return documentType;
  }

  public String getAuthorLastName() {
    return authorLastName;
  }

  public String getAuthorFirstName() {
    return authorFirstName;
  }

  public String getDate() {
    return date;
  }

  public String getCategory() {
    return category;
  }

  public String getSummary() {
    return summary;
  }

  public String getLocation() {
    return location;
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
    sb.append(date);
    sb.append(',');
    sb.append(category);
    sb.append(',');
    sb.append(summary);
    sb.append(',');
    sb.append(location);
    return sb.toString();
  }

  public static class Builder {
    private final String referenceNumber;
    private String title = "";
    private RecordType documentType = RecordType.MEMO;
    private String authorLastName = "";
    private String authorFirstName = "";
    private String date = "";
    private String category = "";
    private String summary = "";
    private String location = "";

    public Builder(String referenceNumber) {
      this.referenceNumber = referenceNumber;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder type(RecordType type) {
      this.documentType = type;
      return this;
    }

    public Builder type(String type) {
      if (InputValidator.isValidRecordType(type.toUpperCase())) {
        this.documentType = RecordType.valueOf(type.toUpperCase());
      }
      return this;
    }

    public Builder lastName(String lastName) {
      if (InputValidator.isValidName(lastName)) {
        this.authorLastName = lastName;
      }
      return this;
    }

    public Builder firstName(String firstName) {
      if (InputValidator.isValidName(firstName)) {
        this.authorFirstName = firstName;
      }
      return this;
    }

    public Builder date(String date) {
      if (InputValidator.isValidDate(date)) {
        this.date = date;
      }
      return this;
    }

    public Builder category(String category) {
      this.category = category;
      return this;
    }

    public Builder summary(String summary) {
      if (InputValidator.isValidSummaryLength(summary)) {
        this.summary = summary;
      } else if (summary.length() > 0) {
        this.summary = summary.substring(0, 500);
      }
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public Record build() {
      return new Record(this);
    }

  }

}
