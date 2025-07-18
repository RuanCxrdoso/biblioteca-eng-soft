package src.funcionalidades;

import java.util.ArrayList;
import java.util.List;
import src.funcionalidades.Livro;
import src.interfaces.IObserver;
import src.interfaces.IReserva;
import src.interfaces.ISubject;

public class Livro implements ISubject {
    private String codigo;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private int anoPublicacao;
    private List<Exemplar> listaExemplares;
    private List<IReserva> listaReservas;
    private List<IObserver> observadores;

    public Livro(String codigo, String titulo, String editora, String edicao, String autores, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.edicao = edicao;
        this.autores = autores;
        this.anoPublicacao = anoPublicacao;
        this.listaExemplares = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    public String obterCodigo() {
        return codigo;
    }

    public String obterTitulo() {
        return titulo;
    }

    public String obterEditora() {
        return editora;
    }

    public String obterEdicao() {
        return edicao;
    }

    public String obterAutor() {
        return autores;
    }

    public int obterAnoPublicacao() {
        return anoPublicacao;
    }

    public List<Exemplar> obterExemplares() {
        return new ArrayList<>(listaExemplares);
    }

    public int obterQntdExemplares() {
        return this.listaExemplares.size();
    }

    public int obterQntdReservas() {
        return this.listaReservas.size();
    }

    public List<IReserva> obterReservas() {
        return new ArrayList<>(listaReservas);
    }

    public boolean temExemplarNaoReservado() {
        for (Exemplar exemplar: listaExemplares) {
            if (exemplar.obterStatus()) {
                return true;
            }
        }
        return false;
    }

    public void definirExemplarDisponivel(String codigoExemplar) {
        for (Exemplar exemplar : listaExemplares) {
            if (exemplar.obterCodigo().equals(codigoExemplar)) {
                exemplar.setStatus(false);
            }
        }
    }

    public Exemplar obterExemplarDisponivel() {
        for (Exemplar exemplar : listaExemplares) {
            if (exemplar.obterStatus()) {
                return exemplar;
            }
        }
        return null;
    }

    public void adicionarExemplar(Exemplar exemplar) {
        this.listaExemplares.add(exemplar);
    }

    public void adicionarReserva(IReserva reserva) {
        this.listaReservas.add(reserva);
        if (listaReservas.size() > 2) {
            notificarObservadores();
        }
    }

    public void registrarObserver(IObserver observer) {
        observadores.add(observer);
    }

    public void removerObserver(IObserver observer) {
        observadores.remove(observer);
    }

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

    public boolean temExemplarDisponivel() {
        for (Exemplar exemplar : listaExemplares) {
            if (exemplar.obterStatus()) {
                return true;
            }
        }

        return false;
    }
}
