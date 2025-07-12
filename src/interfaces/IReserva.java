package src.interfaces;

import java.time.LocalDate;

public interface IReserva {
   public IUsuario obterUsuario();
   public String obterNomeUsuario();
   public ILivro obterLivro();
   public String obterTituloLivro();
   public LocalDate obterDataReserva();
}