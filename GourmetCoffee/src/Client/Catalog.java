package Client;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.AsyncBoxView.ChildLocator;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class Catalog {

	private ArrayList<Product> localProducts;

	public Catalog() {

		localProducts = new ArrayList<Product>();
		setProducts();
	}

	//get the products.
	public ArrayList<Product> getProducts(){
		return localProducts;
	}

	//add product to the catalog.
	public void addProduct(Product product){
		localProducts.add(product);

	}

	//get the iterator of the catalog.
	public Iterator<Product> getIterator(){
		return localProducts.iterator();
	}

	//search the product by name.
	public Product getProduct(String name){

		Iterator<Product> it = getIterator(); 


		while(it.hasNext()){
			Product product = (Product) it.next();
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	//get the number of the products.
	public int getNumberOfProducts(){
		return localProducts.size();
	}

	//get the codes of the string.
	public String[] getCodes(){
		String[] str = new String[getNumberOfProducts()];
		int i = 0;
		for(Product product:localProducts){

			str[i++] = product.getCode();

		}
		return str;
	}

	// set the products information to the catalog.
	public void setProducts(){

		SAXBuilder saxBuilder = new SAXBuilder();
		InputStream in;
		try {
			// get the XML of products info.
			in = new FileInputStream("src/resource/Products.xml");
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");

			//get the document of XML
			Document document = (Document) saxBuilder.build(isr);
			//get the root element.
			Element rootElement = document.getRootElement();
			//get the product list.
			List<Element> productList = rootElement.getChildren();
			//get all the element in the product list.
			for(Element element: productList){

				// if the element is coffee, set the attributes belonging to coffee in the arraylist<Product>.
				if (element.getName() == "Coffee") {

					Coffee coffee = new Coffee();
					//get the child elements of the coffee product.
					List<Element> productChilds = element.getChildren();

					coffee.setCode(element.getAttributes().get(0).getValue());
					for (Element child:productChilds) {
						if (child.getName().equals("name")) {
							coffee.setName(child.getValue());
						}else if (child.getName().equals("price")) {
							coffee.setPrice(new Double(child.getValue()));
						}else if(child.getName().equals("temperature")){
							coffee.setTemperature(child.getValue());
						}else if (child.getName().equals("origin")) {
							coffee.setOrigin(child.getValue());
						}else {
							System.out.println("Here is an Error");
						}
					}
					// add the coffee to the catalog.
					localProducts.add(coffee);

					// if the element is tea milk, set the attributes belonging to tea milk in the arraylist<Product>.
				}else if (element.getName() == "TeaMilk"){

					TeaMilk teaMilk = new TeaMilk();
					//get the child elements of the tea milk product.
					List<Element> productChilds = element.getChildren();

					teaMilk.setCode(element.getAttributes().get(0).getValue());
					for (Element child:productChilds) {
						if (child.getName().equals("name")) {
							teaMilk.setName(child.getValue());
						}else if (child.getName().equals("price")) {
							teaMilk.setPrice(new Double(child.getValue()));
						}else if(child.getName().equals("sweetness")){
							teaMilk.setSweetness(new Boolean(child.getValue()));
						}else {
							System.out.println("Here is an Error");
						}
					}		
					// add the tea milk to the catalog.
					localProducts.add(teaMilk);
				}		
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	// get all the names of the products.
	public ArrayList<String> getProductsName(){
		ArrayList<String> names = new ArrayList<>();

		for(Product product: localProducts){
			names.add(product.getName());
		}
		return names;
	}


}
