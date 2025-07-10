package src.funcionalidades.usuarios.limitacoes;

public enum MaximoLimiteEmprestimos {
    ALUNOGRADUACAO (2), 
    ALUNOPOSGRADUCAO (3);

    private final int dias;
    MaximoLimiteEmprestimos(int qntdDias){
        this.dias = qntdDias;
    }

    public int obterQntdDias(){
        return this.dias;
    }
}