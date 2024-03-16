public class CheckingAccount extends Account {

    private double overdraftProtection;

    public CheckingAccount(double balance, double protect) {
        super(balance);
        this.overdraftProtection = protect;
    }

    public CheckingAccount(double balance) {
        this(balance, -1.0); // Default overdraft protection set to -1.0
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount >= 0.0) {
            balance -= amount;
            return true;
        } else {
            double overdraftNeeded = amount - balance;
            if (overdraftProtection == -1.0 || overdraftProtection < overdraftNeeded) {
                return false; // Withdrawal failed
            } else {
                balance = 0.0;
                overdraftProtection -= overdraftNeeded;
                return true; // Withdrawal successful
            }
        }
    }
}