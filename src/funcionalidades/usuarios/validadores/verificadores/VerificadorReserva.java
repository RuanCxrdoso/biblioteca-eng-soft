package src.funcionalidades.usuarios.validadores.verificadores;

import src.funcionalidades.Livro;
import src.interfaces.IReserva;
import src.interfaces.IUsuario;
import src.interfaces.IVerificadorReserva;

public class VerificadorReserva implements IVerificadorReserva {
    public boolean verificarReserva(IUsuario usuario, Livro livro){
      for (IReserva reserva: usuario.obterReservas()) {
          if (reserva.obterLivro() == livro) {
              return true;
          }
      }
      
      return false;
  }
}
