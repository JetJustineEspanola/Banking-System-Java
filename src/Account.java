public abstract class Account {

    private static int NEXT_ID = 1000;
    private final int accountId;
    private final String ownerName;
    private double balance;

    public Account(String ownerName, double openingBalance) {
        if (openingBalance < 0)
            throw new IllegalArgumentException("Opening balance cannot be negative.");

        this.accountId = NEXT_ID++;
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit must be positive.");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawal must be positive.");
        if (amount > balance)
            throw new IllegalStateException("Insufficient funds.");
        balance -= amount;
    }

    public abstract void monthlyProcess();

    @Override
    public String toString() {
        return String.format("%s (id=%d, owner='%s', balance=%.2f)",
                getClass().getSimpleName(),
                accountId,
                ownerName,
                balance);
    }
}
