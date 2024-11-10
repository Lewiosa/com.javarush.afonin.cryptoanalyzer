import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Cipher {

    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л',
            'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э',
            'Ю', 'Я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static String crypt(String text, int key) {
        StringBuilder sb = new StringBuilder();

        if (key > ALPHABET.length) {
            key %= ALPHABET.length;
        }
        if (key < 0) {
            key %= -ALPHABET.length;
            key += ALPHABET.length;
        }

        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < ALPHABET.length; j++) {
                if (text.charAt(i) == ALPHABET[j]) {
                    if (j + key < ALPHABET.length) {
                        sb.append(ALPHABET[j + key]);
                    } else {
                        sb.append(ALPHABET[(j + key) % ALPHABET.length]);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static String decrypt(String text, int key) {
        return crypt(text, -key);
    }

    public static void brutForce(String text, String path) {
        try {
            if (!Files.exists(Path.of(path))) {
                Files.createDirectory(Path.of(path));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < ALPHABET.length; i++) {
            String s = fileToString(text, Integer.toString(i));
            FileManager.writeFile(path + "/" + (i + 1) + ".txt", s);
        }
    }

    public static String fileToString(String file, String key) {
        List<String> list = FileManager.readFile(file);
        StringBuilder sb = new StringBuilder();

        for (String line : list) {
            String cryptText = Cipher.crypt(line, Integer.parseInt(key));
            sb.append(cryptText).append("\n");
        }
        return sb.toString();
    }
}
