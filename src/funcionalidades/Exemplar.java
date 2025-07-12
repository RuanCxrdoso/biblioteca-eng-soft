package src.funcionalidades;

import java.time.LocalDate;

import src.funcionalidades.usuarios.console.Console;
import src.interfaces.IEmprestimo;
import src.interfaces.IExemplar;
import src.interfaces.ILivro;

public class Exemplar implements IExemplar {
  private String codigo;
  private boolean status;
  private ILivro livro;
  private IEmprestimo emprestimo;

  public Exemplar(String codigo, ILivro livro) {
    this.codigo = codigo;
    this.livro = livro;
    this.status = true; // Exemplar disponível por padrão
    this.emprestimo = null;
  }

  @Override
  public ILivro obterLivro() {
    return this.livro;
  }

  @Override
  public String obterCodigo() {
    return this.codigo;
  }

  @Override
  public boolean obterStatus() {
    return this.status;
  }

  @Override
  public String obterNomeUsuarioEmprestimo() {
      if (this.emprestimo != null) {
          return this.emprestimo.obterNomeUsuario();
      }

      Console.mostrarMensagem(codigo + " não possui empréstimo ativo.");

      return null;
  }

  @Override
  public LocalDate obterDataEmprestimo() {
      if (this.emprestimo != null) {
          return this.emprestimo.obterDataEmprestimo();
      }

      Console.mostrarMensagem(codigo + " não possui empréstimo ativo.");

      return null;
  }

  @Override
  public LocalDate obterDataRetorno() {
      if (this.emprestimo != null) {
          return this.emprestimo.obterDataRetorno();
      }

      Console.mostrarMensagem(codigo + " não possui empréstimo ativo.");

      return null;
  }

  @Override
  public String obterStatusNome() {
    return this.status ? "Disponível" : "Indisponível";
  }

  @Override
  public void removerEmprestimo() {
    if (this.emprestimo != null) {
      this.emprestimo = null;
      this.status = true;
    } else {
      Console.mostrarMensagem(codigo + " não possui empréstimo ativo.");
    }
  }

  @Override
  public void adicionarEmprestimo(IEmprestimo emprestimo) {
    if (this.status) {
      this.emprestimo = emprestimo;
      this.status = false;
    } else {
      Console.mostrarMensagem(codigo + " já está emprestado.");
    }
  }

  @Override
  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String obterTituloLivro() {
    return this.livro.obterTitulo();
  }
}
