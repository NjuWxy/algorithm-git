/**先升后降
 * 从一列不重复的数中筛除尽可能少的数使得从左往右看，这些数是从小到大再从大到小的。
 * 找出数组的所有子集，判断这些子集是否满足先升后降
 * 找出数组arr的所有子集：
 *      用arr.length长度的二进制数i(0=<i<(1<<arr.length))表示所有可能，第j位为1表示arr[j]在这个子集中，为0表示不在（(1<<j)&i==1）
 *      在遍历的过程中判断是否符合先升后降，并记录最大长度，把符合的加到结果集中
 *      外循环为i(0=<i<(1<<arr.length))，内循环为0=<j<len，内循环不满足先升后降的时候跳出
 * 对结果集先筛选出长度为最大长度的，再排序，输出
 */
public class P1_RiseAndDown {
}
