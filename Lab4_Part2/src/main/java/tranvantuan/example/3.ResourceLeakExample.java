package tranvantuan.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class ResourceLeakExample {
    private static final Logger LOGGER = Logger.getLogger(ResourceLeakExample.class.getName());

    static void main(String[] args) {
        String path = (args != null && args.length > 0) ? args[0] : "input.txt";
        new ResourceLeakExample().process(path);
    }

    public void process(String filePath) {
        // filePath là effectively final, dùng được trong lambda
        LOGGER.info(() -> "Starting to read file: " + filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNo = 0;

            while ((line = br.readLine()) != null) {
                lineNo++;

                // Bản sao final cho lambda
                final int ln = lineNo;
                final String msgLine = line;

                // Trì hoãn ghép chuỗi
                LOGGER.fine(() -> "Line " + ln + ": " + msgLine);

                // ... xử lý msgLine nếu cần
            }

            LOGGER.info(() -> "Finished reading file: " + filePath);
        } catch (IOException e) {
            // Java 9+: defer concatenation bằng Supplier + Throwable
            LOGGER.log(Level.SEVERE, e, () -> "I/O error while reading file: " + filePath);
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, e, () -> "Unexpected error during processing for file: " + filePath);
        }
    }
}
