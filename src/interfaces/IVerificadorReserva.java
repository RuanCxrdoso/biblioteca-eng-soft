package src.interfaces;

import src.funcionalidades.Livro;

public interface IVerificadorReserva {
  public boolean verificarReserva(IUsuario usuario, Livro livro);
}
