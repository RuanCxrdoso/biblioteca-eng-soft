package src.interfaces;

import java.util.List;

import src.funcionalidades.Emprestimo;

public interface IUsuario {
        public boolean solicitarEmprestimo(ILivro livro);
        public boolean devolverLivro(ILivro livro);
        public IReserva realizarReserva(ILivro livro);
        public void cancelarReserva(ILivro livro);
        public List<IReserva> obterReservas();
        public List<Emprestimo> obterEmprestimosVigentes();
        public int obterQuantidadeEmprestimosVigentes();
        public String obterNome();
        public String obterId();
        public String obterExemplarEmprestado(ILivro livro);
        public String obterStatusEmprestimo(Emprestimo emprestimo);
}
