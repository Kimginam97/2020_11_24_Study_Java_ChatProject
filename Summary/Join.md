# Join

## 회원가입

### Join.java 구성
```
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


	public Join() {

		setTitle("\uD68C\uC6D0\uAC00\uC785");

		setBounds(100, 100, 300, 205); // 네 창사이즈 설정 입니다.

		//내용에 패널을 만든다
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//frame의 컨텐트 팬을 JPanel로 바꾼다
		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));


		
		JPanel panel = new JPanel();

		contentPane.add(panel, BorderLayout.CENTER);

		panel.setLayout(null);


		//아이디
		JLabel lblID = new JLabel(" I D :");
		lblID.setBounds(39, 28, 27, 15);
		panel.add(lblID);


		//아이디 텍스트 필드
		tf_JoinId = new JTextField();
		tf_JoinId.setBounds(96, 25, 116, 21);
		panel.add(tf_JoinId);
		tf_JoinId.setColumns(10);


		//비밀번호
		JLabel lblNewLabel = new JLabel("PW :");
		lblNewLabel.setBounds(39, 59, 26, 15);
		panel.add(lblNewLabel);


		//이름
		JLabel lblNewLabel_1 = new JLabel("NAME :");
		lblNewLabel_1.setBounds(39, 90, 45, 15);
		panel.add(lblNewLabel_1);


		//이름 텍스트필드
		tf_JoinName = new JTextField();
		tf_JoinName.setBounds(96, 87, 116, 21);
		panel.add(tf_JoinName);
		tf_JoinName.setColumns(10);
		
		
		//확인버튼 
		JButton btnJoinOk = new JButton("\uC644\uB8CC");
		//버튼이벤트 btnjoinok처리
		btnJoinOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int loginOk;
				//loginok 는 dao클래스에 insert함수 호출 id text값과 password값,name값을 넣는다        password필드를 문자로 변환
				loginOk = UserDao.getInstance().insert(tf_JoinId.getText(), new String(pf_JoinPw.getPassword()),tf_JoinName.getText());
				//값이 있을경우
				if (loginOk != 0) {
					JOptionPane.showMessageDialog(null, "회원가입성공");
					//프레임종료
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "이미있는 아이디 입니다.");
				}
			}
		});
		btnJoinOk.setBounds(42, 118, 97, 23);
		panel.add(btnJoinOk);


		//종료버튼
		JButton btnJoinBack = new JButton("\uCDE8\uC18C");
		btnJoinBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//프레임종료
				dispose();
			}
		});
		btnJoinBack.setBounds(148, 118, 97, 23);
		panel.add(btnJoinBack);


		//비밀번호 패스워드 필드
		pf_JoinPw = new JPasswordField();
		pf_JoinPw.setBounds(96, 56, 116, 21);
		panel.add(pf_JoinPw);

	}

}

```

