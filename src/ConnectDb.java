import java.sql.*;

public class ConnectDb {

	public ConnectDb() {
		System.out.println("Iniciação Padrão");
	}

	public ConnectDb(String ip, int porta, String bD, String usuario, String senha) {
		setIp(ip);
		setBD(bD);
		setPorta(porta);
		setUsuario(usuario);
		setSenha(senha);
		System.out.println("Inciciação com atributos");
	}

	private String ip;
	private int porta;
	private String BD;
	private String Usuario;
	private String Senha;
	private Connection connnection;

	private void conectar() {
		try {
			Class.forName("org.postgresql.Driver");
			connnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "matheus", "senha");
		} catch (SQLException e) {
			System.out.println(e.getCause());
			System.out.print("Erro na conexao!!!!KKKKKKK");
		} catch (ClassNotFoundException e) {
			System.out.println("classe não encontrada");
		}
	}

	public void updateQuery(String sql)
	{
	    PreparedStatement s;
	    try
	    {

	        if(connnection == null || connnection.isClosed())
	        {
	            conectar();
	        }
	        s = connnection.prepareStatement(sql);
	        boolean a = s.execute();
	        s.close ();
	        System.out.println (a + " foi o retorno na função");
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        if (connnection != null)
	        {
	            try
	            {
	                connnection.close ();
	                System.out.println ("Database connection terminated");
	            }
	            catch (Exception se) {System.out.println("n sei o q aconteceu");}
	        }
	    }

	}

	
	public <T> T getResultsQuery(String sql, IResultSetHandler<T> resultSetHandler )  {
	    Statement statement = null;
	    try
	    {
	        if(this.connnection == null || this.connnection.isClosed())
	        {
	            conectar();
	        }
	    
	        statement = connnection.createStatement();
	        final ResultSet rs = statement.executeQuery(sql);
	        final T result = resultSetHandler.handle(rs);
	        return result;
	    }catch (SQLException e) {
	    	System.out.println("erro"+e.getCause());
	    }finally {
	        if(statement != null) {
	            try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
		return null;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getBD() {
		return BD;
	}

	public void setBD(String bD) {
		BD = bD;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public Connection getConnnection() {
		return connnection;
	}

}
