import java.util.Arrays;
import java.util.Scanner;

public class PrimeSum {
    private static final int IS_PRIME = 0;
    private static final int NOT_PRIME = 1;
    private static final int FROM_LEFT_TO_RIGHT = 1;
    private static final int FROM_RIGHT_TO_LEFT = 2;

    private static int[] getPrimeArr(int number){
        int[] prime = new int[number+1];//0表示是素数，1表示不是素数
        prime[0] = NOT_PRIME;
        prime[1] = NOT_PRIME;
        for (int i = 2; i <= number; i++) {
            for (int j = i+i; j <= number ; j+=i) {
                prime[j] = NOT_PRIME;
            }
        }
        return prime;
    }
    private static int findNextPrime(int[] prime, int start, int direction){
        if(direction==FROM_LEFT_TO_RIGHT){
            for (int i = start+1; i < prime.length; i++) {
                if(prime[i]==IS_PRIME){
                    return i;
                }
            }
        }else {
            for (int i = start-1; i >=0; i--) {
                if(prime[i]==IS_PRIME){
                    return i;
                }
            }
        }
        return 0;
    }
    private static void solution(int number){
        int[] prime = getPrimeArr(number);
        int i = 0,j=number;
        i=findNextPrime(prime,i,FROM_LEFT_TO_RIGHT);
        j=findNextPrime(prime,j,FROM_RIGHT_TO_LEFT);
        while (i<j){
            if(i+j<number){
                i = findNextPrime(prime,i,FROM_LEFT_TO_RIGHT);
            }else if(i+j>number){
                j=findNextPrime(prime,j,FROM_RIGHT_TO_LEFT);
            }else {
                System.out.println(i+" "+j);
                break;
            }
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int number = scanner.nextInt();
            solution(number);
        }
    }
}
