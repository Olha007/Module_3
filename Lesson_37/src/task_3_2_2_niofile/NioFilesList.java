package task_3_2_2_niofile;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * 1. Додайте пакет niofile.
 * 2. Додайте до цього пакету клас NioFilesList з методом main(String[] args), у якому організуйте з застосуванням
 * методів класу java.nio.file.Files виведення на екран.
 * - списку файлів та каталогів, які містяться у каталозі, вказаному значенням аргументу командного рядка args[0]
 * та його підкаталогах, якщо значення аргументу командного рядка args[1] - "true";
 * - списку файлів та каталогів, які містяться лише в каталозі, вказаному значенням аргументу командного рядка args[0],
 * якщо значення аргументу командного рядка args[1] - "false".
 * 3. Додайте значення аргументів до конфігурації запуску програми та виконайте її два рази, задаючи для args[1]
 * значення true та false.
 *
 * @author Olha Nozdriukhina
 * @version 1.0
 */

public class NioFilesList {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажіть шлях до каталогу та булеве значення (true або false)");
            return;
        }

        Path directory = Paths.get(args[0]);
        boolean recursive = Boolean.parseBoolean(args[1]);

        try {
            if (recursive) {
                try (Stream<Path> paths = Files.walk(directory)) {
                    paths.forEach(System.out::println);
                }
            } else {
                try (Stream<Path> paths = Files.list(directory)) {
                    paths.forEach(System.out::println);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}