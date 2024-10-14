//package gerenciamentoDeFuncionario;
//import pessoa.*;
//public class Motorista extends Pessoa {
//    private String categoria;
//    private String rota;
//
//   public Motorista(String nome) {
//	   super(nome);
//   }
//    
//    public Motorista(String nome, String categoria, String rota) {
//        super(nome);
//        this.categoria = categoria;
//        this.rota = rota;
//    }
//
//    
//    public String getCategoria() {
//        return categoria;
//    }
//    public void setCategoria(String categoria) {
//        this.categoria = categoria;
//    }
//    public String getRota() {
//        return rota;
//    }
//    public void setRota(String rota) {
//        this.rota = rota;
//    }
//
//
//	@Override
//	public String toString() {
//		
//		return super.toString()+" categoria=" + categoria + ", rota=" + rota +"\n";
//	}
//
//    
//}
package gerenciamentoDeFuncionario;

public class Motorista {
    private String nome;
    private Categoria categoria;
    private Veiculo veiculo;

    public Motorista(String nome, Categoria categoria, Veiculo veiculo) {
        this.nome = nome;
        this.categoria = categoria;
        this.veiculo = veiculo;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\tCategoria: " + categoria + "\tVeículo: " + veiculo;
    }
}
