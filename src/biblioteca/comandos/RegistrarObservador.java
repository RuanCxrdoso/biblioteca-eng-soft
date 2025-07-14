package src.biblioteca.comandos;

import src.biblioteca.Repositorio;
import src.interfaces.IComando;

public class RegistrarObservador implements IComando {
    @Override
    public boolean executar(String entrada) {
        Repositorio biblioteca = Repositorio.obterInstanciaUnica();

        String[] p = entrada.split(" ");
      
        biblioteca.registrarObservador(p[0], p[1]);

        return true;
    }
    
}
