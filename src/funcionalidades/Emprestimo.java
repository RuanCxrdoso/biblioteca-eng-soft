package src.funcionalidades;

import java.time.LocalDate;

import src.interfaces.IEmprestimo;
import src.interfaces.IExemplar;
import src.interfaces.ILivro;
import src.interfaces.IUsuario;

public class Emprestimo implements IEmprestimo {
    private IUsuario usuario;
    private IExemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataRetorno;

    public Emprestimo(IExemplar exemplar, IUsuario usuario, int tempoMaximoEmprestimo) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
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

    public IExemplar obterExemplar() {
        return this.exemplar;
    }

    public void excluirExemplar() {
        this.exemplar.removerEmprestimo();
    }

    public ILivro obterLivro() {
        return this.exemplar.obterLivro();
    }

    public String obterTituloLivro() {
        return this.exemplar.obterTituloLivro();
    }
}