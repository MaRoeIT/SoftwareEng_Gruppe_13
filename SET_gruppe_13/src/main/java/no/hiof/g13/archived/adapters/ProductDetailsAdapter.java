package no.hiof.g13.archived;

import no.hiof.g13.DTO.in.ProductDetailsDTO;
import no.hiof.g13.ports.out.ProductDetailsRepositoryPort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDetailsAdapter implements ProductDetailsRepositoryPort {
    private static final String DB_URL = "jdbc:mysql://gruppe-13.mysql.database.azure.com:3306/gruppe13";
    private static final String DB_USER = "superuser";
    private static final String DB_PASSWORD = "Gruppe_13";

    @Override
    public ProductDetailsDTO getProductDetails(int produktId) {
        ProductDetailsDTO productDetails = null;
        String query = "SELECT p.produkt_id,"+
                " b.bucketlink, p.kategori, p.navn,"+
                " p.artikkel, s.modell, s.garanti_måneder,"+
                " s.EAN, a.avhengigav"+
                " FROM produkt p "+
                "LEFT JOIN bilde b ON p.produkt_id = b.produkt_id "+
                "LEFT JOIN spesifikasjoner s ON p.produkt_id = s.spesifikasjoner_id"+
                " LEFT JOIN avhengigheter a ON p.produkt_id = a.produkt_id"+
                " WHERE p.produkt_id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, produktId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    productDetails = new ProductDetailsDTO();
                    productDetails.setProduktId(resultSet.getInt("produkt_id"));
                    productDetails.setBucketlink(resultSet.getString("bucketlink"));
                    productDetails.setKategori(resultSet.getString("kategori"));
                    productDetails.setNavn(resultSet.getString("navn"));
                    productDetails.setDescription(resultSet.getString("artikkel"));
                    productDetails.setModell(resultSet.getString("modell"));
                    productDetails.setGarantiMåneder(resultSet.getInt("garanti_måneder"));
                    productDetails.setEan(resultSet.getString("EAN"));
                    productDetails.setAvhengigAv(resultSet.getString("avhengigav"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with logging if needed
        }

        return productDetails;
    }

}
