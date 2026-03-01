import java.util.Scanner;

public class Module6OOPLab {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // ===== Self-Checks (Enable assertions with -ea) =====
        SavingsAccount s = new SavingsAccount("Alice", 1000, 0.01);
        s.monthlyProcess();
        assert Math.round(s.getBalance() * 100) / 100.0 == 1010.00 : "Savings interest failed";

        CheckingAccount c = new CheckingAccount("Bob", 100, 5.0);
        c.withdraw(20);
        assert Math.round(c.getBalance() * 100) / 100.0 == 75.00 : "Checking fee logic failed";

        boolean threw = false;
        try {
            c.withdraw(1000);
        } catch (IllegalStateException ex) {
            threw = true;
        }
        assert threw : "Should throw on insufficient funds";

        // ===== CLI Driver =====
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n=== Bank Menu ===");
            System.out.println("1) Open Savings");
            System.out.println("2) Open Checking");
            System.out.println("3) Deposit");
            System.out.println("4) Withdraw");
            System.out.println("5) Transfer");
            System.out.println("6) List Accounts");
            System.out.println("7) Month End");
            System.out.println("8) Exit");
            System.out.print("Choose [1-8]: ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {

                    case "1":
                        System.out.print("Owner name: ");
                        String n1 = sc.nextLine();

                        System.out.print("Opening balance: ");
                        double ob1 = Double.parseDouble(sc.nextLine());

                        System.out.print("Interest rate (e.g., 0.01): ");
                        double r = Double.parseDouble(sc.nextLine());

                        int id1 = bank.openSavings(n1, ob1, r);
                        System.out.println("Opened Savings id " + id1);
                        break;

                    case "2":
                        System.out.print("Owner name: ");
                        String n2 = sc.nextLine();

                        System.out.print("Opening balance: ");
                        double ob2 = Double.parseDouble(sc.nextLine());

                        System.out.print("Transaction fee: ");
                        double f = Double.parseDouble(sc.nextLine());

                        int id2 = bank.openChecking(n2, ob2, f);
                        System.out.println("Opened Checking id " + id2);
                        break;

                    case "3":
                        System.out.print("Account id: ");
                        int did = Integer.parseInt(sc.nextLine());

                        System.out.print("Amount: ");
                        double da = Double.parseDouble(sc.nextLine());

                        Account daA = bank.find(did);
                        if (daA == null)
                            throw new IllegalArgumentException("Not found");

                        daA.deposit(da);
                        System.out.println("Deposited.");
                        break;

                    case "4":
                        System.out.print("Account id: ");
                        int wid = Integer.parseInt(sc.nextLine());

                        System.out.print("Amount: ");
                        double wa = Double.parseDouble(sc.nextLine());

                        Account waA = bank.find(wid);
                        if (waA == null)
                            throw new IllegalArgumentException("Not found");

                        waA.withdraw(wa);
                        System.out.println("Withdrawn.");
                        break;

                    case "5":
                        System.out.print("From id: ");
                        int from = Integer.parseInt(sc.nextLine());

                        System.out.print("To id: ");
                        int to = Integer.parseInt(sc.nextLine());

                        System.out.print("Amount: ");
                        double amt = Double.parseDouble(sc.nextLine());

                        bank.transfer(from, to, amt);
                        System.out.println("Transferred.");
                        break;

                    case "6":
                        bank.listAccounts();
                        break;

                    case "7":
                        bank.processMonthEnd();
                        System.out.println("Month-end processing complete.");
                        break;

                    case "8":
                        System.out.println("Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            } 
        }
    }
}
