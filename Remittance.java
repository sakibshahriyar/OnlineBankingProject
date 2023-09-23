public class Remittance {
    public static void sendMoney(BankAccount sender, BankAccount receiver, double amount, String selectedCountry) {
        // Check if the sender has sufficient balance to send the money
        if (sender.getBalance() >= amount) {
            double remittanceCharge = calculateRemittanceCharge(selectedCountry);
            
            // Calculate the total amount to send, including the remittance charge
            double totalAmountToSend = amount + remittanceCharge;
            
            // Withdraw the total amount from the sender's account
            sender.withdraw(totalAmountToSend);
            
            // Deposit the original amount into the receiver's account
            receiver.deposit(amount);
            
            System.out.println("Money sent successfully to " + selectedCountry);
            System.out.println("Remittance charge: $" + remittanceCharge);
            System.out.println("Total amount sent: $" + totalAmountToSend);
        } else {
            System.out.println("Insufficient balance to send money.");
        }
    }

    private static double calculateRemittanceCharge(String selectedCountry) {
        // Implement logic to retrieve the remittance charge based on the selected country
        // You can use the countryRemittanceCharges map from the previous example
        // or any other mechanism to fetch the charge for the selectedCountry.
        // For simplicity, we'll return a fixed charge here.
        return 5.0; // Example: $5 charge for all countries
    }
}
