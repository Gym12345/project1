package userList;

import javax.servlet.http.HttpSession;

public interface UserListDAO {
	public String loginCheck(String userId,String userPw,HttpSession session);
	public void logout(HttpSession session);
	public int userRegister(String userId,String userPw,String userName);
}
