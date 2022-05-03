import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HeapMain {
    private static int[][] rawData;

    public static void main(String[] args) {
        // /res/test_sample.txt file 이 없을 경우, 주석 해제하자.
        // 테스트할 값을 자동 입력 받기 위해, test_sample.txt file 을 생성한다.
        // Util.makeTestDataToSampleFile(5, 10);

        int[][] testData = Util.loadTestDataFromSampleFile();

        Heap<Integer> heap = new Heap<>();

        // 1. heap 에 data 추가
//        for (int i = 0; i < testData.; i++) {
//            for (int j = 0; j < ; j++) {
//
//            }
//        }
    }
}
