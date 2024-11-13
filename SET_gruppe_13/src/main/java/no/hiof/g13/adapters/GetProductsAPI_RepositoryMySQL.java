package no.hiof.g13.adapters;

import no.hiof.g13.DTO.in.GetProductsAPI_DTO;
import no.hiof.g13.ports.in.GetProductsAPI_Port;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetProductsAPI_RepositoryMySQL implements GetProductsAPI_Port {

    @Override
    public List<GetProductsAPI_DTO> getAllProducts() {
        List<GetProductsAPI_DTO> allProducts = new ArrayList<>();

        String mySQL_script = "SELECT *" +
                "FROM produkt";

        try (Connection connection = MySQLAdapter.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                allProducts.add(new GetProductsAPI_DTO(
                        rs.getInt("produkt_id"), rs.getString("navn"), rs.getString("artikkel"),
                        rs.getString("barcode"), rs.getString("kategori"))
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }
}