package src.funcionalidades.usuarios.validadores.verificadores;

import src.interfaces.IVerificadorAtraso;
import src.interfaces.IVerificadorEmprestimosVigentes;
import src.interfaces.IVerificadorLimiteEmprestimos;
import src.interfaces.IVerificadorReserva;

public class FabricaVerificadores {
  public static IVerificadorAtraso obterVerificadorAtraso() {
    return new VerificadorAtraso();
  }

  public static IVerificadorEmprestimosVigentes obterVerificadorEmprestimosVigentes() {
    return new VerificadorEmprestimosVigentes();
  }

  public static IVerificadorLimiteEmprestimos obterVerificadorLimiteEmprestimos() {
    return new VerificadorLimiteEmprestimos();
  }
  
  public static IVerificadorReserva obterVerificadorReserva() {
    return new VerificadorReserva();
  }

}
