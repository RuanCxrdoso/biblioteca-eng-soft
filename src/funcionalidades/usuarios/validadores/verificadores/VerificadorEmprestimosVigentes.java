package src.funcionalidades.usuarios.validadores.verificadores;

import src.funcionalidades.Emprestimo;
import src.funcionalidades.Livro;
import src.interfaces.IUsuario;
import src.interfaces.IVerificadorEmprestimosVigentes;

public class VerificadorEmprestimosVigentes implements IVerificadorEmprestimosVigentes {
    public boolean verificarEmprestimosVigentes(IUsuario usuario, Livro livro) {
      for (Emprestimo emprestimo: usuario.obterEmprestimosVigentes()) {
          if (emprestimo.obterLivro().equals(livro)) {
              return false;
          }
      }
      
      return true;
  }
}
