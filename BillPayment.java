import java.util.Scanner;

public class BillPayment {
    public static void payBill(BankAccount account) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a bill payment option:");
        System.out.println("1. WiFi Bill");
        System.out.println("2. Electricity Bill");
        System.out.println("3. Ticket Bill");
        System.out.println("4. Gas Bill");
        System.out.print("Enter the option number: ");

        int choice = scanner.nextInt();
        String billType = "";

        switch (choice) {
            case 1:
                billType = "WiFi Bill";
                break;
            case 2:
                billType = "Electricity Bill";
                break;
            case 3:
                billType = "Ticket Bill";
                break;
            case 4:
                billType = "Gas Bill";
                break;
            default:
                System.out.println("Invalid option. Bill payment canceled.");
                return;
        }

        System.out.print("Enter the amount for " + billType + ": ");
        double billAmount = scanner.nextDouble();

        if (account.getBalance() >= billAmount) {
            // Deduct the bill amount from the account balance
            account.withdraw(billAmount);
            System.out.println("Payment successful for " + billType);
            System.out.println("Remaining balance: $" + account.getBalance());
        } else {
            System.out.println("Insufficient balance. Bill payment failed.");
        }
    }
}
