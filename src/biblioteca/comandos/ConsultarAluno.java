package src.biblioteca.comandos;

import src.biblioteca.Repositorio;
import src.interfaces.IComando;

public class ConsultarAluno implements IComando {
    @Override
    public boolean executar(String entrada) {
        Repositorio biblioteca = Repositorio.obterInstanciaUnica();
    
        biblioteca.consultarAluno(entrada);

        return true;
    }
    
}

