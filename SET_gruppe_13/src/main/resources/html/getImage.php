<?php
// Display errors for debugging purposes
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

// Set the content type to JSON
header("Content-Type: application/json");

// Database connection details (replace with your own)
$servername = "gruppe-13.mysql.database.azure.com";
$username = "superuser";
$password = "Gruppe_13";
$dbname = "gruppe13";

try {
    // Create a new PDO connection
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Query to fetch the data
    $stmt = $conn->prepare("SELECT b.produkt_id, p.navn, p.kategori, b.bucketlink FROM bilde b LEFT JOIN produkt p ON b.produkt_id = p.produkt_id");
    $stmt->execute();

    // Fetch all rows as an associative array
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

    // Output the data as JSON
    echo json_encode($result);

} catch (PDOException $e) {
    // If there is an error, return a JSON response with the error message
    echo json_encode(["error" => "Connection failed: " . $e->getMessage()]);
}
?>
