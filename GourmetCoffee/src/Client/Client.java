package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Client extends JFrame {

	private static JPanel contentPane;
	private static  Socket socket;
	private static  BufferedReader br;
	private static  JButton btnSend;
	private static  JTextArea textArea;
	private static JTextArea textInput;
	private JButton btnBack;
	private JPanel panel;

	public Client() {
		setTitle("Client");

		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setAlwaysOnTop(true);
		setVisible(true);
		setBounds(100, 100, 462, 293);
		getContentPane().setLayout(null);
		textInput = new JTextArea();
		textInput.setBackground(Color.WHITE);
		textInput.setBounds(16, 209, 429, 23);
		getContentPane().add(textInput);
		textInput.setText("");
		textInput.setLineWrap(true);
		btnSend = new JButton();
		btnSend.setBounds(90, 236, 117, 29);
		getContentPane().add(btnSend);

		btnSend.setText("发送");


		JLabel tel = new JLabel("Tel:13234045130");
		tel.setBounds(35, 16, 126, 16);
		getContentPane().add(tel);

		btnBack = new JButton("Back");
		btnBack.setBounds(246, 236, 117, 29);
		getContentPane().add(btnBack);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBounds(16, 44, 429, 163);
		getContentPane().add(panel);



		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setText("Hi, you can leave messages or call me if urgently!");

		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane);

		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//judge the input is empty, if not empty, try to send message.
				if(!"".equals(textInput.getText().trim()) && !(textInput.getText() == null)){
					PrintWriter pw = null;
					//get the format of the date.
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					textArea.setCaretPosition(textArea.getText().length());
					//output the message to the text area.
					textArea.append("\n" + sdf.format(date) + " :\n" + "我: " + textInput.getText());
					try {
						//output the message to the server.
						pw = new PrintWriter(socket.getOutputStream());
						pw.append(textInput.getText());
						textInput.setText("");
						//flush
						pw.flush();

						if(pw!=null)
							pw.close();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	//get the connection of the server.
	public  void getConnection(){
		try {
			while(true){	
				//use socket TCP to connect the server port is 6888.
				socket = new Socket(InetAddress.getLocalHost(), 6888);
				//keep the socket alive.
				socket.setKeepAlive(true);
				//get the message from the server.
				getMessage();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}   
	
	//get the message from the server input.
	private  void getMessage() {
		String temp = new String();
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(!socket.isClosed()){

				temp = br.readLine();			

				if(temp == null){
					break;
				}
				Date date = new Date(System.currentTimeMillis());
				//set the date format.
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				//output the message.
				textArea.setCaretPosition(textArea.getText().length());
				textArea.append("\n" + sdf.format(date) +" :\n" + "回复: " +  temp);
			
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//get the back button.
	public JButton getBack(){
		return btnBack;
	}
}
