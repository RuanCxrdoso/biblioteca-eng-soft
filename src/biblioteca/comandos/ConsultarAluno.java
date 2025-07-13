package src.biblioteca.comandos;

import src.biblioteca.Biblioteca;
import src.interfaces.IComando;

public class ConsultarAluno implements IComando {
    @Override
    public boolean executar(String entrada) {
        Biblioteca biblioteca = Biblioteca.obterInstanciaUnica();
    
        biblioteca.consultarAluno(entrada);

        return true;
    }
    
}

