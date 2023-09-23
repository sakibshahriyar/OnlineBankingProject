import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount userAccount = new BankAccount("123456789"); // Replace with an actual account number

        while (true) {
            System.out.println("\nWelcome to Online Banking!");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Remittance");
            System.out.println("4. Mobile Recharge");
            System.out.println("5. Pay Education Fee");
            System.out.println("6. Pay Bill");
            System.out.println("7. Check Balance");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    userAccount.deposit(depositAmount);
                    System.out.println("Deposit successful. Current balance: " + userAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    userAccount.withdraw(withdrawalAmount);
                    System.out.println("Withdrawal successful. Current balance: " + userAccount.getBalance());
                    break;
                case 3:
                    initiateRemittance(userAccount);
                    break;
                case 4:
                     MobileRecharge.recharge(userAccount);
                    break;
                  
                case 5:
                    EducationFee.payFee(userAccount);
                    break;
                case 6:
                    BillPayment.payBill(userAccount);
                    break;
                case 7:
                    System.out.println("Current balance: " + userAccount.getBalance());
                    break;
                case 8:
                    System.out.println("Thank you for using Online Banking. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void initiateRemittance(BankAccount userAccount) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Initiating Remittance:");
        System.out.print("Enter recipient's account number: ");
        String receiverAccountNumber = scanner.next();
        BankAccount receiverAccount = new BankAccount(receiverAccountNumber); // Replace with actual account retrieval logic

        System.out.print("Enter the amount to send: ");
        double amountToSend = scanner.nextDouble();

        System.out.print("Enter the recipient's country: ");
        String selectedCountry = scanner.next();

        Remittance.sendMoney(userAccount, receiverAccount, amountToSend, selectedCountry);
    }
}
