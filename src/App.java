import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Validator validator = new Validator();

        while (true) {
            System.out.println("Меню");
            System.out.println("1. Зашифровать файл методом Цезаря");
            System.out.println("2. Расшифровать файл с использованием ключа");
            System.out.println("3. Расшифровать файл методом brute force");
            System.out.println("0. Выйти из программы");

            int choice = console.nextInt();
            console.nextLine();

            switch (choice) {
                case 1:
                    encryptFile(console, validator);
                    break;
                case 2:
                    decryptFile(console, validator);
                    break;
                case 3:
                    bruteForceDecrypt(console, validator);
                    break;
                case 0:
                    System.out.println("Завершение работы программы");
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void encryptFile(Scanner console, Validator validator) {
        System.out.println("Укажите путь к файлу для шифрования");
        String file = console.nextLine();
        System.out.println("Укажите ключ для шифрования");
        String key = console.nextLine();
        System.out.println("Укажите путь для сохранения зашифрованного файла");
        String cryptedFile = console.nextLine();

        if (validator.isFileExists(file) && validator.isFileNotEmpty(file) && validator.isValidKey(key)) {
            List<String> list = FileManager.readFile(file);
            StringBuilder sb = new StringBuilder();
            for (String line : list) {
                String cryptText = Cipher.crypt(line, Integer.parseInt(key));
                sb.append(cryptText).append("\n");
            }
            FileManager.writeFile(cryptedFile, sb.toString());
            System.out.println("Файл успешно зашифрован и сохранен по указанному пути");
        }
    }

    private static void decryptFile(Scanner console, Validator validator) {
        System.out.println("Укажите путь к зашифрованному файлу");
        String text = console.nextLine();
        System.out.println("Укажите ключ для расшифровки");
        String key = console.nextLine();
        System.out.println("Укажите путь для сохранения расшифрованного файла");
        String deCryptedFile = console.nextLine();

        if (validator.isFileExists(text) && validator.isFileNotEmpty(text) && validator.isValidKey(key)) {
            List<String> list = FileManager.readFile(text);
            StringBuilder sb = new StringBuilder();
            for (String line : list) {
                String decryptText = Cipher.decrypt(line, Integer.parseInt(key));
                sb.append(decryptText).append("\n");
            }
            FileManager.writeFile(deCryptedFile, sb.toString());
            System.out.println("Файл успешно расшифрован и сохранен");
        }
    }

    private static void bruteForceDecrypt(Scanner console, Validator validator) {
        System.out.println("Укажите путь к файлу для расшифровки");
        String path = console.nextLine();
        System.out.println("Укажите путь для сохранения расшифрованных файлов");
        String newPath = console.nextLine();

        if (validator.isFileExists(path) && validator.isFileNotEmpty(path)) {
            Cipher.brutForce(path, newPath);
            System.out.println("Расшифрованные файлы сохранены");
        }
    }
}
