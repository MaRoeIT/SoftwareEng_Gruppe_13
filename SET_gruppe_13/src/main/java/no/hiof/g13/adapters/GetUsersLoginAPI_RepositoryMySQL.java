package no.hiof.g13.adapters;

import no.hiof.g13.models.User;
import no.hiof.g13.ports.in.GetUsersLoginAPI_Port;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUsersLoginAPI_RepositoryMySQL implements GetUsersLoginAPI_Port {

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String mySQL_script = "SELECT bruker_id, epost, passord FROM gruppe13.bruker";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script);
            ResultSet rs = preparedStatement.executeQuery()) {

                while(rs.next()) {
                    users.add(new User(
                            rs.getInt("bruker_id"), rs.getString("epost"), rs.getString("passord")
                    ));
                }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("No users found", e);
        }
        return users;
    }

    @Override
    public User getUserById(int userId) {

        String mySQL_script = "SELECT bruker_id, epost, passord FROM gruppe13.bruker WHERE bruker_id = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script)) {

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("bruker_id"), rs.getString("epost"), rs.getString("passord")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        String mySQL_script = "SELECT bruker_id, epost, passord FROM gruppe13.bruker WHERE epost = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script)) {

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("bruker_id"), rs.getString("epost"), rs.getString("passord")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("No user found", e);
        }
        return null;
    }

    @Override
    public int getUserIdByToken(String token) {

        int userId = -1;
        String mySQL_script = "SELECT bruker_id FROM gruppe13.bruker WHERE token = ?";
        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script)) {

            preparedStatement.setString(1, token);
            try(ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("bruker_id");

                } throw new RuntimeException("No user found with id" + userId);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public String getToken(int userId) {

        String token = "";
        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT token FROM gruppe13.bruker WHERE bruker_id = ?")) {

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                token = rs.getString("token");
            } else {
                System.out.println("No user found with id " + userId);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("fetched token " + token);

        return token;
    }
}