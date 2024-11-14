<?php
header("Content-Type: application/json");
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

// Database connection details
$servername = "gruppe-13.mysql.database.azure.com";
$username = "superuser";
$password = "Gruppe_13";
$dbname = "gruppe13";

if (isset($_GET['product_id'])) {
    $product_id = $_GET['product_id'];

    try {
        $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $conn->prepare("
            SELECT p.produkt_id, p.navn, p.artikkel, s.produktserie, s.modell, s.garanti_måneder, s.EAN, b.bucketlink, a.avhengigav 
            FROM produkt p 
            LEFT JOIN bilde b ON p.produkt_id = b.produkt_id 
            LEFT JOIN spesifikasjoner s ON p.produkt_id = s.spesifikasjoner_id 
            LEFT JOIN avhengigheter a ON p.produkt_id = a.produkt_id
            WHERE p.produkt_id = :product_id
        ");
        $stmt->bindParam(':product_id', $product_id, PDO::PARAM_INT);
        $stmt->execute();

        $product = $stmt->fetch(PDO::FETCH_ASSOC);
        echo json_encode($product ? $product : ["error" => "Product not found"]);
    } catch (PDOException $e) {
        echo json_encode(["error" => "Connection failed: " . $e->getMessage()]);
    }
} else {
    echo json_encode(["error" => "No product ID provided"]);
}
?>
