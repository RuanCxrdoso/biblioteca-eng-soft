package src.interfaces;

public interface ISubject {
    public void registrarObserver(IObserver observer);
    public void removerObserver(IObserver observer);
    public void notificarObservadores();
}
