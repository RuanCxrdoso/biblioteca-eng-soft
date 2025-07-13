package src.interfaces;

import src.funcionalidades.Livro;

public interface IVerificadorEmprestimosVigentes {
  public boolean verificarEmprestimosVigentes(IUsuario usuario, Livro livro);
}
