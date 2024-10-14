package gerenciamentoDeFuncionario;

public enum Categoria {
    MOTORISTA("PESADO"),
    MOTORISTA_SENIOR("SENIOR"),
    MOTORISTA_JUNIOR("LEVE");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "\t"+descricao;
    }
}