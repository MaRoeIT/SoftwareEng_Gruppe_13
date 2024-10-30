package no.hiof.g13.adapters;

import io.javalin.http.Context;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.out.UserRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;

public class UserAdapter implements UserRepositoryPort {


    @Override
    public User getUser(int userId) {

        User user = new User();
        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM gruppe13.bruker WHERE bruker_id = ?")) {

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setBruker_id(rs.getInt("bruker_id"));
                user.setFornavn(rs.getString("fornavn"));
                user.setEtternavn(rs.getString("etternavn"));
                user.setStatus_id(rs.getInt("status_id"));
                user.setMobil(rs.getString("mobil"));
                user.setEpost(rs.getString("epost"));
                user.setPassord(rs.getString("passord"));
            } else {
                System.out.println("No user found with id " + userId);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Bruker id: " + user.getBruker_id() + " Navn: " + user.getFornavn());

        return user;
    }

    @Override
    public int getUserId(String token) {

        int userId = -1;
        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT bruker_id FROM gruppe13.bruker WHERE token = ?")) {

            preparedStatement.setString(1, token);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("bruker_id");
            } else {
                System.out.println("No user found with id " + userId);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Bruker id: " + userId);

        return userId;
    }

    @Override
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();
        try {

            Connection connection = MySQLAdapter.getConnection();

            Statement statement = connection.createStatement();

            String selectQuery = "select * from gruppe13.bruker";

            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                User user = new User();
                user.setBruker_id(rs.getInt("bruker_id"));
                user.setFornavn(rs.getString("fornavn"));
                user.setEtternavn(rs.getString("etternavn"));
                user.setStatus_id(rs.getInt("status_id"));
                user.setMobil(rs.getString("mobil"));
                user.setEpost(rs.getString("epost"));
                user.setPassord(rs.getString("passord"));

                users.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (User user : users) {
            System.out.println("Bruker id: " +user.getBruker_id()+ " Navn: " +user.getFornavn());
        }

        return users;
    }

    @Override
    public void deleteUser(int userId) {

        String deleteQuery = "DELETE FROM gruppe13.bruker WHERE bruker_id = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            // Setting the parameters for the prepared statement
            preparedStatement.setInt(1, userId);

            // Executing the query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveUser(User user) {
        String insertQuery = "INSERT INTO gruppe13.bruker (fornavn, etternavn, status_id, mobil, epost, passord) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Setting the parameters for the prepared statement

            preparedStatement.setString(1, user.getFornavn());
            preparedStatement.setString(2, user.getEtternavn());
            preparedStatement.setInt(3, user.getStatus_id());
            preparedStatement.setString(4, user.getMobil());
            preparedStatement.setString(5, user.getEpost());
            preparedStatement.setString(6, user.getPassord());

            // Executing the query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User saved successfully!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticateUser(String epost, String passord) {
        String sql = "SELECT passord FROM bruker WHERE epost = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, epost);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("passord");

                // Verify password with BCrypt
                return BCrypt.checkpw(passord, storedPassword);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void saveToken(String token, int userId){
        String insertQuery = "UPDATE gruppe13.bruker SET token = ? WHERE bruker_id = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Setting the parameters for the prepared statement

            preparedStatement.setString(1, token);
            preparedStatement.setInt(2, userId);

            // Executing the query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Token saved successfully!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
