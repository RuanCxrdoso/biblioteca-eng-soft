package src.funcionalidades.usuarios.limitacoes;

public enum MaximoLimiteTempo {
    ALUNOGRADUACAO(4), 
    ALUNOPOSGRADUACAO(5),
    PROFESSOR(8);

    private final int dias;

    MaximoLimiteTempo(int qntdDias) {
        this.dias = qntdDias;
    }

    public int obterQntdDias() {
        return this.dias;
    }
}