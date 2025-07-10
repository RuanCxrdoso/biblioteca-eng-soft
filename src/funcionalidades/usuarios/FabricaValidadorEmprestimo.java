package src.funcionalidades.usuarios;

import src.funcionalidades.usuarios.validadores.ValidadorEmprestimoAluno;
import src.funcionalidades.usuarios.validadores.ValidadorEmprestimoProfessor;
import src.interfaces.IValidadorEmprestimo;

public class FabricaValidadorEmprestimo {
  public static IValidadorEmprestimo obterValidadorEmprestimoAluno() {
    return new ValidadorEmprestimoAluno();
  }

  public static IValidadorEmprestimo obterValidadorEmprestimoProfessor() {
    return new ValidadorEmprestimoProfessor();
  }
}
