package Server;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Client.Order;
import Client.OrderItem;
public class Server extends JFrame {

	private JPanel contentPane;
	private JButton btnContact;
	private JPanel panel;
	private JLabel logoLabel = new JLabel();
	private JPanel panelSale;
	private JList<String> list;
	private Sale sale;
	private ContactServer contactServer;
	/**
	 * Create the frame.
	 */
	public Server() {


		sale = new Sale();
		setAlwaysOnTop(true);

		setTitle("Sun café");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 100, 450, 310);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnContact = new JButton("Contact");
		btnContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				setVisible(false);
				contactServer = new ContactServer();
				//open another thread.
				new Thread(){
					@Override
					public void run() {
						contactServer.getConnection();
					}
				}.start();
				contactServer.getBack().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						contactServer.setVisible(false);
						setVisible(true);
					}
				});

			}
		});
		btnContact.setBounds(234, 204, 117, 29);
		contentPane.add(btnContact);

		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int value = JOptionPane.showConfirmDialog(Server.this, "你确认要退出程序吗？", "请确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (value == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(234, 243, 117, 29);
		contentPane.add(btnExit);


		JButton btnView = new JButton("View");
		btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!list.isSelectionEmpty()) {
					setVisible(false);

					int index = list.getSelectedIndex();
					Order order = sale.getSale().get(index);
					OrderInfo orderInfo = new OrderInfo();
					orderInfo.setVisible(true);
					orderInfo.getTextDate().setText(order.getDate());
					orderInfo.getTextDorm().setText(order.getDorm());
					orderInfo.getTextName().setText(order.getName());
					orderInfo.getTextCost().setText(order.getCost());	
					orderInfo.getTextTel().setText(order.getTel());
					String string = "";
					for(OrderItem orderItem:order.getItems()){

						string += orderItem.getProduct().getName() + "\n";
						string += "   Price:" + orderItem.getProduct().getPrice() + "\n"; 
						string += "   Quantity:" + orderItem.getQuantity() + "\n";
						JTextArea jTextArea = new JTextArea(string);
						JScrollPane scrlpane = new JScrollPane(jTextArea);

						add(scrlpane);

					}
					JTextArea jTextArea = new JTextArea(string);
					JScrollPane scrlpane = new JScrollPane(jTextArea);

					orderInfo.getProductsPanel().add(scrlpane);



					orderInfo.getBack().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							orderInfo.setVisible(false);
							setVisible(true);

						}
					});


				}
			}
		});
		btnView.setBounds(234, 163, 117, 29);
		contentPane.add(btnView);

		panel = new JPanel();
		panel.setBounds(234, 18, 120, 120);


		ImageIcon icon = new ImageIcon("src/image/logo.jpg");
		logoLabel.setIcon(icon);
		logoLabel.setSize(120,120);
		panel.add(logoLabel);	
		getContentPane().add(panel);

		panelSale = new JPanel();
		panelSale.setBorder(new TitledBorder(null, "Sale", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSale.setBounds(30, 17, 151, 218);
		contentPane.add(panelSale);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				sale = new Sale();
				list.setModel(new AbstractListModel() {
					ArrayList<String> values = sale.getOrdersName();
					public int getSize() {
						return values.size();
					}
					public Object getElementAt(int index) {
						return values.get(index);
					}
				});					
			}
		});
		btnRefresh.setBounds(53, 243, 117, 29);
		contentPane.add(btnRefresh);


		list = new JList<String>();
		list.setAutoscrolls(true);
		list.setModel(new AbstractListModel() {
			ArrayList<String> values = sale.getOrdersName();
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		JScrollPane scrlpane=new JScrollPane(list);
		panelSale.setLayout(new BorderLayout(0, 0));
		panelSale.add(scrlpane);



	}

	public JButton getContact(){
		return btnContact;
	}

}
