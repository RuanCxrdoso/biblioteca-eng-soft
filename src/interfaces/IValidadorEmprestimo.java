package src.interfaces;

import src.funcionalidades.Livro;

public interface IValidadorEmprestimo {
  public boolean validarEmprestimo(IUsuario usuario, Livro livro);
}
