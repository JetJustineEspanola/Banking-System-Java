public class SavingsAccount extends Account {

    private final double interestRate; // e.g., 0.01 for 1% per month

    public SavingsAccount(String ownerName, double openingBalance, double interestRate) {
        super(ownerName, openingBalance);

        if (interestRate < 0)
            throw new IllegalArgumentException("Interest rate cannot be negative.");

        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void monthlyProcess() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
    }
}
