package Day5;
import java.util.ArrayList;
import java.util.List;



public class Account {
    private double balance;
    private List<String> transactionHistory;

    public Account() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
    public static void main(String[] args) {
        Account account = new Account();
        account.deposit(6000.0);
        account.withdraw(200.0);
        account.deposit(150.0);
        System.out.println("Current Balance: " + account.getBalance());
        System.out.println("Transaction History:");
        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
