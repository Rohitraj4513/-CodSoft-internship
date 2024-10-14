import java.util.Scanner;

class ATMINTERFACE{
    private String accountNumber;
    private double balance;

    public ATMINTERFACE(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }
}

class ATM {
    private ATMINTERFACE bankAccount;

    public ATM(ATMINTERFACE bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Select an option (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private void checkBalance() {
        double balance = bankAccount.checkBalance();
        System.out.printf("Your current balance is: $%.2f%n", balance);
    }

    private void depositMoney(Scanner scanner) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        if (bankAccount.deposit(amount)) {
            System.out.printf("$%.2f has been deposited successfully.%n", amount);
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    private void withdrawMoney(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (bankAccount.withdraw(amount)) {
            System.out.printf("$%.2f has been withdrawn successfully.%n", amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATMINTERFACE account = new ATMINTERFACE("123456", 1000.00); // Create a bank account with initial balance
        ATM atm = new ATM(account); // Create an ATM with the user's bank account
        atm.run(); // Run the ATM
    }
}
