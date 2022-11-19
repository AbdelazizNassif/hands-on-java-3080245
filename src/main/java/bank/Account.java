package bank;

import java.nio.file.AtomicMoveNotSupportedException;

import bank.exceptions.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("The minimum amount is 1");
    } else {
      double newBalance = this.balance + amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(getId(), newBalance);
    }
  }

  public void withdraw(double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("The withdraw amount must be greater than 0");
    } else if (amount > getBalance()) {
      throw new AmountException("The withdraw amount must be less than you total");
    } else {
      double newBalance = this.balance - amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(getId(), newBalance);
    }

  }
}
