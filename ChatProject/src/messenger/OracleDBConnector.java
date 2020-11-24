package messenger;

import java.sql.Connection;

import java.sql.DriverManager;



// 멋있어 보이기 위해서 싱글톤 패턴을 이용하여 접속합니다.

// 고정된 메모리 영역을 얻으면서 메모리 낭비를 줄일 수 있습니다.

public class OracleDBConnector {

	private static OracleDBConnector oracleDBConnector = new  OracleDBConnector();

	Connection con = null;

	

	private OracleDBConnector() {}

	

	public static OracleDBConnector getInstance(){

		if(oracleDBConnector == null){

			oracleDBConnector = new OracleDBConnector();

		}

		return oracleDBConnector;

	}

	

	public Connection getConnection() {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url="jdbc:oracle:thin:@localhost:1521:orcl";

			con = DriverManager.getConnection(url, "scott", "1234");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return con;

	}

}