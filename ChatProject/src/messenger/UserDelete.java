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



// JFrame을 사용합니다.

public class UserDelete extends JFrame {



	private JPanel contentPane;

	private JTextField tf_UpdatePw;

	JLabel lb_DeleteId;

	UserDao ud;

        // 회원탈퇴 창을 만들어 줍니다.

	public UserDelete(String id) {

		setTitle("\uD68C\uC6D0\uD0C8\uD1F4");

		setBounds(100, 100, 311, 233);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		

		JLabel lblNewLabel = new JLabel("ID :");

		lblNewLabel.setBounds(49, 48, 57, 15);

		contentPane.add(lblNewLabel);

		

		JLabel lblNewLabel_1 = new JLabel("PW :");

		lblNewLabel_1.setBounds(49, 98, 57, 15);

		contentPane.add(lblNewLabel_1);

		

		tf_UpdatePw = new JTextField();

		tf_UpdatePw.setBounds(95, 95, 116, 21);

		contentPane.add(tf_UpdatePw);

		tf_UpdatePw.setColumns(10);

		// 로그인 , 회원가입과 다를 바가 없습니다.

		JButton btnDeleteOk = new JButton("\uC0AD\uC81C");

		btnDeleteOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					int deleteOk = 0;

					deleteOk = UserDao.getInstance().userDelete(lb_DeleteId.getText(), tf_UpdatePw.getText());

					if(deleteOk !=0){

						JOptionPane.showMessageDialog(null, "삭제완료 바이!");

						System.exit(0);

					}else{

						JOptionPane.showMessageDialog(null, "삭제실패");

					}

				} catch (Exception e1) {

					e1.printStackTrace();

				}

			}

		});

		btnDeleteOk.setBounds(49, 136, 97, 23);

		contentPane.add(btnDeleteOk);

		

		JButton btnUpdateBack = new JButton("\uCDE8\uC18C");

		btnUpdateBack.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dispose();

			}

		});

		btnUpdateBack.setBounds(158, 136, 97, 23);

		contentPane.add(btnUpdateBack);

		

		lb_DeleteId = new JLabel(id);

		lb_DeleteId.setBounds(95, 48, 57, 15);

		contentPane.add(lb_DeleteId);

	}

}