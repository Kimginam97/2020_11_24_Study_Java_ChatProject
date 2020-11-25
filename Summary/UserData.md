# UserData

## 유저 엔티티

### UserData.java 구성
```
package messenger;


//도메인 엔티티
public class UserData {

	private String id;  //아이디
	private String pw;	//비밀번호
	private String name;  //이름


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}


```

