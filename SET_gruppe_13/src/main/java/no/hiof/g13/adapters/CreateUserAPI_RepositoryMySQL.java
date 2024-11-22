package no.hiof.g13.adapters;

import no.hiof.g13.models.User;
import no.hiof.g13.ports.CreateUserAPI_Port;

import java.sql.*;

public class CreateUserAPI_RepositoryMySQL implements CreateUserAPI_Port {

    @Override
    public void createUser(User user) {
        Connection connection = null;
        try {
            connection = MySQLAdapter.getConnection();
            connection.setAutoCommit(false);

            String mySQL_query = "INSERT INTO bruker (fornavn, etternavn, status_id, mobil, epost, passord, user_level) VALUES(?, ?, ?, ?, ?, ?, ?)";
            String address_mySQLQuery = "INSERT INTO adresse (adresse_id, adresse, postnummer) VALUES(?, ?, ?)";

            int userId;

            try (PreparedStatement statement = connection.prepareStatement(mySQL_query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getFornavn());
                statement.setString(2, user.getEtternavn());
                statement.setInt(3, user.getStatus_id());
                statement.setString(4, user.getMobil());
                statement.setString(5, user.getEpost());
                statement.setString(6, user.getPassord());
                statement.setInt(7, user.getUserLevel());

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();

                if (rs.next()) userId = rs.getInt(1);
                else throw new RuntimeException("Creating new user failed, could not get ID");
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to create new user: " + e.getMessage());
            }

            try (PreparedStatement statement = connection.prepareStatement(address_mySQLQuery)) {
                statement.setInt(1, userId);
                statement.setString(2, user.getAddress().getAdresse());
                statement.setString(3, user.getAddress().getPostnummer());
                statement.executeUpdate();
            }

            connection.commit();

        } catch (Exception e) {
            if(connection != null) {
                try {
                    connection.rollback();
                }
                catch (SQLException eq) {
                    throw new RuntimeException("Failed to roll back: " + eq.getMessage());
                }
            }
            throw new RuntimeException("Creating new user failed: " + e.getMessage());
        }
        finally {
            if(connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException eq) {
                    System.err.println("Failed to close connection: " + eq.getMessage());
                }
            }
        }
    }

    @Override
    public boolean userExists(String email) {
        String mySQL_query = "SELECT COUNT(*) FROM bruker WHERE epost = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement statement = connection.prepareStatement(mySQL_query)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) return rs.getInt(1) > 0;
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Could not search for existing accounts", e);
        }
        return false;
    }
}
