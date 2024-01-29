package com.bds.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static final int DEFAULT_BUFFER_SIZE = 16384;

    public static void copyStream(
            InputStream inputStream,
            java.io.OutputStream outputStream)
            throws IOException {
        copyStream(inputStream, outputStream, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Read entire input stream and writes all data to output stream
     * then closes input and flushed output
     */
    public static void copyStream(
            InputStream inputStream,
            java.io.OutputStream outputStream,
            int bufferSize)
            throws IOException {
        try {
            byte[] writeBuffer = new byte[bufferSize];
            for (int br = inputStream.read(writeBuffer); br != -1; br = inputStream.read(writeBuffer)) {
                outputStream.write(writeBuffer, 0, br);
            }
            outputStream.flush();
        } finally {
            // Close input stream
            inputStream.close();
        }
    }

    public static String readFile(Path path) throws IOException {
        if (Files.notExists(path)) {
            return null;
        }
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }
}
