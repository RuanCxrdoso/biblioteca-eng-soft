package src.funcionalidades;

import java.time.LocalDate;

import src.interfaces.ILivro;
import src.interfaces.IReserva;
import src.interfaces.IUsuario;

public class Reserva implements IReserva {
    private ILivro livro;
    private IUsuario usuario;
    private LocalDate dataReserva;

    public Reserva(ILivro livro, IUsuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataReserva = LocalDate.now();
    }

    public IUsuario obterUsuario() {
        return this.usuario;
    }

    public String obterNomeUsuario() {
        return this.usuario.obterNome();
    }

    public ILivro obterLivro() {
        return this.livro;
    }

    public String obterTituloLivro() {
        return this.livro.obterTitulo();
    }

    public LocalDate obterDataReserva() {
        return this.dataReserva;
    }
}
