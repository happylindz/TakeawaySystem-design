package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textDate;
	private JTextField textName;
	private JTextField textDorm;
	private JTextField textTel;
	private JTextField textCost;
	private JButton btnBack;
	private JPanel productsInfo;


	/**
	 * Create the frame.
	 */
	public OrderInfo() {
		setTitle("Order");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 100, 308, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Order Info", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(25, 20, 257, 332);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel labelDate = new JLabel("Date:");
		labelDate.setBounds(27, 25, 61, 16);
		panel.add(labelDate);
		
		textDate = new JTextField();
		textDate.setEditable(false);
		textDate.setBounds(64, 21, 180, 26);
		panel.add(textDate);
		textDate.setColumns(10);
		
		JLabel labelName = new JLabel("Name:");
		labelName.setBounds(27, 55, 61, 16);
		panel.add(labelName);
		
		textName = new JTextField();
		textName.setEnabled(false);
		textName.setEditable(false);
		textName.setBounds(64, 50, 180, 26);
		panel.add(textName);
		textName.setColumns(10);
		
		JLabel labelDorm = new JLabel("Dorm:");
		labelDorm.setBounds(27, 85, 61, 16);
		panel.add(labelDorm);
		
		textDorm = new JTextField();
		textDorm.setEnabled(false);
		textDorm.setEditable(false);
		textDorm.setBounds(64, 80, 180, 26);
		panel.add(textDorm);
		textDorm.setColumns(10);
		
		JLabel labelTel = new JLabel("Tel:");
		labelTel.setBounds(27, 115, 61, 16);
		panel.add(labelTel);
		
		JLabel labelCost = new JLabel("Cost:");
		labelCost.setBounds(27, 145, 61, 16);
		panel.add(labelCost);
		
		textTel = new JTextField();
		textTel.setEnabled(false);
		textTel.setEditable(false);
		textTel.setBounds(64, 110, 180, 26);
		panel.add(textTel);
		textTel.setColumns(10);
		
		textCost = new JTextField();
		textCost.setEnabled(false);
		textCost.setEditable(false);
		textCost.setBounds(64, 140, 180, 26);
		panel.add(textCost);
		textCost.setColumns(10);
		
		productsInfo = new JPanel();
		productsInfo.setBorder(new TitledBorder(null, "Purchase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		productsInfo.setBounds(20, 187, 219, 124);
		panel.add(productsInfo);
		productsInfo.setLayout(new BorderLayout(0, 0));
		
		btnBack = new JButton("Back");
		
		btnBack.setBounds(85, 354, 117, 29);
		contentPane.add(btnBack);
		
		
	}


	public JTextField getTextDate() {
		return textDate;
	}


	public void setTextDate(JTextField textDate) {
		this.textDate = textDate;
	}


	public JTextField getTextName() {
		return textName;
	}
	public JPanel getProductsPanel(){
		return productsInfo;
	}


	public void setTextName(JTextField textName) {
		this.textName = textName;
	}


	public JTextField getTextDorm() {
		return textDorm;
	}


	public void setTextDorm(JTextField textDorm) {
		this.textDorm = textDorm;
	}


	public JTextField getTextTel() {
		return textTel;
	}


	public void setTextTel(JTextField textTel) {
		this.textTel = textTel;
	}


	public JTextField getTextCost() {
		return textCost;
	}


	public void setTextCost(JTextField textCost) {
		this.textCost = textCost;
	}
	public JButton getBack(){
		return btnBack;
	}
}
