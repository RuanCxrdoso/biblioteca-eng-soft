package src.funcionalidades.usuarios;

import java.util.ArrayList;

import src.funcionalidades.usuarios.limitacoes.MaximoLimiteEmprestimos;
import src.funcionalidades.usuarios.limitacoes.MaximoLimiteTempo;
import src.interfaces.IAluno;
import src.interfaces.IEmprestimo;
import src.interfaces.ILivro;
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
  private ArrayList<IEmprestimo> emprestimosSolicitados;
  private ArrayList<IEmprestimo> emprestimosVigentes;

  public AlunoGraduacao(String nome, String id) {
    this.nome = nome;
    this.id = id;
    this.tempoMaximoEmprestimo = MaximoLimiteEmprestimos.ALUNOGRADUACAO.obterQntdDias();
    this.maximoEmprestimosVigentes = MaximoLimiteTempo.ALUNOGRADUACAO.obterQntdDias();
    this.validadorEmprestimo = FabricaValidadorEmprestimo.obterValidadorEmprestimoAluno();
    this.reservasSolicitadas = new ArrayList<>();
    this.reservasAtivas = new ArrayList<>();
    this.emprestimosSolicitados = new ArrayList<>();
    this.emprestimosVigentes = new ArrayList<>();
  }

  @Override
  public boolean solicitarEmprestimo(ILivro livro) {
    if (validadorEmprestimo.validarEmprestimo(this, livro)) {
      IEmprestimo emprestimo = new Emprestimo(livro, this, tempoMaximoEmprestimo); // Fazer fabrica
    
      emprestimosSolicitados.add(emprestimo);
      
      return true;
    }

    Console.mostrarMensagem("\nO usuário " + nome + " não pode solicitar o emprestimo do livro " + livro.obterTitulo() + "!");

    return false;
  }
}
