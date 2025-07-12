package src.funcionalidades.usuarios.validadores;

import src.funcionalidades.usuarios.validadores.verificadores.FabricaVerificadores;
import src.interfaces.IAluno;
import src.interfaces.ILivro;
import src.interfaces.IUsuario;
import src.interfaces.IValidadorEmprestimo;
import src.interfaces.IVerificadorAtraso;
import src.interfaces.IVerificadorEmprestimosVigentes;
import src.interfaces.IVerificadorLimiteEmprestimos;
import src.interfaces.IVerificadorReserva;

public class ValidadorEmprestimoAluno implements IValidadorEmprestimo {
  private IVerificadorAtraso verificadorAtraso;
  private IVerificadorReserva verificadorReserva;
  private IVerificadorLimiteEmprestimos verificadorLimiteEmprestimos;
  private IVerificadorEmprestimosVigentes verificadorEmprestimosVigentes;

  public ValidadorEmprestimoAluno() {
    this.verificadorAtraso = FabricaVerificadores.obterVerificadorAtraso();
    this.verificadorReserva = FabricaVerificadores.obterVerificadorReserva();
    this.verificadorLimiteEmprestimos = FabricaVerificadores.obterVerificadorLimiteEmprestimos();
    this.verificadorEmprestimosVigentes = FabricaVerificadores.obterVerificadorEmprestimosVigentes();
  }

  public boolean validarEmprestimo(IUsuario usuario, ILivro livro) {
    if (verificadorAtraso.verificarAtraso(usuario)) {
      Console.mostrarMensagem("\nO usuário " + usuario.obterNome() + " possui emprestimo com atraso de devolução!");

      return false;
    }
  
    // Necessariamente um usuário deve ser do tipo IAluno pois professores não tem limite de empréstimos
    if (!verificadorLimiteEmprestimos.verificarLimiteEmprestimos((IAluno) usuario)) {
      Console.mostrarMensagem("\nO aluno " + usuario.obterNome() + " atingiu o máximo de emprestimos possiveis!");

      return false;
    }

    if (!verificadorEmprestimosVigentes.verificarEmprestimosVigentes(usuario, livro)) {
      Console.mostrarMensagem("\nO usuário " + usuario.obterNome() + " já possui um emprestimo vigente do livro " + livro.obterTitulo() + "!");

      return false;
    }

    if (!(verificadorReserva.verificarReserva(usuario, livro) || livro.temExemplarNaoReservado())) {
      Console.mostrarMensagem("\nO usuário " + usuario.obterNome() + " não possui uma reserva ativa para o livro " + livro.obterTitulo() + "!");

      return false;
    }

    Console.mostrarMensagem("\nO usuário " + usuario.obterNome() + " pode solicitar o emprestimo do livro " + livro.obterTitulo() + "!");

    return true;
  }
}
