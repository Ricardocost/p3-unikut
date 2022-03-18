import java.util.Scanner;

public class mainAPP {
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int option = 0;
		Unikut unikutApp = new Unikut();
		Account c;
		String username, pass, name;
		System.out.println("Bem vindo a  UNIKUT\nA rede social mais antisocial do mundo !\n");

		do {
			exibeMenuStart();
			option = s.nextInt();
			s.nextLine();
			switch (option) {
			case 1:
				System.out.print("Digite seu login:");
				username = s.nextLine();

				System.out.print("Digite sua senha:");
				pass = s.next();
				s.nextLine();

				System.out.print("Digite seu nome. Caso não queira, será chamado de convidado: ");
				name = s.nextLine();

				if (name == "") {
					name = "Convidado";
				}
				c = new Account(username, pass, name);
				if (unikutApp.createAccount(c) == false) {
					System.out.println("Conta já existente!");
				} else {
					System.out.println("Conta Criada com Sucesso!");
				}
				break;
			case 2:
				System.out.print("Digite seu login: ");
				username = s.nextLine();
				;
				System.out.print("Digite sua senha: ");
				pass = s.next();

				if (unikutApp.login(username, pass) == true) {
					System.out.println("Login com sucesso!");
					do {
						exibeMenuUsuario();
						option = s.nextInt();
						s.nextLine();
						switch (option) {
						case 1:
							// falta implementar o metodo de alteração de dados
							System.out.print("Digite seu novo login: ");
							username = s.nextLine();
							;
							System.out.print("Digite sua nova senha: ");
							pass = s.next();
							s.nextLine();
							System.out.print("Digite seu novo nome: ");
							name = s.nextLine();
							;
							break;

						}
					} while (option != 0);

				} else {
					System.out.println("Usuario ou senha errada!");
				}
				break;
			}

		} while (option != 0);
		System.out.println("Volte sempre (ou não)");
		s.close();

	}

	public static void exibeMenuStart() {
		System.out.println("1. Cadastro Usuario");
		System.out.println("2. Login");
		System.out.println("0. Sair");
		System.out.println("O que deseja fazer ?");
	}

	public static void exibeMenuUsuario() {
		System.out.println("1. Editar peril");
		System.out.println("0. Sair");
		System.out.println("O que deseja fazer ?");
	}

}
