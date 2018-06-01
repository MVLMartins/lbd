import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class GetResults implements DAO {

	private ConnectDb db;
	
	public GetResults(ConnectDb db) {
		this.db = db;
	}
	
	public List<EstatistcVendas> query1(int idLoja,int mes){
		criaFunctionStatusDia(db);
		criaFunctionVendaAcumulada();
		
		List<EstatistcVendas> list = new ArrayList<>();
		Connection con = db.conectar();
		String sql = "select * from vendaAcumulada(?,?);";
		PreparedStatement stat;
		ResultSet r;
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1,Integer.toString(idLoja));
			stat.setString(2,Integer.toString(mes));
			r = stat.executeQuery();
			while (r.next()) {
				double semana = r.getDouble(1);
				String dia = r.getString(2);
				double dataVenda = r.getDouble(3);
				int venda = r.getInt(4);
				int part = r.getInt(5);
				String status = r.getString(6);
				EstatistcVendas est = new EstatistcVendas(semana, dia, dataVenda, venda, part, status);
				list.add(est);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getCause());
		}
		
		return list;
	}
	
	private void criaFunctionVendaAcumulada() {
		Connection con = db.conectar();
		String sql = "CREATE OR REPLACE FUNCTION vendaAcumulada(numeric,numeric)	-- id_loja e mês\n" + 
				"    RETURNS  table(semana float, dia char(3), dataVenda float, venda numeric, part numeric, status char(7)) -- precisa alterar pra usar...\n" + 
				"	AS \n" + 
				"	$$	\n" + 
				"		SELECT\n" + 
				"			trunc((EXTRACT(DAY FROM NOTA_FISCAL.DATA) - 1) / 7 + 1) as semana,\n" + 
				"			(CASE \n" + 
				"					WHEN EXTRACT(DOW FROM NOTA_FISCAL.DATA) = 0	THEN 'DOM'\n" + 
				"					WHEN EXTRACT(DOW FROM NOTA_FISCAL.DATA) = 1 THEN 'SEG'\n" + 
				"					WHEN EXTRACT(DOW FROM NOTA_FISCAL.DATA) = 2 THEN 'TER'\n" + 
				"					WHEN EXTRACT(DOW FROM NOTA_FISCAL.DATA) = 3 THEN 'QUA'\n" + 
				"					WHEN EXTRACT(DOW FROM NOTA_FISCAL.DATA) = 4 THEN 'QUI'\n" + 
				"					WHEN EXTRACT(DOW FROM NOTA_FISCAL.DATA) = 5 THEN 'SEX'\n" + 
				"					ELSE 'SÁB'\n" + 
				"			END) as dia,\n" + 
				"			EXTRACT(DAY FROM NOTA_FISCAL.DATA) as dataVenda,\n" + 
				"			Quantidade as venda,\n" + 
				"			trunc(Quantidade/SUM(Quantidade),2) as part,		-- testar com SUM(Quantidad) em vez de totalAcumulado\n" + 
				"			statusDia(Quantidade/SUM(Quantidade)) as status\n" + 
				"		from NOTA_FISCAL\n" + 
				"		join 	TRANSACAO ON TRANSACAO.ID_NOTA_FISCAL = NOTA_FISCAL.ID\n" + 
				"		where 	REL_ID = $1\n" + 
				"		GROUP BY 	NOTA_FISCAL.DATA,\n" + 
				"					Quantidade;\n" + 
				"	$$\n" + 
				"LANGUAGE SQL;";
		
		try {
			con.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.getCause());
		}
		
		
	}
	
	private void criaFunctionStatusDia(ConnectDb db) {
		Connection con = db.conectar();
	
		String sql = "CREATE OR REPLACE FUNCTION statusDia(parcial float) RETURNS char(7) AS $$\n" + 
			"Begin\n" + 
			"				CASE \n" + 
			"					WHEN parcial < 4.00 THEN RETURN 'FRACO';\n" + 
			"					WHEN parcial < 8.00 THEN RETURN 'MÉDIO';\n" + 
			"					WHEN parcial > 8.00 THEN RETURN'ÓTIMO';\n" + 
			"				END CASE;\n" + 
			"END;\n" + 
			"$$ LANGUAGE plpgsql;";
			
		try {
			con.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.getCause());
		}
	}
	
	
}
