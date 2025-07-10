package src.interfaces;

import java.time.LocalDate;

public interface IExemplar {
   public ILivro obterLivro();
   public String obterCodigo();
   public boolean obterStatus();
   public String obterNomeUsuarioEmprestimo();
   public LocalDate obterDataEmprestimo();
   public LocalDate obterDataRetorno();
   public String obterStatusNome();
   public void removerEmprestimo();
   public void adicionarEmprestimo(IEmprestimo emprestimo);
   public void setStatus(boolean status);
   public String obterTituloLivro();
}
