package view.posLoginAutenticado;

import controller.controlador.PrincipalController;



import java.util.Scanner;

public class AmigosView {

    public void amigos(Scanner in, String logado, int op, PrincipalController controllerPrincipal) {
        String login;

        switch (op) {
            case 1:// Adicionar amigo
                in.nextLine();
                System.out.print("Informe o login do usuário a ser adicionado: ");
                login = in.next();
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            boolean pedidoPendente = controllerPrincipal.adicionarAmigo(login, logado);
                            if (pedidoPendente) {
                                System.out.println("");
                                System.out.println("Pedido de amizade enviado. Aguardando aceitação.");
                                System.out.println("");
                            } else {
                                System.out.println("");
                                System.out.println("Pedido de amizade aceito! Vocês agora são amigos.");
                                System.out.println("");
                            }
                        } catch (Exception e) {
                            System.out.println("");
                            System.out.println(e.getMessage());
                            System.out.println("");
                        }
                    }
                });
                t.start();
                break;

            case 2:
                menuListar();// Listar amigos e pedidos de amizade
                op = in.nextInt();
                while (op < 0 || op > 2) {
                    System.out.println("Opção inválida. Tente:");
                    menuListar();
                    op = in.nextInt();
                }
                switch (op) {
                    case 0:// Sair
                        break;
                    case 1:// Exibir lista de amigos
                        Thread t1 = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    String exibirLista = controllerPrincipal.exibirAmigos(logado);
                                    System.out.println("Lista de amigos: ");
                                    System.out.println(exibirLista);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        });
                        t1.run();

                        break;
                    case 2: // Exibir pendentes
                        Thread t2 = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    String exibirLista1 = controllerPrincipal.exibirPendentes(logado);
                                    System.out.println(
                                            "Envie convites para remover a pendência e que vocês sejam amigos!");
                                    System.out.println("Há convites de amizade pendentes de:");
                                    System.out.println(exibirLista1);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        });
                        t2.run();
                        break;
                }
                break;
        }
    }

    public void menuListar() {
        System.out.println("1 - Listar amigos");
        System.out.println("2 - Pedidos pendentes");
        System.out.println("0 - Sair");
    }
}
