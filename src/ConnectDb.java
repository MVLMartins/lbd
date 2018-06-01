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

	public Connection conectar() {
		if(connnection!=null) return connnection;
		
		try {
			Class.forName("org.postgresql.Driver");
			connnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "matheus", "senha");
			return connnection;
		} catch (SQLException e) {
			System.out.println(e.getCause());
			System.out.print("Erro na conexao!!!!KKKKKKK");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("classe não encontrada");
			return null;
		}
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
