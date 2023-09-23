import java.util.Scanner;

public class MobileRecharge {
    public static void recharge(BankAccount account) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for mobile number and recharge amount
        System.out.print("Enter mobile number: ");
        String mobileNumber = scanner.nextLine();

        System.out.print("Enter recharge amount: ");
        double rechargeAmount = scanner.nextDouble();

        // Check if there's enough balance for the recharge
        if (rechargeAmount > 0 && account.getBalance() >= rechargeAmount) {
            account.withdraw(rechargeAmount);
            System.out.println("Recharge successful for mobile number " + mobileNumber);
            System.out.println("Remaining balance: " + account.getBalance());
        } else {
            System.out.println("Recharge failed. Insufficient funds or invalid recharge amount.");
        }
    }
}
