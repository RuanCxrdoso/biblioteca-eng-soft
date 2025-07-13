package src.funcionalidades;

import java.util.ArrayList;
import java.util.List;

import src.interfaces.IObserver;
import src.interfaces.IReserva;
import src.interfaces.ISubject;
import src.interfaces.IExemplar;
import src.interfaces.ILivro;

public class Livro implements ILivro, ISubject {
    private String codigo;
    private String titulo;
    private String editora;
    private String autores;
    private int anoPublicacao;
    private List<IExemplar> listaExemplares;
    private List<IReserva> listaReservas;
    private List<IObserver> observadores;

    public Livro(String codigo, String titulo, String editora, String autores, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.anoPublicacao = anoPublicacao;
        this.listaExemplares = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    @Override
    public String obterCodigo() {
        return codigo;
    }

    @Override
    public String obterTitulo() {
        return titulo;
    }

    @Override
    public String obterEditora() {
        return editora;
    }

    @Override
    public String obterAutor() {
        return autores;
    }

    @Override
    public int obterAnoPublicacao() {
        return anoPublicacao;
    }

    @Override
    public List<IExemplar> obterExemplares() {
        return new ArrayList<>(listaExemplares);
    }

    @Override
    public List<IReserva> obterReservas() {
        return new ArrayList<>(listaReservas);
    }

    @Override
    public boolean temExemplarNaoReservado() {
        for (IExemplar exemplar : listaExemplares) {
            if (exemplar.obterStatus()) {
                return true;
            }
        }
        return false;
    }

    public IExemplar obterExemplarDisponivel() {
        for (IExemplar exemplar : listaExemplares) {
            if (exemplar.obterStatus()) {
                return exemplar;
            }
        }
        return null;
    }

    public void adicionarExemplar(IExemplar exemplar) {
        this.listaExemplares.add(exemplar);
    }

    public void adicionarReserva(IReserva reserva) {
        this.listaReservas.add(reserva);
        if (listaReservas.size() > 2) {
            notificarObservadores();
        }
    }

    @Override
    public void registrarObserver(IObserver observer) {
        observadores.add(observer);
    }

    @Override
    public void removerObserver(IObserver observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificarObservadores() {
        for (IObserver observer : observadores) {
            observer.notificar();
        }
    }

    public int quantidadeExemplares() {
        return listaExemplares.size();
    }

    public int quantidadeReservas() {
        return listaReservas.size();
    }
}
