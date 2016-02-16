package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Client.Client;
import Client.LogOn;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.temporal.JulianFields;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ServerLogOn extends JFrame {

	private JPanel contentPane;
	private JTextField textAccount;
	private JPasswordField password;
	private JButton btnBack;
	private JButton btnExit;
	private JButton btnLogOn;
	private Server server = null;


	/**
	 * Create the frame.
	 */
	public ServerLogOn() {


		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("Server");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 100, 236, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setBounds(17, 40, 61, 16);
		contentPane.add(lblAccount);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(17, 78, 69, 16);
		contentPane.add(lblPassword);

		textAccount = new JTextField();
		textAccount.setBounds(90, 35, 130, 26);
		contentPane.add(textAccount);
		textAccount.setColumns(10);

		password = new JPasswordField();
		password.setBounds(90, 73, 130, 26);
		contentPane.add(password);

		btnLogOn = new JButton("Log On");
		btnLogOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textAccount.getText().isEmpty()&&!password.getText().isEmpty()) {

					SAXBuilder saxBuilder = new SAXBuilder();
					InputStream in;
					Boolean isTrue = false;
					// get the XML of products info.
					try {
						in = new FileInputStream("src/resource/Accounts.xml");
						InputStreamReader isr = new InputStreamReader(in, "UTF-8");

						//get the document of XML
						Document document = (Document) saxBuilder.build(isr);
						//get the root element.
						Element rootElement = document.getRootElement();
						//get the product list.
						List<Element> accounts = rootElement.getChildren();	
						for(Element elem:accounts){
							String id = elem.getChild("id").getValue();
							String passwordStr = elem.getChild("password").getValue();
							if (id.equals(textAccount.getText()) && passwordStr.equals(password.getText())) {
								isTrue = true;
							}
						}


					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JDOMException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (isTrue) {
						setVisible(false);
						server = new Server();							
						server.setVisible(true);
					}


				}
			}
		});
		btnLogOn.setBounds(17, 120, 100, 29);
		contentPane.add(btnLogOn);

		btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int value = JOptionPane.showConfirmDialog(ServerLogOn.this, "你确认要退出程序吗？", "请确认", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (value == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
		});
		btnExit.setBounds(17, 154, 203, 29);
		contentPane.add(btnExit);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(120, 120, 100, 29);
		contentPane.add(btnBack);
	}

	public JButton getBack(){
		return btnBack;
	}
}
