package no.hiof.g13.adapters;

import no.hiof.g13.DTO.in.GetProductsAPI_DTO;
import no.hiof.g13.DTO.in.ProductDetailsDTO;
import no.hiof.g13.models.ProductImage;
import no.hiof.g13.ports.GetProductsAPI_Port;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetProductsAPI_RepositoryMySQL implements GetProductsAPI_Port {

    @Override
    public List<GetProductsAPI_DTO> getAllProducts() {
        List<GetProductsAPI_DTO> allProducts = new ArrayList<>();

        String mySQL_script = "SELECT * FROM produkt";

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

    @Override
    public GetProductsAPI_DTO getProductById(int productId) {
        String mySQL_script = "SELECT * FROM produkt WHERE produkt_id = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(mySQL_script)) {

            preparedStatement.setInt(1, productId);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                return new GetProductsAPI_DTO(
                  rs.getInt("produkt_id"), rs.getString("navn"), rs.getString("artikkel"),
                  rs.getString("barcode"), rs.getString("kategori")
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return new GetProductsAPI_DTO();
    }

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

        try (Connection connection = MySQLAdapter.getConnection();
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

    @Override
    public List<ProductImage> getAllProductImages() {
        List<ProductImage> productImages = new ArrayList<>();
        String query = "SELECT b.produkt_id, p.navn, p.kategori, b.bucketlink FROM bilde b LEFT JOIN produkt p ON b.produkt_id = p.produkt_id";

        try (Connection connection = MySQLAdapter.getConnection();
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
            e.printStackTrace();
        }
        return productImages;
    }

    @Override
    public List<ProductDetailsDTO> getProductsByUserId(int userId) {
        List<ProductDetailsDTO> products = new ArrayList<>();

        String query = "SELECT p.produkt_id, navn, kategori FROM bruker b JOIN mine_produkter mp ON mp.bruker_id = b.bruker_id JOIN produkt p ON p.produkt_id = mp.produkt_id WHERE b.bruker_id = ?";

        try(Connection connection = MySQLAdapter.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                ProductDetailsDTO dto = new ProductDetailsDTO();
                dto.setProduktId(rs.getInt("produkt_id"));
                dto.setNavn(rs.getString("navn"));
                dto.setKategori(rs.getString("kategori"));

                products.add(dto);
            }
            return products;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}