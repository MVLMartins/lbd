
public class AcumuladoDia {
	
	private double dataVenda;
	private int venda;
	private int part;
	
	@Override
	public String toString() {
		return "" + dataVenda + " | " + venda + " | " + part;
	}
	
	public AcumuladoDia(double dataVenda, int venda, int part) {
		setDataVenda(dataVenda);
		setPart(part);
		setVenda(venda);
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
