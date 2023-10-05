<?php
$imagePath = "UploadFile/".$_GET["fileName"]; // Replace with the actual path to your image file
// Check if the file exists
if (file_exists($imagePath)) {
    // Set the appropriate Content-Type header based on the image file type
    $imageInfo = getimagesize($imagePath);
    header("Content-Type: " . $imageInfo["mime"]);
    // Display the image
    readfile($imagePath);
} else {
    // Image file not found
    echo "Image not found.";
}