package Client;


public class TeaMilk extends Product{
	
	private boolean sweetness;
	
	public TeaMilk(String name,String code,double price,boolean sweetness) {
		// TODO Auto-generated constructor stub
		super(name, code, price);
		this.sweetness = sweetness;
		setType("TeaMilk");
	}

	public TeaMilk(){
		setType("TeaMilk");
	}
	
	public boolean isSweetness() {
		return sweetness;
	}

	public void setSweetness(boolean sweetness) {
		this.sweetness = sweetness;
	}

	
	
	
}
