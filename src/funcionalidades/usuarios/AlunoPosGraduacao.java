package src.funcionalidades.usuarios;

import java.util.ArrayList;

import src.biblioteca.Console;
import src.funcionalidades.FabricaFuncionalidades;
import src.funcionalidades.usuarios.limitacoes.MaximoLimiteEmprestimos;
import src.funcionalidades.usuarios.limitacoes.MaximoLimiteTempo;
import src.interfaces.IAluno;
import src.interfaces.IEmprestimo;
import src.interfaces.IExemplar;
import src.interfaces.ILivro;
import src.interfaces.IReserva;
import src.interfaces.IValidadorEmprestimo;

public class AlunoPosGraduacao implements IAluno {
  private String nome;
  private String id;
  private int tempoMaximoEmprestimo;
  private int maximoEmprestimosVigentes;
  private IValidadorEmprestimo validadorEmprestimo;
  private ArrayList<IReserva> reservasSolicitadas;
  private ArrayList<IReserva> reservasAtivas;
  private ArrayList<IEmprestimo> emprestimosSolicitados;
  private ArrayList<IEmprestimo> emprestimosVigentes;

  public AlunoPosGraduacao(String nome, String id) {
  this.nome = nome;
  this.id = id;
  this.tempoMaximoEmprestimo = MaximoLimiteTempo.ALUNOPOSGRADUACAO.obterQntdDias();
  this.maximoEmprestimosVigentes = MaximoLimiteEmprestimos.ALUNOPOSGRADUACAO.obterQntd();
  this.validadorEmprestimo = FabricaValidadorEmprestimo.obterValidadorEmprestimoAluno();
  this.reservasSolicitadas = new ArrayList<>();
  this.reservasAtivas = new ArrayList<>();
  this.emprestimosSolicitados = new ArrayList<>();
  this.emprestimosVigentes = new ArrayList<>();
  }

  @Override
  public boolean solicitarEmprestimo(ILivro livro) {
    if (this.validadorEmprestimo.validarEmprestimo(this, livro)) {
      IExemplar exemplar = livro.obterExemplarDisponivel();
      exemplar.setStatus(false); // exemplar fica indisponível após o empréstimo

      IEmprestimo emprestimo = FabricaFuncionalidades.criaEmprestimo(exemplar, this, tempoMaximoEmprestimo);
      
      exemplar.adicionarEmprestimo(emprestimo); // Registra o empréstimo no exemplar
      this.emprestimosSolicitados.add(emprestimo);
      this.emprestimosVigentes.add(emprestimo);
      this.cancelarReserva(livro);

      Console.mostrarMensagem("\nO usuário " + nome + " solicitou o emprestimo do livro " + livro.obterTitulo() + "!");
      
      return true;
    }

    Console.mostrarMensagem("\nO usuário " + nome + " não pode solicitar o emprestimo do livro " + livro.obterTitulo() + "!");

    return false;
  }

  @Override
  public boolean devolverLivro(ILivro livro) {
    for (IEmprestimo emprestimo: this.emprestimosVigentes) {
      if (emprestimo.obterLivro().equals(livro)) {
        emprestimo.excluirExemplar();
        this.emprestimosVigentes.remove(emprestimo);

        Console.mostrarMensagem("\nO usuário " + nome + " devolveu o livro " + livro.obterTitulo() + "!");

        return true;
      }
    }

    Console.mostrarMensagem("\nO usuário " + nome + " não possui o livro " + livro.obterTitulo() + " emprestado!");
  
    return false;
  }

  @Override
  public IReserva realizarReserva(ILivro livro) {
    if (this.reservasAtivas.size() <= 7) {
      IReserva reserva = FabricaFuncionalidades.criarReserva(livro, this);
      this.reservasSolicitadas.add(reserva);
      this.reservasAtivas.add(reserva);

      Console.mostrarMensagem("\nO usuário " + nome + " realizou a reserva do livro " + livro.obterTitulo() + "!");

      return reserva;
    }

    Console.mostrarMensagem("\nO usuário " + nome + " não pode realizar mais reservas! Limite de 7 reservas ativas atingido.");

    return null;
  }

  @Override
  public void cancelarReserva(ILivro livro) {
    for (IReserva reserva: this.reservasAtivas) {
      if (reserva.obterLivro().equals(livro)) {
        this.reservasAtivas.remove(reserva);

        Console.mostrarMensagem("\nO usuário " + nome + " cancelou a reserva do livro " + livro.obterTitulo() + "!");

        return;
      }
    }

    Console.mostrarMensagem("\nO usuário " + nome + " não possui reserva ativa para o livro " + livro.obterTitulo() + "!");
  }

  @Override
  public ArrayList<IReserva> obterReservas() {
    return this.reservasAtivas;
  }

  @Override
  public ArrayList<IEmprestimo> obterEmprestimosVigentes() {
    return this.emprestimosVigentes;
  }

  @Override
  public ArrayList<IEmprestimo> obterEmprestimosSolicitados() {
    return this.emprestimosSolicitados;
  }

  @Override
  public int obterQuantidadeEmprestimosVigentes() {
    return this.emprestimosVigentes.size();
  }

  @Override
  public int obterQuantidadeEmprestimosSolicitados() {
    return this.emprestimosSolicitados.size();
  }

  @Override
  public String obterNome() {
    return this.nome;
  }

  @Override
  public String obterId() {
    return this.id;
  }

  @Override
  public String obterExemplarEmprestado(ILivro livro) {
    for (IEmprestimo emprestimo: this.emprestimosVigentes) {
      if (emprestimo.obterLivro().equals(livro)) {
        return emprestimo.obterExemplar().obterCodigo();
      }
    }

    Console.mostrarMensagem("\nO usuário " + nome + " não possui o livro " + livro.obterTitulo() + " emprestado!");

    return null;
  }

  @Override
  public String obterStatusEmprestimo(IEmprestimo emprestimo) {
    if (this.emprestimosVigentes.contains(emprestimo)) {
      return "Vigente";
    } else if (this.emprestimosSolicitados.contains(emprestimo)) {
      return "Finalizado";
    } else {
      return "Não encontrado";
    }
  }

  @Override
  public int obterQntdEmprestimosPossiveis() {
    return this.maximoEmprestimosVigentes;
  }
}
