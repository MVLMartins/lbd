import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;;


public class telaConsulta extends JFrame implements WindowListener, ActionListener{

	private JLabel labelIdVendedor;
	private JLabel labelMes;
	private JLabel labelBanco;
	private JTextField textIp;
	private JTextField textPorta;
	private JTextField textNomeBanco;
	private JTextField textUsuario;
	private JTextField textSenha;
	private JLabel labelIp;
	private JLabel labelPorta;
	private JLabel labelNomeBanco;
	private JLabel labelUsuario;
	private JLabel labelSenha;
	private JTextField textIdVendedor;
	private JTextField textMes;
	private JButton dadosBanco;
	private JButton vendaAcumulada;
	private JButton rankVenda;
	private JButton acumuladoDia;
	private ConnectDb bd;
	
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
	
	public telaConsulta(ConnectDb bd) {
		super("Consultas");
		
		setLayout(new FlowLayout());
		
		
		this.bd=bd;
		labelIdVendedor = new JLabel("ID Loja:");
		textIdVendedor = new JTextField(2);
		labelMes = new JLabel("M�s de Consulta:");
		textMes = new JTextField(2);
		labelIp = new JLabel("IP do banco:");
		textIp = new JTextField(20);
		labelPorta = new JLabel("Porta do banco:");
		textPorta = new JTextField(20);
		labelNomeBanco = new JLabel("Nome do banco:");
		textNomeBanco = new JTextField(20);
		labelUsuario = new JLabel("Usuario do banco:");
		textUsuario = new JTextField(20);
		labelSenha = new JLabel("Senha do banco:");
		textSenha = new JTextField(20);
		labelBanco = new JLabel("");
		
		dadosBanco = new JButton("Set Configuracoes do banco");
		vendaAcumulada = new JButton("Venda Acumulada");
		rankVenda = new JButton("Rank de Vendas");
		acumuladoDia = new JButton("Acumulado por Dia");
		
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		// arrumando grid de impress�o em tela
		//ip
		grid.gridx = 0;
		grid.gridy = 0;
		p.add(labelIp,grid);
		grid.gridx = 1;
		p.add(textIp,grid);
		//porta
		grid.gridx = 0;
		grid.gridy = 2;
		p.add(labelPorta,grid);
		grid.gridx = 1;
		p.add(textPorta,grid);
		//nome
		grid.gridx = 0;
		grid.gridy = 3;
		p.add(labelNomeBanco,grid);
		grid.gridx = 1;
		p.add(textNomeBanco,grid);
		//usuario
		grid.gridx = 0;
		grid.gridy = 4;
		p.add(labelUsuario,grid);
		grid.gridx = 1;
		p.add(textUsuario,grid);
		//senha
		grid.gridx = 0;
		grid.gridy = 5;
		p.add(labelSenha,grid);
		grid.gridx = 1;
		p.add(textSenha,grid);
		//botao
		grid.gridx = 0;
		grid.gridy = 6;
		p.add(dadosBanco,grid);
		grid.gridx = 1;
		p.add(labelBanco,grid);
		//id vendedor
		grid.gridx = 0;
		grid.gridy = 7;
		p.add(labelIdVendedor,grid);
		grid.gridx = 1;
		p.add(textIdVendedor,grid);
		//mes
		grid.gridx = 0;
		grid.gridy = 8;
		p.add(labelMes,grid);
		grid.gridx = 1;
		p.add(textMes,grid);
		//botoes grafico
		grid.gridx = 0;
		grid.gridy = 9;
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
		dadosBanco.setEnabled(true);
		
		vendaAcumulada.addActionListener(this);
		rankVenda.addActionListener(this);
		acumuladoDia.addActionListener(this);
		dadosBanco.addActionListener(this);
		
		//JOptionPane.showMessageDialog(null, "textao");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String texto;
		//inserir graficos
		if(arg0.getSource()== vendaAcumulada) {
			//insere gr�fico vendaAcumulada
			texto = ("id loja: " + textIdVendedor.getText() + " M�s: " + textMes.getText());
			JOptionPane.showMessageDialog(null, texto);
		}else if (arg0.getSource()== rankVenda){
			//insere gr�fico vendaAcumulada
			texto = ("id loja: " + textIdVendedor.getText() + " M�s: " + textMes.getText());
			JOptionPane.showMessageDialog(null, texto);
		}else if (arg0.getSource()== acumuladoDia) {
			//insere gr�fico vendaAcumulada
			texto = ("id loja: " + textIdVendedor.getText() + " M�s: " + textMes.getText());
			JOptionPane.showMessageDialog(null, texto);
		}else if (arg0.getSource()== dadosBanco) {
			bd.setIp(textIp.getText());
			bd.setBD(textNomeBanco.getText());
			bd.setPorta(Integer.parseInt(textPorta.getText()));
			bd.setUsuario(textUsuario.getText());
			bd.setSenha(textSenha.getText());
			bd.closeConnection();
			labelBanco.setText("inserido");
			JOptionPane.showMessageDialog(this, "Inserido con sucesso");
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