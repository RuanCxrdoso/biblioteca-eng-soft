package src.interfaces;

import java.util.List;
public interface ILivro {
    public String obterTitulo();
    public String obterCodigo();
    public String obterEditora();
    public String obterAutor();
    public int obterAnoPublicacao();
    public List<IExemplar> obterExemplares();
    public IExemplar obterExemplarDisponivel();
    public List<IReserva> obterReservas();
}
