// Insecure temporary file creation methods should not be used

import java.io.File;
import java.io.IOException;

public class TempDirExample {

    public static void main(String[] args) {
        
        // Create a temporary directory
        File tempDir;
        try {
            tempDir = File.createTempFile("", ".");
            tempDir.delete();
            tempDir.mkdir();
        } catch (IOException e) {
            System.err.println("Failed to create temporary directory: " + e.getMessage());
            return;
        }
        
        // Now we can do something with the temporary directory...
        System.out.println("Created temporary directory: " + tempDir.getAbsolutePath());
        // For example, let's create a new file in the directory
        File tempFile = new File(tempDir.getAbsolutePath() + File.separator + "example.txt");
        try {
            if (tempFile.createNewFile()) {
                System.out.println("Created temporary file: " + tempFile.getAbsolutePath());
            } else {
                System.err.println("Failed to create temporary file");
            }
        } catch (IOException e) {
            System.err.println("Failed to create temporary file: " + e.getMessage());
        }
        
        // When we're done, we can delete the temporary directory and its contents
        deleteDirectory(tempDir);
        System.out.println("Deleted temporary directory: " + tempDir.getAbsolutePath());
    }
    
    private static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        dir.delete();
    }
}
