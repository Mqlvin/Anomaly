package backup;

import log.Logger;
import log.Severity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Methods {
    public static void copyDirectory() throws IOException {
        Path source = Paths.get("./runtime");
        Path destination = Paths.get("./backup/temp");
        File destinationFile = new File(destination.toString());

        if(destinationFile.exists()) {
            Files.walk(destination).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            destinationFile.delete();
        }

        Files.walk(source).forEach(sourcePath -> {
                try {
                    Path targetPath = destination.resolve(source.relativize(sourcePath));
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    Logger.log(e.toString(), Severity.FATAL);
                }
        });
    }

    public static void removeUselessFiles() {
        File logFolder = new File("./backup/temp/logs");
        File[] listOfLogs = logFolder.listFiles();
        String date;

        ArrayList<String> logGetDate = new ArrayList<>();
        try {
            logGetDate = (ArrayList<String>) Files.readAllLines(Paths.get(logFolder + "/latest.txt"));
        } catch(IOException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
        date = logGetDate.get(0).split(" - ")[0].replace("/", "∕");

        for(File listOfLog : listOfLogs) {
            if(listOfLog.isFile() && !(listOfLog.getName().startsWith(date))) {
                listOfLog.delete();
            } else {
                try {
                    String desiredName = listOfLog.getName().substring(0, listOfLog.getName().lastIndexOf('.'));
                    if(!logFolder.exists()) {
                        logFolder.mkdir();
                    }
                    byte[] bytes = new byte[1024];
                    ZipInputStream zis = new ZipInputStream(new FileInputStream(listOfLog));
                    ZipEntry ze = zis.getNextEntry();
                    while(ze != null) {
                        String filePath = logFolder.getPath() + File.separator + desiredName;
                        if(!ze.isDirectory()) {
                            FileOutputStream fos = new FileOutputStream(filePath);
                            int len;
                            while((len = zis.read(bytes)) > 0) {
                                fos.write(bytes, 0, len);
                            }
                            fos.close();
                        } else {
                            File dir = new File(filePath);
                            dir.mkdir();
                        }

                        zis.closeEntry();
                        ze = zis.getNextEntry();
                    }
                    zis.closeEntry();
                    zis.close();
                } catch(Exception e){
                    Logger.log(e.toString(), Severity.FATAL);
                }
            }
        }
        File[] newFiles = logFolder.listFiles();
        for(File newFile : newFiles) {
            if(newFile.getName().endsWith(".zip")) {
                newFile.delete();
            } else {
                newFile.renameTo(new File(newFile.getPath() + ".txt"));
            }
        }
    }

    public static void createZip() throws IOException {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/YYYY");
        Date date = new Date();
        File inputDirectory = new File("./backup/temp");
        File outputZip = new File("./backup/" + time.format(date).replace("/", "∕") + "-1.zip");
        if(outputZip.exists()) {
            outputZip = new File("./backup/" + time.format(date).replace("/", "∕") + "-∞.zip");
        }
        outputZip.getParentFile().mkdirs();
        List listFiles = new ArrayList();
        listFiles(listFiles, inputDirectory);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputZip));
        createZipFile(listFiles, inputDirectory, zipOutputStream);
    }

    public static void createZipFile(List listFiles, File inputDirectory, ZipOutputStream zipOutputStream) throws IOException {

        File[] fileFiles = new File[listFiles.size()];
        for(int i = 0; listFiles.size() > i; i++) {
            fileFiles[i] = new File(listFiles.get(i).toString());
        }

        for (File file : fileFiles) {
            String filePath = file.getCanonicalPath();
            int lengthDirectoryPath = inputDirectory.getCanonicalPath().length();
            int lengthFilePath = file.getCanonicalPath().length();

            String zipFilePath = filePath.substring(lengthDirectoryPath + 1, lengthFilePath);

            ZipEntry zipEntry = new ZipEntry(zipFilePath);
            zipOutputStream.putNextEntry(zipEntry);

            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) >= 0) {
                zipOutputStream.write(bytes, 0, length);
            }
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }

    public static List listFiles(List listFiles, File inputDirectory) {
        File[] allFiles = inputDirectory.listFiles();
        if (allFiles != null) {
            for (File file : allFiles) {
                if (file.isDirectory()) {
                    listFiles(listFiles, file);
                } else {
                    listFiles.add(file);
                }
            }
        }
        return listFiles;
    }

    public static void deleteDir(File directory) {
        try {
            Files.walkFileTree(Paths.get(directory.toString()), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc == null) {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    } else {
                        throw exc;
                    }
                }
            });
        }
        catch (IOException e)
        {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }

    public static void waitTime(int MS) {
        try {
            TimeUnit.MILLISECONDS.sleep(MS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
