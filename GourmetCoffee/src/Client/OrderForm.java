package Client;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class OrderForm extends JFrame {

	private JPanel contentPane;
	private JPanel panel;



	/**
	 * Create the frame.
	 */
	public OrderForm() {
		
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setAlwaysOnTop(true);
		setTitle("Bill");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel labelOK = new JLabel("Ok,your foods are on the way!");
		labelOK.setHorizontalAlignment(SwingConstants.CENTER);
		labelOK.setBounds(45, 46, 263, 16);
		contentPane.add(labelOK);

		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});

		btnExit.setBounds(125, 202, 117, 29);
		contentPane.add(btnExit);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Bill", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(65, 74, 250, 116);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
	}

	public JPanel getPanel(){
		return panel;
	}
}
