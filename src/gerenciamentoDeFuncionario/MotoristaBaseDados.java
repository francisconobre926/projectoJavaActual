package gerenciamentoDeFuncionario;
import java.sql.*;
import java.util.*;

public class MotoristaBaseDados {
    private static Connection conexao;

    public MotoristaBaseDados() {
        conectarBanco();
    }

    public static void conectarBanco() {
        String url = "jdbc:mysql://localhost:3306/projectoTransportadora";
        String user = "root";  
        String password = "Magneto1";  

        try {
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão com o banco de dados estabelecida.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (modelo, placa, rota) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getRota().name());

            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                veiculo.setId_veiculo(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar veículo: " + e.getMessage());
        }
    }

    public void adicionarMotorista(Motorista motorista) {
        adicionarVeiculo(motorista.getVeiculo());

        String sql = "INSERT INTO funcionario (nome, categoria, id_veiculo) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            if( motorista.getNome()!="null" && motorista.getVeiculo().getId_veiculo()!=0 ) {
            	stmt.setString(1, motorista.getNome());
                stmt.setString(2, motorista.getCategoria().name());
                stmt.setInt(3, motorista.getVeiculo().getId_veiculo());
                stmt.executeUpdate();
            }else {
            	System.out.println("nome ou id do veiculo invlaido...");
            }
        	
            System.out.println("Motorista " + motorista.getNome() + " adicionado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar motorista: " + e.getMessage());
        }
    }

    public List<Motorista> listarMotoristas() {
        List<Motorista> motoristas = new ArrayList<>();
        String sql = "SELECT m.nome, m.categoria, v.modelo, v.placa, v.rota FROM motorista m JOIN veiculo v ON m.id_veiculo = v.id_veiculo";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
                String modeloVeiculo = rs.getString("modelo");
                String placaVeiculo = rs.getString("placa");
                Rotas rotaVeiculo = Rotas.valueOf(rs.getString("rota"));

                Veiculo veiculo = new Veiculo(modeloVeiculo, placaVeiculo, rotaVeiculo);
                Motorista motorista = new Motorista(nome, categoria, veiculo);
                motoristas.add(motorista);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar motoristas: " + e.getMessage());
        }

        return motoristas;
    }

    
    
    public void apagarMotorista(String nome, String placaVeiculo) throws SQLException {
        String sql = "DELETE FROM motorista WHERE nome = ? AND id_veiculo = (SELECT id_veiculo FROM veiculo WHERE placa = ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, placaVeiculo);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Motorista apagado com sucesso.");
            } else {
                System.out.println("Nenhum motorista encontrado para apagar.");
            }
            
            if (rowsAffected > 0) {
                System.out.println("Motorista com nome " + nome + " e placa " + placaVeiculo + " apagado com sucesso.");
            } else {
                System.out.println("Nenhum motorista encontrado com o nome " + nome + " e placa " + placaVeiculo + ".");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao apagar motorista: " + e.getMessage());
        }

    }
}
//
//package gerenciamentoDeFuncionario;
//
//import java.sql.*;
//import java.util.regex.Pattern;
//import java.util.*;
//
//public class MotoristaBaseDados {
//    private static Connection conexao;
//    private static final String PLACA_REGEX = "^[A-Z]{3}\\d[A-Z]\\d{2}$"; // Regex para validar a placa
//
//    public MotoristaBaseDados() {
//        conectarBanco();
//    }
//
//    public static void conectarBanco() {
//        String url = "jdbc:mysql://localhost:3306/projectoTransportadora";
//        String user = "root";  
//        String password = "Magneto1";  
//
//        try {
//            conexao = DriverManager.getConnection(url, user, password);
//            System.out.println("Conexão com o banco de dados estabelecida.");
//        } catch (SQLException e) {
//            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
//        }
//    }
//
//    private boolean isPlacaValida(String placa) {
//        return Pattern.matches(PLACA_REGEX, placa);
//    }
//
//    public void adicionarVeiculo(Veiculo veiculo) {
//        if (!isPlacaValida(veiculo.getPlaca())) {
//            System.out.println("Placa inválida: " + veiculo.getPlaca());
//            return;
//        }
//
//        String sql = "INSERT INTO veiculo (modelo, placa, rota) VALUES (?, ?, ?)";
//        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setString(1, veiculo.getModelo());
//            stmt.setString(2, veiculo.getPlaca());
//            stmt.setString(3, veiculo.getRota().name());
//
//            stmt.executeUpdate();
//            ResultSet rs = stmt.getGeneratedKeys();
//            if (rs.next()) {
//                veiculo.setId_veiculo(rs.getInt(1));
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro ao adicionar veículo: " + e.getMessage());
//        }
//    }
//
//    public void adicionarMotorista(Motorista motorista) {
//        if (motorista.getVeiculo() == null || !isPlacaValida(motorista.getVeiculo().getPlaca())) {
//            System.out.println("Veículo inválido para o motorista " + motorista.getNome());
//            return;
//        }
//
//        adicionarVeiculo(motorista.getVeiculo());
//
//        String sql = "INSERT INTO motorista (nome, categoria, id_veiculo) VALUES (?, ?, ?)";
//        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
//            if (!motorista.getNome().equals("null") && motorista.getVeiculo().getId_veiculo() != 0) {
//                stmt.setString(1, motorista.getNome());
//                stmt.setString(2, motorista.getCategoria().name());
//                stmt.setInt(3, motorista.getVeiculo().getId_veiculo());
//                stmt.executeUpdate();
//                System.out.println("Motorista " + motorista.getNome() + " adicionado com sucesso.");
//            } else {
//                System.out.println("Nome ou ID do veículo inválido...");
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro ao adicionar motorista: " + e.getMessage());
//        }
//    }
//
//    public List<Motorista> listarMotoristas() {
//        List<Motorista> motoristas = new ArrayList<>();
//        String sql = "SELECT m.nome, m.categoria, v.modelo, v.placa, v.rota FROM motorista m JOIN veiculo v ON m.id_veiculo = v.id_veiculo";
//
//        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String nome = rs.getString("nome");
//                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));
//                String modeloVeiculo = rs.getString("modelo");
//                String placaVeiculo = rs.getString("placa");
//                Rotas rotaVeiculo = Rotas.valueOf(rs.getString("rota"));
//
//                Veiculo veiculo = new Veiculo(modeloVeiculo, placaVeiculo, rotaVeiculo);
//                Motorista motorista = new Motorista(nome, categoria, veiculo);
//                motoristas.add(motorista);
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro ao listar motoristas: " + e.getMessage());
//        }
//
//        return motoristas;
//    }
//
//    public void apagarMotorista(String nome, String placaVeiculo) throws SQLException {
//        String sql = "DELETE FROM motorista WHERE nome = ? AND id_veiculo = (SELECT id_veiculo FROM veiculo WHERE placa = ?)";
//        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
//            stmt.setString(1, nome);
//            stmt.setString(2, placaVeiculo);
//            int rowsAffected = stmt.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Motorista com nome " + nome + " e placa " + placaVeiculo + " apagado com sucesso.");
//            } else {
//                System.out.println("Nenhum motorista encontrado com o nome " + nome + " e placa " + placaVeiculo + ".");
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro ao apagar motorista: " + e.getMessage());
//        }
//    }
//}
//
//


