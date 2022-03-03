package com.Banking.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import banking.app.dao.EmployeeBankingDAO;
import banking.app.dao.EmployeeBankingDAOImpl;
import banking.app.dao.CustomerBankingDAO;
import banking.app.dao.CustomerBankingDAOImpl;
import banking.app.model.Banking;

public class BankingApp {
	Scanner sc = new Scanner(System.in);
	int selectionC = 0;
	int selectionE = 0;
	int loginChoice = 0;
	int selectionSU = 0;
	EmployeeBankingDAO EbankingDAO = new EmployeeBankingDAOImpl();
	CustomerBankingDAO CbankingDAO = new CustomerBankingDAOImpl();
	Banking bank = new Banking();
	List<Banking> banking = new ArrayList<Banking>();

	public void startBankingApp() throws IOException {
		int accountid = 0;
		String password = null;
		String accountnamef = null;
		String accountnamel = null;
		double amount = 0;
		int send = 0;
		int rec = 0;
		double transfer = 0;
		int pin = 0;
		double balance = 0;
		while (true) {
			System.out.println("***********************************************");
			System.out.println("WELCOME TO REVATURE BANK LOGIN/SIGNUP");
			System.out.println("Please Select From The Following Choices (1-4)");
			System.out.println("1. LogIn Customer Banking");
			System.out.println("2. SignUp Customer Banking");
			System.out.println("3. Employee Section");
			System.out.println("4. Exit Banking App");
			System.out.println("***********************************************");

			System.out.println("Selection (1-4): ");
			loginChoice = sc.nextInt();

			switch (loginChoice) {
			case 1:

				while (true) {
					System.out.println("***********************************************");
					System.out.println("WELCOME TO REVATURE BANK");
					System.out.println("Please Select From The Following Choices (1-5)");
					System.out.println("1. Withdraw");
					System.out.println("2. Deposit");
					System.out.println("3. Transfer");
					System.out.println("4. Check Account Balance");
					System.out.println("5. Exit Account");
					System.out.println("***********************************************");

					System.out.println("Selection (1-5): ");
					selectionC = sc.nextInt();

					switch (selectionC) {
					case 1:
						System.out.println("*You Have Selected Account Withdraw*");
						System.out.println("Please Enter Amount To Withdraw");
						amount = sc.nextDouble();
						CbankingDAO.withdrawAccountBalance(balance, accountnamef);
						System.out.println("New Balance"+balance);
						System.out.println("\n***********************************************\n");
						break;
					case 2:
						System.out.println("*You Have Selected Account Deposit*");
						System.out.println("Desired Amount To Deposit: ");
						amount = sc.nextDouble();
						CbankingDAO.depositAccountBalance(balance, accountnamef);
						System.out.println("New Balance"+balance);
						System.out.println("\n***********************************************\n");
						break;
					case 3:
						System.out.println("*You Have Selected Account Transfer*");
						System.out.println("Please Enter First AccountID (Sender): ");
						send = sc.nextInt();
						System.out.println("Please Enter Second AccountID (Receiver): ");
						rec = sc.nextInt();
						System.out.println("Enter Amount To Transfer");
						transfer = sc.nextDouble();
						if (CbankingDAO.transferAccountBalance(rec, send, rec, send, pin)) {
							System.out.println("Transfer Succsseful");
						} else {
							System.out.println("");
						}
						System.out.println("\n***********************************************\n");
						break;
					case 4:
						System.out.println("*You Have Selected Check Account Balance*");
						System.out.println("Please Enter Bank Password To Verify");
						password = sc.next();
						bank = CbankingDAO.checkAccountBalance(password);
						if (bank.equals(accountnamef)) {
							allBankDetails((List<Banking>) bank);
						} else {
							System.out.println("No Account Found");
						}
						System.out.println("\n***********************************************\n");
						break;
					case 5:
						System.out.println("**Thank You For Banking With Revature Bank**");
						System.exit(loginChoice);
					default:
						System.out.println("Your Selection Was not (1-5)\nPlease Try Again\n");
						break;
					}
				}
			case 2:
				while (true) {
					System.out.println("***********************************************");
					System.out.println("WELCOME TO CUSTOMER SIGNUP");
					System.out.println("Please Select 1 or 2 ");
					System.out.println("1. Create Bank Account");
					System.out.println("2. Exit SignUp Page");
					System.out.println("***********************************************");

					System.out.println("Selection (1 or 2): ");
					selectionSU = sc.nextInt();
					switch (selectionSU) {
					case 1:
						System.out.println("*You Have Selected To Create A New Account With Revature Bank*");
						System.out.println("Please Enter First Name: ");
						accountnamef = sc.next();
						System.out.println("Please Enter Last Name: ");
						accountnamel = sc.next();
						if (CbankingDAO.doesAccountExist(accountnamef, accountnamel)) {
							System.out.println("Account With " + accountnamef + " " + accountnamel
									+ " Already Exist in The System");
							continue;
						}
						System.out.println("Enter 4 digit Pin (Cannot Begin With 0): ");
						pin = sc.nextInt();
						if (pin < 999) {
							System.out.println("Has To Be a Four(4) Digit Pin");
						} else if (pin > 9999) {
							System.out.println("Pin Cannot Have More Than Four(4) Digits");
						} else {
							System.out.println("Enter Password For Account: ");
						}
						password = sc.next();
						System.out.println("Please Enter Starting Balance For Account ($25 Minimum/$5000 Max)");
						balance = sc.nextDouble();
						if (balance < 25) {
							System.out.println("Amount Cannot Be Less Than ($25.00)");
						} else if (balance > 5000) {
							System.out.println("Amount Cannot Be Greater than ($5000.00) Daily");
						} else {
							System.out.println("Thank you for Creating An Account With Revature Bank " + accountnamef
									+ " " + accountnamel);
						}
						Banking bank = new Banking(accountid, balance, accountnamef, accountnamel, password, pin);
						CbankingDAO.createAccount(bank);
						System.out.println("\n***********************************************\n");
						break;
					case 2:
						System.out.println("**Thank You For Using Revature Bank**");
						System.exit(loginChoice);
					default:
						System.out.println("Selection Has to be One(1) or Two(2)\n");
						break;
					}
				}
			case 3:
				while (true) {
					System.out.println("***********************************************");
					System.out.println("WELCOME TO EMPLOYEE SECTION");
					System.out.println("Please Select From The Following Choices (1-4)");
					System.out.println("1. Approve/Decline Pending Accounts");
					System.out.println("2. Check All Existing Accounts");
					System.out.println("3. Delete Existing Accounts");
					System.out.println("4. Exit Employee Menu");
					System.out.println("***********************************************");

					System.out.println("Selection (1-4): ");
					selectionE = sc.nextInt();

					switch (selectionE) {
					case 1:
						System.out.println("*You Have Selected Approve/Decline Pending Accounts*");
						System.out.println("The Following Accounts Can Be Approved Or Denied: ");
						banking = EbankingDAO.allPendingAccounts();
						if (banking.size() == 0) {
							System.out.println("No Accounts");
							continue;
						}
						allBankDetails(banking);
						System.out.println("Please Select The Account ID That You Want To Approve: ");
						accountid = sc.nextInt();
						if (!EbankingDAO.doesAccountExist(accountid)) {
							System.out.println("ID:" + accountid + " Isn't In The System");
							continue;
						}
						bank = new Banking(accountid, balance, accountnamef, accountnamel, password, pin);
						EbankingDAO.approveAccount(accountid);
						System.out.println("Bank Account With ID: " + accountid + " Has Successfully Been Approved");
						System.out.println("\n***********************************************\n");
						break;
					case 2:
						System.out.println("*You Have Selected Check All (Approved) Accounts*");
						banking = EbankingDAO.showAccountInformation();
						if (banking.size() == 0) {
							System.out.println("No Bank Accounts Opened");
							continue;
						}
						System.out.println("All Existing Accounts Are Listed Below: ");
						allBankDetails(banking);
						System.out.println("\n***********************************************\n");
						break;
					case 3:
						System.out.println("*You Have Selected Delete Existing Accounts*");
						System.out.println("Which Account Do You Wish to Delete (By ID): ");
						accountid = sc.nextInt();
						if (EbankingDAO.doesAccountExist(accountid)) {
							EbankingDAO.deleteAccount(accountid);
							System.out.println("Account With ID : " + accountid + " Has Been Deleted From the System");
						} else {
							System.out.println("The Account With The ID Of: " + accountid + " Does Not Exist");
						}
						System.out.println("\n***********************************************\n");
						break;
					case 4:
						System.out.println("**Thank You For Using The Employee Section**");
						System.exit(loginChoice);
					default:
						System.out.println("Your Selection Was not (1-4)\nPlease Try Again\n");
						break;
					}
				}
			case 4:
				System.out.println("Thank You For Using Revature Bank");
				System.exit(0);
			default:
				System.out.println("Your Selection Was not (1-4)\nPlease Try Again\n");
				break;
			}
		}
	}

	public void allBankDetails(List<Banking> banks) {
		Iterator<Banking> iter = banks.iterator();
		while (iter.hasNext()) {
			Banking temp = iter.next();
			System.out.println(temp);
		}
	}
}
