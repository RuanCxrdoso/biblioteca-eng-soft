package src.funcionalidades;

import java.time.LocalDate;

import src.interfaces.IUsuario;

public class Emprestimo {
    private IUsuario usuario;
    private Exemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataRetorno;

    public Emprestimo(Exemplar exemplar, IUsuario usuario, int tempoMaximoEmprestimo) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        // this.dataRetorno = LocalDate.now().minusDays(10); // Para testar falha ao soliciar empréstimo possuindo outro empréstimo atrasado
        this.dataRetorno = dataEmprestimo.plusDays(tempoMaximoEmprestimo);
    }

    public String obterNomeUsuario() {
        return this.usuario.obterNome();
    }
    
    public LocalDate obterDataEmprestimo() {
        return this.dataEmprestimo;
    }

    public LocalDate obterDataRetorno() {
        return this.dataRetorno;
    }

    public Exemplar obterExemplar() {
        return this.exemplar;
    }

    public void excluirExemplar() {
        this.exemplar.removerEmprestimo();
    }

    public Livro obterLivro() {
        return this.exemplar.obterLivro();
    }

    public String obterTituloLivro() {
        return this.exemplar.obterTituloLivro();
    }
}