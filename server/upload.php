<?php
$targetDirectory = "uploads/"; // Directory where the file should be uploaded
$targetFile = $targetDirectory . basename($_FILES["fileToUpload"]["name"]); // Path of the uploaded file

// Check if the file is selected and uploaded successfully
if (isset($_FILES["fileToUpload"]) && $_FILES["fileToUpload"]["error"] === UPLOAD_ERR_OK) {
    // Move the uploaded file to the target directory
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $targetFile)) {
        echo "The file " . basename($_FILES["fileToUpload"]["name"]) . " has been uploaded.";
    } else {
        echo "Sorry, there was an error uploading your file.";
    }
} else {
    echo "Please select a file to upload.";
}
