package messenger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Join extends JFrame {



	private JPanel contentPane; // 내용

	private JTextField tf_JoinId; // 아이디

	private JTextField tf_JoinName; // 이름

	private JPasswordField pf_JoinPw; // 패스워드



	/**

	 * Create the frame.

         * 프레임을 만들어줍니다.

         * 손까락만 까딱하면 만들어주는 프로그램이 있습니다.

         * 이클립스 마켓에 있는것 같아요오...

	 */

	public Join() {

		setTitle("\uD68C\uC6D0\uAC00\uC785");

		setBounds(100, 100, 300, 205); // 네 창사이즈 설정 입니다.

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));



		JPanel panel = new JPanel();

		contentPane.add(panel, BorderLayout.CENTER);

		panel.setLayout(null);



		JLabel lblID = new JLabel(" I D :");

		lblID.setBounds(39, 28, 27, 15);

		panel.add(lblID);



		tf_JoinId = new JTextField();

		tf_JoinId.setBounds(96, 25, 116, 21);

		panel.add(tf_JoinId);

		tf_JoinId.setColumns(10);



		JLabel lblNewLabel = new JLabel("PW :");

		lblNewLabel.setBounds(39, 59, 26, 15);

		panel.add(lblNewLabel);



		JLabel lblNewLabel_1 = new JLabel("NAME :");

		lblNewLabel_1.setBounds(39, 90, 45, 15);

		panel.add(lblNewLabel_1);



		tf_JoinName = new JTextField();

		tf_JoinName.setBounds(96, 87, 116, 21);

		panel.add(tf_JoinName);

		tf_JoinName.setColumns(10);
		JButton btnJoinOk = new JButton("\uC644\uB8CC");

		btnJoinOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int loginOk;

				loginOk = UserDao.getInstance().insert(tf_JoinId.getText(), new String(pf_JoinPw.getPassword()),

						tf_JoinName.getText());

				if (loginOk != 0) {

					JOptionPane.showMessageDialog(null, "회원가입성공");

					dispose();

				}else{

					JOptionPane.showMessageDialog(null, "이미있는 아이디 입니다.");

				}

			}

		});

		btnJoinOk.setBounds(42, 118, 97, 23);

		panel.add(btnJoinOk);



// UserDao 는 이따가 만들어 줄겁니다 걱정마세요!

// 섬세하게 뒤로가기 버튼도 만들어 줍니다.

		JButton btnJoinBack = new JButton("\uCDE8\uC18C");

		btnJoinBack.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dispose();

			}

		});



		btnJoinBack.setBounds(148, 118, 97, 23);

		panel.add(btnJoinBack);



		pf_JoinPw = new JPasswordField();

		pf_JoinPw.setBounds(96, 56, 116, 21);

		panel.add(pf_JoinPw);

	}

}
