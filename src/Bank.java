import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<Account> accounts = new ArrayList<>();

    public int openSavings(String owner, double opening, double rate) {
        Account a = new SavingsAccount(owner, opening, rate);
        accounts.add(a);
        return a.getAccountId();
    }

    public int openChecking(String owner, double opening, double fee) {
        Account a = new CheckingAccount(owner, opening, fee);
        accounts.add(a);
        return a.getAccountId();
    }

    public Account find(int id) {
        for (Account a : accounts)
            if (a.getAccountId() == id)
                return a;
        return null;
    }

    public void transfer(int fromId, int toId, double amount) {
        Account from = find(fromId);
        Account to = find(toId);

        if (from == null || to == null)
            throw new IllegalArgumentException("Account not found.");

        if (amount <= 0)
            throw new IllegalArgumentException("Transfer must be positive.");

        from.withdraw(amount);
        to.deposit(amount);
    }

    public void processMonthEnd() {
        for (Account a : accounts)
            a.monthlyProcess();
    }

    public void listAccounts() {
        for (Account a : accounts)
            System.out.println(a);
    }
}
