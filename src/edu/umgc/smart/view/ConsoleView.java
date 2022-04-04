package edu.umgc.smart.view;

import java.util.List;
import java.util.Scanner;
import edu.umgc.smart.model.Record;

public class ConsoleView extends View {

  private Scanner scanner;

  public ConsoleView() {
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    System.out.println("Hello World!");
    System.out.println("\nCurrent Records:");
    printRecords();
    scanner.nextLine();
  }

  private void printRecords() {
    List<Record> records = this.dataAccessor.getAll();
    System.out.println(Record.getHeaders());
    records.forEach((r) -> {
      System.out.println(r);
    });
  }

}
