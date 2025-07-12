package src.funcionalidades.usuarios.validadores.verificadores;

import src.interfaces.ILivro;
import src.interfaces.IReserva;
import src.interfaces.IUsuario;
import src.interfaces.IVerificadorReserva;

public class VerificadorReserva implements IVerificadorReserva {
    public boolean verificarReserva(IUsuario usuario, ILivro livro){
      for (IReserva reserva: usuario.obterReservas()) {
          if (reserva.obterLivro() == livro) {
              return true;
          }
      }
      
      return false;
  }
}
