import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainClass {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String PATH = "Test.txt";

    public static void main(String[] args) throws IOException {
        createFile(PATH);
        Integer[] numbers = Arrays.stream(readFile(PATH).split(","))
                .map(Integer::parseInt).toArray(Integer[]::new);
        System.out.println(Arrays.toString(numbers));

        // 1-st task
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));

        //2-nd task
        Arrays.sort(numbers, Collections.reverseOrder());
        System.out.println(Arrays.toString(numbers));
    }

    // Создание файла
    public static Path createFile(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int [] numbers = new int[21];

        for(int j = 0; j <= 20; j++){
            numbers[j] = j;
        }

        // Алгоритм Фишера – Йетса
        for (int i = numbers.length - 1; i >= 1; i--){
            int j = random.nextInt(i + 1);
            int temp = numbers[j];
            numbers[j] = numbers[i];
            numbers[i] = temp;
            stringBuilder.append(numbers[i]).append(",");
        }

        return Files.write(Paths.get(path),
                stringBuilder.substring(0, stringBuilder.length() - 1).getBytes(UTF_8));
    }

    //Считывание файла
    public static String readFile(String path) throws IOException {
       return new String(Files.readAllBytes(Paths.get(path)), UTF_8);
    }
}
