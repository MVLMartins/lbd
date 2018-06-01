import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class GetResults {

	private ConnectDb db;

	public GetResults(ConnectDb db) {
		this.db = db;
	}
	
	public void criaFuncao() {
		String sql = "CREATE OR REPLACE FUNCTION statusDia(parcial float) RETURNS char(7) AS $$\n" + 
				"Begin\n" + 
				"				CASE \n" + 
				"					WHEN parcial < 4.00 THEN RETURN 'FRACO';\n" + 
				"					WHEN parcial < 8.00 THEN RETURN 'MÉDIO';\n" + 
				"					WHEN parcial > 8.00 THEN RETURN'ÓTIMO';\n" + 
				"				END CASE;\n" + 
				"END;\n" + 
				"$$ LANGUAGE plpgsql;";
		db.updateQuery(sql);
	}
	
	
	
	
	public List<EstatistcVendas> query1(int idLoja,int mes){
		criaFuncao();
		String sql = "SELECT\n" + 
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
				"		where 	REL_ID = " + idLoja + "\n" + 
				"		GROUP BY 	NOTA_FISCAL.DATA,\n" + 
				"					Quantidade;";
		List<EstatistcVendas> ids = null;
		
		try {
			ids = db.getResultsQuery(sql, new IResultSetHandler<List<EstatistcVendas>>() {
		        public List<EstatistcVendas> handle(ResultSet rs) throws SQLException {
		            List<EstatistcVendas> ids = new ArrayList<EstatistcVendas>();
		            while(rs.next()) {
		            	double semana = rs.getDouble(1) ;
		            	String dia = rs.getString(2);
		            	double dataVenda = rs.getDouble(3);
		            	int venda = rs.getInt(4);
		            	double part = rs.getDouble(5);
		            	String status = rs.getString(6);
		            	EstatistcVendas est = new EstatistcVendas(semana,dia,dataVenda,venda,part,status);
		            	ids.add(est);
		            }
		            return ids;
		        }
		    });
		}catch (Exception e) {
			System.out.println(e.getCause());
		}
		
		return ids;
	}
	
	public List<RankVenda> query2(int idLoja,int mes){
		String sql = "		SELECT\n" + 
				"			rank() OVER (ORDER BY Quantidade DESC) as rk,\n" + 
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
				"			trunc(Quantidade/SUM(Quantidade),2) as part\n" + 
				"		from NOTA_FISCAL\n" + 
				"		join 	TRANSACAO ON TRANSACAO.ID_NOTA_FISCAL = NOTA_FISCAL.ID\n" + 
				"		where 	REL_ID = " + idLoja + "\n" +
				"		GROUP BY 	NOTA_FISCAL.DATA,\n" + 
				"					Quantidade;\n";
		List<RankVenda> ids = null;
		
		try {
			ids = db.getResultsQuery(sql, new IResultSetHandler<List<RankVenda>>() {
		        public List<RankVenda> handle(ResultSet rs) throws SQLException {
		            List<RankVenda> ids = new ArrayList<RankVenda>();
		            while(rs.next()) {
		            	long rk = rs.getLong(1);
						String dia = rs.getString(2);
						double dataVenda = rs.getDouble(3);
						int venda = rs.getInt(4);
						int part = rs.getInt(5);
						
						RankVenda est = new RankVenda(rk, dia, dataVenda, venda, part);
						ids.add(est);
		            }
		            return ids;
		        }
		    });
		}catch (Exception e) {
			System.out.println(e.getCause());
		}
		
		return ids;
	}
	
	
	public List<AcumuladoDia> query3(int idLoja,int mes){
		String sql = "SELECT\n" + 
				"			EXTRACT(DAY FROM NOTA_FISCAL.DATA) as dataVenda,\n" + 
				"			Quantidade as venda,\n" + 
				"			trunc(Quantidade/SUM(Quantidade),2) as part\n" + 
				"		from NOTA_FISCAL\n" + 
				"		join 	TRANSACAO ON TRANSACAO.ID_NOTA_FISCAL = NOTA_FISCAL.ID\n" + 
				"		where 	REL_ID = " + idLoja + "\n"+  
				"		GROUP BY 	NOTA_FISCAL.DATA,\n" + 
				"					Quantidade;";
		List<AcumuladoDia> ids = null;
		
		try {
			ids = db.getResultsQuery(sql, new IResultSetHandler<List<AcumuladoDia>>() {
		        public List<AcumuladoDia> handle(ResultSet rs) throws SQLException {
		            List<AcumuladoDia> ids = new ArrayList<AcumuladoDia>();
		            while(rs.next()) {
		            	double dataVenda = rs.getDouble(1); 
		            	int venda = rs.getInt(2);
		            	int part = rs.getInt(3);
		            	AcumuladoDia est = new AcumuladoDia( dataVenda, venda, part);
		            	ids.add(est);
		            }
		            return ids;
		        }
		    });
		}catch (Exception e) {
			System.out.println(e.getCause());
		}
		
		return ids;
	}
	
	
}
