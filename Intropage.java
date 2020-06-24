package swing;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Intropage {

	private JFrame frmLogin;
	public Intropage() {
		initialize();
	}

	
	private void initialize() {
		frmLogin = new JFrame();
		
		frmLogin.setTitle("Run");
		frmLogin.setBounds(100, 100, 300, 150);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocationRelativeTo(null);

		MenuTable Ml = new MenuTable();

		Ml.setVisible(false);
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.setVisible(false);
				Ml.setVisible(true);
			}
		});
		btnNewButton.setBounds(126, 70, 137, 30);

		frmLogin.getContentPane().add(btnNewButton);
		frmLogin.setVisible(true);
		Label label = new Label("군 정보 조회 시스템");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Adobe 명조 Std M", Font.PLAIN, 13));
		label.setBounds(10, 10, 173, 54);
		frmLogin.getContentPane().add(label);

	}

}
