package com.crimsonlogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crimsonlogic.model.User;

public class UserDAOImpl implements UserDAO {

	private Connection connection;

	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public boolean addUser(User user) {
		String query = "INSERT INTO users (user_firstName, user_lastName, user_email,user_password,user_phoneNo,user_address,user_dateofBirth)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			
			preparedStatement = connection.prepareStatement(query);

			
			preparedStatement.setString(1, user.getUser_firstName());
			preparedStatement.setString(2, user.getUser_lastName());
			preparedStatement.setString(3, user.getUser_email());
			preparedStatement.setString(4, user.getUser_password());
			preparedStatement.setLong(5, user.getUser_phoneNo());
			preparedStatement.setString(6, user.getUser_address());
			preparedStatement.setDate(7, java.sql.Date.valueOf(user.getUser_dateofBirth()));
		
			int result = preparedStatement.executeUpdate();

			// Return true if one or more rows were affected
			return result > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// Close the PreparedStatement
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
	}

	public User login(String user_email, String user_password) throws SQLException {
	    String query = "SELECT * FROM users WHERE user_email = ? AND user_password = ?";
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, user_email);
	        preparedStatement.setString(2, user_password);

	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            return new User(
	                resultSet.getInt("userId"),
	                resultSet.getString("user_firstName"),
	                resultSet.getString("user_lastName"),
	                resultSet.getString("user_email"),
	                resultSet.getString("user_password"),
	                resultSet.getLong("user_phoneNo"),
	                resultSet.getString("user_address"),
	                resultSet.getDate("user_dateofBirth").toLocalDate()     
	            );
	        } else {
	            return null;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }
	    }
	}

}
