package src.interfaces;

import java.time.LocalDate;

import src.funcionalidades.Livro;

public interface IReserva {
   public IUsuario obterUsuario();
   public String obterNomeUsuario();
   public Livro obterLivro();
   public String obterTituloLivro();
   public LocalDate obterDataReserva();
}