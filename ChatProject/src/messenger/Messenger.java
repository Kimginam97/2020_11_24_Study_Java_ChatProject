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



// implements 문을 잘 보도록합니다. ActionListener를 이용합니다.

public class Messenger extends JFrame implements ActionListener {

	// 연결할 포트를 설정하기 위한 변수입니다.

  private InetAddress inetAddress;

	private int port;

  // 기억을 되살려 맨 위에서 보았던 UDP에서 사용하는 소켓들입니다.

	private MulticastSocket Socket;

	private DatagramPacket Packet;

	private DatagramSocket dataSocket;



	String userId;

	String userName;

	private JTextField textField;

	private JTextArea textArea;



	public Messenger(String id, String name, InetAddress ip, int p) throws UnknownHostException, IOException {

		inetAddress = ip;

		port = p;

		userName = name;

		userId = id;

		

		setTitle("\uCC44\uD305\uBC29");

		setBounds(100, 100, 400, 280);



		getContentPane().setLayout(new BorderLayout(0, 0));



		JPanel panel = new JPanel();

		getContentPane().add(panel, BorderLayout.SOUTH);

		panel.setLayout(new GridLayout(1, 0, 0, 0));



		textField = new JTextField();

		textField.setColumns(10);



		JPanel panel_2 = new JPanel();

		getContentPane().add(panel_2, BorderLayout.CENTER);

		panel_2.setLayout(new GridLayout(0, 1, 0, 0));



		JScrollPane scrollPane = new JScrollPane();

		panel_2.add(scrollPane);



		textArea = new JTextArea();

		scrollPane.setViewportView(textArea);

		

		JPanel panel_1 = new JPanel();

		scrollPane.setColumnHeaderView(panel_1);

		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		// 누르면 수정창

		JButton btnUpdate = new JButton("\uC815\uBCF4\uC218\uC815");

		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				UserUpdate update = new UserUpdate(userId);

				update.setVisible(true);

			}

		});

		panel_1.add(btnUpdate);

		// 누르면 삭제창

		JButton btnDelete = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");

		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

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