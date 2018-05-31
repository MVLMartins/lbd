
public class ConnectDb {

	public ConnectDb() {
		System.out.println("Padrão");
	}
	
	public ConnectDb(String ip, int porta, String BD, String Usuario, String Senha) {
		System.out.println("Padrão");
	}
	
	private String ip;
	private int porta;
	private String BD; 
	private String Usuario;
	private String Senha;
	
	
	
	
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

	
	
	
	
	
}
