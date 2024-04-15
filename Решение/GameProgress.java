import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance, int i) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    //сохраняем данные
    public void saveGame(GameProgress progress, String path) {
        try (FileOutputStream outputStream = new FileOutputStream(path);// создаем поток для записи в файл на диске по указанному пути
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {//создаем поток для сериализации обьекта
            objectOutputStream.writeObject(progress);//сериализуем и записываем обьект в поток
        } catch (Exception e) {
            System.out.println("не удалось сохранить данные");
        }
    }

    //архивируем файлы и удаляем уже заархивированные файлы
    public void zipFiles(String zipFilePath, String[] filesToZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFilePath))) { // открываем поток для записи данных в zip-файл
            for (String filePath : filesToZip) { // перебираем файлы которые нужно запаковывать
                File file = new File(filePath); // создаем обьект File для текущего файла
                try (FileInputStream fis = new FileInputStream(file)) {  //открываем поток для чтения данных из файла
                    ZipEntry entry = new ZipEntry(file.getName());//создаем новую запись в zip-файле с именем текущего файла.
                    zout.putNextEntry(entry);//добавляем новую запись в zip-файл.
                    byte[] buffer = new byte[1024];// чтения данных из файла в буфер
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zout.write(buffer, 0, length);//записываем данные из буфера в файл
                    }
                    zout.closeEntry();//закрываем текущую запись
                } catch (IOException e) {
                    System.out.println("Ошибка при архивации файла: " + filePath);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + zipFilePath);
        }
        for (String filePath : filesToZip) {
            File file = new File(filePath);
            file.delete();//удаляем исходный файл после его успешной архивации
        }
    }

    //распаковываем файлы
    public void openZip(String pathZip, String pathFolder) {
        //создаем поток для чтения архива
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathZip));) {
            //распаковываем файлы архива
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                //получаем название файла
                String fileName = zipEntry.getName();
                //создаем файл в указанной директории
                File file = new File(pathFolder + File.separator + fileName);
                file.getParentFile().mkdir();
                //создаем поток для записи файла
                FileOutputStream fos = new FileOutputStream(file);
                //копируем данные из архива в файл
                byte[] buffer = new byte[1024];
                int it;
                while ((it = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, it);
                }
                //закрываем потоки
                fos.close();
                zis.closeEntry();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //читаем и выводим в консоль сохраненные обьекты
    public void openProgress(String[] saveFilePaths) {
        //перебираем строки масива
        for (String saveFilePath : saveFilePaths) {
            //читаем данные текущего файла
            try (FileInputStream fileInputStream = new FileInputStream(saveFilePath);
                 //читаем обьекты из fileInputStream
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                //читаем обьекты из fileInputStream и проводим их апкаст
                GameProgress gameProgress = (GameProgress) objectInputStream.readObject();
                //результат выводим в консоль
                System.out.println(gameProgress.toString());
            } catch (FileNotFoundException e) {
                System.out.println("Файл сохранения не найден: " + saveFilePath);
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла сохранения: " + saveFilePath);
            } catch (ClassNotFoundException e) {
                System.out.println("Класс GameProgress не найден");
            }
        }
    }
}
