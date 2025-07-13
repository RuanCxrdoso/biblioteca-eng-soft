package src.funcionalidades.usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import src.funcionalidades.FabricaFuncionalidades;
import src.funcionalidades.usuarios.console.Console;
import src.funcionalidades.usuarios.limitacoes.MaximoLimiteTempo;
import src.interfaces.IEmprestimo;
import src.interfaces.IExemplar;
import src.interfaces.ILivro;
import src.interfaces.IObserver;
import src.interfaces.IReserva;
import src.interfaces.IUsuario;
import src.interfaces.IValidadorEmprestimo;

public class Professor implements IUsuario, IObserver {
    private String nome;
    private String id;
    private int tempoMaximoEmprestimoEmDias;
    private IValidadorEmprestimo validadorEmprestimo;
    private ArrayList<IReserva> reservasSolicitadas;
    private ArrayList<IEmprestimo> emprestimosRealizados;
    private int totalNotificacoes = 0;

    public Professor( String id, String nome){
        this.nome = nome;
        this.id = id;
        this.tempoMaximoEmprestimoEmDias = MaximoLimiteTempo.PROFESSOR.obterQntdDias();
        this.validadorEmprestimo = FabricaValidadorEmprestimo.obterValidadorEmprestimoProfessor();
        this.reservasSolicitadas = new ArrayList<>();
        this.emprestimosRealizados = new ArrayList<>();
    }

    @Override
    public boolean solicitarEmprestimo(ILivro livro){
        if (validadorEmprestimo.validarEmprestimo(this, livro)){
            Optional<IExemplar> exemplarOpt = livro.obterExemplares().stream().filter(IExemplar::obterStatus) .findFirst();

            if (!exemplarOpt.isPresent()) {
                Console.mostrarMensagem("\nErro interno: Nenhum exemplar disponível após validação para" + nome + "!" + "Contate o suporte.");
                return false;
            }

            IExemplar exemplar = exemplarOpt.get();

            IEmprestimo novoEmprestimo = FabricaFuncionalidades.criaEmprestimo(exemplar, this, tempoMaximoEmprestimoEmDias);

            exemplar.adicionarEmprestimo(novoEmprestimo); 
            emprestimosRealizados.add(novoEmprestimo);

            Console.mostrarMensagem("\nEmpréstimo do livro '" + livro.obterTitulo() + "' (exemplar: " + exemplar.obterCodigo() + ") para Professor " + nome + " realizado. Devolução prevista para: " + novoEmprestimo.obterDataRetorno() + ".");
            return true;

        }

        Console.mostrarMensagem("\nO Professor " + nome + " não pode solicitar o empréstimo do livro " + livro.obterTitulo() + "!");
        return false;

    }

    @Override
    public boolean devolverLivro(ILivro livro) {
        Optional<IEmprestimo> emprestimoVigenteOpt = emprestimosRealizados.stream().filter(e -> e.obterLivro().equals(livro) && e.obterExemplar().obterStatus()).findFirst();

        if (!emprestimoVigenteOpt.isPresent()) {
            Console.mostrarMensagem("O professor " + nome + " não possui um empréstimo em curso para o livro '" + livro.obterTitulo() + "'.");
            return false;
        }

        IEmprestimo emprestimo = emprestimoVigenteOpt.get();
        IExemplar exemplarDevolvido = emprestimo.obterExemplar();

        exemplarDevolvido.removerEmprestimo();
        
        Console.mostrarMensagem("Devolução do livro '" + livro.obterTitulo() + "' (exemplar: " + exemplarDevolvido.obterCodigo() + ") por Professor " + nome + " realizada com sucesso em " + LocalDate.now() + ".");
        return true;

    }

    @Override
    public IReserva realizarReserva(ILivro livro){
        boolean jaTemReserva = reservasSolicitadas.stream().anyMatch(r -> r.obterLivro().equals(livro));

        if (jaTemReserva) {
            Console.mostrarMensagem("Não foi possível realizar a reserva. O professor " + nome + " já possui uma reserva para o livro '" + livro.obterTitulo() + "'.");
            return null;
        }

        boolean jaTemEmprestimoVigente = emprestimosRealizados.stream().filter(e -> e.obterExemplar().obterStatus()) .anyMatch(e -> e.obterLivro().equals(livro));

        if (jaTemEmprestimoVigente) {
            Console.mostrarMensagem("Não foi possível realizar a reserva, pois o professor já tem um exemplar deste mesmo livro em empréstimo no momento.");
            return null;
        }

        IReserva novaReserva = FabricaFuncionalidades.criarReserva(livro, this); 
        this.reservasSolicitadas.add(novaReserva);

        Console.mostrarMensagem("Reserva do livro '" + livro.obterTitulo() + "' para Professor " + nome + " realizada com sucesso em " + LocalDate.now() + ".");
        return novaReserva;

    }

    @Override
    public void cancelarReserva(ILivro livro) {
        Optional<IReserva> reservaOpt = reservasSolicitadas.stream().filter(r -> r.obterLivro().equals(livro)).findFirst();

        if (reservaOpt.isPresent()) {
            IReserva reserva = reservaOpt.get();
            reservasSolicitadas.remove(reserva);
            Console.mostrarMensagem("Reserva do livro '" + livro.obterTitulo() + "' para o Professor " + nome + " cancelada.");
        }
    }

    @Override
    public ArrayList<IReserva> obterReservas() {
        return this.reservasSolicitadas;
    }

    @Override
    public List<IEmprestimo> obterEmprestimosVigentes() { 
        return this.emprestimosRealizados.stream().filter(e -> !e.obterExemplar().obterStatus()).collect(Collectors.toList()); 
    }

    @Override
    public ArrayList<IEmprestimo> obterEmprestimosSolicitados() {
        return this.emprestimosRealizados;
    }

    @Override
    public int obterQuantidadeEmprestimosVigentes() {
        return obterEmprestimosVigentes().size();
    }

    @Override
    public int obterQuantidadeEmprestimosSolicitados() {
        return obterEmprestimosSolicitados().size();
    }

    @Override
    public String obterNome() {
        return nome;
    }

    @Override
    public String obterId() {
        return id;
    }
    
    @Override
    public String obterExemplarEmprestado(ILivro livro) {
        Optional<IEmprestimo> emprestimoOpt = emprestimosRealizados.stream().filter(e -> e.obterExemplar().obterStatus() && e.obterLivro().equals(livro)).findFirst();
        return emprestimoOpt.isPresent() ? emprestimoOpt.get().obterExemplar().obterCodigo() : null;
    }

    @Override
    public String obterStatusEmprestimo(IEmprestimo emprestimo) {
        if (this.emprestimosRealizados.contains(emprestimo)) {
            return "Vigente";
        } else if (this.emprestimosRealizados.contains(emprestimo)) {
            return "Finalizado";
        } else {
            return "Não encontrado";
        }
    }

    public int getTempoMaximoEmprestimoEmDias() {
        return tempoMaximoEmprestimoEmDias;
    }

    @Override
    public void notificar(String mensagem) {
        totalNotificacoes++;
        Console.mostrarMensagem("Professor " + nome + " foi notificado: " + mensagem);
    }

    public int getTotalNotificacoes() {
        return totalNotificacoes;
    }

}

