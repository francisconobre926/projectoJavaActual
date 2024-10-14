package gerenciamentoDeFuncionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MotoristaGui extends JFrame {
    private MotoristaBaseDados motoristaBaseDados;

    // Components
    private JTextField nomeField;
    private JComboBox<Categoria> categoriaCombo;
    private JTextField modeloVeiculoField;
    private JTextField placaVeiculoField;
    private JComboBox<Rotas> rotaCombo;
    private JTextArea outputArea;

    public MotoristaGui() {
        motoristaBaseDados = new MotoristaBaseDados();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gerenciamento de Motoristas");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input Fields
        nomeField = new JTextField(15);
        categoriaCombo = new JComboBox<>(Categoria.values());
        modeloVeiculoField = new JTextField(15);
        placaVeiculoField = new JTextField(15);
        rotaCombo = new JComboBox<>(Rotas.values());

        // Buttons
        JButton addButton = new JButton("Adicionar Motorista");
        JButton listButton = new JButton("Listar Motoristas");
        JButton deleteButton = new JButton("Apagar Motorista");

        // Output Area
        outputArea = new JTextArea(15, 80);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add Components to Frame
        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("Categoria:"));
        add(categoriaCombo);
        add(new JLabel("Modelo do Veículo:"));
        add(modeloVeiculoField);
        add(new JLabel("Placa do Veículo:"));
        add(placaVeiculoField);
        add(new JLabel("Rota:"));
        add(rotaCombo);
        add(addButton);
        add(listButton);
        add(deleteButton);
        add(scrollPane);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarMotorista();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarMotoristas();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apagarMotorista();
            }
        });
    }

    private void adicionarMotorista() {
        String nome = nomeField.getText();
        Categoria categoria = (Categoria) categoriaCombo.getSelectedItem();
        String modelo = modeloVeiculoField.getText();
        String placa = placaVeiculoField.getText();
        Rotas rota = (Rotas) rotaCombo.getSelectedItem();

        Veiculo veiculo = new Veiculo(modelo, placa, rota);
        Motorista motorista = new Motorista(nome, categoria, veiculo);

        motoristaBaseDados.adicionarMotorista(motorista);
        outputArea.append("Motorista " + nome + " adicionado com sucesso.\n");
        limparCampos();
    }

    private void listarMotoristas() {
    	outputArea.setText("\t\tListar Motorista\t\t\n");
        List<Motorista> motoristas = motoristaBaseDados.listarMotoristas();

        for (Motorista motorista : motoristas) {
            outputArea.append(motorista.toString() + "\n");
        }
    }

    private void apagarMotorista() {
        String nome = nomeField.getText();
        String placa = placaVeiculoField.getText();

        try {
			motoristaBaseDados.apagarMotorista(nome, placa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        outputArea.append("Tentativa de apagar motorista " + nome + ".\n");
        outputArea.append("apagando... "+".\n");
        outputArea.append("motorista" + nome +"de "+placa+ "foi removido"+ ".\n");
        limparCampos();
    }

      private void limparCampos() {
        nomeField.setText("");
        modeloVeiculoField.setText("");
        placaVeiculoField.setText("");
     }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MotoristaGui gui = new MotoristaGui();
            gui.setVisible(true);
        });
    }
}
