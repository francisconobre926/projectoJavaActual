package gerenciamentoDeFuncionario;

public class Veiculo {
    private String modelo;
    private String placa;
    private int id_veiculo;
    private Rotas rota;

    public Veiculo(String modelo, String placa, Rotas rota) {
        this.modelo = modelo;
        this.placa = placa;
        this.rota = rota;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public Rotas getRota() {
        return rota;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    @Override
    public String toString() {
        return "\tModelo: " + modelo + "\tPlaca: " + placa + "\tRota: " + rota;
    }
}


//
//
//
//package gerenciamentoDeFuncionario;
//
//import java.util.regex.Pattern;
//
//public class Veiculo {
//    private String modelo;
//    private String placa;
//    private int id_veiculo;
//    private Rotas rota;
//
//    // Regex para validar a placa (ex: ABC1D23)
//    private static final String PLACA_REGEX = "^[A-Z]{3}\\d[A-Z]\\d{2}$";
//
//    public Veiculo(String modelo, String placa, Rotas rota) {
//        if (!isPlacaValida(placa)) {
//            throw new IllegalArgumentException("Placa inválida: " + placa);
//        }
//        this.modelo = modelo;
//        this.placa = placa;
//        this.rota = rota;
//    }
//
//    private boolean isPlacaValida(String placa) {
//        return Pattern.matches(PLACA_REGEX, placa);
//    }
//
//    public String getModelo() {
//        return modelo;
//    }
//
//    public String getPlaca() {
//        return placa;
//    }
//
//    public Rotas getRota() {
//        return rota;
//    }
//
//    public int getId_veiculo() {
//        return id_veiculo;
//    }
//
//    public void setId_veiculo(int id_veiculo) {
//        this.id_veiculo = id_veiculo;
//    }
//
//    @Override
//    public String toString() {
//        return "\tModelo: " + modelo + "\tPlaca: " + placa + "\tRota: " + rota;
//    }
//}
