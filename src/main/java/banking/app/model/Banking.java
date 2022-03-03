package banking.app.model;

public class Banking {
	private int accountid;
	private double balance;
	private String accountnamef;
	private String accountnamel;
	private String password;
	private int pin;
	
	public Banking() {
	
	}

	public Banking(int accountid, double balance, String accountnamef, String accountnamel, String password, int pin) {
		super();
		this.accountid = accountid;
		this.balance = balance;
		this.accountnamef = accountnamef;
		this.accountnamel = accountnamel;
		this.password = password;
		this.pin = pin;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountnamef() {
		return accountnamef;
	}

	public void setAccountnamef(String accountnamef) {
		this.accountnamef = accountnamef;
	}

	public String getAccountnamel() {
		return accountnamel;
	}

	public void setAccountnamel(String accountnamel) {
		this.accountnamel = accountnamel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountid;
		result = prime * result + ((accountnamef == null) ? 0 : accountnamef.hashCode());
		result = prime * result + ((accountnamel == null) ? 0 : accountnamel.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + pin;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banking other = (Banking) obj;
		if (accountid != other.accountid)
			return false;
		if (accountnamef == null) {
			if (other.accountnamef != null)
				return false;
		} else if (!accountnamef.equals(other.accountnamef))
			return false;
		if (accountnamel == null) {
			if (other.accountnamel != null)
				return false;
		} else if (!accountnamel.equals(other.accountnamel))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pin != other.pin)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Banking [accountid=" + accountid + ", balance=" + balance + ", accountnamef=" + accountnamef
				+ ", accountnamel=" + accountnamel + ", password=" + password + ", pin=" + pin + "]";
	}

	

	
}
