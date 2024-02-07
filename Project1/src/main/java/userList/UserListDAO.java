package userList;

import javax.servlet.http.HttpSession;

public interface UserListDAO {
	public String loginCheck(String userId,String userPw);
	public void logout(HttpSession session);

}
