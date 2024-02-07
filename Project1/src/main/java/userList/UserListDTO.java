package userList;

import java.time.LocalDate;
import java.time.LocalDateTime;

//ULID	NUMBER	No		1	
//USERID	VARCHAR2(50 BYTE)	No		2	
//USERPW	VARCHAR2(100 BYTE)	No		3	
//USERNAME	VARCHAR2(100 BYTE)	No		4	
//JOIN_DATE	DATE	Yes	sysdate	5	
//LASTLOGINTIME	TIMESTAMP(6)	Yes		6	
public class UserListDTO {
	
	private int ulid;
	private String userId, userPw,userName;
	private LocalDate joinDate;
    private LocalDateTime lastLoginTime;
    
    
    public UserListDTO() {
		// TODO Auto-generated constructor stub
	}
    
    
	public UserListDTO(int ulid, String userId, String userPw, String userName, LocalDate joinDate,
			LocalDateTime lastLoginTime) {
		super();
		this.ulid = ulid;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.joinDate = joinDate;
		this.lastLoginTime = lastLoginTime;
	}
	

	public int getUlid() {
		return ulid;
	}
	public void setUlid(int ulid) {
		this.ulid = ulid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDate getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	@Override
	public String toString() {
		return "UserListDTO [ulid=" + ulid + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", joinDate=" + joinDate + ", lastLoginTime=" + lastLoginTime + "]";
	}
    
	
	

}
