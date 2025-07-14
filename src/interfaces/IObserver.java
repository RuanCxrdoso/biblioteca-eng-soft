package src.interfaces;

import src.funcionalidades.Livro;

public interface IObserver {
    public void notificar(Livro livro);
    public int obterQntdTotalNotificacoes();
}
