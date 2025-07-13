package src.interfaces;

import java.util.List;

import src.funcionalidades.Emprestimo;
import src.funcionalidades.Livro;

public interface IUsuario {
        public boolean solicitarEmprestimo(Livro livro);
        public boolean devolverLivro(Livro livro);
        public IReserva realizarReserva(Livro livro);
        public void cancelarReserva(Livro livro);
        public List<IReserva> obterReservas();
        public List<Emprestimo> obterEmprestimosVigentes();
        public List<Emprestimo> obterEmprestimosSolicitados();
        public int obterQuantidadeEmprestimosVigentes();
        public int obterQuantidadeEmprestimosSolicitados();
        public String obterNome();
        public String obterId();
        public String obterExemplarEmprestado(Livro livro);
        public String obterStatusEmprestimo(Emprestimo emprestimo);
}
