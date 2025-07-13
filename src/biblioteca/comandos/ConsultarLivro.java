package src.biblioteca.comandos;

import src.biblioteca.Biblioteca;
import src.interfaces.IComando;

public class ConsultarLivro implements IComando {
    @Override
    public boolean executar(String entrada) {
        Biblioteca biblioteca = Biblioteca.obterInstanciaUnica();
        
        biblioteca.consultarLivro(entrada);

        return true;
    }
    
}


