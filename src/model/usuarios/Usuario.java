package model.usuarios;

import model.exceptions.*;
import model.mensagens.Mensagem;
import model.mensagens.MensagemSecreta;

import java.util.ArrayList;
import java.util.List;


public class Usuario {

    private String login, senha, nomeDeUsuario;
    private ArrayList<Usuario> amigos, pendentes, matches, meusMatches;
    private ArrayList<Mensagem> recados;
    private ArrayList<String> murais;

    boolean adm;

    public Usuario(String login, String senha, String nomeDeUsuario) {
        this.login = login;
        this.senha = senha;
        this.nomeDeUsuario = nomeDeUsuario;
        amigos = new ArrayList<>();
        pendentes = new ArrayList<>();
        recados = new ArrayList<>();
        murais = new ArrayList<>();
        matches = new ArrayList<>();
        meusMatches = new ArrayList<>();
        adm = false;
    }

    public String getLogin() {
        return login;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void adicionarConta(Usuario u, List<Usuario> lista) {
        lista.add(u);
    }

    public boolean verificaSenha(String senha) {
        return (this.getSenha().equals(senha));
    }

    public boolean adicionar(Usuario u) throws Exception {// Adicionar amigos
        if (this.contem(this.pendentes, u)) {// Se já existe um convite pendente de "u"
            this.adicionarNaLista(u);
            u.adicionarNaLista(this);
            this.removerPendente(u);
            u.removerPendente(this);
            return false;// corresponde à aceitação do pedido de amizade
        } else if (u.contem(u.pendentes, this)) {// Se "u" possui um convite do usuário
            throw exceptionsModelFactory.getException(6, "Já existe um pedido pendente. Aguarde a aceitação do usuário solicitado.");// falha
        } else if (u.contem(u.amigos, this) && this.contem(amigos, u)) {// Se ambos são amigos
            throw exceptionsModelFactory.getException(2,"Você e o usuário solicitado já são amigos.");// falha
        } else {
            u.adicionarPendente(this);
            return true;// Pedido enviado e pendente
        }
    }

    public void adicionarNaLista(Usuario u) {
        amigos.add(u);
    }

    public void adicionarPendente(Usuario u) {
        pendentes.add(u);
    }

    public void removerPendente(Usuario u) {
        pendentes.remove(u);
    }

    public void adicionarRecado(Mensagem recado) {/////
        recados.add(recado);
    }

    public void limparRecados() throws Exception {
        if (isEmpty(recados)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia");
        } else {
            recados.clear();
        }
    }

    @Override // Comparador de usuários utilizando equals com o login
    public boolean equals(Object o) {
        Usuario u = (Usuario) o;
        return this.login.equals(u.login);
    }

    @Override
    public String toString() {
        return getLogin() + "\n";
    }

    public String listaDeAmigos() throws Exception {// Exibir amigos
        String list = "";
        if (isEmpty(amigos)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia");
        } else {
            for (Usuario u : amigos) {
                list += u.toString();
            }
        }
        return list;
    }

    public String listaDePendentes() throws Exception {// Exibir pendentes
        String list = "";
        if (isEmpty(pendentes)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia");
        } else {
            for (Usuario u : pendentes) {
                list += u.toString();
            }
        }
        return list;
    }

    public String listaRecados() throws Exception {// Exibir pendentes
        String list = "";
        if (isEmpty(recados)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia");
        } else {
            for (int i = 0; i < recados.size(); i++) {
                list += (i + 1) + " - " + recados.get(i).toString() + "\n";/////
            }
            return list;
        }
    }

    public String listaMurais() throws Exception {// Exibir murais
        String list = "";
        if (isEmpty(murais)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia.");
        } else {
            for (int i = 0; i < murais.size(); i++) {
                list += murais.get(i) + "\n";
            }
            return list;
        }
    }

    public void adicionarMural(String mural) {
        murais.add(mural);

    }

    public void limparMural() throws Exception {
        if (isEmpty(murais)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia.");
        } else {
            murais.clear();
        }
    }

    public String decodificar(int indice, String palavraChave)
            throws Exception {/////
        if (isEmpty(recados)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia");
        } else if (!indiceValido(indice, recados.size())) {
            throw new IndexOutOfBoundsException("Indice indisponível.");
        } else if (!recados.get(indice).isSecreta()) {
            throw exceptionsModelFactory.getException(5,"Mensagem é comum.");
        } else {
            try {
                return ((MensagemSecreta) recados.get(indice)).verificarAcesso(palavraChave);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public String listaDeMatches() throws Exception {
        String list = "";
        if (isEmpty(matches)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia.");
        } else {
            for (Usuario u : matches) {
                list += u.toString();
            }
            return list;
        }
    }

    public boolean adicionarMatch(Usuario u) throws Exception {
        if (this.contem(meusMatches, u)) {// Se "u" possui um match pendente do usuário
            throw exceptionsModelFactory.getException(4,"Solicitação de match já feita anteriormente.");
        } else if (this.contem(matches, u)) {// Se ambos ja tinham match
            throw exceptionsModelFactory.getException(1,"Match já existente anteriormente.");
        } else {
            meusMatches.add(u);
            if (this.contem(meusMatches, u) && u.contem(u.meusMatches, this)) {// Se já existe um match pendente de "u"
                this.matches.add(u);
                u.matches.add(this);
                meusMatches.remove(u);
                u.meusMatches.remove(this);
                return false;
            }
            return true;
        }
    }

    public String listaDeMeusMatches() throws Exception {
        String list = "";
        if (isEmpty(meusMatches)) {
            throw exceptionsModelFactory.getException(3,"Lista vazia.");
        } else {

            for (Usuario u : meusMatches) {
                list += u.toString();
            }

            return list;
        }
    }

    public boolean indiceValido(int i, int tamanho) {
        return i >= 0 && i < tamanho + 1;
    }

    public boolean contem(ArrayList lista, Usuario u) {
        return lista.contains(u);
    }

    public boolean isEmpty(ArrayList lista) {
        return lista.size() == 0;
    }

    public boolean isAdm() {
        return adm;
    }

}
