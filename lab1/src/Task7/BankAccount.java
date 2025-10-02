package Task7;

public class BankAccount {
    private final String owner;
    private final Bank bank;
    private final String currency;
    private double balance;

    public BankAccount(String owner, Bank bank, String currency, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.owner = owner;
        this.bank = bank;
        this.currency = currency;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public Bank getBank() {
        return bank;
    }

    public String getCurrency() {
        return currency;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds in account");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return owner + " (" + bank.getName() + ", " + currency + "): " +
                String.format("%.2f", balance);
    }
}