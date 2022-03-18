import java.util.LinkedList;
import java.util.Scanner;


public class Unikut {

	private LinkedList<Account> accounts = new LinkedList<Account>();

	public boolean createAccount(Account c) {
		for (Account accounts : accounts) {
			if (c.getUserName().equals(accounts.getUserName())) {
				return false;
			}

		}
		accounts.add(c);
		return true;
	}

	public boolean login(String userName, String senha) {
		for (Account accounts : accounts) {
			if (accounts.getUserName().equals(userName)) {
				if (accounts.getPass().equals(senha)) {
					return true;
				}
			}

		}
		return false;

	}
	
	

}
