package src.funcionalidades.usuarios.validadores;

import java.util.Optional;
import src.funcionalidades.usuarios.console.Console;
import src.funcionalidades.usuarios.validadores.verificadores.FabricaVerificadores;
import src.interfaces.IExemplar;
import src.interfaces.ILivro;
import src.interfaces.IUsuario;
import src.interfaces.IValidadorEmprestimo;
import src.interfaces.IVerificadorAtraso;

public class ValidadorEmprestimoProfessor implements IValidadorEmprestimo{

    private IVerificadorAtraso verificadorAtraso;

    public ValidadorEmprestimoProfessor() {
        this.verificadorAtraso = FabricaVerificadores.obterVerificadorAtraso();
    }

    @Override
    public boolean validarEmprestimo(IUsuario usuario, ILivro livro) {

        if (verificadorAtraso.verificarAtraso(usuario)) {
            Console.mostrarMensagem("\nO usuário " + usuario.obterNome()+ " possui empréstimo em atraso! Impossível realizar novo empréstimo.");
            return false;
        }

        Optional<IExemplar> disp = livro.obterExemplares().stream().filter(IExemplar::obterStatus).findFirst();

        if (!disp.isPresent()) {
            Console.mostrarMensagem("\nNão há exemplar disponível do livro '"+ livro.obterTitulo() + "' para o Professor "+ usuario.obterNome() + "!");
            return false;
        }


        Console.mostrarMensagem("\nProfessor " + usuario.obterNome()+ " pode solicitar empréstimo do livro '"+ livro.obterTitulo() + "'.");
        return true;
    }
  
}
