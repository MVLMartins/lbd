import java.util.List;

public class Main {
	public static void main(String[] args) {
		
		ConnectDb db = new ConnectDb();
		GetResults rs = new GetResults(db);
		
		List<EstatistcVendas> list = rs.query1(1, 1);
		
		if(list.size()==0) {
			System.out.println("vazio");
		}
		for(EstatistcVendas a : list) {
			System.out.println(a);
		}
		
	}
}
