import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**链表回文 todo
 * 判断一个单向链表是否为回文结构。自定义链表数据结构，要求时间复杂度为O(n)，额外空间复杂度为O(1)。
 * 画图
 * 比如 a(beforeStart)->b(start)->c->d->e>f(afterEnd) k=4   翻转后  a->e->d->c->b->f
 * kReverse(beforeStart,start,afterEnd,k)
 * 右半区反转
 */
public class P2_LinkedListHuiWen {
    static class MyNode{
        String str;
        MyNode next;

        public MyNode(String str) {
            this.str = str;
        }
    }
    private static void solution2(String[] strs){
        if(strs.length==0){
            System.out.println(true);
            return;
        }
        //构建链表,找出翻转链表所需参数
        MyNode head=null,last=null,curr=null;
        MyNode beforeStart=null,start=null;
        for (int i = 0; i < strs.length; i++) {
            //构建链表
            curr = new MyNode(strs[i]);
            if(i==0){
                head = curr;
            }else {
                last.next = curr;
            }
            last = curr;
            //找出翻转链表所需参数
            if(i==(strs.length+1)/2-1){//!!!
                beforeStart = curr;
            }else if(i==(strs.length+1)/2){
                start = curr;
            }
        }
        curr.next = null;
        int k = strs.length/2;
        //右半区翻转
        MyNode rightPartStart = kReverse(beforeStart,start,null,k);
        System.out.println(isSame(head,rightPartStart,k));
//        print(head);

    }
    private static MyNode kReverse(MyNode beforeStart,MyNode start,MyNode afterEnd,int k){
        MyNode curr = start, last = afterEnd, next;
        for (int i = 0; i < k-1; i++) {//!!! k-1,最后一个单独处理
            next = curr.next;
            curr.next = last;
            last = curr;
            curr = next;
        }
        curr.next = last;
        if(beforeStart!=null){
            beforeStart.next = curr;
        }
        return curr;
    }

    private static boolean isSame(MyNode start1,MyNode start2,int k){
        boolean res = true;
        MyNode p1 = start1,p2=start2;
        for (int i = 0; i <k ; i++) {
            if(!p1.str.equals(p2.str)){
                res = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return res;
    }

    private static void print(MyNode head){
        MyNode p = head;
        while (p!=null){
            System.out.print(p.str+" ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cases = 0;
        try {
            cases = Integer.parseInt(reader.readLine());
            for(int i=0;i<cases;i++){
                String string = reader.readLine();
                String line = string.substring(string.indexOf(" ")+1);
                String[] strs = line.split(" ");
                solution2(strs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
