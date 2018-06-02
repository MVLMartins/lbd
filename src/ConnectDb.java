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

	private String ip = "localhost";
	private int porta = 5432;
	private String BD = "postgres";
	private String usuario = "matheus";
	private String senha  = "senha";
	private Connection connection;

	public void closeConnection() {
		try {
			if(!connection.isClosed()) connection.close();
		}catch (Exception e) {
			System.out.println("erro: " + e.getClass());
		}
	}
		
	
	private void conectar() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://"+ip+":"+porta+"/"+BD, usuario,senha);
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

	        if(connection == null || connection.isClosed())
	        {
	            conectar();
	        }
	        s = connection.prepareStatement(sql);
	        boolean a = s.execute();
	        s.close ();
	        System.out.println (a + " foi o retorno na função");
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	        if (connection != null)
	        {
	            try
	            {
	                connection.close ();
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
	        if(this.connection == null || this.connection.isClosed())
	        {
	            conectar();
	        }
	    
	        statement = connection.createStatement();
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
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Connection getConnnection() {
		return connection;
	}

}
