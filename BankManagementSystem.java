import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// Interface for bank account operations
interface BankOperations 
{
    public void deposit(double amount);
    public void withdraw(double amount);
    public double getBalance();
}
// Abstract class for bank account with user details
abstract class BankAccount implements BankOperations 
{
    protected String name;
    protected String address;
    protected String mobileNumber;
    protected double balance;
    protected String accountNumber;
    protected String pin;
    public BankAccount(String name, String address, String mobileNumber, double initialBalance, String accountNumber, String pin) 
    {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
        this.pin = pin;
    }
    //Setters
    public void setMobileNumber(String mobileNumber) 
    {
    	this.mobileNumber = mobileNumber;
    }
    public void setAddress(String address) 
    {
    	this.address = address;
    }
    public void setPinNumber(String pin)
    {
     	this.pin=pin;
    }
    // Getters
    public String getName() 
    {
        return name;
    }
    public String getAddress() 
    {
        return address;
    }
    public String getMobileNumber() 
    {
        return mobileNumber;
    }
    public String getAccountNumber() 
    {
        return accountNumber;
    }
    public String getPin() 
    {
        return pin;
    }
}
// Savings Account class
class SavingsAccount extends BankAccount 
{
    public SavingsAccount(String name, String address, String mobileNumber, double initialBalance, String accountNumber, String pin, double interestRate) 
    {
        super(name, address, mobileNumber, initialBalance, accountNumber, pin);
    }
    @Override
    public void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println(amount + " deposited successfully.");
        } 
        else 
        {
            System.out.println("Invalid amount for deposit.");
        }
    }
    @Override
    public void withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance) 
        {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully.");
        }
        else 
        {
            System.out.println("Invalid amount or insufficient balance for withdrawal.");
        }
    }
    @Override
    public double getBalance() 
    {
        return balance;
    }
}
// Checking Account class
class CheckingAccount extends BankAccount 
{
    private double overdraftLimit;
    public CheckingAccount(String name, String address, String mobileNumber, double initialBalance, String accountNumber, String pin, double overdraftLimit) 
    {
        super(name, address, mobileNumber, initialBalance, accountNumber, pin);
        this.overdraftLimit = overdraftLimit;
    }
    @Override
    public void withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance + overdraftLimit) 
        {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully.");
        }
        else 
        {
            System.out.println("Invalid amount or overdraft limit exceeded.");
        }
    }
    @Override
    public void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println(amount + " deposited successfully.");
        } 
        else 
        {
            System.out.println("Invalid amount for deposit.");
        }
    }
    @Override
    public double getBalance() 
    {
        return balance;
    }
}
// Main class to manage bank accounts
public class BankManagementSystem
{
    private static Map<String, BankAccount> accounts = new HashMap<>(); // Map to store account details (account number as key)
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) 
    {
        while (true) 
        {
            System.out.println("\nWelcome to PaisaVasool Bank");
            System.out.println("\nMenu:");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Checking Account");
            System.out.println("3. Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) 
            {
                case 1:createSavingsAccount();
                       break;
                case 2:createCheckingAccount();
                       break;
                case 3:login();
                       break;
                case 4:System.out.println("\nThank You for Banking with us!");
                       sc.close();
                       System.exit(0);
                default:System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
    // Method to create a new savings account
    private static void createSavingsAccount() 
    {
        sc.nextLine(); // Consume newline
	System.out.println("Enter your Details to create a Savings Account");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your address: ");
        String address = sc.nextLine();
	String mobileNumber;
        while(true)
	{
		System.out.print("Enter your mobile number: ");
        	mobileNumber = sc.nextLine();
		if(mobileNumber.matches("\\d{10}"))
 		break;
		else
		System.out.println("Please Enter a valid Mobile Number");
	}
        System.out.print("Enter initial balance: ");
        double initialBalance = sc.nextDouble();
        System.out.print("Enter interest rate: ");
        double interestRate = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Set a 4-digit PIN: ");
        String pin = sc.nextLine();
        while(pin.length()!=4 || !pin.matches("\\d{4}"))
        {
        	System.out.println("Invalid Format");
                System.out.println("Re-enter the new pin in the correct format");
                pin=sc.nextLine();
        }
        String accountNumber = generateAccountNumber();
        SavingsAccount savingsAccount = new SavingsAccount(name, address, mobileNumber, initialBalance, accountNumber, pin, interestRate);
        accounts.put(accountNumber, savingsAccount);
        System.out.println("\nSavings Account created successfully.\nYour account number is: " + accountNumber);
    }
    // Method to create a new checking account
    private static void createCheckingAccount() 
    {
        sc.nextLine(); // Consume newline
 	System.out.println("Enter your Details to create a Checking Account");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your address: ");
        String address = sc.nextLine();
	String mobileNumber;
        while(true)
	{
		System.out.print("Enter your mobile number: ");
        	mobileNumber = sc.nextLine();
		if(mobileNumber.matches("\\d{10}"))
 		break;
		else
		System.out.println("Please Enter a valid Mobile Number");
	}
        System.out.print("Enter initial balance: ");
        double initialBalance = sc.nextDouble();
        System.out.print("Enter overdraft limit: ");
        double overdraftLimit = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Set a 4-digit PIN: ");
        String pin = sc.nextLine();
        while(pin.length()!=4 || !pin.matches("\\d{4}"))
        {
        	System.out.println("Invalid Format");
                System.out.println("Re-enter the new pin in the correct format");
                pin=sc.nextLine();
        }
        String accountNumber = generateAccountNumber();
        CheckingAccount checkingAccount = new CheckingAccount(name, address, mobileNumber, initialBalance, accountNumber, pin, overdraftLimit);
        accounts.put(accountNumber, checkingAccount);
        System.out.println("Checking Account created successfully.\nYour account number is: " + accountNumber);
    }
    // Method to generate a random 8-digit account number
    private static String generateAccountNumber() 
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) 
        {
            sb.append((int)(Math.random() * 10));
        }
        return sb.toString();
    }
    // Method to log in to an existing account
    private static void login() 
    {
        sc.nextLine(); // Consume newline
        System.out.print("Enter your account number: ");
        String accountNumber = sc.nextLine();
        System.out.print("Enter your 4-digit PIN: ");
        String pin = sc.nextLine();
        if (accounts.containsKey(accountNumber) && accounts.get(accountNumber).getPin().equals(pin)) 
        {
            BankAccount account = accounts.get(accountNumber);
            System.out.println("Login successful. Welcome, " + account.getName() + "!");
            performBankOperations(account);
        } 
        else 
        {
            System.out.println("\nInvalid account number or PIN.");
	    System.out.println("Exiting");	
        }
    }
    // Method to perform bank operations after successful login
    private static void performBankOperations(BankAccount account) 
    {
        while (true) 
        {
            System.out.println("\nOptions:");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Check Account Details");
            System.out.println("5. Edit Details");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) 
            {
                case 1:System.out.print("Enter amount to deposit: ");
                       double depositAmount = sc.nextDouble();
                       account.deposit(depositAmount);
                       break;
                case 2:System.out.print("Enter amount to withdraw: ");
                       double withdrawAmount = sc.nextDouble();
                       account.withdraw(withdrawAmount);
                       break;
                case 3:System.out.println("Account Balance: " + account.getBalance());
                       break;
                case 4:System.out.println("Account Holder Name: " + account.getName());
                       System.out.println("Mobile Number: " + account.getMobileNumber());
                       System.out.println("Address: " + account.getAddress());
                       System.out.println("Account Balance: " + account.getBalance());
                       break;
                case 5:editDetails(account);
                       break;
                case 6:System.out.println("Logging out...");
                       System.out.println("Thank You for Banking with us");
                       return;
                default:System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
    //Method to edit bank account details after successful login
    private static void editDetails(BankAccount account) 
    {
    	sc.nextLine(); // Consume newline
    	System.out.println("\nSelect detail to edit:");
    	System.out.println("1. Mobile Number");
    	System.out.println("2. Address");
        System.out.println("3. Pin Number");
    	System.out.print("Enter your choice: ");
    	int choice = sc.nextInt();
    	sc.nextLine(); // Consume newline
   	switch (choice) 
        {
        	case 1:String newMobileNumber;
        		while(true)
			{
				System.out.print("Enter your new mobile number: ");
        			newMobileNumber = sc.nextLine();
				if(newMobileNumber.matches("\\d{10}"))
 				break;
				else
				System.out.println("Please Enter a valid Mobile Number");
			}
                       account.setMobileNumber(newMobileNumber);
                       System.out.println("Mobile number updated successfully.");
                       break;
        	case 2:System.out.print("Enter new address: ");
                       String newAddress = sc.nextLine();
                       account.setAddress(newAddress);
                       System.out.println("Address updated successfully.");
            	       break;
                case 3:System.out.println("Enter your new pin: ");
                       String pin=sc.nextLine();
                       while(pin.length()!=4 || !pin.matches("\\d{4}"))
        	       {
        	       	   System.out.println("Invalid Format");
                           System.out.println("Re-enter the pin in the correct format");
                       	   pin=sc.nextLine();
                       }
                       account.setPinNumber(pin);
                       System.out.println("Pin Updated Successfully");
                       break;
        	default:System.out.println("Invalid choice.");
    	}
    }
}