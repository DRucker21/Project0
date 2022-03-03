package banking.app.dao;

import java.util.List;

import banking.app.model.Banking;

public interface CustomerBankingDAO {
	public boolean createAccount(Banking bankAccountUser);
	public Banking checkAccountBalance(String password);
	public boolean withdrawAccountBalance(double withdraw, String accountnamef);
	public boolean depositAccountBalance(double deposit, String accountnamef);
	public boolean transferAccountBalance(int rec, int send, double recBalance, double sendBalance, double balance);
	public boolean doesAccountExist(String accountNamef, String accountNamel);
}
