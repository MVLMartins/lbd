import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class InsercaoAleatoria {

	private ConnectDb db;
	
	public InsercaoAleatoria(ConnectDb db) {
		this.db=db;
	}
	
	public void criaFuncao() {
		
		ArrayList<String> nomes = new ArrayList<>();
		ArrayList<String> cidades = new ArrayList<>();
		ArrayList<String> itens = new ArrayList<>();
		ArrayList<String> ruas = new ArrayList<>();
		ArrayList<String> cpf = new ArrayList<>();
		ArrayList<Integer> categoria = new ArrayList<>();
		File insersaoLojas = new File ("produto.txt");
		FileWriter fileW ;
        BufferedWriter buffW = null;
		try {
			Scanner in = new Scanner(new File("/home/matheus/eclipse-workspace/lbd/src/cidades.csv"));
			
			categoria.add(11111);
			categoria.add(22222);
			categoria.add(33333);
			categoria.add(44444);
			categoria.add(55555);
			categoria.add(66666);
			while (in.hasNextLine()) {
				cidades.add(in.nextLine());
			}
			in = new Scanner(new File("/home/matheus/eclipse-workspace/lbd/src/nomes.csv"));
			while (in.hasNextLine()) {
				nomes.add(in.nextLine());
			}
			in = new Scanner(new File("/home/matheus/eclipse-workspace/lbd/src/itens.csv"));
			while (in.hasNextLine()) {
				itens.add(in.nextLine());
			}
			in = new Scanner(new File("/home/matheus/eclipse-workspace/lbd/src/ruas.csv"));
			while (in.hasNextLine()) {
				ruas.add(in.nextLine());
			}
			in = new Scanner(new File("/home/matheus/eclipse-workspace/lbd/src/cpf.csv"));
			while (in.hasNextLine()) {
				cpf.add(in.nextLine());
			}

			fileW = new FileWriter (insersaoLojas);//arquivo para escrita
	        buffW = new BufferedWriter (fileW);
			Random r = new Random();
			Random r1 = new Random();
			Random r3 = new Random();
			for(int i=3;i<=100;i++) {
				int indiceNome = r.nextInt(itens.size());
				int cep = 333 + i;
				int indiceCidade = r3.nextInt(10000);
				int insCat = r1.nextInt(categoria.size());
				int unidade = r3.nextInt(100);
				String sql = "INSERT INTO PRODUTOS VALUES ('"+cep+"', '"+itens.get(indiceNome)+"', '"+unidade
				+"', '"+categoria.get(insCat)+"');";
				buffW.write (sql);
                buffW.newLine ();
				db.updateQuery(sql);
			}
			
		}catch (Exception e) {
			System.out.println("deu ruim");
		}finally {
			try {
				buffW.close ();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//String sql = "INSERT INTO LOJAS VALUES (1, 'Matheus Martins', 03622030, ' ', 'São Paulo', 'SP');";
		
		
		
		//db.updateQuery(sql);
	}
	
	
}
