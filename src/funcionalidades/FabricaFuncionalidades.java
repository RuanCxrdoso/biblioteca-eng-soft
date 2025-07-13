package src.funcionalidades;

import src.interfaces.IReserva;
import src.interfaces.IUsuario;

public class FabricaFuncionalidades {
  public static Emprestimo criaEmprestimo(Exemplar exemplar, IUsuario usuario, int tempoMaximoEmprestimo) {
    return new Emprestimo(exemplar, usuario, tempoMaximoEmprestimo);
  }

  public static IReserva criarReserva(Livro livro, IUsuario usuario) {
    return new Reserva(livro, usuario);
  }
}
