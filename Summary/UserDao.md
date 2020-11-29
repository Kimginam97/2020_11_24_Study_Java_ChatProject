# UserDao

## DB접근부분

### UserDao.java 구성
```java
package messenger;

import java.awt.EventQueue;

import java.net.InetAddress;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.ArrayList;


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

	//USERDAO 생성
	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

    // UserData 를 가지고 있는 ArrayList 를 반환합니다. 
	public ArrayList<UserData> select() throws Exception {
		//오라클 DB연동
		con = oracleDBConnector.getConnection();
		String sql = "SELECT * FROM CUSTOMERS";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		userList = new ArrayList<>();
		//SELECT * FROM CUSTOMERS에 값이 있을경우 userdata에 아이디,비밀번호,이름을 넣는다
		while (rs.next()) {
			user = new UserData();
			user.setId(rs.getString("id"));
			user.setPw(rs.getString("pw"));
			user.setName(rs.getString("name"));
			//ArrayList에 값을 넣는다
			userList.add(user);
		}
		return userList;
	}

    //회원가입 DB값 넣는곳
	public int insert(String id, String pw, String name) {
		//오라클 DB연동
		con = oracleDBConnector.getConnection();
		int loginOk;
		try {
			String sql = "INSERT INTO CUSTOMERS VALUES ('" + id + "','" + pw + "','" + name + "')";
			pstmt = con.prepareStatement(sql);
			//loginOk에 값을 넣는다 값이있을경우 1반환
			loginOk = pstmt.executeUpdate();
		} catch (Exception e) {
			return loginOk = 0;
		}
		//loginOk값 반환
		return loginOk;
	}



	//회원가입 여부 확인
	public boolean loginCheck(String id, String pw) throws Exception {
		//오라클 DB연동
		con = oracleDBConnector.getConnection();
		//아이디 비밀번호 이름 ArrayList 를 userList에 가져온다
		userList = this.select();
		boolean loginOk = false;
		for (UserData user : userList) {
			//아이디 비밀번호가 같을경우 true반환
			if (id.equals(user.getId()) && pw.equals(user.getPw()))
				return loginOk = true;
		}
		return loginOk;
	}

       

    // 로그인을 성공했을때 발생합니다.
	public void loginSuccess(String id) throws Exception {
		//오라클 연결
		con = oracleDBConnector.getConnection();
		for (UserData user : userList) {
			if (id.equals(user.getId())) {
				//loginName변수 유저이름을 넣는다
				String loginName = user.getName();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							//getbyname을 이용하여 ip 을 불러온다
							InetAddress address = InetAddress.getByName("224.0.1.100");
							//메신저에 id ,이름 , ip ,포트번호
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

	
	//회원가입수정
	public int userUpdate(String id, String pw, String cPw, String name) throws Exception {
		//오라클 DB연결
		con = oracleDBConnector.getConnection();
		int update = 0;
		for (UserData user : userList) {
			//아이디랑 비밀번호가 같으면
			if (id.equals(user.getId()) && pw.equals(user.getPw())){
				String sql = "UPDATE CUSTOMERS SET PW = '"+cPw+"', NAME = '"+name+"' WHERE ID = '"+id+"'";
				pstmt = con.prepareStatement(sql);
				//값을 update에 넣는다
				update = pstmt.executeUpdate();
				break;
			}
		}
		//update값 반환
		return update;
	}

	//회원탈퇴
	public int userDelete(String id, String pw) throws Exception {
		//오라클 DB연결
		con = oracleDBConnector.getConnection();
		int delete = 0;
		for (UserData user : userList) {
			//아이디 비밀번호 같은경우
			if (id.equals(user.getId()) && pw.equals(user.getPw())){
				String sql = "DELETE FROM CUSTOMERS WHERE ID = '"+id+"'";
				pstmt = con.prepareStatement(sql);
				//값은 delete에 넣는다
				delete = pstmt.executeUpdate();
				break;
			}
		}	
		//delete값 반환
		return delete;
	}

}


```

