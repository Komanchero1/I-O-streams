import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileManager dir = new FileManager();


        GameProgress progress_1 = new GameProgress(3000, 1500, 25, 101, 45);
        GameProgress progress_2 = new GameProgress(10000, 2800, 35, 504, 12);
        GameProgress progress_3 = new GameProgress(15000, 3500, 40, 457, 20);


        //создаем директорию "src"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\src", "src")).append("\n");
        //создаем директорию "res"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\res", "res")).append("\n");
        //создаем директорию "savegames"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\savegames", "savegames")).append("\n");
        //создаем директорию "temp"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\temp", "temp")).append("\n");
        //создаем директорию "main"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\src\\main", "main")).append("\n");
        //создаем директорию "test"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\src\\test", "test")).append("\n");
        //создаем директорию "drawables"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\res\\drawables", "dravables")).append("\n");
        //создаем директорию "vectors"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\res\\vectors", "vectors")).append("\n");
        //создаем директорию "icons"
        sb.append(dir.createDirectories("C:\\Users\\mehan\\Desktop\\Games\\res\\icons", "icons")).append("\n");
        //создаем файл "Main.java"
        sb.append(dir.createFile("C:\\Users\\mehan\\Desktop\\Games\\src\\main\\Main.java", "Main.java")).append("\n");
        //создаем файл "Utils.java"
        sb.append(dir.createFile("C:\\Users\\mehan\\Desktop\\Games\\src\\main\\Utils.java", "Utils.java")).append("\n");
        //создаем файл "temp.txt"
        sb.append(dir.createFile("C:\\Users\\mehan\\Desktop\\Games\\temp\\temp.txt", "temp.txt")).append("\n");


        //записываем лог в файл "temp.txt"
        dir.saveFile("C:\\Users\\mehan\\Desktop\\Games\\temp\\temp.txt", sb.toString());

        //сохраняем данные каждого объекта в файлы
        progress_1.saveGame(progress_1, "C:\\Users\\mehan\\Desktop\\Games\\savegames\\save1.dat");
        progress_2.saveGame(progress_2, "C:\\Users\\mehan\\Desktop\\Games\\savegames\\save2.dat");
        progress_3.saveGame(progress_3, "C:\\Users\\mehan\\Desktop\\Games\\savegames\\save3.dat");

        //создаем масив путей к файлам с сохраненными данными
        String[] pathSaveFile = {
                "C:\\Users\\mehan\\Desktop\\Games\\savegames\\save1.dat",
                "C:\\Users\\mehan\\Desktop\\Games\\savegames\\save2.dat",
                "C:\\Users\\mehan\\Desktop\\Games\\savegames\\save3.dat"
        };

        //архивируем файлы с данными и удаляем уже заархивированные файлы
        progress_1.zipFiles("C:\\Users\\mehan\\Desktop\\Games\\savegames\\progress.zip", pathSaveFile);
        progress_1.openZip("C:\\Users\\mehan\\Desktop\\Games\\savegames\\progress.zip", "C:\\Users\\mehan\\Desktop\\Games\\savegames");
        progress_1.openProgress(pathSaveFile);

    }
}