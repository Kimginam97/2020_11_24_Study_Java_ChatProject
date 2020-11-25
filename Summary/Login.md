# Login

## 로그인

### Login.java 구성
```
package messenger;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JPasswordField;

import javax.swing.JTextField;



public class Login extends JFrame {

	private JTextField tf_Id;
	private JPasswordField pf_Pw;

	public static void main(String[] args) {
		//invokelater 전달된 Runnable 객체가  event dispatch thread에서실행되는 큐에 넣고 곧바로 리턴한다
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
		setTitle("\uCC44\uD305");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 160);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		//아이디
		JLabel lblNewLabel = new JLabel(" I D :");
		lblNewLabel.setBounds(24, 29, 27, 15);
		panel.add(lblNewLabel);

		//아이디 텍스트필드
		tf_Id = new JTextField();
		tf_Id.setBounds(71, 26, 116, 21);
		panel.add(tf_Id);
		tf_Id.setColumns(10);

		//비밀번호
		JLabel lblNewLabel_1 = new JLabel("PW :");
		lblNewLabel_1.setBounds(25, 60, 26, 15);
		panel.add(lblNewLabel_1);


		//로그인버튼
		JButton login = new JButton("\uB85C\uADF8\uC778");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean loginOk;
					//logincheck에 아이디와 비밀번호를 넣어 나온값을 loginok로 가져온다
					loginOk = UserDao.getInstance().loginCheck(tf_Id.getText(), new String(pf_Pw.getPassword()));
					//값일 있을경우
					if (loginOk) {
						//loginSuccess호출
						UserDao.getInstance().loginSuccess(tf_Id.getText());
						//종료
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "로그인실패");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		login.setBounds(12, 88, 96, 23);
		panel.add(login);


		//회원가입버튼
		JButton join = new JButton("\uD68C\uC6D0\uAC00\uC785");
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Join frame = new Join();
				frame.setVisible(true);
			}
		});
		join.setBounds(120, 88, 96, 23);
		panel.add(join);


		//비밀번호 텍스트 필드
		pf_Pw = new JPasswordField();
		pf_Pw.setBounds(71, 57, 116, 21);
		panel.add(pf_Pw);

	}



}


```

