package Server;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContactServer extends JFrame{

	private  Socket socket;
	private  ServerSocket ss;
	private  BufferedReader br;
	private  JButton btnSend;
	private  JTextArea textArea;
	private  JTextArea textInput;
	private JPanel panel;
	private JButton btnBack;


	public ContactServer(){

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Server");
		setBounds(800, 100, 593, 328);
		setAlwaysOnTop(true);
		btnSend = new JButton();
		btnSend.setBounds(102, 259, 143, 29);
		textInput = new JTextArea();
		textInput.setBounds(47, 226, 500, 24);
		textInput.setLineWrap(true);

		btnSend.setText("Send");
		//send the masseage from the server to the client
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				//judge the input is empty. if so, do nothing.
				if(!"".equals(textInput.getText().trim()) && !(textInput.getText() == null)){
					PrintWriter pw = null;
					Date date = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					textArea.setCaretPosition(textArea.getText().length());
					if(textArea.getText() == null || "".equals(textArea.getText().trim()))
						textArea.append("" + sdf.format(date) + " :\n" + "我: " +  textInput.getText());
					else
						textArea.append("\n" + sdf.format(date) + " :\n" + "我: " +  textInput.getText());
					try {
						pw = new PrintWriter(socket.getOutputStream());
						pw.append(textInput.getText());
						textInput.setText("");
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

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(47, 28, 500, 186);
		panel.setLayout(new BorderLayout(0, 0));


		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(47,57,508,161);
		textArea.setText("");
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane);


		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(textInput);
		getContentPane().add(btnSend);

		btnBack = new JButton("Back");
		btnBack.setBounds(330, 259, 143, 29);
		getContentPane().add(btnBack);
		getContentPane().add(panel);

	}



	//create the socket server and wait the client to connect.
	public  void getConnection(){
		try {
			ss = new ServerSocket(6888, 10, InetAddress.getLocalHost());
			while(true){
				socket = ss.accept();
				socket.setKeepAlive(true);
				getMessage();
			}           
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//get the message from the client.
	private  void  getMessage(){
		String temp = new String();
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(!socket.isClosed()){
				temp = br.readLine();
				if(temp == null)
					break;
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				textArea.setCaretPosition(textArea.getText().length());
				if(textArea.getText() == null || "".equals(textArea.getText().trim()))
					textArea.append("" + sdf.format(date) + " :\n" + "顾客: " +  temp);
				else
					textArea.append("\n" + sdf.format(date) + " :\n" + "顾客: " +  temp);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JButton getBack(){
		return btnBack;
	}

	public void resetServer(){
		ss = null;
	}

}
