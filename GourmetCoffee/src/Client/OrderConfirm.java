package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderConfirm extends JFrame {

	private JPanel contentPane;
	private JTextField textQuantity;
	private Order order;
	private JList<OrderItem> list;
	private JLabel labelErr;
	private JButton btnBack;
	private JLabel labelNameInfo;
	private Double value = 0.0;
	private JTextField textDorm;
	private JTextField textName;
	private JLabel labelValueInfo;
	private JLabel labelPriceInfo;
	private DecimalFormat df = new DecimalFormat(".0");
	private String tel;
	private OrderForm orderForm = null;
	private JButton btnContact;
	private JLabel labelnputErr;
	/**
	 * Create the frame.
	 */
	public OrderConfirm(Order order) {

		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("Confirm Order");
		this.order = order;
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 23, 138, 181);
		contentPane.add(panel);

		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


				String string = textQuantity.getText();
				boolean isNum = string.matches("[0-9]+"); 
				//judge the input is a Integer. if not,cast wrong.
				if (isNum) {
					labelErr.setText("");
					OrderItem orderItem = order.getItembyName(list.getSelectedValue()+"");
					orderItem.setQuantity(new Integer(textQuantity.getText()));
				}else{
					labelErr.setText("Wrong");

				}

				//change the value of order.
				labelValueInfo.setText(df.format(order.getTotalCost()));

			}
		});
		btnReset.setBounds(240, 145, 117, 29);
		contentPane.add(btnReset);

		btnBack = new JButton("Back");
		btnBack.setForeground(Color.BLACK);
		btnBack.setBounds(240, 178, 117, 29);
		contentPane.add(btnBack);

		list = new JList<OrderItem>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()){

					OrderItem orderItem = order.getItembyName(list.getSelectedValue()+"");
					labelPriceInfo.setText(orderItem.getProduct().getPrice() + "");
					labelNameInfo.setText(orderItem.getProduct().getName());
					textQuantity.setText(orderItem.getQuantity()+"");	

				}
			}
		});
		list.setAutoscrolls(true);

		list.setModel(new AbstractListModel() {
			ArrayList<String> values = order.getOrderItemsName();

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

		labelErr = new JLabel("");
		labelErr.setBounds(385, 85, 61, 16);
		contentPane.add(labelErr);

		JLabel labelDorm = new JLabel("Dorm:");
		labelDorm.setBounds(30, 265, 61, 16);
		contentPane.add(labelDorm);

		JLabel labelCustom = new JLabel("Name:");
		labelCustom.setBounds(30, 303, 61, 16);
		contentPane.add(labelCustom);

		JPanel info = new JPanel();
		info.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Information", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		info.setBounds(21, 219, 401, 136);
		contentPane.add(info);
		info.setLayout(null);

		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setBounds(228, 30, 117, 29);
		info.add(btnPurchase);

		btnContact = new JButton("Contact");
		btnContact.setBounds(228, 60, 117, 29);
		info.add(btnContact);
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showConfirmDialog(OrderConfirm.this, "你确认要退出程序吗？", "请确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (value == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
		});
		btnExit.setBounds(228, 90, 117, 29);
		info.add(btnExit);

		textDorm = new JTextField();
		textDorm.setBounds(54, 40, 130, 26);
		info.add(textDorm);
		textDorm.setColumns(10);

		textName = new JTextField();
		textName.setBounds(54, 79, 130, 26);
		info.add(textName);
		textName.setColumns(10);
		
		labelnputErr = new JLabel("");
		labelnputErr.setBounds(350, 35, 61, 16);
		info.add(labelnputErr);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});
		btnPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//create the xml of the order.
				SAXBuilder saxBuilder = new SAXBuilder();
				FileInputStream is;
				org.jdom2.Document document = null;
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				
				try {
					is = new FileInputStream("src/resource/Sale.xml");
					document = saxBuilder.build(is);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (JDOMException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				org.jdom2.Element rootElement = document.getRootElement();
				org.jdom2.Element element = new org.jdom2.Element("Order");
				element.setAttribute("date",sdf.format(date));
				element.setAttribute("name", textName.getText());
				element.setAttribute("dorm", textDorm.getText());
				element.setAttribute("tel", getTel()); 
				element.setAttribute("cost", df.format(order.getTotalCost()));
				
				for(OrderItem orderItem:order){

					org.jdom2.Element elem = new org.jdom2.Element(orderItem.getProduct().getName());

					org.jdom2.Element attrPrice = new org.jdom2.Element("price");
					attrPrice.setText(orderItem.getProduct().getPrice()+"");
					elem.addContent(attrPrice);

					org.jdom2.Element attrQuantity = new org.jdom2.Element("quantity");
					attrQuantity.setText(orderItem.getQuantity()+"");
					elem.addContent(attrQuantity);

					element.addContent(elem);
				}
				rootElement.addContent(element);

				Format format = Format.getCompactFormat();
				format.setIndent("");
				format.setEncoding("UTF-8");

				XMLOutputter outputter = new XMLOutputter(format);
				try {
					outputter.output(document, new FileOutputStream(new File("src/resource/Sale.xml")));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//create order form to the customer
				if (!textName.getText().isEmpty()&&!textDorm.getText().isEmpty()&&getTel()!="") {
					setVisible(false);
					orderForm = new OrderForm();
					orderForm.setVisible(true);
										String str = sdf.format(date) + "\n";
					str += "name:" + textName.getText() + "\n";
					str += "dorm:" + textDorm.getText() + "\n";
					str += "tel:"  + getTel() + "\n";
					str += "cost:" + df.format(order.getTotalCost()) + "\n";
					str += "order:" + "\n";
					for(OrderItem orderItem:order){

						str += "     " + orderItem.getProduct().getName() + ":" + "\n";
						str += "          price:" + orderItem.getProduct().getPrice() + "\n";
						str += "          quantity:" + orderItem.getQuantity() + "\n";
						
					}
					JTextArea jTextArea = new JTextArea(str);
					JScrollPane scrlpane = new JScrollPane(jTextArea);

					orderForm.getPanel().add(scrlpane);
				}else{
					labelnputErr.setText("wrong");
				}
				

			}
		});

		JPanel panelnfo = new JPanel();
		panelnfo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Order Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelnfo.setBounds(201, 24, 172, 116);
		contentPane.add(panelnfo);
		panelnfo.setLayout(null);

		JLabel labelQuantity = new JLabel("Quantity:");
		labelQuantity.setBounds(19, 85, 61, 16);
		panelnfo.add(labelQuantity);

		JLabel labelPrice = new JLabel("Price:");
		labelPrice.setBounds(19, 63, 40, 16);
		panelnfo.add(labelPrice);



		JLabel labelName = new JLabel("Name:");
		labelName.setBounds(19, 41, 40, 16);
		panelnfo.add(labelName);

		JLabel labelValue = new JLabel("Total:");
		labelValue.setBounds(20, 18, 40, 16);
		panelnfo.add(labelValue);

		textQuantity = new JTextField();
		textQuantity.setBounds(80, 80, 80, 26);
		panelnfo.add(textQuantity);
		textQuantity.setColumns(10);

		labelPriceInfo = new JLabel("");
		labelPriceInfo.setBounds(80, 63, 72, 16);
		panelnfo.add(labelPriceInfo);
		labelPriceInfo.setBorder(new LineBorder(new Color(0, 0, 0)));

		labelNameInfo = new JLabel("");
		labelNameInfo.setBounds(80, 41, 72, 16);
		panelnfo.add(labelNameInfo);
		labelNameInfo.setBorder(new LineBorder(new Color(0, 0, 0)));

		labelValueInfo = new JLabel("");
		labelValueInfo.setBounds(80, 18, 72, 16);
		panelnfo.add(labelValueInfo);
		labelValueInfo.setBorder(new LineBorder(new Color(0, 0, 0)));

		labelValueInfo.setText(df.format(order.getTotalCost()));

	}

	//get the back button.
	public JButton getBack(){
		return btnBack;
	}
	
	//set the tel number.
	public void setTel(String tel){
		this.tel = tel;
	}
	//get the tel number.
	public String getTel(){
		return tel;
	}
	//get the button to contact.
	public JButton getContact(){
		return btnContact;
	}
}
