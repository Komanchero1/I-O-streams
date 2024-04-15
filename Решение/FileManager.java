import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    //создаем директорию
    public String createDirectories(String path, String nameDir) {
        String log; // объявляем переменную для возврата сообщения о создании директории
        File newDir = new File(path);//создаем новый объект с указанным путем
        if (newDir.mkdir()) { //проверяем удалось создать директорию
            log = "директория \"" + nameDir + "\"  создана";
            return log;//возвращаем сообщение о создании директории
        } else {
            log = "директория \"" + nameDir + "\"  уже существует";
            return log;//возвращаем сообщение о существующей директории
        }
    }

    //создаем файл
    public String createFile(String path, String nameFile) {
        String log;// объявляем переменную для возврата сообщения о создании файла
        File newFile = new File(path);//создаем новый объект с указанным путем
        try {
            if (newFile.createNewFile()) {//проверяем удалось создать файл
                log = "файл \"" + nameFile + "\" создан";
                return log;//возвращаем сообщение о создании файла
            } else {
                log = "файл \"" + nameFile + "\" уже существует";
                return log;//возвращаем сообщение о существующем файле
            }
        } catch (IOException e) {
            log = "не верно указан путь"; //возвращаем сообщение если путь не верен
            return log;
        }
    }

    //записываем лог в файл
    public void saveFile(String path, String content) {
        File file = new File(path);//создаем новый объект с указанным путем
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {//создаем объект для записи в файл
            writer.write(content);//записываем содержимое в файл
        } catch (IOException e) {
            System.out.println("файл не записан");
        }
    }
}
