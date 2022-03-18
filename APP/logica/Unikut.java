package logica;

import java.util.ArrayList;

public class Unikut {
	private Conta [] contas ;
	public Conta currentAccount;
	private int indice;
	public Unikut() {
		indice = 0;
		contas = new Conta[1000];

	}

	public Conta criarConta(String login, String nome, String senha) {

		Conta novaConta = new Conta(login, nome, senha);
		contas[indice] = novaConta;
		indice++;
		return novaConta;
	}

	public Conta pesquisa(String login) {
		for(int i =0;i<indice;i++) {
			if(login.equals(contas[i].getLogin())) {
				return contas[i];
			}
		}
		return null;
	}

	public boolean verificaLogin(String login) {
		boolean loginFound = false;
		for (int i = 0; i < indice; i++) {
			if (login.equals(contas[i].getLogin())) {
				loginFound = true;
				break;
			}
		}

		return loginFound;
	}

	public boolean verificaSenha(String login, String senha) {
		Conta conta = this.pesquisa(login);
		if (conta != null) {
			return senha.equals(conta.getSenha());
		}
		return false;
	}
	
	public void alteraNome (String login, String novoNome) {
		Conta conta = this.pesquisa(login);
		conta.setNome(novoNome);
	}
	public void alteraSenha (String login, String novaSenha) {
		Conta conta = this.pesquisa(login);
		conta.setNome(novaSenha);
	}

}
