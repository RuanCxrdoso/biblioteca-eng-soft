package src.interfaces;

import java.time.LocalDate;

public interface IEmprestimo {
  public String obterNomeUsuario();
  public LocalDate obterDataEmprestimo();
  public LocalDate obterDataRetorno();
  public IExemplar obterExemplar();
  public void excluirExemplar();
  public ILivro obterLivro();
  public String obterTituloLivro();
}