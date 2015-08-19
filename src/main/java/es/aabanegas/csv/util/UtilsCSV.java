package es.aabanegas.csv.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Methods to manage csv files
 * 
 * @author Aurelio Aragones
 */
public class UtilsCSV {

    /**
     * Read folder .
     * 
     * @param path  Path CSV folder
     * @return Lith of Path, filter by extension {@code *.csv}
     * @throws IOException 
     */
    public static List<Path> filesCSVInPath(String path) throws IOException {
        final List<Path> files = new ArrayList<>();
        Path paths = Paths.get(path);
        
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + Constants.pattern);
        Files.walkFileTree(paths, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                
                if (matcher.matches(file.getFileName())) {
                    files.add(file);
                }
                
                return FileVisitResult.CONTINUE;
            }
        });        
        return files;
    }
}
