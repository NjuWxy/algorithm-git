import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**链表区间逆序 todo 和之前链表回文有一个共同问题就是对链表的某个区间逆序
 * 将单个链表的每K个节点之间逆序，打印出新链表；最后不足K的节点数不需要逆序；要求时间复杂度为O(n)，额外空间复杂度为O(1)。
 * 画图
 * 比如 a(beforeStart)->b(start)->c->d->e>f(afterEnd) k=4   翻转后  a->e->d->c->b->f
 * kReverse(beforeStart,start,afterEnd,k)
 * 遍历链表，每k个送过去逆序
 */
public class P2_LinkedListRangeReverse {
    static class MyNode{
        String str;
        MyNode next;
        public MyNode(String str){
            this.str = str;
        }
    }
    private static void solution(String[] strs,int k){
        if(strs.length==0){
            System.out.println();
            return;
        }
        //构造链表
        MyNode head=null,curr=null,last=null;
        for (int i = 0; i < strs.length; i++) {
            curr = new MyNode(strs[i]);
            if(i==0){
                head = curr;
            }else {
                last.next = curr;
            }
            last = curr;
        }
        //k区间逆序
        MyNode p = head,beforeStart=null,start=null,afterEnd;
        int count = 0;
        int wholeCount = 0;
        while (p!=null){
            count++;
            wholeCount++;
            if(count==1){
                start = p;
            }
            if(count==k){
                afterEnd = p.next;
                MyNode temp = kReverse(beforeStart,start,afterEnd,k);//翻转之后start变量指向了k的结尾
                if(wholeCount==k){
                    head = temp;
                }
                count=0;
                p = start;
                beforeStart = p;
            }
            p = p.next;
        }
        print(head);
    }
    //返回翻转之后的开头
    private static MyNode kReverse(MyNode beforeStart,MyNode start,MyNode afterEnd,int k){
        MyNode last=afterEnd,curr=start,next=null;
        for (int i = 0; i <k-1 ; i++) {
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
    private static void print(MyNode head){
        MyNode p = head;
        StringBuilder builder = new StringBuilder();
        while (p!=null){
            builder.append(p.str+" ");
            p = p.next;
        }
        builder.deleteCharAt(builder.length()-1);
        System.out.println(builder.toString());
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cases = 0;
        try {
            cases = Integer.parseInt(reader.readLine());
            for(int i=0;i<cases;i++){
                String string = reader.readLine();
                String line = string.substring(string.indexOf(" ")+1,string.lastIndexOf(" "));
                String[] strs = line.split(" ");
                int k = Integer.parseInt(string.split(" ")[string.split(" ").length-1]);
                solution(strs,k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
