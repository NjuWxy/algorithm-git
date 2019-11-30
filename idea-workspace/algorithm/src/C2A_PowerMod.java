//https://blog.csdn.net/quinnnorris/article/details/77587096

import java.util.Scanner;

/**
 * 数学公式
 * <p>
 * 快速幂算法：a^b
 * 其主要理论依据如下：
 * 1，当b为偶数时，a^b可以转为a^2的b/2次方。
 * 2，当b为奇数时，a^b可以转为a^2的b/2次方，再乘以a。
 * 而a^2的b/2次方，以可以使用上述方式转为a^4的b/4次方再乘以某个数。代码如下:
 * while(b>0){
 * if(b%2==1){
 * res = res*a;
 * }
 * a = a*a;
 * b = b/2;
 * }
 * 再结合a*b%m=(a%m)*(b%m)%m，每一步都取模就好了
 */
public class C2A_PowerMod {
    private static int quickPower(int a, int b) {
        int res = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                res = res * a;
            }
            a = a * a;
            b = b / 2;
        }
        return res;
    }
    private static int quickPowerMod(int a, int b, int c) {
        int res = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                a = a%c;
                res = res%c;
                res = res * a;
            }
            a = a%c;
            a = a * a;
            b = b / 2;
        }
        return res%c;
    }

    //    public static void powerMod(int a,int b,int c){
//        int res = 1;
//        a %= c;
//        for (; b != 0; b /= 2) {
//            if (b % 2 == 1)
//                res = (res * a) % c;
//            a = (a * a) % c;
//        }
//        System.out.println(res);
//    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int a = scanner.nextInt(), b = scanner.nextInt(), c = scanner.nextInt();
            System.out.println(quickPowerMod(a,b,c));
        }

    }
}
