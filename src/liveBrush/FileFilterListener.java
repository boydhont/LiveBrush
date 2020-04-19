package liveBrush;

import java.io.File;
import java.io.FileFilter;

public class FileFilterListener implements FileFilter {
    private String filter;

    public FileFilterListener(String filter) {
        this.filter = filter;
    }

    /**
     * Checks if the file has the correct format
     */
    public boolean accept(File file) {
        if ("".equals(filter)) return true;
        return (file.getName().endsWith(filter));
    }
}
