# Messenger

## 메신저

### Join.java 구성
```
package messenger;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.IOException;

import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;

import java.net.MulticastSocket;

import java.net.UnknownHostException;

import java.util.Vector;



import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.JTable;

import javax.swing.JTextArea;

import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;

import javax.swing.JButton;




public class Messenger extends JFrame implements ActionListener {

	//IP주소를 표현하는 클래스
	private InetAddress inetAddress;
    private int port;
    
    
    //멀티소캣 생성
	private MulticastSocket Socket;
	private DatagramPacket Packet;
	private DatagramSocket dataSocket;

	String userId;
	String userName;
	private JTextField textField;
	private JTextArea textArea;

	//메신저에 아이디 비밀번호 ip 포트번호를 넣는다
	public Messenger(String id, String name, InetAddress ip, int p) throws UnknownHostException, IOException {
		
		inetAddress = ip;
		port = p;
		userName = name;
		userId = id;
		
		setTitle("\uCC44\uD305\uBC29");
		setBounds(100, 100, 400, 280);
		getContentPane().setLayout(new BorderLayout(0, 0));

		//패널을 만들고 borderlayout에 밑에 배치
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		//행 열 수평 수직
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		textField = new JTextField();
		textField.setColumns(10);


		//패널을 만들고 borderLayout에 가운데 배치
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		//행 열 수평 수직
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		//스크롤 팬생성
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		//많은문자 쓰기위해 textArea
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		
		//누르면수정
		JPanel panel_1 = new JPanel();
		scrollPane.setColumnHeaderView(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		JButton btnUpdate = new JButton("\uC815\uBCF4\uC218\uC815");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//update 클래스에 유저 아이디값을 넣는다
				UserUpdate update = new UserUpdate(userId);
				update.setVisible(true);
			}
		});
		panel_1.add(btnUpdate);

		// 누르면 삭제
		JButton btnDelete = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete 클래스에 유저 아이디값을 넣는다
				UserDelete delete = new UserDelete(userId);
				delete.setVisible(true);
			}
		});
		panel_1.add(btnDelete);
		textField.addActionListener(this);
		panel.add(textField);
		this.setVisible(true);

		
		// 누구누구가 접속하였습니다를 구현하였습니다.
		Runnable r = new Runnable() {
			public void run() {
				byte[] buffer = new byte[512];
				try {
					// socket에 joinGroup을 해줘야합니다.
					Socket = new MulticastSocket(port);
					Socket.joinGroup(inetAddress);
					dataSocket = new DatagramSocket();
					String connect = "[ " + userName + "님 이 접속하셨습니다. 종료는 exit ]";
					buffer = connect.getBytes();
					Packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
					Socket.send(Packet);
					
					while (true) {
						Packet = new DatagramPacket(buffer, buffer.length);
						Socket.receive(Packet);
						String msg = new String(Packet.getData(), 0, Packet.getLength());
						textArea.append(msg + "\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				} finally {
					Socket.close();
				}
			}
		};
		new Thread(r).start();
	}

  

   // exit를 입력했을때 퇴장되게 만들었습니다.
	@Override
	public void actionPerformed(ActionEvent evt) {
		String s = textField.getText();
		textField.setText(null);
		byte[] buffer = new byte[512];


		if (s == null || s == "" || s == "\n" || s.length() == 0) {
			return;
		} else if (s.equals("exit")) {
			String connect = "[" + userName + "님 이 퇴장하셨습니다.]";
			buffer = connect.getBytes();
			Packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
			try {
				Socket.send(Packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Socket.close();
			System.exit(0);
		}

		try {
			dataSocket = new DatagramSocket();
			String msg = "[" + userName + "] " + s;
			buffer = msg.getBytes();
			Packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
			Socket.send(Packet);
		} catch (IOException ie) {
			System.out.println("send 오류");
		} finally {
			dataSocket.close();  // 연결을 종료합니다.
		}
	}

}

```

