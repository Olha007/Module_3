package task_3_1_2_readerswriters;

/**
 * 1. Додайте пакет readerswriters.
 * 2. Додайте до цього пакету клас TextFileCopyCompare з методом main(String[] args), у якому викликається метод
 * long textFileCopy(File file), що виконує ПОСИМВОЛЬНЕ копіюваня вмісту текстового файлу "Harry_Potter.txt"
 * (знаходиться у каталозі з завданням, скопіюйте його до каталогу з програмою) до файлу з ім'ям "Harry_Potter-copy.txt"
 * у тому ж каталозі засобами класів Reader та Writer із вимірюваням тривалості копіювання у мілісекундах, яка
 * повертається методом.
 * 3. Також додайте до методу main(String[] args) виклик методу long textFileBufferedCopy(File file), що виконує
 * ПОСИМВОЛЬНЕ копіюваня ІЗ БУФЕРІЗАЦЄЮ потоками BufferedReader та BufferedWriter того ж самого текстового файлу
 * із вимірюваням тривалості копіювання у мілісекундах, яка повертається методом.
 * 4. Виведіть у методі main(String[] args) тривалості обох копіювань та порівняйте результати.
 *
 * @version 1.0
 * @author Olha Nozdriukhina
 */

import java.io.*;

public class TextFileCopyCompare {
    public static long textFileCopy(File file) throws IOException {
        long startTime = System.currentTimeMillis();
        try (Reader reader = new FileReader(file);
             Writer writer = new FileWriter("Harry_Potter-copy.txt")) {
            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);
            }
        }
        return System.currentTimeMillis() - startTime;
    }

    public static long textFileBufferedCopy(File file) throws IOException {
        long startTime = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Harry_Potter-buffered-copy.txt"))) {
            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);
            }
        }
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) throws IOException {
        //File file = new File("Harry_Potter.txt");

        File file = new File("/Users/olhanozdriukhina/IdeaProjects/Module_3/Lesson_36/src/task_3_1_2_readerswriters/Harry_Potter.txt");
        if (file.exists()) {
            System.out.println("File exists.");
        } else {
            System.out.println("File doesn`t exists");
        }

        long durationWithoutBuffer = textFileCopy(file);
        long durationWithBuffer = textFileBufferedCopy(file);

        System.out.println("Time taken without buffer: " + durationWithoutBuffer + " ms");
        System.out.println("Time taken with buffer: " + durationWithBuffer + " ms");
    }
}