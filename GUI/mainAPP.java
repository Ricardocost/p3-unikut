import java.util.Scanner;

public class mainAPP {
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int option = 0;
		Unikut unikutApp = new Unikut();
		Account c;
		System.out.println("Bem vindo a  UNIKUT\nA rede social mais antisocial do mundo !\n");

		do {
			System.out.println("1. Login");
			System.out.println("2. Cadastro");
			System.out.println("3. Sair");
			System.out.println("O que deseja fazer ?");
			option = s.nextInt();
			if (option == 1) {
				System.out.print("Digite seu login:");
				String username = s.next();
				System.out.print("Digite sua senha:");
				String pass = s.next();

				if (unikutApp.login(username, pass) == true) {
					System.out.println("Login com sucesso!");
				} else {
					System.out.println("Usuario ou senha errada!");
				}
			} else if (option == 2) {
				System.out.print("Digite seu login:");
				String username = s.next();
				System.out.print("Digite sua senha:");
				String pass = s.next();
				c = new Account(username, pass);
				if (unikutApp.createAccount(c) == false) {
					System.out.println("Conta já existente!");
				} else {
					System.out.println("Conta Criada com Sucesso!");
				}
			}

		} while (option != 3);
		System.out.println("Volte sempre (ou não)");
		s.close();
	}
}
