package no.hiof.g13.archived;

import no.hiof.g13.models.ProductImage;
import no.hiof.g13.ports.out.ProductImageRepositoryPort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductImageAdapter implements ProductImageRepositoryPort {

    // Database connection details from the PHP file
    private static final String DB_URL = "jdbc:mysql://gruppe-13.mysql.database.azure.com:3306/gruppe13";
    private static final String DB_USER = "superuser";
    private static final String DB_PASSWORD = "Gruppe_13";

    @Override
    public List<ProductImage> getAllProductImages() {
        List<ProductImage> productImages = new ArrayList<>();
        String query = "SELECT b.produkt_id, p.navn, p.kategori, b.bucketlink FROM bilde b LEFT JOIN produkt p ON b.produkt_id = p.produkt_id"; // Replace 'product_images' with your actual table name if different

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProductImage productImage = new ProductImage();
                productImage.setProduktId(resultSet.getInt("produkt_id"));
                productImage.setBucketlink(resultSet.getString("bucketlink"));
                productImage.setKategori(resultSet.getString("kategori"));
                productImage.setNavn(resultSet.getString("navn"));

                productImages.add(productImage);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger for better error handling
        }

        return productImages;
    }
}
