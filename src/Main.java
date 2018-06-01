import java.util.List;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {

		ConnectDb db = new ConnectDb();
		GetResults rs = new GetResults(db);

		//exemplos de como pegar as listas
		List<EstatistcVendas> list1 = rs.query1(1, 1);
		List<RankVenda> list2 = rs.query2(1, 1);
		List<AcumuladoDia> list3 = rs.query3(1, 1);

		
		//executando aplicação
		telaConsulta tela = new telaConsulta();
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setSize(600, 200);
		tela.setVisible(true);

	}
}
