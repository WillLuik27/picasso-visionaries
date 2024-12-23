package picasso.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GetImagesFromFolder {

    public static ArrayList<String> getImages() throws IOException {
    	ArrayList<String> imageNames = new ArrayList<>();
        File folder = new File("images");

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            // Check each file in the folder
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName().toLowerCase();
                        if (fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
                            imageNames.add(file.getName());
                        }
                    }
                }
            }
        } else {
            System.out.println("The folder does not exist or is not a directory.");
        }
        return imageNames;
    }    

}