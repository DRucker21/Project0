package banking.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banking.app.model.Banking;
import banking.utility.DBConnection;

public class EmployeeBankingDAOImpl implements EmployeeBankingDAO {

	Connection con = DBConnection.getConnection();

	public List<Banking> transferMoneyHistory(double bankTransfer) {
		System.out.println("The Amount that was Transfered to" + bankTransfer);
		List<Banking> banks = new ArrayList<Banking>();
		PreparedStatement state = null;
		try {
			state = con.prepareStatement("select AccountName from Banktransfer where AccountID = ?");
			state.setDouble(1, bankTransfer);
			ResultSet result = state.executeQuery();
			while (result.next()) {
				Banking bank = new Banking();
				bank.getAccountid();
				bank.getBalance();
				banks.add(bank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return banks;
	}

	public Boolean approveAccount(int bankAccount){
		PreparedStatement state = null;
		boolean approved = false;
		try {
			state = con.prepareStatement("insert into bank_approved select * from bank_pending where accountid = ?");
			//state = con.prepareStatement("delete from bank_pending where accountid = ?");
			state.setInt(1, bankAccount);
			ResultSet result = state.executeQuery();
			approved = result.next();
		}catch(SQLException e){
			System.out.println("");
		}
		return approved;
	}

	public boolean deleteAccount(int bankAccount) {
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = con.prepareStatement("delete from bank_pending where accountid = ?");
			statement.setInt(1, bankAccount);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}

	public boolean updateAccount(Banking bankAccount) {
		PreparedStatement state = null;
		int rows = 0;
		try {
			state = con.prepareStatement("update Bank set AccountName = ? where accountid = ?");
			state.setString(1, bankAccount.getAccountnamef());
			state.setInt(2, bankAccount.getAccountid());
			state.setDouble(3, bankAccount.getBalance());
			rows = state.executeUpdate();
			System.out.println(rows + " Updated Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}

	public List<Banking> showAccountInformation() {

		List<Banking> banking = new ArrayList<Banking>();

		try {
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery("select * from bank_approved");
			while (result.next()) {
				Banking bank = new Banking();
				bank.setAccountnamef(result.getString(1));
				bank.setAccountnamel(result.getString(2));
				bank.setPassword(result.getString(4));
				bank.setBalance(result.getDouble(3));
				bank.setPin(result.getInt(5));
				bank.setAccountid(result.getInt(6));
				banking.add(bank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return banking;

	}

	public boolean doesAccountExist(int bankAccountUser) {
		boolean accountExist = false;
		PreparedStatement stat;
		try {
			stat = con.prepareStatement("Select * from bank_pending where accountid = ?");
			stat.setInt(1, bankAccountUser);

			ResultSet result = stat.executeQuery();
			accountExist = result.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountExist;
	}

	public List<Banking> allPendingAccounts() {
		System.out.println("Showing All Pending Accounts: ");
		List<Banking> banking = new ArrayList<Banking>();

		try {
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery("select * from bank_pending");
			while (result.next()) {
				Banking bank = new Banking();
				bank.setAccountnamef(result.getString(1));
				bank.setAccountnamel(result.getString(2));
				bank.setPassword(result.getString(4));
				bank.setBalance(result.getDouble(3));
				bank.setPin(result.getInt(5));
				bank.setAccountid(result.getInt(6));
				banking.add(bank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return banking;
	}

}
/*
 * System.out.println("Showing All Pending Accounts: ");
		List<Banking> banking = new ArrayList<Banking>();
		Statement state;
		try {
			state = con.createStatement();
			ResultSet result = state.executeQuery("select * from bank_pending");
			while (result.next()) {
				Banking bank = new Banking();
				bank.setAccountnamef(result.getString(1));
				bank.setAccountnamel(result.getString(2));
				bank.setPassword(result.getString(4));
				bank.setBalance(result.getDouble(3));
				bank.setPin(result.getInt(5));
				bank.setAccountid(result.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
 * */
 