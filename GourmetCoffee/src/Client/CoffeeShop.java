package Client;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CoffeeShop extends JFrame {

	private JPanel contentPane;
	private JTextField textQuantity;
	private JPanel panel;
	private JButton btnPurchase;
	private JButton btnSettle;
	private JButton btnExit;
	private JPanel panelInfo;
	private JPanel panelImage;
	private JList list;
	private Catalog catalog;
	private JLabel labelType;
	private JLabel labelTypeInfo;
	private JButton btnBack;
	private JLabel labelName;
	private JLabel labelNameInfo;
	private JLabel labelCode;
	private JLabel labelCodeInfo;
	private JLabel lblPrice;
	private JLabel labelPriceInfo;
	private JLabel labelTemp;
	private JLabel labelTempInfo;
	private JLabel labelOrigin;
	private JLabel labelOriginInfo;
	private JLabel labelSweet;
	private JLabel labelSweetInfo;
	private JLabel imageLabel = new JLabel();
	private JLabel label;
	private Order order;

	/**
	 * Create the frame.
	 */
	public CoffeeShop() {

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		catalog = new Catalog();
		order = new Order();

		setAlwaysOnTop(true);
		setTitle("Coffee Shop");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel CoffeeShop = new JPanel();
		CoffeeShop.setOpaque(false);
		CoffeeShop.setForeground(Color.LIGHT_GRAY);
		CoffeeShop.setBackground(Color.BLACK);
		CoffeeShop.setBounds(6, 6, 622, 471);
		contentPane.add(CoffeeShop);
		CoffeeShop.setLayout(null);

		panelInfo = new JPanel();
		panelInfo.setBorder(new TitledBorder(null, "Product Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfo.setBounds(203, 23, 353, 175);
		CoffeeShop.add(panelInfo);
		panelInfo.setLayout(null);

		labelType = new JLabel("Type:");
		labelType.setBounds(24, 30, 61, 16);
		panelInfo.add(labelType);

		labelTypeInfo = new JLabel("");
		labelTypeInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelTypeInfo.setBounds(84, 30, 72, 16);
		panelInfo.add(labelTypeInfo);

		labelName = new JLabel("Name:");
		labelName.setBounds(24, 58, 61, 16);
		panelInfo.add(labelName);

		labelNameInfo = new JLabel("");
		labelNameInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelNameInfo.setBounds(84, 58, 72, 16);
		panelInfo.add(labelNameInfo);

		labelCode = new JLabel("Code:");
		labelCode.setBounds(24, 86, 61, 16);
		panelInfo.add(labelCode);

		labelCodeInfo = new JLabel("");
		labelCodeInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelCodeInfo.setBounds(84, 86, 72, 16);
		panelInfo.add(labelCodeInfo);

		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(24, 114, 61, 16);
		panelInfo.add(lblPrice);

		labelPriceInfo = new JLabel("");
		labelPriceInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelPriceInfo.setBounds(84, 114, 72, 16);
		panelInfo.add(labelPriceInfo);

		labelTemp = new JLabel("TEMP:");
		labelTemp.setBounds(178, 30, 61, 16);
		panelInfo.add(labelTemp);

		labelTempInfo = new JLabel("");
		labelTempInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelTempInfo.setBounds(251, 30, 72, 16);
		panelInfo.add(labelTempInfo);

		labelOrigin = new JLabel("Origin:");
		labelOrigin.setBounds(178, 58, 61, 16);
		panelInfo.add(labelOrigin);

		labelOriginInfo = new JLabel("");
		labelOriginInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelOriginInfo.setBounds(251, 58, 72, 16);
		panelInfo.add(labelOriginInfo);

		labelSweet = new JLabel("Sweet:");
		labelSweet.setBounds(178, 86, 61, 16);
		panelInfo.add(labelSweet);

		labelSweetInfo = new JLabel("");
		labelSweetInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelSweetInfo.setBounds(251, 86, 61, 16);
		panelInfo.add(labelSweetInfo);

		panelImage = new JPanel();
		panelImage.setBorder(new TitledBorder(null, "Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelImage.setBounds(238, 210, 253, 225);
		CoffeeShop.add(panelImage);

		JLabel labelQuantity = new JLabel("Quantity:");
		labelQuantity.setBounds(20, 287, 61, 16);
		CoffeeShop.add(labelQuantity);

		textQuantity = new JTextField();
		textQuantity.setBounds(80, 282, 77, 26);
		CoffeeShop.add(textQuantity);
		textQuantity.setColumns(10);

		btnPurchase = new JButton("Purchase");
		btnPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				label.setText("Succeed");

				//get the selected item string.
				String str = (String) list.getSelectedValue();
				//get the selected item.
				Product product = catalog.getProduct(str);

				//judge the input quantity is empty. if empty, cast wrong.
				if (!textQuantity.getText().isEmpty()) {

					String string = textQuantity.getText();
					boolean isNum = string.matches("[0-9]+");  

					//judge the input number is a Integer not a string or double. if so,cast wrong.
					if(isNum){

						OrderItem orderItem = new OrderItem(product,new Integer(textQuantity.getText()));

						//search the product, if has it, current quantity plus the past quantity together.
						if (order.equals(orderItem) != null) {

							int localQuantity = order.equals(orderItem).getQuantity();
							order.equals(orderItem).setQuantity(localQuantity + orderItem.getQuantity());;

						}else{

							//add the new orderItem to the order.
							order.addItem(orderItem);

						}

					}else{
						label.setText("Wrong");
					}
				}else{
					label.setText("Wrong");;
				}


			}
		});

		btnPurchase.setBounds(20, 320, 117, 29);
		CoffeeShop.add(btnPurchase);

		btnSettle = new JButton("Settle");
		btnSettle.setBounds(20, 350, 117, 29);
		CoffeeShop.add(btnSettle);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showConfirmDialog(CoffeeShop.this, "你确认要退出程序吗？", "请确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (value == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
		});

		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//exit system.
				System.exit(1);
			}
		});
		btnExit.setBounds(20, 410, 117, 29);
		CoffeeShop.add(btnExit);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Catalog", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 6, 139, 269);
		CoffeeShop.add(panel);



		list = new JList<String>();
		list.setAutoscrolls(true);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				//avoid two times value change.
				if(!e.getValueIsAdjusting()){

					label.setText("");

					//get the product
					Product product =  catalog.getProduct(list.getSelectedValue()+"");
					labelTypeInfo.setText(product.getType());
					labelNameInfo.setText(product.getName());
					labelCodeInfo.setText(product.getCode());
					labelPriceInfo.setText(product.getPrice() + "");


					//judge product is coffee or tea milk, output the different value to the label.
					if (product.getType() == "Coffee") {

						labelTempInfo.setText(((Coffee)product).getTemperature());
						labelTempInfo.setBorder(new LineBorder(Color.BLACK, 1, true));
						labelOriginInfo.setText(((Coffee)product).getOrigin());
						labelOriginInfo.setBorder(new LineBorder(Color.BLACK, 1, true));
						labelSweetInfo.setText("");
						labelSweetInfo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));


					}else if (product.getType() == "TeaMilk") {

						labelSweetInfo.setText(((TeaMilk)product).isSweetness() + "");
						labelSweetInfo.setBorder(new LineBorder(Color.BLACK, 1, true));
						labelTempInfo.setText("");
						labelTempInfo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
						labelOriginInfo.setText("");
						labelOriginInfo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

					}else {
						System.out.println("an Error");
					}

					// set the image to the label to display the product appearance.
					ImageIcon icon = new ImageIcon("src/image/" + product.getName() + ".jpg");
					imageLabel.setIcon(icon);
					imageLabel.setSize(200, 200);
					panelImage.add(imageLabel);	
				}


			}
		});

		// set the product names to the display list.
		list.setModel(new AbstractListModel() {
			ArrayList<String> values = catalog.getProductsName();
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		JScrollPane scrlpane=new JScrollPane(list);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(scrlpane);

		btnBack = new JButton("Back");

		btnBack.setBounds(20, 380, 117, 29);
		CoffeeShop.add(btnBack);

		label = new JLabel(" ");
		label.setBounds(149, 324, 61, 16);
		CoffeeShop.add(label);
	}

	//get the back button.
	public JButton getBtnBack(){
		return btnBack;
	}

	// get the order.
	public Order getOrder(){
		return order;
	}

	// get the button to the settle
	public JButton getSettle(){
		return btnSettle;
	}



}
