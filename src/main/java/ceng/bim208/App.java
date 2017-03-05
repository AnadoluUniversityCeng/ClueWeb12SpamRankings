package ceng.bim208;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.err.println("Usage: java App spamDir docIDs ...");
            return;
        }
        
        Path spamDir = Paths.get(args[0]);
        String docIDs = args[1];
             
        // your codes goes here ...
    }
}
