
public class Account implements Comparable<Account> {
	private String accountUser;
	private String pass;
	private String name;

	public Account(String username, String pass, String name) {
		this.accountUser = username;
		this.pass = pass;
		this.name = name;

	}

	public String getUserName() {
		return accountUser;
	}

	public String setUserName(String username) {
		return this.accountUser = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Account o) {
		return this.accountUser.compareTo(o.accountUser);
	}
}
