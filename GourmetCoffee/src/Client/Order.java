package Client;


import java.util.Iterator;
import java.util.ArrayList;

public class Order implements Iterable<OrderItem>{

	private ArrayList<OrderItem> items;
	private String date;
	private String name;
	private String dorm;
	private String tel;
	private String cost;
	
	public Order() {
		items = new ArrayList<OrderItem>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void addItem(OrderItem orderItem){
		items.add(orderItem);
	}
	
	public void removeItem(OrderItem orderItem){
		items.remove(orderItem);
	}
	
	public Iterator<OrderItem> iterator(){
		return items.iterator();
	}
	
	public ArrayList<OrderItem> getItems(){
		return items;
	}
	
	
	//get the same item by order item.
	public OrderItem equals(OrderItem orderItem){
		Iterator<OrderItem> it = iterator();
		while(it.hasNext()){
			OrderItem item = (OrderItem) it.next();
			if (item.getProduct().getName() == orderItem.getProduct().getName()) {
				return item;
			}
		}
		
		return null;
		
	}
	
	//get the same item by name.
	public OrderItem getItembyName(String name){
		
		for(OrderItem orderItem: items){
			
			if (orderItem.getProduct().getName().equals(name)) {
				return orderItem;
			}
			
		}
			
		return null;
	}		
	

	//get the item number.
	public int getNumberOfItems(){
		
		return items.size();
		
	}
	
	//get the total cost of the order.
	public double getTotalCost(){
		
		double totalCost = 0;
		Iterator<OrderItem> it = iterator();
		while(it.hasNext()){
			OrderItem item = (OrderItem) it.next();
			totalCost += item.getValue();
		}	
		return totalCost;
	}
	
	
	//get the name of the item in the order.
	public ArrayList<String> getOrderItemsName(){
		ArrayList<String> itemsName = new ArrayList<>();
		Iterator<OrderItem> it = iterator();
		while(it.hasNext()){
			OrderItem item = (OrderItem) it.next();
			itemsName.add(item.getProduct().getName());
		}
		return itemsName;
	}
	
}
