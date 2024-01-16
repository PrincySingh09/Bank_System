import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {

  private double balance;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
      System.out.println("Deposit successful. New balance: Rupees " + balance);
    } else {
      System.out.println("Invalid deposit amount. Please enter a positive value.");
    }
  }

  public void withdrawal(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      System.out.println("Withdrawal successful. New balance: Rupees " + balance);
    } else {
      System.out.println("Invalid withdrawal amount or insufficient funds.");
    }
  }

  public void displayBalance() {
    System.out.println("Account balance: Rupees " + balance);
  }
}

class Customer {

  private int accountNumber;
  private String name;
  private BankAccount account;

  public Customer(int accountNumber, String name, double initialBalance) {
    this.accountNumber = accountNumber;
    this.name = name;
    this.account = new BankAccount(initialBalance);
  }

  public BankAccount getAccount() {
    return account;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public String getName() {
    return name;
  }
}

class Bank {

  private Map<Integer, Customer> customers;

  public Bank() {
    this.customers = new HashMap<>();
  }

  public void addCustomer(int accountNumber, String name, double initialBalance) {
    customers.put(accountNumber, new Customer(accountNumber, name, initialBalance));
  }

  public Customer getCustomer(int accountNumber) {
    return customers.get(accountNumber);
  }
}

public class BankSystem{

  public static void main(String[] args) {

    Bank bank = new Bank();

    bank.addCustomer(1001, "Princy Singh", 14000);
    bank.addCustomer(1002, "Khushi Amb", 20000);
    bank.addCustomer(1003, "Atharva Gupta", 18000);
    bank.addCustomer(1004, "Ayush Dubey", 24000);
    bank.addCustomer(1005, "Mukund Khandelwal", 22000);

    int choice;
    do {
      Scanner scanner = new Scanner(System.in);
      System.out.println("1. Display Balance");
      System.out.println("2. Deposit");
      System.out.println("3. Withdrawal");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter account number: ");
          int accNumDisplay = scanner.nextInt();
          Customer customerDisplay = bank.getCustomer(accNumDisplay);
          if (customerDisplay != null) {
            System.out.println("Customer: " + customerDisplay.getName());
            customerDisplay.getAccount().displayBalance();
          } else {
            System.out.println("Invalid account number.");
          }
          break;
        case 2:
          System.out.print("Enter account number: ");
          int accNumDeposit = scanner.nextInt();
          Customer customerDeposit = bank.getCustomer(accNumDeposit);
          if (customerDeposit != null) {
            System.out.println("Customer: " + customerDeposit.getName());
            System.out.print("Enter deposit amount: Rupees ");
            double depositAmount = scanner.nextDouble();
            customerDeposit.getAccount().deposit(depositAmount);
          } else {
            System.out.println("Invalid account number.");
          }
          break;
        case 3:
          System.out.print("Enter account number: ");
          int accNumWithdrawal = scanner.nextInt();
          Customer customerWithdrawal = bank.getCustomer(accNumWithdrawal);
          if (customerWithdrawal != null) {
            System.out.println("Customer: " + customerWithdrawal.getName());
            System.out.print("Enter withdrawal amount: Rupees ");
            double withdrawalAmount = scanner.nextDouble();
            customerWithdrawal.getAccount().withdrawal(withdrawalAmount);
          } else {
            System.out.println("Invalid account number.");
          }
          break;

        default:
          System.out.println("Invalid choice. Please enter a valid option.");
      }
    } while (choice != 4);
  }
}
