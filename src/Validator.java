import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {

    public boolean isFileNotEmpty(String filePath) {
        Path path = Path.of(filePath);
        try {
            return Files.size(path) > 0;
        } catch (IOException e) {
            throw new RuntimeException("Файл не должен быть пустым", e);
        }
    }

    public boolean isValidKey(String key) {
        if (key == null) {
            System.out.println("Введен некорректный ключ");
            return false;
        }
        try {
            Integer.parseInt(key);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Введен некорректный ключ");
            return false;
        }
    }

    public boolean isFileExists(String filePath) {
        Path path = Path.of(filePath);
        boolean exists = Files.exists(path);
        if (!exists) {
            throw new RuntimeException("Файл не найден по указанному пути " + path);
        }
        return true;
    }
}