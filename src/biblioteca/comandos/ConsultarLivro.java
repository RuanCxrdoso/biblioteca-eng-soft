package src.biblioteca.comandos;

import src.biblioteca.Repositorio;
import src.interfaces.IComando;

public class ConsultarLivro implements IComando {
    @Override
    public boolean executar(String entrada) {
        Repositorio biblioteca = Repositorio.obterInstanciaUnica();
        
        biblioteca.consultarLivro(entrada);

        return true;
    }
    
}


