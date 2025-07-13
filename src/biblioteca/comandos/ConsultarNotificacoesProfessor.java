package src.biblioteca.comandos;

import src.biblioteca.Biblioteca;
import src.interfaces.IComando;

public class ConsultarNotificacoesProfessor implements IComando {
    @Override
    public boolean executar(String entrada) {
        Biblioteca biblioteca = Biblioteca.obterInstanciaUnica();

        biblioteca.consultarNotificacoesProfessor(entrada);

        return true;
    }
    
}

