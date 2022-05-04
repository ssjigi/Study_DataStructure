public class HeapMain {
    private static int[][] rawData;

    public static void main(String[] args) {
        // /res/test_sample.txt file 이 없을 경우, 주석 해제하자.
        // 테스트할 값을 자동 입력 받기 위해, test_sample.txt file 을 생성한다.
        // Util.makeTestDataToSampleFile(5, 10);

        int[][] testData = Util.loadTestDataFromSampleFile();

        Heap<Integer> heap = new Heap<>();

        // 1. heap 에 data 추가
        for (int[] i : testData) {
            for (int j : i) {
                heap.add(j);
            }

            // 2. heap 내부상태 출력
            System.out.print("heap 내부 상태 : ");
            for (Object val : heap.toArray()) {
                System.out.print(val + " ");
            }
            System.out.println();

            // 3. heap 에서 한개씩 요소 제거
            System.out.print("heap 요소 뽑기 : ");
//            while (!heap.isEmpty()) {
//                System.out.print(heap.remove() + " ");
//            }
        }
    }
}

