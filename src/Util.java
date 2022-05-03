import java.io.*;
import java.util.Random;
import java.util.Scanner;

public final class Util {
    private static final String SAMPLE_FILE_PATH = "res/test_sample.txt";

    public static void makeTestDataToSampleFile(int height, int width) {
        Random random = new Random();
        PrintStream fileStream;
        PrintStream consoleStream = System.out;
        try {
            fileStream = new PrintStream(new FileOutputStream(SAMPLE_FILE_PATH));
            System.setOut(fileStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(height + " " + width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(random.nextInt(1000));
                if (j < width - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        fileStream.close();
        System.setOut(consoleStream);
        System.out.println("makeTestDataToSampleFile, done!! height = " + height + ", width = " + width);
    }

    public static int[][] loadTestDataFromSampleFile() {

        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(SAMPLE_FILE_PATH);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(inputStream);
        int height = sc.nextInt();
        int width = sc.nextInt();

        System.out.println("loadTestDataFromSampleFile, height = " + height + ", width = " + width);

        int[][] testData = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                testData[i][j] = sc.nextInt();
            }
        }

        return testData;
    }
}
