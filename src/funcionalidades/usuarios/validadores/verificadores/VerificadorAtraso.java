package src.funcionalidades.usuarios.validadores.verificadores;

import java.time.LocalDate;

import src.funcionalidades.Emprestimo;
import src.interfaces.IUsuario;
import src.interfaces.IVerificadorAtraso;

public class VerificadorAtraso implements IVerificadorAtraso {
  public boolean verificarAtraso(IUsuario usuario) {
      for (Emprestimo emprestimo: usuario.obterEmprestimosVigentes()) {
          if(LocalDate.now().isBefore(emprestimo.obterDataRetorno())) {
              return true;
          }
      }

      return false;
  }
}
