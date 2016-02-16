import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.stream.events.StartDocument;
import Client.*;
import Server.*;
public class Controller {

	private static LogOn logOn = null;
	private static CoffeeShop coffeeShop = new CoffeeShop();
	private static OrderConfirm settleOrder = null;
	private static Client client;
	private static ServerLogOn serverLogOn = null;
	
	public static void main(String[] args) {


		Controller controller = new Controller();

	}

	//constructor
	public Controller(){

		logOn = new LogOn();
		logOn.setVisible(true);
		
		goServer();
		toCoffeeShop();
		goBacktoLogOn();
		settletoOrder();
	}
	/*
	 * change the logOn UI to the coffeeShop UI.
	 */
	public void toCoffeeShop(){

		logOn.getLogOn().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


				int x = 0;
				//get the input code
				if (!logOn.getCode().getText().isEmpty()) {
					x = new Integer(logOn.getCode().getText());
				}

				int y = new Integer(logOn.getRamdom());

				//if the input code is the same as right code, log on to the user interface, or cast wrong
				if (x == y) {

					//turn the logOn UI to the coffeeshop UI
					coffeeShop.setVisible(true);
					logOn.getLabelErr().setText("");
					logOn.setVisible(false);

				}else{
					logOn.getLabelErr().setText("Wrong");
				}

			}
		});	
	}

	/*
	 * change back to the logOn UI.
	 */
	public void goBacktoLogOn(){

		coffeeShop.getBtnBack().addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e){

				logOn.setVisible(true);
				coffeeShop.setVisible(false);

			}
		});
	}

	/*
	 * go to the order confirm.
	 */
	public void settletoOrder(){
		coffeeShop.getSettle().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				settleOrder = new OrderConfirm(coffeeShop.getOrder());
				settleOrder.setVisible(true);
				coffeeShop.setVisible(false);
				settleOrder.setTel(logOn.getTel());
				goBacktoCoffeeShop();
				goContact();
			}
		});

	}


	/*
	 * go back to the coffee shop.
	 */
	public void goBacktoCoffeeShop(){

		settleOrder.getBack().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				coffeeShop.setVisible(true);
				settleOrder.setVisible(false);

			}
		});

	}

	/*
	 * go to client 
	 */
	public void goContact(){


		settleOrder.getContact().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				settleOrder.setVisible(false);
				client = new Client();
				//open another thread.
				new Thread(){
					@Override
					public void run() {
						client.getConnection();
					}
				}.start();
				goSettleOrder();

			}
		});
	}
	
	/*
	 * go back to the order confirm from the client.
	 */
	public void goSettleOrder(){
		client.getBack().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				client.setVisible(false);
				settleOrder.setVisible(true);
			}
		});

	}

	/*
	 * go to the server.
	 */
	public void goServer(){
		logOn.getMenuServer().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serverLogOn = new ServerLogOn();
				serverLogOn.setVisible(true);
				logOn.setVisible(false);
				goBacktoClient();
			}
		});
	}
	
	
	/*
	 * go back to the client.
	 */
	public void goBacktoClient(){
		serverLogOn.getBack().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logOn.setVisible(true);
				serverLogOn.setVisible(false);
			}
		});
	}
	

}
