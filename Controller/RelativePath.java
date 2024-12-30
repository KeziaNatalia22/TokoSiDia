package Controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativePath {

    public static String getRelativePath(String absPath){
        return Paths.get("").toAbsolutePath().relativize(new File(absPath).toPath()).toString();
    }
    
}
