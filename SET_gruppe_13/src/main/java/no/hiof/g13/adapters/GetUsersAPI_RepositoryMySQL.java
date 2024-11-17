package no.hiof.g13.adapters;

import no.hiof.g13.DTO.in.GetUserRequestDTO;
import no.hiof.g13.models.User;
import no.hiof.g13.ports.in.GetUsersAPI_Port;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUsersAPI_RepositoryMySQL implements GetUsersAPI_Port {

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String mySQL_script = "SELECT bruker_id, fornavn, etternavn, status_id, mobil, epost, user_level, adresse_id, adresse, postnummer FROM bruker JOIN adresse a ON adresse_id = bruker_id";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script);
            ResultSet rs = preparedStatement.executeQuery()) {

                while(rs.next()) {
                    GetUserRequestDTO dto = GetUserRequestDTO.fromResult(rs);
                    users.add(dto.toUser());
                }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("No users found", e);
        }
        return users;
    }

    @Override
    public User getUserById(int userId) {

        String mySQL_script = "SELECT bruker_id, fornavn, etternavn, status_id, mobil, epost, user_level, adresse_id, adresse, postnummer FROM bruker JOIN adresse a ON adresse_id = bruker_id WHERE bruker_id = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script)) {

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                GetUserRequestDTO dto = GetUserRequestDTO.fromResult(rs);
                return dto.toUser();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        String mySQL_script = "SELECT bruker_id, fornavn, etternavn, status_id, mobil, epost, user_level, adresse_id, adresse, postnummer FROM bruker JOIN adresse a ON adresse_id = bruker_id WHERE epost = ?";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script)) {

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                GetUserRequestDTO dto = GetUserRequestDTO.fromResult(rs);
                return dto.toUser();
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("No user found", e);
        }
        return null;
    }
}