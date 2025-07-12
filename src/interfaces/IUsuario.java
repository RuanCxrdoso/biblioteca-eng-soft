package src.interfaces;

import java.util.List;

public interface IUsuario {
        public boolean solicitarEmprestimo(ILivro livro);
        public boolean devolverLivro(ILivro livro);
        public IReserva realizarReserva(ILivro livro);
        public void cancelarReserva(ILivro livro);
        public List<IReserva> obterReservas();
        public List<IEmprestimo> obterEmprestimosVigentes();
        public List<IEmprestimo> obterEmprestimosSolicitados();
        public int obterQuantidadeEmprestimosVigentes();
        public int obterQuantidadeEmprestimosSolicitados();
        public String obterNome();
        public String obterId();
        public String obterExemplarEmprestado(ILivro livro);
        public String obterStatusEmprestimo(IEmprestimo emprestimo);
}
