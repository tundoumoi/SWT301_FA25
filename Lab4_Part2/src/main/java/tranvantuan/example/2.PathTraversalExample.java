package tranvantuan.example;

import java.io.*;
import java.util.logging.Logger;

class PathTraversalExample {

    private PathTraversalExample() {
        throw new AssertionError("No instances");
    }
    static void main() throws IOException {
        final Logger logger = Logger.getLogger(PathTraversalExample.class.getName());

        String userInput = "../secret.txt";
        File file = new File(userInput);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            logger.info("Reading file: " + file.getPath());
            reader.close();
        }
    }
}
