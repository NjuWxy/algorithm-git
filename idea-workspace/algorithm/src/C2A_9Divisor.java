/**有9个因数的数
 *Find the count of numbers less than N having exactly 9 divisors
 * 先分解质因数，得到p1^a1*p2^a2*...*pn^an。则全部因数的个数为(a1+1)(a2+1)...(an+1)，
 * 9=1*9=3*3，所以有九个因数的数分解质因数有两种情况，一种是p3^8,一种是p1^2*p2^2(p1!=p2)
 * 先求质数数组prime[]，再求p1,p2,p3的边界
 *
 * todo
 */
public class C2A_9Divisor {

}
