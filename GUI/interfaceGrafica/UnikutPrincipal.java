package interfaceGrafica;

import java.util.Scanner;

import logica.Unikut;

public class UnikutPrincipal {
	static Unikut u = new Unikut();

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("BEM VINDO A UNIKUT!\n");

		int op = 0;
		String login, nome, senha, confirmaSenha;
		do {
			menuStart();
			op = s.nextInt();
			s.nextLine();
			switch (op) {
			case 1:

				System.out.print("Criar Login: ");
				login = s.nextLine();
				while (login.length() == 0) {
					System.out.print("Login não pode ser vazio. Digite Novamente: ");
					login = s.nextLine();
				}

				while (u.verificaLogin(login)) {
					System.out.print("Login Ja Existente. Digite Novamente: ");
					login = s.nextLine();
				}

				System.out.print("Criar Apelido (Opcional): ");
				nome = s.nextLine();

				System.out.print("Crie uma senha: ");
				senha = s.nextLine();

				while (senha.length() == 0) {
					System.out.print("Senha nao pode ser vazio. Digite Novamente: ");
					senha = s.nextLine();
				}

				System.out.print("Confirme sua senha: ");
				confirmaSenha = s.nextLine();

				while (!senha.equals(confirmaSenha)) {
					System.out.print("Senhas nao estao compativeis. Digite novamente a senha: ");
					senha = s.next();
					System.out.print("Verifique a senha: ");
					confirmaSenha = s.next();
				}

				if (nome.isEmpty() == true) {
					nome = "Convidado!";
				}

				System.out.println("CONTA CRIADA COM SUCESSO!!!");

				u.currentAccount = u.criarConta(login, nome, senha);
				break;

			case 2:
				System.out.print("Login: ");

				login = s.nextLine();
				while (!u.verificaLogin(login)) {
					System.out.print("Login não existente. Digite outro Login: ");
					login = s.nextLine();
				}
				System.out.print("Senha: ");
				senha = s.nextLine();
				while (!u.verificaSenha(login, senha)) {
					System.out.print("Senha incorreta. Digite novamente: ");
					senha = s.nextLine();
				}

				u.currentAccount = u.pesquisa(login);

				System.out.println("BEM VINDO " + u.currentAccount.getNome() + "!");
				do {
					menuLogado();
					op = s.nextInt();
					s.nextLine();
					switch (op) {
					case 1: // Editar perfil
						do {

							menuEditar();
							op = s.nextInt();
							s.nextLine();
							switch (op) {
							case 1: // Alterar Apelido
								System.out.println("Novo Apelido:");
								String newNickName = s.nextLine();
								u.alteraNome(u.currentAccount.getLogin(), newNickName);
								System.out.println("\nApelido alterado com sucesso!");
								break;

							case 2: // Alterar Senha
								System.out.println("Nova Senha:");
								String newPassword = s.nextLine();
								u.alteraSenha(u.currentAccount.getLogin(), newPassword);
								System.out.println("\nSenha alterada com sucesso!");
								break;

							case 3:
								System.out.println("Voltando...");
								break;

							default:
								System.out.println("Comando inválido");
							}

						} while (op != 3);

					case 2: // Exibir amigos
						// mostrarAmigos();
						break;
					case 3:// Adicionar amigos
							// menuAddAmigos();
						break;

					case 4: // Enviar recados
						// menuRecados();
						break;
					case 5:
						System.out.println("Voltando...");
						break;

					default:
						System.out.println("Comando inválido");
					}

				} while (op != 5);
				System.out.println("Ate a proxima " + u.currentAccount.getNome() + "!");
				menuStart();

			case 3:
				System.out.println("Voltando...");
				break;

			default:
				System.out.println("Comando inválido");
			}
		} while (op != 3);
		System.out.println("Obrigado por usar nossa rede social!!!");
	}

	public static void menuStart() {
		System.out.println("1 -> Deseja criar uma conta:?");
		System.out.println("2 -> Entrar na sua conta");
		System.out.println("3 -> Sair");
		System.out.println("Informe a opção desejada: ");
		System.out.println("");
	}

	public static void menuLogado() {
		System.out.println("Olá " + u.currentAccount.getNome() + ".");
		System.out.println("\nMenu de alteração de conta ");
		System.out.println("1 -> Editar Perfil");
		System.out.println("2 -> Ver Amigos");
		System.out.println("3 -> Adicionar Amigos");
		System.out.println("4 -> Recados");
		System.out.println("5 -> Voltar menu anterior");
		System.out.println("Informe a opção desejada: ");
		System.out.println("");
	}

	public static void menuEditar() {
		System.out.println("\nOla " + u.currentAccount.getNome() + ".");
		System.out.println("\nMenu Alterar Conta");
		System.out.println("1 => Editar Apelido");
		System.out.println("2 => Editar Senha");
		System.out.println("3 => Voltar ao menu anterior");
		System.out.print("=>");

	}
}
