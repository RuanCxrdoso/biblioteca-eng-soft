package src.funcionalidades.usuarios;

import java.util.ArrayList;

import src.biblioteca.Console;
import src.funcionalidades.Emprestimo;
import src.funcionalidades.Exemplar;
import src.funcionalidades.FabricaFuncionalidades;
import src.funcionalidades.Livro;
import src.funcionalidades.usuarios.limitacoes.MaximoLimiteEmprestimos;
import src.funcionalidades.usuarios.limitacoes.MaximoLimiteTempo;
import src.interfaces.IAluno;
import src.interfaces.IReserva;
import src.interfaces.IValidadorEmprestimo;

public class AlunoGraduacao implements IAluno {
  private String nome;
  private String id;
  private int tempoMaximoEmprestimo;
  private int maximoEmprestimosVigentes;
  private IValidadorEmprestimo validadorEmprestimo;
  private ArrayList<IReserva> reservasSolicitadas;
  private ArrayList<IReserva> reservasAtivas;
  private ArrayList<Emprestimo> emprestimosSolicitados;
  private ArrayList<Emprestimo> emprestimosVigentes;

  public AlunoGraduacao(String nome, String id) {
    this.nome = nome;
    this.id = id;
    this.tempoMaximoEmprestimo = MaximoLimiteTempo.ALUNOGRADUACAO.obterQntdDias();
    this.maximoEmprestimosVigentes = MaximoLimiteEmprestimos.ALUNOGRADUACAO.obterQntd();
    this.validadorEmprestimo = FabricaValidadorEmprestimo.obterValidadorEmprestimoAluno();
    this.reservasSolicitadas = new ArrayList<>();
    this.reservasAtivas = new ArrayList<>();
    this.emprestimosSolicitados = new ArrayList<>();
    this.emprestimosVigentes = new ArrayList<>();
  }

  @Override
  public boolean solicitarEmprestimo(Livro livro) {
    if (this.validadorEmprestimo.validarEmprestimo(this, livro)) {
      Exemplar exemplar = livro.obterExemplarDisponivel();

      Emprestimo emprestimo = FabricaFuncionalidades.criaEmprestimo(exemplar, this, tempoMaximoEmprestimo);
      
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
  public boolean devolverLivro(Livro livro) {
    for (Emprestimo emprestimo: this.emprestimosVigentes) {
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
  public IReserva realizarReserva(Livro livro) {
    boolean jaTemReserva = reservasAtivas.stream().anyMatch(r -> r.obterLivro().equals(livro));

    if (jaTemReserva) {
      Console.mostrarMensagem("\nO usuário " + nome + " já possui uma reserva ativa para o livro " + livro.obterTitulo() + "!");

      return null;
    } else if (this.reservasAtivas.size() >= 5) {
      Console.mostrarMensagem("\nO usuário " + nome + " não pode realizar mais reservas! Limite de 5 reservas ativas atingido.");

      return null;
    }
  

    IReserva reserva = FabricaFuncionalidades.criarReserva(livro, this);
    this.reservasSolicitadas.add(reserva);
    this.reservasAtivas.add(reserva);

    Console.mostrarMensagem("\nO usuário " + nome + " realizou a reserva do livro " + livro.obterTitulo() + "!");

    return reserva;
  }

  @Override
  public void cancelarReserva(Livro livro) {
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
  public ArrayList<Emprestimo> obterEmprestimosVigentes() {
    return this.emprestimosVigentes;
  }

  @Override
  public ArrayList<Emprestimo> obterEmprestimosSolicitados() {
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
  public String obterExemplarEmprestado(Livro livro) {
    for (Emprestimo emprestimo: this.emprestimosVigentes) {
      if (emprestimo.obterLivro().equals(livro)) {
        return emprestimo.obterExemplar().obterCodigo();
      }
    }

    Console.mostrarMensagem("\nO usuário " + nome + " não possui o livro " + livro.obterTitulo() + " emprestado!");

    return null;
  }

  @Override
  public String obterStatusEmprestimo(Emprestimo emprestimo) {
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
