package src;

import java.util.ArrayList;
import java.util.List;

import src.funcionalidades.Livro;
import src.funcionalidades.Exemplar;
import src.funcionalidades.usuarios.AlunoGraduacao;
import src.funcionalidades.usuarios.AlunoPosGraduacao;
import src.funcionalidades.usuarios.Professor;
import src.interfaces.IUsuario;
public class Repositorio {
    private static Repositorio instancia;
    private List<IUsuario> usuarios;
    private List<Livro> livros;

    public Repositorio() {
        
        this.usuarios = new ArrayList<>();
        this.livros = new ArrayList<>();

        inicializarUsuarios();
        inicializarLivros();
        inicializarExemplares();
    }

    public static synchronized Repositorio getInstancia() {
        if (instancia == null) {
            instancia = new Repositorio();
        }
        return instancia;
    }
    
    private void inicializarUsuarios() {
        usuarios.add(new AlunoGraduacao("123", "João da Silva"));
        usuarios.add(new AlunoPosGraduacao("456", "Luiz Fernando Rodrigues"));
        usuarios.add(new AlunoGraduacao("789", "Pedro Paulo"));
        usuarios.add(new Professor("100", "Carlos Lucena"));
    }

    private void inicializarLivros(){
        livros.add(new Livro("100", "Engenharia de Software","Addison Wesley", "6°", "Ian Sommerville", 2000));
        livros.add(new Livro("101", "UML - Guia do Usuário", "Campus","7°", "Grady Booch,James Rumbaugh,Ivar Jacobson", 2000));
        livros.add(new Livro("200", "Code Complete", "Microsoft Press","2°","Steve McConnell", 2014));
        livros.add(new Livro("201", "Agile Software Development,Principles, Patterns and Practices", "Prentice Hall ","1°","Robert Martin", 2002));
        livros.add(new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison Wesley Professional ","1°","Martin Fowler", 1999));
        livros.add(new Livro("301", "Software Metrics: A rigorous and Practical Approach", "CRC Press","3°","Martin Fowler", 2014));
        livros.add(new Livro("400", "Design Patterns: Element of Reusable Object-Oriented Software", "Addison Wesley Professional","1°","Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 1994));
        livros.add(new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison Wesley Professional","3°","Martin Fowler", 2003));
    }

    private void inicializarExemplares(){
        Livro l100 = buscarLivroPorCodigo("100");
        if (l100 != null) {
            l100.adicionarExemplar(new Exemplar("01", l100));
            l100.adicionarExemplar(new Exemplar("02", l100));
        }

        Livro l101 = buscarLivroPorCodigo("101");
        if (l101 != null) {
            l101.adicionarExemplar(new Exemplar("03", l101));
           
        }

        Livro l200 = buscarLivroPorCodigo("200");
        if (l200 != null) {
            l200.adicionarExemplar(new Exemplar("04", l200));
           
        }

        Livro l201 = buscarLivroPorCodigo("201");
        if (l201 != null) {
            l201.adicionarExemplar(new Exemplar("05", l201));
           
        }

        Livro l300 = buscarLivroPorCodigo("300");
        if (l300 != null) {
            l300.adicionarExemplar(new Exemplar("06", l300));
            l300.adicionarExemplar(new Exemplar("07", l300));
        }

        Livro l400 = buscarLivroPorCodigo("400");
        if (l400 != null) {
            l400.adicionarExemplar(new Exemplar("08", l400));
            l400.adicionarExemplar(new Exemplar("09", l400));
        }

    }

    public IUsuario buscarUsuarioPorId(String id) {
        return usuarios.stream().filter(u -> u.obterId().equals(id)).findFirst().orElse(null);
    }

    public Livro buscarLivroPorCodigo(String codigo) {
        return livros.stream().filter(l -> l.obterCodigo().equals(codigo)).findFirst().orElse(null);
    }
}
