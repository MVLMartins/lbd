
public class RankVenda {

	private long rk;
	private String dia;
	private double dataVenda;
	private int venda;
	private int part;

	@Override
	public String toString() {
		return ""+ rk + " | " + dia + " | " + dataVenda + " | " + venda + " | " + part;
	}
	public RankVenda(long rk, String dia, double dataVenda, int venda, int part) {
		setRk(rk);
		setDia(dia);
		setDataVenda(dataVenda);
		setVenda(venda);
		setPart(part);
	}

	public long getRk() {
		return rk;
	}

	private void setRk(long rk) {
		this.rk = rk;
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

}
