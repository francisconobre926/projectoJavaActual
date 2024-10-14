package gerenciamentoDeFuncionario;

public enum Rotas {
    ROTA_A("Rota A"),
    ROTA_B("Rota B"),
    ROTA_C("Rota C");

	
    private String descricao;

    Rotas(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
