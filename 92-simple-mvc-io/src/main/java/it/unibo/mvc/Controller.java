package it.unibo.mvc;

import java.io.IOException;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private File file;
    private final String separator;

    /**
     * Set default parameters of the UI.
     */
    public Controller() {
        separator = System.getProperty("file.separator");
        file = new File(System.getProperty("user.home") + separator + "output.txt");
    }

    /**
     * Set a new file as default output file.
     *
     * @param newFile the File object of the new file
     */
    void setCurrentFile(final File newFile) {
        this.file = newFile;
    }

    /**
     * Get the current file.
     *
     * @return the current file
     */
    File getCurrentFile() {
        return this.file;
    }

    /**
     * Get the current file path.
     *
     * @return the path in a string
     */
    String getCurrentFilePath() {
        return this.file.getPath();
    }

    /**
     * Save a string in the output file.
     *
     * @param data is the string that the user want save
     */
    void saveStringInFile(final String data) throws IOException {
        try (OutputStream output = new FileOutputStream(file);
            DataOutputStream dstream = new DataOutputStream(output);
            ) {
            dstream.writeChars(data);
        }
    }
}
