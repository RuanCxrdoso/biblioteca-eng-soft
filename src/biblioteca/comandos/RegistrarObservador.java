package src.biblioteca.comandos;

import src.biblioteca.Biblioteca;
import src.interfaces.IComando;

public class RegistrarObservador implements IComando {
    @Override
    public boolean executar(String entrada) {
        Biblioteca biblioteca = Biblioteca.obterInstanciaUnica();

        String[] p = entrada.split(" ");
      
        biblioteca.registrarObservador(p[0], p[1]);

        return true;
    }
    
}
