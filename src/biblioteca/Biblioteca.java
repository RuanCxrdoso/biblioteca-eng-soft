package src.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import src.funcionalidades.Emprestimo;
import src.funcionalidades.Exemplar;
import src.funcionalidades.Reserva;
import src.funcionalidades.usuarios.AlunoGraduacao;
import src.funcionalidades.usuarios.AlunoPosGraduacao;
import src.funcionalidades.usuarios.Professor;
import src.interfaces.IEmprestimo;
import src.interfaces.ILivro;
import src.interfaces.IReserva;
import src.interfaces.IUsuario;

public class Biblioteca {
    private Biblioteca() {};

    private static Biblioteca biblioteca;
    private List<IUsuario> usuarios = new ArrayList<IUsuario>();
    private List<ILivro> livros = new ArrayList<ILivro>();

    public static Biblioteca obterInstanciaUnica() {
        if (biblioteca == null) {
            biblioteca = new Biblioteca();
        }

        return biblioteca;
    }

    public void inicializarBiblioteca() {
        usuarios.add(new AlunoGraduacao("123", "João da Silva"));
        usuarios.add(new AlunoPosGraduacao("456", "Luiz Fernando Rodrigues"));
        usuarios.add(new AlunoGraduacao("789", "Pedro Paulo"));
        usuarios.add(new Professor("100", "Carlos Lucena"));

        livros.add(new Livro("100", "Engenharia de Software","Addison Wesley", "6°", "Ian Sommerville", 2000));
        livros.add(new Livro("101", "UML - Guia do Usuário", "Campus","7°", "Grady Booch,James Rumbaugh,Ivar Jacobson", 2000));
        livros.add(new Livro("200", "Code Complete", "Microsoft Press","2°","Steve McConnell", 2014));
        livros.add(new Livro("201", "Agile Software Development,Principles, Patterns and Practices", "Prentice Hall ","1°","Robert Martin", 2002));
        livros.add(new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison Wesley Professional ","1°","Martin Fowler", 1999));
        livros.add(new Livro("301", "Software Metrics: A rigorous and Practical Approach", "CRC Press","3°","Martin Fowler", 2014));
        livros.add(new Livro("400", "Design Patterns: Element of Reusable Object-Oriented Software", "Addison Wesley Professional","1°","Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 1994));
        livros.add(new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison Wesley Professional","3°","Martin Fowler", 2003));
        
        livros.get(0).addExemplar(new Exemplar("01", livros.get(0)));
        livros.get(0).addExemplar(new Exemplar("02", livros.get(0)));
        livros.get(1).addExemplar(new Exemplar("03", livros.get(1)));
        livros.get(2).addExemplar(new Exemplar("04", livros.get(2)));
        livros.get(3).addExemplar(new Exemplar("05", livros.get(3)));
        livros.get(4).addExemplar(new Exemplar("06", livros.get(4)));
        livros.get(4).addExemplar(new Exemplar("07", livros.get(4)));
        livros.get(6).addExemplar(new Exemplar("08", livros.get(6)));
        livros.get(6).addExemplar(new Exemplar("09", livros.get(6)));
        livros.get(6).addExemplar(new Exemplar("10", livros.get(6)));   
    }

    public void solicitarEmprestimo(String codigoUsuario, String codigoLivro) {
        IUsuario usuario = obterUsuario(codigoUsuario);
        ILivro livro = obterLivro(codigoLivro);

        String mensagem;

        if (livro.temExemplarDisponivel()) {
            if (usuario.solicitarEmprestimo(livro)) {
                mensagem = "\nSolicitação de empréstimo  do livro " + livro.obterTitulo() + " para o usuário " + usuario.obterNome() + "finalizada com sucesso!";
  
                Console.mostrarMensagem(mensagem);
            }
        } else {
            mensagem = "\nEmpréstimo do livro " + livro.obterTitulo() + " para o usuário " + usuario.obterNome() + " falhou devido a falta de exemplar disponivel!";

            Console.mostrarMensagem(mensagem);
        }
    }
  
    public void reservarLivro(String codigoUsuario, String codigoLivro) {
        IUsuario usuario = obterUsuario(codigoUsuario);
        ILivro livro = obterLivro(codigoLivro);
        String mensagem;
    
        if (livro.temExemplarDisponivel()) {
            if (livro.obterQntdExemplares() > livro.obterQntdReservas()) {
                IReserva reserva = usuario.realizarReserva(livro);

                if (reserva != null) {
                    livro.adicionarReserva(reserva);

                    mensagem = "\nReserva realizada: " + usuario.obterNome() + " fez uma reserva para o livro " + livro.obterTitulo() + ".";

                    Console.mostrarMensagem(mensagem);
                } else {
                    mensagem = "\nO usuário " + usuario.obterNome() + " atingiu o número máximo permitido de reservas.";

                    Console.mostrarMensagem(mensagem);
                }
            } else {
                mensagem = "\nNão há exemplares disponíveis para reserva do livro " + livro.obterTitulo() + ".";

                Console.mostrarMensagem(mensagem);
            }
        } else {
            mensagem = "\nTodos os exemplares do livro " + livro.obterTitulo() + " estão indisponíveis no momento.";

            Console.mostrarMensagem(mensagem);
        }
    }

    public void retornarLivro(String codigoUsuario, String codigoLivro) {
        IUsuario usuario = obterUsuario(codigoUsuario);
        ILivro livro = obterLivro(codigoLivro);
        String codigoExemplar = usuario.obterExemplarEmprestado(livro);
        String mensagem;

        if (usuario.devolverLivro(livro)) {
            livro.definirExemplarDisponivel(codigoExemplar);
            mensagem = "\nO usuário " + usuario.obterNome() + " realizou a devolução do exemplar do livro " + livro.obterTitulo() + ".";

            Console.mostrarMensagem(mensagem);
        } else {
            mensagem = "\nO usuário " + usuario.obterNome() + " não possui exemplar do livro " + livro.obterTitulo() + " para devolver.";

            Console.mostrarMensagem(mensagem);
        }
    }

    public void consultarLivro(String codigoLivro) {
        ILivro livro = obterLivro(codigoLivro);
        int qntdReservas = livro.obterQntdReservas();

        if (qntdReservas == 0) {
            Console.mostrarMensagem("\nO livro " + livro.obterTitulo() + " não possui reservas.");
        } else {
            Console.mostrarMensagem("\nO livro " + livro.obterTitulo() + " possui " + qntdReservas + " reserva(s) pendente(s).");

            int i = 1;
            for (IReserva reserva: livro.obterReservas()) {
                Console.mostrarMensagem("Reserva " + i + ": " + reserva.obterNomeUsuario());
                i++;
            }
        }

        if (livro.obterQntdExemplares() == 0) {
            Console.mostrarMensagem("\nO livro " + livro.obterTitulo() + " não possui exemplares cadastrados.");
        } else {
            Console.mostrarMensagem("\nO livro " + livro.obterTitulo() + " possui " + livro.obterQntdExemplares() + " exemplar(es) cadastrado(s).");

            for (Exemplar exemplar: livro.obterExemplares()) {
                Console.mostrarMensagem("Exemplar: " + exemplar.obterCodigo() + " - Disponível: " + exemplar.obterStatusNome() + "Nome do usuário: " + exemplar.obterNomeUsuarioEmprestimo() + " - Data de Empréstimo: " + exemplar.obterDataEmprestimo() + " - Data de Retorno: " + exemplar.obterDataRetorno());
            }
        }
    }

    public void consultarAluno(String codigoUsuario) {
        IUsuario usuario = obterUsuario(codigoUsuario);

        List<IEmprestimo> emprestimos = usuario.obterEmprestimosSolicitados();

        if (emprestimos == null || emprestimos.isEmpty()) {
            Console.mostrarMensagem("\nO usuário não possui empréstimos.");
        } else {
            Console.mostrarMensagem("\nEmpréstimos:\nTítulo | Data de Empréstimo | Status | Data de Devolução");

            int i = 1;
            for (IEmprestimo emprestimo : emprestimos) {
                Console.mostrarMensagem(i + ". " + emprestimo.obterTituloLivro() + " | " + emprestimo.obterDataEmprestimo() + " | " + usuario.obterStatusEmprestimo(emprestimo) + " | " + emprestimo.obterDataRetorno());
                i++;
            }
        }

        List<IReserva> reservas = usuario.obterReservas();

        if (reservas == null || reservas.isEmpty()) {
            Console.mostrarMensagem("\nO usuário não possui reservas.");
        } else {
            Console.mostrarMensagem("\nReservas:\nTítulo | Data de Solicitação");
            int j = 1;
            for (IReserva reserva: reservas) {
                Console.mostrarMensagem(j + ". " + reserva.obterTituloLivro() + " | " + reserva.obterDataReserva());
                j++;
            }
        }
    }

    public void consultarNotificacoesProfessor(String codigoUsuario) {
        Observer usuario = (Observer) obterUsuario(codigoUsuario);

        String mensagem = "\nNúmero de notificações do professor " + usuario.toString() + ": " + usuario.obterQntdNotificacoes();

        Console.mostrarMensagem(mensagem);
    }

    public ILivro obterLivro(String codigoLivro) {
        for (ILivro livro: livros) {
            if (livro.obterCodigo().equals(codigoLivro)) {
                return livro;
            }
        }

        return null;
    }

    public IUsuario obterUsuario(String codigoUsuario) {
        for (IUsuario usuario: usuarios) {
            if(usuario.obterId().equals(codigoUsuario)) {
                return usuario;
            }
        }
    
        return null;
    }

    public void registrarObservador(String codigoUsuario, String codigoLivro) {
        IUsuario usuario = obterUsuario(codigoUsuario);
        ILivro livro = obterLivro(codigoLivro);

        livro.registrarObservador(usuario);
    }
}
