package gerenciamentoDeFuncionario;

import javax.swing.SwingUtilities;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  initializeUI();
	        SwingUtilities.invokeLater(() -> {
		           MotoristaGui gui = new MotoristaGui();
		           gui.setVisible(true);
		          
		       });
		
		
		
	}

}
