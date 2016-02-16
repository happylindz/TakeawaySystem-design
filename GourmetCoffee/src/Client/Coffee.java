package Client;


public class Coffee extends Product {

	private String temperature;
	private String origin;
	
	public Coffee(String name,String code,Double price,String temperature,String origin) {
		
		super(name, code, price);
		this.temperature = temperature;
		this.origin = origin;
		setType("Coffee");
	
	}


	public Coffee() {
		// TODO Auto-generated constructor stub
		setType("Coffee");
	}



	public String getTemperature() {
		return temperature;
	}


	public void setTemperature(String temperative) {
		this.temperature = temperative;
	}


	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
}
