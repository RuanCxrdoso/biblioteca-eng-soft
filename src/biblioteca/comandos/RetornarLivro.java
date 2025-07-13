package src.biblioteca.comandos;

import src.biblioteca.Biblioteca;
import src.interfaces.IComando;

public class RetornarLivro implements IComando {
    @Override
    public boolean executar(String entrada) {
        Biblioteca biblioteca = Biblioteca.obterInstanciaUnica();

        String[] p = entrada.split(" ");

        biblioteca.retornarLivro(p[0], p[1]);
    
        return true;
    }
    
}

