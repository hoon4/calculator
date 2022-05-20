import java.util.Scanner;

public class example7 {
    public static void main(String[] args) {
        System.out.println("[안내]피보나치 수열 프로그램 시작.");

        int count = getUserInput();                       // 수열의 길이 입력받는 메소드 호출
        int[] fibo = new int[count];                      // 피보나치 수열 배열 생성
        getFibonacci(fibo);                               // 피보나치 수열 생성 메소드 호출
        printNumbers(fibo);                               // 피보나치 수열 출력 메소드 호출

    }
    public static int getUserInput() {
        System.out.print("원하는 수열의 개수를 입력해 주세요 : ");
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        sc.close();

        return count;
    }

    public static void getFibonacci(int[] fibo) {
        fibo[0] = 1;          // 피보나치 수열 0,1번 1로 초기설정
        fibo[1] = 1;
        int a = fibo[0];
        int b = fibo[1];

        for(int i = 2; i < fibo.length; i++) {    // 피보나치 수열 계산
            fibo[i] = a + b;
            a = b;
            b = fibo[i];
        }
    }

    public static void printNumbers(int[] arr) {
        System.out.println("[피보나치 수열 출력]");

        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
