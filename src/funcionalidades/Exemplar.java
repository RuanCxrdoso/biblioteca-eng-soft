package src.funcionalidades;

import java.time.LocalDate;

import src.biblioteca.Console;

public class Exemplar {
  private String codigo;
  private boolean status;
  private Livro livro;
  private Emprestimo emprestimo;

  public Exemplar(String codigo, Livro livro) {
    this.codigo = codigo;
    this.livro = livro;
    this.status = true; // Exemplar disponível por padrão
    this.emprestimo = null;
  }

  public Livro obterLivro() {
    return this.livro;
  }

  public String obterCodigo() {
    return this.codigo;
  }

  public boolean obterStatus() {
    return this.status;
  }

  public String obterNomeUsuarioEmprestimo() {
      if (this.emprestimo != null) {
          return this.emprestimo.obterNomeUsuario();
      }

      return null;
  }

  public LocalDate obterDataEmprestimo() {
      if (this.emprestimo != null) {
          return this.emprestimo.obterDataEmprestimo();
      }

      return null;
  }

  public LocalDate obterDataRetorno() {
      if (this.emprestimo != null) {
          return this.emprestimo.obterDataRetorno();
      }

      return null;
  }

  public String obterStatusNome() {
    return this.status ? "Disponível" : "Indisponível";
  }

  public void removerEmprestimo() {
    if (this.emprestimo != null) {
      this.emprestimo = null;
      this.status = true;
    } else {
      Console.mostrarMensagem(codigo + " não possui empréstimo ativo.");
    }
  }

  public void adicionarEmprestimo(Emprestimo emprestimo) {
    if (this.status) {
      this.emprestimo = emprestimo;
      this.status = false;
    } else {
      Console.mostrarMensagem(codigo + " já está emprestado.");
    }
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String obterTituloLivro() {
    return this.livro.obterTitulo();
  }
}
