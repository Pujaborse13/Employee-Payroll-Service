package com.employeepayrollservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryWatchService {

    public static void main(String[] args) {
        String directoryToWatch = "exampleDir";
        Path path = Paths.get(directoryToWatch);

        // Ensure the directory exists
        File directory = new File(directoryToWatch);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Directory created: " + directoryToWatch);
        }

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            registerAll(path, watchService);

            System.out.println("Watching directory: " + path);

            while (true) {
                WatchKey watchKey = watchService.take();

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();

                    System.out.println("Event kind: " + kind + ", File affected: " + eventPath);

                    // If the event is for a new file, count its entries
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        File newFile = path.resolve(eventPath).toFile();
                        if (newFile.isFile()) {
                            System.out.println("New file detected. Counting entries...");
                            countFileEntries(newFile);
                        }
                    }
                }

                boolean valid = watchKey.reset();
                if (!valid) {
                    break;
                }
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void registerAll(final Path start, final WatchService watchService) throws IOException {
        // Register the root directory
        start.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

        // Walk through all subdirectories and register them
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void countFileEntries(File file) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                count++;
            }
            System.out.println("Number of entries in file '" + file.getName() + "': " + count);
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
            e.printStackTrace();
        }
    }
}
