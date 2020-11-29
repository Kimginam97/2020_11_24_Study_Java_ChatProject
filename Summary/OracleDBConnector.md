# OracleDBConnector

## 오라클 연결

### OracleDBConnector.java 구성
```java
package messenger;

import java.sql.Connection;

import java.sql.DriverManager;


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

```

