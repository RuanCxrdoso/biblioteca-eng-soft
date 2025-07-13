package src.funcionalidades.usuarios.validadores.verificadores;

import src.interfaces.IAluno;
import src.interfaces.IVerificadorLimiteEmprestimos;

public class VerificadorLimiteEmprestimos implements IVerificadorLimiteEmprestimos {
    public boolean verificarLimiteEmprestimos(IAluno usuario) {
      if (usuario.obterQuantidadeEmprestimosVigentes() >= usuario.obterQntdEmprestimosPossiveis()){
          return false;
      }

      return true;
  }
}
