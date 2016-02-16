package Client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Server.ServerLogOn;

public class LogOn extends JFrame {

	private JPanel contentPane;
	private JPanel logOn;
	private JPanel blackBoard;
	private JLabel labelTel;
	private JLabel labelCode;
	private JTextField textCode;
	private JButton btnGetCode;
	private JButton btnLogOn;
	private JLabel labelRamdom;
	private String random;
	private JLabel labelErr;
	private String tel;
	private JLabel labelTelErr;
	private JLabel logoLabel = new JLabel();
	private JLabel suncafe;
	private JMenuItem menuCustomer;
	private JMenuItem menuServer;
	private JMenuItem mntmExit;

	/**
	 * Create the frame.
	 */
	public LogOn() {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setTitle("Welcome to suncafé");
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logOn = new JPanel();
		logOn.setBounds(6, 32, 438, 266);
		contentPane.add(logOn);
		logOn.setLayout(null);
		
		blackBoard = new JPanel();
		blackBoard.setBorder(new TitledBorder(null, "Blackboard", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		blackBoard.setBounds(23, 16, 229, 158);
		logOn.add(blackBoard);
		blackBoard.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setBackground(UIManager.getColor("Button.background"));
		
		//set the discount information.
		textArea.setText("双十一优惠:" + "\n" 
				+ "1、Mocha only 12.9   20% off\n"
				+ "2、Flat only 6.5   50% off\n"
				+ "3、Cappuccino only 8   30% off\n"
				+ "4、Orange only 6   20% off\n\n"
				+ "东大校区 20分钟必达");
		
		blackBoard.add(textArea, BorderLayout.CENTER);
		
		labelTel = new JLabel("Tel:");
		labelTel.setBounds(49, 197, 32, 16);
		logOn.add(labelTel);
		
		JTextField textTel = new JTextField();
		textTel.setBounds(103, 190, 130, 26);
		logOn.add(textTel);
		textTel.setColumns(10);
		
		labelCode = new JLabel("Code:");
		labelCode.setBounds(49, 225, 47, 16);
		logOn.add(labelCode);
		
		textCode = new JTextField();
		textCode.setBounds(103, 220, 130, 26);
		logOn.add(textCode);
		textCode.setColumns(10);
		
		btnGetCode = new JButton("Get Code");
		btnGetCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//judge the tel is empty, if is empty cast wrong.
				if (!labelTel.getText().isEmpty()) {
					
					String string = textTel.getText();
					boolean isNum = string.matches("[0-9]{11}");   
					
					//jugde the tel number is 11 bits if is not, cast wrong.
					if (isNum) {
						
						labelTelErr.setText("");
						
						//create a random number for the code.
						int x = 1 + (int)(Math.random() * 9998);
						
						//the code must be four bits.
						if (x < 10) {
							random = "000" + x;
						}else if (x < 100) {
							random = "00" + x;
						}else if (x < 1000) {
							random = "0" + x;
						}else {
							random = "" +x;
						}
						labelRamdom.setText(random);
						setTel(textTel.getText());
					
					}else{
						
						labelTelErr.setText("wrong");
						
					}

					
					
				}else{
					labelTelErr.setText("wrong");
				}
				

			}
		});
		btnGetCode.setBounds(246, 181, 117, 29);
		logOn.add(btnGetCode);
		
		btnLogOn = new JButton("Log On");
		btnLogOn.setBackground(UIManager.getColor("Button.background"));
		btnLogOn.setBounds(246, 207, 117, 29);
		logOn.add(btnLogOn);
		
		labelRamdom = new JLabel("");
		labelRamdom.setEnabled(false);
		labelRamdom.setBounds(113, 245, 61, 16);
		logOn.add(labelRamdom);
		
		JButton btnExit = new JButton("Exit");
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//exit system
				int value = JOptionPane.showConfirmDialog(LogOn.this, "你确认要退出程序吗？", "请确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (value == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(245, 232, 117, 29);
		logOn.add(btnExit);
		
		labelErr = new JLabel("");
		labelErr.setBounds(367, 212, 55, 16);
		logOn.add(labelErr);
		
		labelTelErr = new JLabel("");
		labelTelErr.setBounds(367, 186, 61, 16);
		logOn.add(labelTelErr);
		
		JPanel logo = new JPanel();
		logo.setBounds(264, 38, 120, 120);
		
		//input the logo to the logo panel
		ImageIcon icon = new ImageIcon("src/image/logo.jpg");
		logoLabel.setIcon(icon);
		logoLabel.setSize(120,120);
		logo.add(logoLabel);	
		logOn.add(logo);
		
		suncafe = new JLabel("Welcome to suncafé");
		suncafe.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		suncafe.setForeground(Color.BLACK);
		suncafe.setBounds(262, 10, 160, 16);
		logOn.add(suncafe);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(38, 4, 55, 27);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		menuCustomer = new JMenuItem("Customer");
		menu.add(menuCustomer);
		
		menuServer = new JMenuItem("Server");
		menu.add(menuServer);
		
		mntmExit = new JMenuItem("Exit");
		menu.add(mntmExit);
		
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showConfirmDialog(LogOn.this, "你确认要退出程序吗？", "请确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (value == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	
	}
	
	//get the random string
	public String getRamdom(){
		return random;
	}

	//get the log on button
	public JButton getLogOn(){
		return btnLogOn;
	}

	//get the code textField
	public JTextField getCode(){
		return textCode;
	}
	
	//get the Err label
	public JLabel getLabelErr(){
		return labelErr;
	}
	
	//get the tel number
	public String getTel(){
		return tel;
	}
	
	//set the tel number
	public void setTel(String tel){
		this.tel = tel;
	}
	
	public JMenuItem getMenuServer(){
		return menuServer;
	}
}
