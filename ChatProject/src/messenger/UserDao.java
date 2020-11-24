package messenger;

import java.awt.EventQueue;

import java.net.InetAddress;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.ArrayList;



// 오라클 접속합시다.

// 그리고 쿼리문을 날리기 위한 PreparedStatement 문을 준비합니다.

public class UserDao {

	private static UserDao userDao = new UserDao();

	private static OracleDBConnector oracleDBConnector = OracleDBConnector.getInstance();

	Connection con;

	PreparedStatement pstmt;

	ResultSet rs;

	UserData user;

	ArrayList<UserData> userList;



	private UserDao() {

	}

 // 마찬가지로 뭔가 있어 보입니다.

	public static UserDao getInstance() {

		if (userDao == null) {

			userDao = new UserDao();

		}

		return userDao;

	}

       // UserData 를 가지고 있는 ArrayList 를 반환합니다. 

	public ArrayList<UserData> select() throws Exception {

		con = oracleDBConnector.getConnection();

		String sql = "SELECT * FROM CUSTOMERS";

		pstmt = con.prepareStatement(sql);

		rs = pstmt.executeQuery();

		userList = new ArrayList<>();

		while (rs.next()) {

			user = new UserData();

			user.setId(rs.getString("id"));

			user.setPw(rs.getString("pw"));

			user.setName(rs.getString("name"));

			userList.add(user);

		}

		return userList;

	}

        // 회원가입에 성공하게 된다면 발생되는 메소드입니다

	public int insert(String id, String pw, String name) {

		con = oracleDBConnector.getConnection();

		int loginOk;

		try {

			String sql = "INSERT INTO CUSTOMERS VALUES ('" + id + "','" + pw + "','" + name + "')";

			pstmt = con.prepareStatement(sql);

			loginOk = pstmt.executeUpdate();

		} catch (Exception e) {

			return loginOk = 0;

		}

		return loginOk;

	}



  // 회원가입된 인간인지 확인하기 위하여 만들어줍니다.

	public boolean loginCheck(String id, String pw) throws Exception {

		con = oracleDBConnector.getConnection();

		userList = this.select();

		boolean loginOk = false;

		for (UserData user : userList) {

			if (id.equals(user.getId()) && pw.equals(user.getPw()))

				return loginOk = true;

		}

		return loginOk;

	}

       

        // 로그인을 성공했을때 발생합니다.

	public void loginSuccess(String id) throws Exception {

		con = oracleDBConnector.getConnection();



		for (UserData user : userList) {

			if (id.equals(user.getId())) {

				String loginName = user.getName();

				EventQueue.invokeLater(new Runnable() {

					public void run() {

						try {

							InetAddress address = InetAddress.getByName("224.0.1.100");

							new Messenger(id,loginName, address , 9999);

						} catch (Exception e) {

							e.printStackTrace();

						}

					}

				});

				break;

			}

		}

	}

	// 회원정보를 구지 수정하겠다는 사람을 위해 만들었습니다.

	public int userUpdate(String id, String pw, String cPw, String name) throws Exception {

		con = oracleDBConnector.getConnection();

		int update = 0;

		for (UserData user : userList) {

			if (id.equals(user.getId()) && pw.equals(user.getPw())){

				String sql = "UPDATE CUSTOMERS SET PW = '"+cPw+"', NAME = '"+name+"' WHERE ID = '"+id+"'";

				pstmt = con.prepareStatement(sql);

				update = pstmt.executeUpdate();

				break;

			}

		}

		return update;

	}

	// 이 프로그램이 쓸모 없다는걸 안 사람들이 회원 탈퇴를 하기 시작합니다.

	public int userDelete(String id, String pw) throws Exception {

		con = oracleDBConnector.getConnection();

		int delete = 0;

		for (UserData user : userList) {

			if (id.equals(user.getId()) && pw.equals(user.getPw())){

				String sql = "DELETE FROM CUSTOMERS WHERE ID = '"+id+"'";

				pstmt = con.prepareStatement(sql);

				delete = pstmt.executeUpdate();

				break;

			}

		}	

		return delete;

	}

}
