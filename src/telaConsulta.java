import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;;


public class telaConsulta extends JFrame implements WindowListener, ActionListener{

	private JLabel label1;
	private JLabel label2;
	private JTextField text1;
	private JTextField text2;
	private JButton vendaAcumulada;
	private JButton rankVenda;
	private JButton acumuladoDia;
	
	
	// utilizar no main:
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		telaConsulta tela = new telaConsulta();
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setSize(600,200);
		tela.setVisible(true);	
		
	}
	 */
	
	public telaConsulta() {
		super("Consultas");
		
		setLayout(new FlowLayout());
		
		label1 = new JLabel("ID Loja:");
		text1 = new JTextField(2);
		label2 = new JLabel("Mês de Consulta:");
		text2 = new JTextField(2);
		
		vendaAcumulada = new JButton("Venda Acumulada");
		rankVenda = new JButton("Rank de Vendas");
		acumuladoDia = new JButton("Acumulado por Dia");
		
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		// arrumando grid de impressão em tela
		grid.gridx = 0;
		grid.gridy = 0;
		p.add(label1,grid);
		grid.gridx = 1;
		p.add(text1,grid);
		grid.gridx = 0;
		grid.gridy = 1;
		p.add(label2,grid);
		grid.gridx = 1;
		p.add(text2,grid);
		grid.gridx = 0;
		grid.gridy = 2;
		p.add(vendaAcumulada,grid);
		grid.gridx = 1;
		p.add(rankVenda,grid);
		grid.gridx = 2;
		p.add(acumuladoDia,grid);
		// add grid
		add(p);
		
		vendaAcumulada.setEnabled(true);
		rankVenda.setEnabled(true);
		acumuladoDia.setEnabled(true);
		
		vendaAcumulada.addActionListener(this);
		rankVenda.addActionListener(this);
		acumuladoDia.addActionListener(this);
		
		//JOptionPane.showMessageDialog(null, "textao");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String texto;
		//inserir graficos
		if(arg0.getSource()== vendaAcumulada) {
			//insere gráfico vendaAcumulada
			texto = ("id loja: " + text1.getText() + " Mês: " + text2.getText());
			JOptionPane.showMessageDialog(null, texto);
		}else if (arg0.getSource()== rankVenda){
			//insere gráfico vendaAcumulada
			texto = ("id loja: " + text1.getText() + " Mês: " + text2.getText());
			JOptionPane.showMessageDialog(null, texto);
		}else if (arg0.getSource()== acumuladoDia) {
			//insere gráfico vendaAcumulada
			texto = ("id loja: " + text1.getText() + " Mês: " + text2.getText());
			JOptionPane.showMessageDialog(null, texto);
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		dispose();
        System.exit(0);
	}

	@Override
	public void windowActivated(WindowEvent e) {	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {	}

	@Override
	public void windowDeiconified(WindowEvent e) {	}

	@Override
	public void windowIconified(WindowEvent e) {	}

	@Override
	public void windowOpened(WindowEvent e) {	}
	
	
}
/*



telaConsulta tela = new telaConsulta();
tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
tela.setSize(600,400);
tela.setVisible(true);

*/