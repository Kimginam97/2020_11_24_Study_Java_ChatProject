# UserUpdate

## 회원수정

### UserUpdate.java 구성
```
package messenger;

import java.awt.BorderLayout;

import java.awt.EventQueue;



import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JTextField;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;



public class UserUpdate extends JFrame {


	private JPanel contentPane;
	private JTextField tf_UpdatePw;
	private JTextField tf_UpdateUserName;
	private JLabel lb_UpdateId;
	private JTextField tf_ChangePw;
	

  // 회원정보를 수정하기 위한 창을 만듭니다.
	public UserUpdate(String id) {
		setTitle("\uD68C\uC6D0\uC815\uBCF4\uC218\uC815");
		setBounds(100, 100, 315, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//아이디
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setBounds(49, 28, 57, 15);
		contentPane.add(lblNewLabel);

		//비밀번호
		JLabel lblNewLabel_1 = new JLabel("PW :");
		lblNewLabel_1.setBounds(49, 53, 57, 15);
		contentPane.add(lblNewLabel_1);

		//비밀번호 텍스트 필드
		tf_UpdatePw = new JTextField();
		tf_UpdatePw.setBounds(118, 50, 116, 21);
		contentPane.add(tf_UpdatePw);
		tf_UpdatePw.setColumns(10);

		


		//수정버튼
		JButton btnUpdateOk = new JButton("\uC218\uC815");
		btnUpdateOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int udateOk = 0;
					//아이디   /  전 비밀번호  /새로운 비밀번호  /유저이름
					udateOk = UserDao.getInstance().userUpdate(lb_UpdateId.getText(), tf_UpdatePw.getText(),tf_ChangePw.getText(), tf_UpdateUserName.getText());
					if(udateOk != 0){
						//쇼메진저 다이어로그 호출
						JOptionPane.showMessageDialog(null, "수정완료 재실행 부탁!");
						System.exit(0);
					}else{
						JOptionPane.showMessageDialog(null, "수정실패 비밀번호 확인!");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		//버튼위치
		btnUpdateOk.setBounds(49, 148, 97, 23);
		contentPane.add(btnUpdateOk);

		
		JButton btnUpdateBack = new JButton("\uCDE8\uC18C");
		btnUpdateBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		btnUpdateBack.setBounds(158, 148, 97, 23);
		contentPane.add(btnUpdateBack);

		
		lb_UpdateId = new JLabel(id);
		lb_UpdateId.setBounds(118, 28, 113, 15);
		contentPane.add(lb_UpdateId);

		

		JLabel lblNewLabel_2 = new JLabel("NAME :");
		lblNewLabel_2.setBounds(49, 103, 57, 15);
		contentPane.add(lblNewLabel_2);

		
		tf_UpdateUserName = new JTextField();
		tf_UpdateUserName.setBounds(118, 100, 116, 21);
		contentPane.add(tf_UpdateUserName);
		tf_UpdateUserName.setColumns(10);

		

		JLabel lblChangePw = new JLabel("\uBCC0\uACBD PW:");
		lblChangePw.setBounds(49, 78, 57, 15);
		contentPane.add(lblChangePw);

		

		tf_ChangePw = new JTextField();
		tf_ChangePw.setBounds(118, 75, 116, 21);
		contentPane.add(tf_ChangePw);
		tf_ChangePw.setColumns(10);

	}

}

```

