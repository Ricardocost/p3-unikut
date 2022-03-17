
public class Account implements Comparable<Account> {
	private String accountUser, senha;

	public Account(String username, String senha) {
		this.accountUser = username;
		this.setSenha(senha);
	}

	public String getUsername() {
		return accountUser;
	}

	public String setUsername(String username) {
		return this.accountUser = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int compareTo(Account o) {
		return this.accountUser.compareTo(o.accountUser);
	}
}
