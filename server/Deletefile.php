<?php
$file = "uploads/".$_GET["filename"];
if (file_exists($file)) {
    if (unlink($file)) {
        echo "File deleted successfully.";
    } else {
        echo "Unable to delete the file.";
    }
} else {
    echo "File does not exist.";
}
