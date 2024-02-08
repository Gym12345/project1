package userList;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserListMapper implements RowMapper<String> {

    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        String userName = rs.getString("USERNAME");
        return userName;
    }
    
    public UserListDTO mapRowForRegister(ResultSet rs, int rowNum) throws SQLException {
    	UserListDTO userListDTO = new UserListDTO();
    	userListDTO.setUlid(rs.getInt("ULID"));
        userListDTO.setUserId(rs.getString("USERID"));
        userListDTO.setUserPw(rs.getString("USERPW"));
        userListDTO.setUserName(rs.getString("USERNAME"));
        userListDTO.setJoinDate(rs.getDate("JOIN_DATE").toLocalDate());
        userListDTO.setLastLoginTime(rs.getTimestamp("LASTLOGINTIME").toLocalDateTime());
        return userListDTO;
    }
    
}
 