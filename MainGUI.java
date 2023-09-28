import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private BankAccount userAccount;
    private boolean isLoggedIn = false; // Track user login status

    private JLabel balanceLabel;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton remittanceButton;
    private JButton mobileRechargeButton;
    private JButton educationFeeButton;
    private JButton billPaymentButton;
    private JButton signInButton;
    private JButton signOutButton;

    public MainGUI() {
        // Initialize the user account (replace with your actual account logic)
        userAccount = new BankAccount("123456789");

        // Set up the JFrame
        setTitle("nowpay");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create GUI components
        balanceLabel = new JLabel();
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        remittanceButton = new JButton("Remittance");
        mobileRechargeButton = new JButton("Mobile Recharge");
        educationFeeButton = new JButton("Pay Education Fee");
        billPaymentButton = new JButton("Pay Bill");
        signInButton = new JButton("Sign In");
        signOutButton = new JButton("Sign Out");
        signOutButton.setEnabled(false); // Initially disabled

        // Add action listeners to the buttons
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the user is logged in
                if (!isLoggedIn) {
                    JOptionPane.showMessageDialog(null, "Please sign in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Implement deposit logic here
                double depositAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter deposit amount:"));
                userAccount.deposit(depositAmount);
                updateBalanceLabel();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the user is logged in
                if (!isLoggedIn) {
                    JOptionPane.showMessageDialog(null, "Please sign in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Implement withdraw logic here
                double withdrawAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter withdrawal amount:"));
                if (userAccount.getBalance() >= withdrawAmount) {
                    userAccount.withdraw(withdrawAmount);
                    updateBalanceLabel();
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance.");
                }
            }
        });

        remittanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the user is logged in
                if (!isLoggedIn) {
                    JOptionPane.showMessageDialog(null, "Please sign in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Implement remittance logic here
                String recipientAccountNumber = JOptionPane.showInputDialog("Enter recipient's account number:");
                double amountToSend = Double.parseDouble(JOptionPane.showInputDialog("Enter amount to send:"));
                String selectedCountry = (String) JOptionPane.showInputDialog(null, "Select Country:",
                        "Country Selection", JOptionPane.QUESTION_MESSAGE, null,
                        new String[]{"Bangladesh", "India", "Pakistan"}, "Bangladesh");

                // Implement logic to find the recipient's BankAccount based on the account number
                BankAccount recipientAccount = findRecipientAccount(recipientAccountNumber);

                if (recipientAccount != null) {
                    if (userAccount.getBalance() >= amountToSend) {
                        double remittanceCharge = calculateRemittanceCharge(selectedCountry);

                        // Calculate the total amount to send, including the remittance charge
                        double totalAmountToSend = amountToSend + remittanceCharge;

                        // Withdraw the total amount from the sender's account
                        userAccount.withdraw(totalAmountToSend);

                        // Deposit the original amount into the receiver's account
                        recipientAccount.deposit(amountToSend);

                        updateBalanceLabel();

                        JOptionPane.showMessageDialog(null, "Money sent successfully to " + selectedCountry
                                + "\nRemittance charge: $" + remittanceCharge
                                + "\nTotal amount sent: $" + totalAmountToSend, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance to send money.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Recipient account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mobileRechargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the user is logged in
                if (!isLoggedIn) {
                    JOptionPane.showMessageDialog(null, "Please sign in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Implement mobile recharge logic here
                String[] operators = {"TeleTalk", "Robi", "Airtel"}; // Replace with actual operators
                String selectedOperator = (String) JOptionPane.showInputDialog(null, "Select Operator:",
                        "Operator Selection", JOptionPane.QUESTION_MESSAGE, null, operators, operators[0]);

                String phoneNumber = JOptionPane.showInputDialog("Enter phone number:");

                double rechargeAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter recharge amount:"));
                if (userAccount.getBalance() >= rechargeAmount) {
                    userAccount.withdraw(rechargeAmount);
                    updateBalanceLabel();
                    JOptionPane.showMessageDialog(null, "Mobile recharge successful with " + selectedOperator + "\nPhone Number: " + phoneNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance for mobile recharge.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        billPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the user is logged in
                if (!isLoggedIn) {
                    JOptionPane.showMessageDialog(null, "Please sign in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Implement bill payment logic here
                String[] billTypes = {"WiFi", "Gas", "Electricity"}; // Replace with actual bill types
                String selectedBillType = (String) JOptionPane.showInputDialog(null, "Select Bill Type:",
                        "Bill Type Selection", JOptionPane.QUESTION_MESSAGE, null, billTypes, billTypes[0]);

                String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                String selectedMonth = (String) JOptionPane.showInputDialog(null, "Select Month:",
                        "Month Selection", JOptionPane.QUESTION_MESSAGE, null, months, months[0]);

                double billAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter bill amount:"));
                if (userAccount.getBalance() >= billAmount) {
                    userAccount.withdraw(billAmount);
                    updateBalanceLabel();
                    JOptionPane.showMessageDialog(null, "Bill payment successful for " + selectedBillType + "\nMonth: " + selectedMonth, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance for bill payment.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        educationFeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the user is logged in
                if (!isLoggedIn) {
                    JOptionPane.showMessageDialog(null, "Please sign in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Implement education fee payment logic here
                String[] universities = {"AIUB", "University Of Schollar", "EastWest "}; // Replace with actual universities
                String selectedUniversity = (String) JOptionPane.showInputDialog(null, "Select University:",
                        "University Selection", JOptionPane.QUESTION_MESSAGE, null, universities, universities[0]);

                String[] semesters = {"Fall", "Summer", "Spring"}; // Replace with actual semesters
                String selectedSemester = (String) JOptionPane.showInputDialog(null, "Select Semester:",
                        "Semester Selection", JOptionPane.QUESTION_MESSAGE, null, semesters, semesters[0]);

                String studentId = JOptionPane.showInputDialog("Enter Student ID:");

                double feeAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter education fee amount:"));
                if (userAccount.getBalance() >= feeAmount) {
                    userAccount.withdraw(feeAmount);
                    updateBalanceLabel();
                    JOptionPane.showMessageDialog(null, "Education fee payment successful for " + selectedUniversity + "\nSemester: " + selectedSemester + "\nStudent ID: " + studentId, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance for education fee payment.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement sign-in logic here (you can use a username and password dialog)
                String username = JOptionPane.showInputDialog("Enter your username:");
                String password = JOptionPane.showInputDialog("Enter your password:");

                // Validate the user (replace this with your authentication logic)
                if ("nowpay".equals("nowpay") && "1234".equals(password)) {
                    isLoggedIn = true;
                    updateBalanceLabel();
                    signInButton.setEnabled(false);
                    signOutButton.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Sign-in successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement sign-out logic here
                isLoggedIn = false;
                userAccount = null; // Clear user's bank account information
                balanceLabel.setText(""); // Clear balance label
                signInButton.setEnabled(true);
                signOutButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Sign-out successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(remittanceButton);
        buttonPanel.add(mobileRechargeButton);
        buttonPanel.add(educationFeeButton);
        buttonPanel.add(billPaymentButton);

        // Add components to the JFrame
        setLayout(new BorderLayout());
        add(balanceLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(signInButton, BorderLayout.WEST);
        add(signOutButton, BorderLayout.EAST);
    }

    private void updateBalanceLabel() {
        if (isLoggedIn) {
            balanceLabel.setText("Current balance: $" + userAccount.getBalance());
        } else {
            balanceLabel.setText("Please sign in to view balance.");
        }
    }

    private BankAccount findRecipientAccount(String accountNumber) {
        if (accountNumber.equals("987654321")) {
            return new BankAccount("987654321");
        } else {
            return null; // Account not found
        }
    }

    private double calculateRemittanceCharge(String selectedCountry) {
        return 5.0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUI mainGUI = new MainGUI();
                mainGUI.setVisible(true);
            }
        });
    }
}
