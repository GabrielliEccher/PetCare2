package view;

import controller.UsuarioController;
import model.dao.Usuario;
import model.dao.Pessoa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UsuarioController usuarioController = new UsuarioController();

        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Fazer Login \n2 - Criar Conta ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {

            case 1:
                if (usuarioController.conectaBD("petcare")) {

                    do {
                        System.out.println("LOGIN\n");

                        System.out.println("Login (CPF ou E-mail)");
                        String login = sc.next();

                        System.out.println("Senha ");
                        String senha = sc.next();

                        Usuario usuarioLogin = new Usuario(login, senha);

                        if (usuarioController.VerificarLogin("usuario", usuarioLogin)) {

                            System.out.println("Login efetuado com sucesso\n");
                            Usuario usuariologado = new Usuario(login, senha);

                            break;

                        } else {
                            System.out.println("Login e/ou senha inválido.");
                        }

                    } while (true);

                    System.out.println("1 - Agendamentos");
                    System.out.println("2 - Pets ");
                    System.out.println("3 - Sair ");
                    int opcaoMenu = sc.nextInt();
                    sc.nextLine();

                } else {
                    System.out.println("Erro ao conectar ao banco de dados.");
                    }

            case 2:

                if (usuarioController.conectaBD("petcare")) {

                    do {

                        System.out.println("Nome:");
                        String nome = sc.nextLine();

                        System.out.println("CPF: ");
                        String CPF = sc.nextLine();

                        System.out.println("Telefone: ");
                        String telefone = sc.nextLine();

                        System.out.println("Email: ");
                        String email = sc.nextLine();

                        System.out.println("Endereco: ");
                        String endereco = sc.nextLine();

                        System.out.println("CEP: ");
                        String cep = sc.nextLine();

                        System.out.println("Cidade: ");
                        String cidade = sc.nextLine();

                        System.out.println("Senha: ");
                        String senha = sc.nextLine();

                        Pessoa pessoa = new Pessoa(nome, CPF, email, telefone, endereco, cidade, cep);

                        Usuario usuario = new Usuario(CPF, senha);


                        if (usuarioController.VerificarUsuarioExiste("usuario", usuario)) {

                            System.out.println("Usuário já cadastrado! Digite novamente.");

                        } else {

                            String NovoUsuario = usuarioController.InserirUsuario("usuario", usuario, pessoa);
                            System.out.println(NovoUsuario);

                            break;

                        }

                    } while (true);

                } else {
                    System.out.println("Erro ao conectar ao banco de dados.");
                }

        }


    }
}
