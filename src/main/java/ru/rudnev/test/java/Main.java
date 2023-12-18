package ru.rudnev.test.java;
import java.io .*;

public class Main {
    public static void main(String[] args) {
        File folder = new File("/home/rudnievi/IdeaProjects/javaio/src/main/java/ru/rudnev/test/java");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        // Выводим список файлов на экран
        System.out.println("Список текстовых файлов в корневом каталоге проекта:");
        for (File file : listOfFiles) {
            System.out.println(file.getName());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя файла: ");
        String fileName;
        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: ");
            return;
        }
        // Считываем содержимое файла и выводим его на экран
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл " + fileName + " не найден");
            return;
        }
        if (!file.isFile()) {
            System.out.println(fileName + " не является файлом");
            return;
        }
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            System.out.println("Содержимое файла " + fileName + ":");
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла " + fileName);
            return;
        }

        // Записываем введенную пользователем строку в указанный файл
        System.out.print("Введите строку для записи в файл: ");
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка чтения строки");
            return;
        }
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true))) {
            fileWriter.write(line);
            fileWriter.newLine();
            System.out.println("Строка успешно записана в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл " + fileName);
            return;
        }
    }

}