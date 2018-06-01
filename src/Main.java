import java.util.List;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {

		//defaut
		ConnectDb db = new ConnectDb();
		
		//Logins e senhas do banco
		//ConnectDb db = new ConnectDb(ip, porta, bD, usuario, senha);
		
		//objeto que executa as querys
		GetResults rs = new GetResults(db);

		//exemplos de como pegar as listas
		List<EstatistcVendas> query1 = rs.query1(1, 2);
		List<RankVenda> query2 = rs.query2(1, 2);
		List<AcumuladoDia> query3 = rs.query3(1, 2);

		
		//executando aplicação
		telaConsulta tela = new telaConsulta();
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setSize(600, 200);
		tela.setVisible(true);

	}
}
