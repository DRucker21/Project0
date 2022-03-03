package banking.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import banking.app.model.Banking;
import banking.utility.DBConnection;

public class CustomerBankingDAOImpl implements CustomerBankingDAO {

	Connection con = DBConnection.getConnection();

	public boolean createAccount(Banking bankAccountUser) {
		PreparedStatement state = null;
		int row = 0;
		try {
			state = con.prepareStatement("insert into bank_pending values(?,?,?,?,?)");
			state.setString(1, bankAccountUser.getAccountnamef());
			state.setString(2, bankAccountUser.getAccountnamel());
			state.setInt(5, bankAccountUser.getPin());
			state.setString(4, bankAccountUser.getPassword());
			state.setDouble(3, bankAccountUser.getBalance());
			state.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row == 0)
			return false;
		else
			return true;
	}

	public Banking checkAccountBalance(String password) {
		Banking banks = new Banking();
		PreparedStatement state;
		try {
			Banking bank = new Banking();
			state = con.prepareStatement("select balance from bank_approved where password = ?");
			state.setString(1, password);
			ResultSet result = state.executeQuery();
			while (result.next()) {
				bank.setBalance(result.getDouble(1));
				bank.setAccountid(result.getInt(2));
				bank.setAccountnamef(result.getString(3));
				bank.setAccountnamel(result.getString(4));
				bank.setPassword(result.getString(5));
				bank.setPin(result.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return banks;
	}

	public boolean doesAccountExist(String accountnamef, String accountnamel) {
		boolean accountExist = false;
		PreparedStatement state;
		try {
			state = con.prepareStatement("select * from bank_pending where accountnamef=? and accountnamel=?");
			state.setString(1, accountnamef);
			state.setString(2, accountnamel);

			ResultSet result = state.executeQuery();
			accountExist = result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountExist;
	}

	public boolean withdrawAccountBalance(double withdraw, String accountnamef) {
		PreparedStatement state;

		try {
			state = con.prepareStatement("select balance - ? from bank_approved where accountid = ?");
			state.setDouble(1, withdraw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean depositAccountBalance(double deposit, String accountnamef) {
		PreparedStatement state;

		try {
			state = con.prepareStatement("select balance + ? from bank_approved where accountid = ?");
			state.setDouble(1, deposit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean transferAccountBalance(int rec, int send, double recBalance, double sendBalance, double balance) {
		
		try {
			CallableStatement state = con.prepareCall("Call transferAmount(?,?,?,?,?)");
			state.setInt(1, send);
			state.setInt(2, rec);
			state.setDouble(3, balance);

			state.registerOutParameter(4, Types.DOUBLE);
			state.setDouble(4, sendBalance);

			state.registerOutParameter(5, Types.DOUBLE);
			state.setDouble(5, recBalance);
			
			state.execute();
			
			sendBalance = state.getInt(4);
			recBalance = state.getInt(5);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Senders Balance: "+(float)sendBalance);
		System.out.println("Receivers Balace: "+(float)recBalance);
		return true;
	}
}
