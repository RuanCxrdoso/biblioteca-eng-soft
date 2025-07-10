package src.funcionalidades.usuarios.validadores.verificadores;

import src.interfaces.IEmprestimo;
import src.interfaces.ILivro;
import src.interfaces.IUsuario;
import src.interfaces.IVerificadorEmprestimosVigentes;

public class VerificadorEmprestimosVigentes implements IVerificadorEmprestimosVigentes {
    public boolean verificarEmprestimosVigentes(IUsuario usuario, ILivro livro) {
      for (IEmprestimo emprestimo: usuario.obterEmprestimosVigentes()) {
          if (emprestimo.obterLivro().equals(livro)) {
              return false;
          }
      }
      
      return true;
  }
}
