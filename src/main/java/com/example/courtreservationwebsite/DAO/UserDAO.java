package com.example.courtreservationwebsite.DAO;

import com.example.courtreservationwebsite.Model.User;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

@Configuration
public class UserDAO extends DbConnection{

    private static final String CHECK_LOGIN = "SELECT * FROM user WHERE username = ? AND password = ?";

    private static final String SAVE_USER_UPDATE = "UPDATE user SET name=?, username=?, email=?, address=?, dob=? WHERE id=?";


    public boolean checkLogin(User user) {
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(CHECK_LOGIN);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassowrd());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getDate("dob"));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUsernameExists(String username, int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ? AND id <> ?")) {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }

    public boolean isEmailExists(String email, int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE email = ? AND id <> ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_UPDATE)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setDate(5, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setInt(6, user.getId());

            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
