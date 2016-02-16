import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import Client.*;

public class XMLParse {

	private static DocumentBuilder db;
	private static Document document;

	
	public XMLParse() {
		db = getDocumentBuilder();
		document = db.newDocument();
	}

	// create document builder.
	public static DocumentBuilder getDocumentBuilder() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}

	//add the new product to the products store.
	public org.w3c.dom.Element addProduct(Product product){
		
		String name = product.getName();
		String code = product.getCode();
		double price = product.getPrice();
		String temperature = "";
		String origin = "";
		boolean sweetness = false;
		org.w3c.dom.Element productElem = null;
		
		if (product instanceof Coffee) {
			temperature = ((Coffee) product).getTemperature();
			origin = ((Coffee) product).getOrigin();	
			productElem = document.createElement("Coffee");
		}else if (product instanceof TeaMilk) {
			sweetness = ((TeaMilk) product).isSweetness();
			productElem = document.createElement("TeaMilk");
		}
		
		
		productElem.setAttribute("code", code);
		
		org.w3c.dom.Element nameElem = document.createElement("name");
		nameElem.setTextContent(name);
		productElem.appendChild(nameElem);
		
		org.w3c.dom.Element priceElem = document.createElement("price");
		priceElem.setTextContent(price + "");
		productElem.appendChild(priceElem);
		
		if (productElem.getTagName() == "Coffee") {
			
			org.w3c.dom.Element tempElem = document.createElement("temperature");
			tempElem.setTextContent(temperature);
			productElem.appendChild(tempElem);
			
			org.w3c.dom.Element originElem = document.createElement("origin");
			originElem.setTextContent(origin);
			productElem.appendChild(originElem);
			
		}else if (productElem.getTagName() == "TeaMilk") {
			org.w3c.dom.Element sweetElem = document.createElement("sweetness");
			sweetElem.setTextContent(sweetness + "");
			productElem.appendChild(sweetElem);
		}
		
		
		
		return productElem;
	}
	
	
	//create the root elem named productStore.
	public org.w3c.dom.Element createProductStore(){
		return document.createElement("productStore");
	}
	
	//create the XML file.
	public void createXMLFile(Document document){
		
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf;
		try {
			
			tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT,"yes");
			tf.transform(new DOMSource(document), new StreamResult(new File("order.xml")));

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//get the document.
	public Document getDocument(){
		return document;
	}
	
//	public static void main(String[] args){
//		
//		XMLParse test = new XMLParse();
//		Document document = test.getDocument();
////		org.w3c.dom.Element productStore = test.createProductStore();
//		
//		productStore.appendChild(test.addProduct(new Coffee("Latte", "A001", 8.5, "high", "American")));
//		productStore.appendChild(test.addProduct(new Coffee("Fancy", "A002", 12.4, "low", "Canada")));
//		productStore.appendChild(test.addProduct(new Coffee("Mocha", "A003", 12.9, "normal", "China")));
//		productStore.appendChild(test.addProduct(new Coffee("Espresso", "A004", 18.5, "high", "China")));
//		productStore.appendChild(test.addProduct(new Coffee("Cappuccino", "A005", 10.2, "high", "Indian")));
//		productStore.appendChild(test.addProduct(new Coffee("Americano", "A006", 15.0, "high", "Italy")));
//		productStore.appendChild(test.addProduct(new Coffee("Chocolate", "A007", 9.5, "low", "China")));
//		productStore.appendChild(test.addProduct(new Coffee("Flat", "A008", 6.5, "high", "Singapo")));
//		productStore.appendChild(test.addProduct(new Coffee("Matcha", "A009", 12.7, "low", "China")));
//		productStore.appendChild(test.addProduct(new Coffee("Vanilla", "A010", 12.7, "normal", "Brazil")));
//		productStore.appendChild(test.addProduct(new TeaMilk("Caramel","B001",7.77,false)));
//		productStore.appendChild(test.addProduct(new TeaMilk("Banana","B002",15.7,false)));
//		productStore.appendChild(test.addProduct(new TeaMilk("Orange", "B003", 11.5, true)));
//		productStore.appendChild(test.addProduct(new TeaMilk("Mango", "B004", 7.65, false)));
//		productStore.appendChild(test.addProduct(new TeaMilk("Blueberry", "B005", 5.5, true)));
//		productStore.appendChild(test.addProduct(new TeaMilk("Cocoa", "B006", 9.9, true)));
//		
//		document.appendChild(productStore);
//
//		test.createXMLFile(document);
//	}
	
	
	
}
