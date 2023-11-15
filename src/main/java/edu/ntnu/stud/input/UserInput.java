package edu.ntnu.stud.input;

import java.util.Scanner;

public class UserInput {
  private final Scanner scanner;

  public UserInput(){
    this.scanner = new Scanner(System.in);
  }

  public String getText(){
    return scanner.nextLine();
  }

  public int getInt(){
    return Integer.parseInt(scanner.nextLine());
  }

  public double getDouble(){
    return Double.parseDouble(scanner.nextLine());
  }
}
