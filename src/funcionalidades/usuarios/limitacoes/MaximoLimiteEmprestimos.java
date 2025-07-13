package src.funcionalidades.usuarios.limitacoes;

public enum MaximoLimiteEmprestimos {
    ALUNOGRADUACAO (2), 
    ALUNOPOSGRADUACAO (3);

    private final int dias;
    MaximoLimiteEmprestimos(int qntd){
        this.dias = qntd;
    }

    public int obterQntd(){
        return this.dias;
    }
}