package src.biblioteca.comandos;

import src.biblioteca.Repositorio;
import src.interfaces.IComando;

public class ConsultarNotificacoesProfessor implements IComando {
    @Override
    public boolean executar(String entrada) {
        Repositorio biblioteca = Repositorio.obterInstanciaUnica();

        biblioteca.consultarNotificacoesProfessor(entrada);

        return true;
    }
    
}

