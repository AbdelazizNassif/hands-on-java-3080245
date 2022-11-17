package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to International bank");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();
    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer authenticateUser() {
    System.out.println("Please enter username");
    String username = scanner.next();
    System.out.println("Please enter password");
    String password = scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      e.printStackTrace();
      System.out.println("There is a n error: " + e.getMessage());
    }
    return customer;
  }

  public void showMenu(Customer customer, Account account)
  {
    int selection  = 0;
    while (customer.isAuthenticated() && selection != 4)
    {
          System.out.println("*************************");

      System.out.println("Please select one option");

      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
    System.out.println("*************************");

      selection = scanner.nextInt();
      switch (selection) {
        

      }
    }

    
    
  }

}
