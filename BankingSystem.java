import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Transaction {
    private final LocalDate date;
    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final String note;

    Transaction(String type, double amount,
                double balanceAfter, String note) {
        this.date = LocalDate.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.note = note;
    }

    LocalDate getDate() {
        return date;
    }

    void printRow() {
        System.out.printf("%-12s %-18s %10.2f %12.2f %s%n",
                date, type, amount, balanceAfter, note);
    }
}

class Account {
    private final int accountNumber;
    private final String holderName;
    private double balance;

    private double withdrawnToday = 0.0;
    private LocalDate withdrawalDate = LocalDate.now();

    private final ArrayList<Transaction> transactions =
            new ArrayList<>();

    private static final double DAILY_WITHDRAWAL_LIMIT = 20000.00;

    Account(int accountNumber, String holderName,
            double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;

        transactions.add(new Transaction(
                "Opening Balance", openingBalance,
                balance, "Account opened"));
    }

    int getAccountNumber() {
        return accountNumber;
    }

    String getHolderName() {
        return holderName;
    }

    double getBalance() {
        return balance;
    }

    // Method overloading
    void deposit(double amount) {
        deposit(amount, "Cash deposit");
    }

    void deposit(double amount, String note) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println(
                    "ERROR: Amount must be greater than zero.");
            return;
        }

        balance += amount;

        transactions.add(new Transaction(
                "Deposit", amount, balance, note));

        System.out.printf(
                "Deposit successful. New balance: Rs. %.2f%n",
                balance);
    }

    void withdraw(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println(
                    "ERROR: Amount must be greater than zero.");
            return;
        }

        LocalDate today = LocalDate.now();

        if (!today.equals(withdrawalDate)) {
            withdrawnToday = 0.0;
            withdrawalDate = today;
        }

        if (amount > balance) {
            System.out.println("ERROR: Insufficient balance.");
            return;
        }

        if (withdrawnToday + amount > DAILY_WITHDRAWAL_LIMIT) {
            System.out.printf(
                    "ERROR: Daily withdrawal limit exceeded. "
                    + "Remaining limit: Rs. %.2f%n",
                    DAILY_WITHDRAWAL_LIMIT - withdrawnToday);
            return;
        }

        balance -= amount;
        withdrawnToday += amount;

        transactions.add(new Transaction(
                "Withdrawal", amount, balance, "Cash withdrawal"));

        System.out.printf(
                "Withdrawal successful. New balance: Rs. %.2f%n",
                balance);
    }

    void displayBalance() {
        System.out.printf(
                "Available balance: Rs. %.2f%n", balance);
    }

    void displayAccount() {
        System.out.println("--------------------------------------------");
        System.out.println("Account type   : General Account");
        System.out.println("Account number : " + accountNumber);
        System.out.println("Account holder : " + holderName);
        System.out.printf("Balance        : Rs. %.2f%n", balance);
        System.out.println("--------------------------------------------");
    }

    void printPassbook(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            System.out.println(
                    "ERROR: From date must not be after To date.");
            return;
        }

        System.out.println();
        System.out.println("PASSBOOK FOR ACCOUNT "
                + accountNumber + " - " + holderName);

        System.out.printf("%-12s %-18s %10s %12s %s%n",
                "Date", "Transaction", "Amount",
                "Balance after", "Note");

        System.out.println(
                "--------------------------------------------------------------------------");

        boolean found = false;

        for (Transaction t : transactions) {
            if (!t.getDate().isBefore(from)
                    && !t.getDate().isAfter(to)) {
                t.printRow();
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No transactions found in the selected date range.");
        }

        System.out.println(
                "--------------------------------------------------------------------------");
    }
}

class SavingsAccount extends Account {
    SavingsAccount(int accountNumber, String holderName,
                   double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    @Override
    void displayAccount() {
        System.out.println("--------------------------------------------");
        System.out.println("Account type   : Savings Account");
        System.out.println("Account number : " + getAccountNumber());
        System.out.println("Account holder : " + getHolderName());

        System.out.printf(
                "Balance        : Rs. %.2f%n", getBalance());

        System.out.println("--------------------------------------------");
    }
}

public class BankingSystem {

    private static final Map<Integer, Account> accounts =
            new LinkedHashMap<>();

    private static int nextAccountNumber = 1006;

    public static void main(String[] args) {
        loadFiveSampleAccounts();

        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println(
                        "ERROR: Please enter a number from 1 to 8.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount(sc);
                    break;

                case 2:
                    depositMoney(sc);
                    break;

                case 3:
                    withdrawMoney(sc);
                    break;

                case 4:
                    checkBalance(sc);
                    break;

                case 5:
                    displayAccountInformation(sc);
                    break;

                case 6:
                    printPassbook(sc);
                    break;

                case 7:
                    displayAllAccounts();
                    break;

                case 8:
                    System.out.println(
                            "Thank you for using the Banking Management System.");
                    sc.close();
                    return;

                default:
                    System.out.println(
                            "ERROR: Invalid choice. Please select 1 to 8.");
            }
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("       BANKING MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Display Account Information");
        System.out.println("6. Print Passbook (date range)");
        System.out.println("7. Display All Accounts");
        System.out.println("8. Exit");
        System.out.println("----------------------------------------------");
    }

    // Load five sample accounts
    private static void loadFiveSampleAccounts() {
        accounts.put(1001,
                new SavingsAccount(1001, "Rahul Patil", 10000.00));

        accounts.put(1002,
                new SavingsAccount(1002, "Priya Sharma", 15000.00));

        accounts.put(1003,
                new SavingsAccount(1003, "Amit Kulkarni", 20000.00));

        accounts.put(1004,
                new SavingsAccount(1004, "Sneha Deshmukh", 12000.00));

        accounts.put(1005,
                new SavingsAccount(1005, "Akash Jadhav", 25000.00));

        System.out.println(
                "Five sample accounts loaded successfully.");
    }

    // Create a new account
    private static void createAccount(Scanner sc) {
        sc.nextLine();

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("ERROR: Name cannot be empty.");
            return;
        }

        System.out.print("Enter opening balance (Rs.): ");

        if (!sc.hasNextDouble()) {
            System.out.println(
                    "ERROR: Please enter a valid amount.");
            sc.nextLine();
            return;
        }

        double amount = sc.nextDouble();

        if (!Double.isFinite(amount) || amount < 0) {
            System.out.println(
                    "ERROR: Opening balance cannot be negative.");
            return;
        }

        int number = nextAccountNumber++;

        Account account =
                new SavingsAccount(number, name, amount);

        accounts.put(number, account);

        System.out.println("Account created successfully.");
        System.out.println("New account number: " + number);
    }

    // Find an account by account number
    private static Account findAccount(Scanner sc) {
        System.out.print("Enter account number: ");

        if (!sc.hasNextInt()) {
            System.out.println(
                    "ERROR: Account number must be numeric.");
            sc.nextLine();
            return null;
        }

        int number = sc.nextInt();

        Account account = accounts.get(number);

        if (account == null) {
            System.out.println("ERROR: Account not found.");
        }

        return account;
    }

    // Deposit money
    private static void depositMoney(Scanner sc) {
        Account account = findAccount(sc);

        if (account == null) {
            return;
        }

        System.out.print("Enter deposit amount (Rs.): ");

        if (!sc.hasNextDouble()) {
            System.out.println(
                    "ERROR: Please enter a valid amount.");
            sc.nextLine();
            return;
        }

        account.deposit(sc.nextDouble(), "Cash deposit");
    }

    // Withdraw money
    private static void withdrawMoney(Scanner sc) {
        Account account = findAccount(sc);

        if (account == null) {
            return;
        }

        System.out.print("Enter withdrawal amount (Rs.): ");

        if (!sc.hasNextDouble()) {
            System.out.println(
                    "ERROR: Please enter a valid amount.");
            sc.nextLine();
            return;
        }

        account.withdraw(sc.nextDouble());
    }

    // Check account balance
    private static void checkBalance(Scanner sc) {
        Account account = findAccount(sc);

        if (account != null) {
            account.displayBalance();
        }
    }

    // Display account information
    private static void displayAccountInformation(Scanner sc) {
        Account account = findAccount(sc);

        if (account != null) {
            account.displayAccount();
        }
    }

    // Print passbook for a date range
    private static void printPassbook(Scanner sc) {
        Account account = findAccount(sc);

        if (account == null) {
            return;
        }

        try {
            System.out.print(
                    "Enter From date (YYYY-MM-DD): ");
            LocalDate from = LocalDate.parse(sc.next());

            System.out.print(
                    "Enter To date (YYYY-MM-DD): ");
            LocalDate to = LocalDate.parse(sc.next());

            account.printPassbook(from, to);

        } catch (DateTimeParseException e) {
            System.out.println(
                    "ERROR: Invalid date. Use YYYY-MM-DD.");
        }
    }

    // Display all accounts
    private static void displayAllAccounts() {
        System.out.println();
        System.out.println("ALL BANK ACCOUNTS");
        System.out.println("================================================");

        for (Account account : accounts.values()) {
            account.displayAccount();
        }

        System.out.println("Total accounts: " + accounts.size());
    }
}
