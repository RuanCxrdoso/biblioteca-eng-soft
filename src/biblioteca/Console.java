package src.biblioteca;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import src.biblioteca.comandos.ConsultarAluno;
import src.biblioteca.comandos.ConsultarLivro;
import src.biblioteca.comandos.ConsultarNotificacoesProfessor;
import src.biblioteca.comandos.RegistrarObservador;
import src.biblioteca.comandos.ReservarLivro;
import src.biblioteca.comandos.RetornarLivro;
import src.biblioteca.comandos.Sair;
import src.biblioteca.comandos.SolicitarEmprestimo;
import src.interfaces.IComando;

public class Console {
    private Map<String, IComando> comandos = new HashMap<String, IComando>();

    public Console() {
        this.registrarComandos();
    }

    public void registrarComandos() {
        this.comandos.put("emp", new SolicitarEmprestimo());
        this.comandos.put("dev", new RetornarLivro());
        this.comandos.put("res", new ReservarLivro());
        this.comandos.put("liv", new ConsultarLivro());
        this.comandos.put("usu", new ConsultarAluno());
        this.comandos.put("ntf", new ConsultarNotificacoesProfessor());
        this.comandos.put("obs", new RegistrarObservador());
        this.comandos.put("sai", new Sair());
    }

    public void sistema() {
        Biblioteca biblioteca;
        biblioteca = Biblioteca.obterInstanciaUnica();
        biblioteca.inicializarBiblioteca();

        Scanner teclado = new Scanner(System.in);

        String opcao;
        String comando;
        String entrada;
        boolean condicao = true;

        while (condicao) {
            opcao = teclado.nextLine();

            comando = opcao.substring(0, 3);

            try {
                entrada = opcao.substring(4);
            } catch (Exception e) {
                entrada = "vazio";
            }
            
            IComando c = comandos.get(comando);
            condicao = c.executar(entrada);
            System.out.println("");
        }

        teclado.close();
    }

    public static void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
