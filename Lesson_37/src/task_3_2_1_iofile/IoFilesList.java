package task_3_2_1_iofile;

import java.io.File;

/**
 * 1. Додайте пакет iofile.
 * 2. Додайте до цього пакету клас IoFilesList.
 * 3. У класі IoFilesList розробіть метод void walk(String path, boolean isWalk), який з використанням виключно засобів класу
 * java.io.File виводить на екран:
 * - список файлів та каталогів, які містяться у каталозі, вказаному значенням параметру path та його підкаталогах,
 * якщо параметр isWalk має значення "true";
 * - список файлів та каталогів, які містяться лише в каталозі, вказаному значенням параметру path, якщо параметр
 * isWalk має значення "false".
 * 4. Додайте до класу IoFilesList метод main(String[] args), у якому організуйте виклик метода walk з передачею
 * йому шляху до довільного каталогу з підкаталогами та файлами, який вводиться першим аргументом командного рядка
 * (args[0]), та значення булевого параметру, що вказує на необхідність рекурсивного обходу підкаталогів, яке вводиться
 * другим аргументом командного рядка (args[1]).
 * 5. Додайте значення аргументів до конфігурації запуску програми та виконайте її два рази, задаючи для args[1]
 * значення true та false.
 *
 * @author Olha Nozdriukhina
 * @version 1.0
 */

public class IoFilesList {
    public static void walk(String path, boolean isWalk) {
        File root = new File(path);
        if (root.exists() && root.isDirectory()) {
            walkRecursive(root, isWalk, 0);
        } else {
            System.out.println("Вказаний шлях не існує або не є каталогом");
        }
    }

    private static void walkRecursive(File dir, boolean isWalk, int level) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                for (int i = 0; i < level; i++) {
                    System.out.print("  ");
                }
                System.out.println(file.getName());
                if (file.isDirectory() && isWalk) {
                    walkRecursive(file, true, level + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необхідно передати шлях до каталогу та булевий параметр (true або false)");
            return;
        }
        String path = args[0];
        boolean isWalk = Boolean.parseBoolean(args[1]);
        walk(path, isWalk);
    }
}
