package br.com.fatec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import br.com.fatec.connection.ConnectionMySql;
import br.com.fatec.entity.TokenInfo;
import br.com.fatec.entity.User;

public class DaoUser {

	@SuppressWarnings("finally")
	public static User getLogin(String email, String password) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		User user = new User();
		
		ResultSet rs = null;
		try {
			String query = "select usr_kind, usr_code, usr_token from user where usr_username = '" + email + "' and usr_password = '" + password + "';";
			PreparedStatement cmd;
			cmd = (PreparedStatement) conn.prepareStatement(query);
			rs = cmd.executeQuery();
			if (rs != null) {
				rs.next();
				String token = updateTokenUser(rs.getString("USR_CODE"));
				user.setKindPerson(rs.getString("USR_KIND"));
				user.setUserCode(Long.parseLong(rs.getString("USR_CODE")));
				user.setToken(token); 
			}

		} catch (SQLException e) {
			// TODO: handle exceptions
		} finally {
			rs.close();
			conn.close();
			return user;
		}
	}

	private static String updateTokenUser(String id) throws SQLException {
		ConnectionMySql connection = new ConnectionMySql();
		String tk = Token.createJsonWebToken(id, (long) 1);
		String query = "UPDATE USER SET usr_token=?  WHERE usr_code=?";

		PreparedStatement cmd;
		cmd = (PreparedStatement) conn.prepareStatement(query);
		cmd.setString(1, tk);
		cmd.setString(2, id);
		int rowsUpdated = cmd.executeUpdate();
		if (rowsUpdated > 0){
			System.out.println("An existing user was updated successfully!");
			return tk;
		}
		return null;
	}

}
