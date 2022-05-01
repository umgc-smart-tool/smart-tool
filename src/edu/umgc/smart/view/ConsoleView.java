package edu.umgc.smart.view;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.umgc.smart.model.Record;

public class ConsoleView extends View {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private final transient Scanner scanner = new Scanner(System.in);

  public void start() {
    String message = String.format("%nCurrent Records:%n%s", getFormattedRecords());
    LOGGER.log(Level.INFO, message);
    scanner.nextLine();
  }

  private String getFormattedRecords() {
    List<Record> records = List.of(this.dataAccessor.getAll());
    StringBuilder sb = new StringBuilder();
    sb.append(Record.getHeaders());
    sb.append('\n');
    records.forEach(r -> {
      sb.append(r);
      sb.append('\n');
    });
    return sb.toString();
  }

}
