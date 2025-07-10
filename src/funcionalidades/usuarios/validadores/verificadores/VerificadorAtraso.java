package src.funcionalidades.usuarios.validadores.verificadores;

import java.time.LocalDate;

import src.interfaces.IEmprestimo;
import src.interfaces.IUsuario;
import src.interfaces.IVerificadorAtraso;

public class VerificadorAtraso implements IVerificadorAtraso {
  public boolean verificarAtraso(IUsuario usuario) {
      for (IEmprestimo emprestimo: usuario.obterEmprestimosVigentes()) {
          if(LocalDate.now().isBefore(emprestimo.obterDataRetorno())) {
              return true;
          }
      }

      return false;
  }
}
