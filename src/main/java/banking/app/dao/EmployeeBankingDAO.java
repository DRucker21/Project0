package banking.app.dao;

import java.util.List;

import banking.app.model.Banking;

public interface EmployeeBankingDAO {
	public List <Banking> transferMoneyHistory(double banktransfer);
	public Boolean approveAccount(int bankAccount);
	public boolean deleteAccount(int bankAccountUser);
	public boolean updateAccount(Banking bankAccountUser);
	public List <Banking> showAccountInformation();
	public List <Banking> allPendingAccounts();
	public boolean doesAccountExist(int bankAccountUser);
}
