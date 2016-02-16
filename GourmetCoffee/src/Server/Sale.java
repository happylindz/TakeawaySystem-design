package Server;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Client.Order;
import Client.OrderItem;
import Client.Product;

public class Sale{
	private ArrayList<Order> sale;

	public Sale()
	{
		this.sale = new ArrayList<>();
		loadItems();
	}

	public double getTotalCost()
	{
		double d = 0.0D;
		for (Order order : this.sale) {
			d += order.getTotalCost();
		}
		return d;
	}

	public void loadItems(){
		SAXBuilder saxBuilder = new SAXBuilder();
		InputStream in;

		// get the XML of products info.
		try {
			in = new FileInputStream("src/resource/Sale.xml");

			InputStreamReader isr = new InputStreamReader(in, "UTF-8");

			//get the document of XML
			Document document = (Document) saxBuilder.build(isr);
			//get the root element.
			Element rootElement = document.getRootElement();
			//get the order list.
			List<Element> orderList = rootElement.getChildren();

			//get all the element in the product list.
			for(Element element: orderList){
				Order order = new Order();
				order.setDate(element.getAttribute("date").getValue());
				order.setName(element.getAttribute("name").getValue());
				order.setDorm(element.getAttribute("dorm").getValue());
				order.setCost(element.getAttribute("cost").getValue());
				order.setTel(element.getAttribute("tel").getValue());
				
				List<Element> localOrder = element.getChildren();

				for(Element elem:localOrder){
					
					OrderItem orderItem = new OrderItem();
					Product product = new Product();
					product.setName(elem.getName());
					
					List<Element> attrs = elem.getChildren();
					for(Element attr:attrs){
						
						if (attr.getName() == "price") {
							
							product.setPrice(new Double(attr.getValue()));
							
						}else if (attr.getName() == "quantity") {
							
							orderItem.setQuantity(new Integer(attr.getValue()));
						
						}
						
					}
					orderItem.setProduct(product);
					order.addItem(orderItem);
				}
				sale.add(order);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
			

	}

	public ArrayList<Order> getSale(){
		return sale;
	}
	
	
	public ArrayList<String> getOrdersName(){
		ArrayList<String> names = new ArrayList<>();
		int i = 1;
		for(Order order:getSale()){

			names.add("Order" + i++);
		}
		return names;
	}
	
}
