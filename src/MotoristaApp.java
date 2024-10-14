// import javax.swing.*;
// import gerenciamentoDeFuncionario.*;
// import pessoa.*;

// import java.util.List;

// public class MotoristaApp extends Application {

//     private MotoristaBaseDados transportadora = new MotoristaBaseDados();

//     private TextField nomeField;
//     private TextField cpfField;
//     private TextField cnhField;
//     private TextField enderecoField;
//     private TextArea resultadoArea;

//     @Override
//     public void start(Stage primaryStage) {
//         primaryStage.setTitle("Gerenciamento de Motoristas");

//         // Criação do painel
//         GridPane grid = new GridPane();
//         grid.setPadding(new Insets(10));
//         grid.setVgap(8);
//         grid.setHgap(10);

//         // Campos de entrada
//         nomeField = new TextField();
//         nomeField.setPromptText("Nome");
//         GridPane.setConstraints(nomeField, 0, 0);

//         cpfField = new TextField();
//         cpfField.setPromptText("CPF");
//         GridPane.setConstraints(cpfField, 0, 1);

//         cnhField = new TextField();
//         cnhField.setPromptText("CNH");
//         GridPane.setConstraints(cnhField, 0, 2);

//         enderecoField = new TextField();
//         enderecoField.setPromptText("Endereço");
//         GridPane.setConstraints(enderecoField, 0, 3);

//         // Botões
//         Button adicionarButton = new Button("Adicionar Motorista");
//         adicionarButton.setOnAction(e -> adicionarMotorista());
//         GridPane.setConstraints(adicionarButton, 0, 4);

//         Button listarButton = new Button("Listar Motoristas");
//         listarButton.setOnAction(e -> listarMotoristas());
//         GridPane.setConstraints(listarButton, 1, 4);

//         Button buscarButton = new Button("Buscar Motorista");
//         buscarButton.setOnAction(e -> buscarMotorista());
//         GridPane.setConstraints(buscarButton, 2, 4);

//         // Área de resultado
//         resultadoArea = new TextArea();
//         resultadoArea.setEditable(false);
//         GridPane.setConstraints(resultadoArea, 0, 5, 3, 1); // ocupa 3 colunas

//         // Adicionando todos os elementos ao grid
//         grid.getChildren().addAll(nomeField, cpfField, cnhField, enderecoField, adicionarButton, listarButton, buscarButton, resultadoArea);

//         // Criando a cena e configurando o palco
//         Scene scene = new Scene(grid, 400, 400);
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     private void adicionarMotorista() {
//         String nome = nomeField.getText();
//         String cpf = cpfField.getText();
//         String cnh = cnhField.getText();
//         String endereco = enderecoField.getText();

//         Motorista motorista = new Motorista(nome, cpf, cnh, endereco);
//         transportadora.adicionarMotorista(motorista);
//         resultadoArea.append("Motorista adicionado: " + nome + "\n");
        
//         // Limpar campos após adicionar
//         nomeField.clear();
//         cpfField.clear();
//         cnhField.clear();
//         enderecoField.clear();
//     }

//     public void listarMotoristas() {
//         List<Motorista> motoristas = transportadora.listarMotoristas();
        
//         for (Motorista motorista : motoristas) {
//             resultadoArea.append("Motorista: " + motorista.getNome() + ", CPF: " + motorista.getCpf() + ", CNH: " + motorista.getCnh() + "\n");
//         }
//     }

//     private void buscarMotorista() {
//         String cpf = cpfField.getText();
//         Motorista motoristaEncontrado = transportadora.buscarMotoristaPorCpf(cpf);
        
//         resultadoArea.append("\n");
//         if (motoristaEncontrado != null) {
//             resultadoArea.append("motorista encontrado "+ motorista.getNome());;
//         } else {
//             resultadoArea.append("motorista nao encontrado.");
//         }
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }
