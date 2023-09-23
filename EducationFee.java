import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EducationFee {
    private static List<String> bangladeshiInstitutions;

    static {
        // Initialize the list of Bangladeshi institutions
        bangladeshiInstitutions = new ArrayList<>();
        bangladeshiInstitutions.add("Pabna Zilla School");
        bangladeshiInstitutions.add("Govt.Shaheed Bulbul College");
        bangladeshiInstitutions.add("American International University, Bangladesh");
        bangladeshiInstitutions.add("University Of Schollar");
        bangladeshiInstitutions.add("Kuratoli University Of Bangladesh");
        // Add more institutions as needed
	}  
 public static void payFee(BankAccount account) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose a Bangladeshi institution for fee payment:");
        for (int i = 0; i < bangladeshiInstitutions.size(); i++) {
            System.out.println((i + 1) + ". " + bangladeshiInstitutions.get(i));
        }

        System.out.print("Enter the number of the institution you want to pay fees for: ");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= bangladeshiInstitutions.size()) {
            String selectedInstitution = bangladeshiInstitutions.get(choice - 1);

            System.out.print("Enter the fee amount: ");
            double feeAmount = scanner.nextDouble();

            if (account.getBalance() >= feeAmount) {
                // Deduct the fee amount from the account balance
                account.withdraw(feeAmount);

                System.out.println("Payment successful for " + selectedInstitution);
                System.out.println("Remaining balance: $" + account.getBalance());
            } else {
                System.out.println("Insufficient balance. Fee payment failed.");
            }
        } else {
            System.out.println("Invalid choice. Please select a valid institution.");
        }
    }
}