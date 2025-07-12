package src.funcionalidades.usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import src.funcionalidades.Emprestimo;
import src.funcionalidades.Reserva;
import src.funcionalidades.usuarios.limitacoes.MaximoLimiteTempo;
import src.funcionalidades.usuarios.validadores.FabricaValidadorEmprestimo;
import src.interfaces.IUsuario;
import src.interfaces.IEmprestimo;
import src.interfaces.IExemplar;
import src.interfaces.ILivro;
import src.interfaces.IObserver;
import src.interfaces.IReserva;
import src.interfaces.IValidadorEmprestimo;
import src.funcionalidades.usuarios.console.Console;

public class Professor implements IUsuario {
    private String nome;
    private String id;
    private int tempoMaximoEmprestimoEmDias;
    private IValidadorEmprestimo validadorEmprestimo;
    private List<IReserva> reservasSolicitadas;
    private List<IEmprestimo> emprestimosRealizados;
    private int totalNotificacoes;

    public Professor( String id, String nome){
        this.nome = nome;
        this.id = id;
        this.tempoMaximoEmprestimoEmDias = MaximoLimiteTempo.PROFESSOR.obterQntdDias();
        this.validadorEmprestimo = FabricaValidadorEmprestimo.obterValidadorEmprestimoProfessor();
        this.reservasSolicitadas = new ArrayList<>();
        this.emprestimosRealizados = new ArrayList<>();
        this.totalNotificacoes = 0;
    }

    @Override
    public boolean solicitarEmprestimo(ILivro livro){
        if (validadorEmprestimo.validarEmprestimo(this, livro)){
            Optional<IExemplar> exemplarOpt = livro.obterExemplares().stream().filter(IExemplar::obterStatus) .findFirst();

        if (!exemplarOpt.isPresent()) {
                Console.mostrarMensagem("\nErro interno: Nenhum exemplar disponível após validação para" + this.nome + "!" + "Contate o suporte.");
                return false;
        }

        IExemplar exemplar = exemplarOpt.get();

        IEmprestimo novoEmprestimo = new Emprestimo(livro, this, tempoMaximoEmprestimoEmDias);

        exemplar.adicionarEmprestimo(novoEmprestimo); 
        emprestimosRealizados.add(novoEmprestimo);

        Console.mostrarMensagem("\nEmpréstimo do livro '" + livro.obterTitulo() + "' (exemplar: " + exemplar.obterCodigo() + ") para Professor " + this.nome + " realizado. Devolução prevista para: " + novoEmprestimo.obterDataRetorno() + ".");
        return true;

        }

        Console.mostrarMensagem("\nO Professor " + this.nome + " não pode solicitar o empréstimo do livro " + livro.obterTitulo() + "!");
        return false;

    }

    @Override
    public boolean devolverLivro(ILivro livro) {
        Optional<IEmprestimo> emprestimoVigenteOpt = emprestimosRealizados.stream().filter(e -> e.obterLivro().equals(livro) && e.obterExemplar().obterStatus()).findFirst();

        if (!emprestimoVigenteOpt.isPresent()) {
            Console.mostrarMensagem("O professor " + this.nome + " não possui um empréstimo em curso para o livro '" + livro.obterTitulo() + "'.");
            return false;
        }

        IEmprestimo emprestimo = emprestimoVigenteOpt.get();
        IExemplar exemplarDevolvido = emprestimo.obterExemplar();

        exemplarDevolvido.removerEmprestimo();
        
        Console.mostrarMensagem("Devolução do livro '" + livro.obterTitulo() + "' (exemplar: " + exemplarDevolvido.obterCodigo() + ") por Professor " + nome + " realizada com sucesso em " + LocalDate.now() + ".");
        return true;

    }
}

