package edu.umgc.smart.view;

import java.util.Scanner;

public class ConsoleView implements View {

  private Scanner scanner;

  public ConsoleView() {
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    System.out.println("Hello World!");
    scanner.nextLine();
  }
}
