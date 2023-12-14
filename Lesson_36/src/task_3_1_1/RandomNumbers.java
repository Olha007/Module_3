package task_3_1_1;

/**
 * 1. Додайте пакет iostreams.
 * 2. Додайте до цього пакету клас RandomNumbers з методом main(String[] args), у якому викликається метод
 * void writeRandomBytes(File file), що виконує запис у бінарний файл randomdata.bin у каталозі програми 100
 * байтів із значеннями, обраними випадковим чином у повному діапазоні значень байта.
 * 3. Виведіть у методі main(String[] args) довжину заповненого файлу у байтах.
 * 4. Також додайте до методу main(String[] args) виклик методу byte[] readBytes(File file), що виконує зчитування
 * з заповненого випадковими байтами файлу їх значення у байтовий буфер, який повертається з методу.
 * 5. Відсортуйте у методі main(String[] args) буфер байтів у натуральному порядку та виведіть його на екран.
 *
 * @version 1.0
 * @author Olha Nozdriukhina
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class RandomNumbers {

    public static void main(String[] args) {
        try {
            File file = new File("randomdata.bin");

            //Запис 100 випадкових байтів
            writeRandomBytes(file);

            //Вивести довжину файлу
            System.out.println("File length in bytes: " + file.length());

            //Читання
            byte[] bytes = readBytes(file);

            Arrays.sort(bytes);

            System.out.println("Sorted bytes:");
            for (byte b : bytes) {
                System.out.print(b + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRandomBytes(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            Random rand = new Random();
            byte[] bytes = new byte[100];
            rand.nextBytes(bytes);
            fos.write(bytes);
        }
    }

    public static byte[] readBytes(File file) throws IOException {
        byte[] bytes = new byte[100];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        }
        return bytes;
    }
}




