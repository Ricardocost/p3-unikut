import java.util.LinkedList;
import java.util.Scanner;

public class Unikut {

	private LinkedList<Account> accounts = new LinkedList<Account>();

	public boolean createAccount(Account c) {
		for (Account accounts : accounts) {
			if (c.getUsername().equals(accounts.getUsername())) {
				return false;
			}

		}
		accounts.add(c);
		return true;
	}

	public boolean login(String userName, String senha) {
		for (Account accounts : accounts) {
			if (accounts.getUsername().equals(userName)) {
				if (accounts.getSenha().equals(senha)) {
					return true;
				}
			}

		}
		return false;

	}

}
