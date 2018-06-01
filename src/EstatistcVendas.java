
public class EstatistcVendas {

	private double semana ;
	private String dia;
	private double dataVenda;
	private int venda;
	private int part;
	private String status;
	
	
	@Override
	public String toString() {
		
		return dia + "|" + semana  + "|" + dataVenda  + "|" + venda  + "|" + part + "|" + status;
	}
	
	public EstatistcVendas(double semana,String dia,double dataVenda,int venda,	int part,String status) {
		this.setSemana(semana);
		this.setDia(dia);
		this.setDataVenda(dataVenda);
		this.setVenda(venda);
		this.setStatus(status);
		this.setPart(part);
	}

	public double getSemana() {
		return semana;
	}

	private void setSemana(double semana) {
		this.semana = semana;
	}

	public String getDia() {
		return dia;
	}

	private void setDia(String dia) {
		this.dia = dia;
	}

	public double getDataVenda() {
		return dataVenda;
	}

	private void setDataVenda(double dataVenda) {
		this.dataVenda = dataVenda;
	}

	public int getVenda() {
		return venda;
	}

	private void setVenda(int venda) {
		this.venda = venda;
	}

	public int getPart() {
		return part;
	}

	private void setPart(int part) {
		this.part = part;
	}

	public String getStatus() {
		return status;
	}

	private void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
