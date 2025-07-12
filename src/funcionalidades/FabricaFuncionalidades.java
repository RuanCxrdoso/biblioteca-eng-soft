package src.funcionalidades;

import src.interfaces.IEmprestimo;
import src.interfaces.IExemplar;
import src.interfaces.ILivro;
import src.interfaces.IReserva;
import src.interfaces.IUsuario;

public class FabricaFuncionalidades {
  public static IEmprestimo criaEmprestimo(IExemplar exemplar, IUsuario usuario, int tempoMaximoEmprestimo) {
    return new Emprestimo(exemplar, usuario, tempoMaximoEmprestimo);
  }

  public static IReserva criarReserva(ILivro livro, IUsuario usuario) {
    return new Reserva(livro, usuario);
  }
}
