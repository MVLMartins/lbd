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
		telaConsulta tela = new telaConsulta(db,query1,query2,query3);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setSize(1000, 600);
		tela.setVisible(true);
		
		//InsercaoAleatoria a = new InsercaoAleatoria(db);
		//a.criaFuncao();
	}
}
