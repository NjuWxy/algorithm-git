import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**管道网络
 * Every house in the colony has at most one pipe going into it and at most one pipe going out of it.
 * Tanks and taps are to be installed in a manner such that every house with one outgoing pipe but no incoming pipe
 * gets a tank installed on its roof and every house with only an incoming pipe and no outgoing pipe gets a tap.
 * Find the efficient way for the construction of the network of pipes.
 */
public class C5A_PipeNet {
    static class Link{
        int start,end,value;
        public Link(int start,int end,int value){
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
    private static void insert(LinkedList<LinkedList<Link>> constructions,Link link){
        for (LinkedList<Link> links:constructions) {
            if(links.peekFirst().start==link.start){
                links.addFirst(link);
                return;
            }else if(links.peekLast().end==link.end){
                links.addLast(link);
                return;
            }
        }
        LinkedList<Link> links = new LinkedList<>();
        links.add(link);
        constructions.add(links);
    }

    private static LinkedList<LinkedList<Link>> construct(LinkedList<LinkedList<Link>> constructions){
        LinkedList<LinkedList<Link>> res = new LinkedList<>();
        while (!constructions.isEmpty()){
            LinkedList<Link> construction = constructions.pollFirst();
            boolean find = false;
            for (LinkedList<Link> construction2:constructions) {
                if(construction.peekFirst().start==construction2.peekLast().end){
                    construction2.addAll(construction);
                    find = true;
                    break;
                }else if(construction.peekLast().end==construction2.peekFirst().start){
                    construction2.addAll(0,construction);
                    find = true;
                    break;
                }
            }
            if(!find){
                res.add(construction);
            }
        }
        return res;

    }


    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] line0 = reader.readLine().split(" ");
                int n = Integer.parseInt(line0[0]);
                int q = Integer.parseInt(line0[1]);
                LinkedList<Link> pipes = new LinkedList<>();
                LinkedList<LinkedList<Link>> constructionsInput = new LinkedList<>();
                List<String> outs = new ArrayList<>();
                for (int i = 0; i < q ; i++) {
                    String[] line = reader.readLine().split(" ");
                    Link link = new Link(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]));
                    pipes.add(link);
                    insert(constructionsInput,link);
                }
                LinkedList<LinkedList<Link>> constructions = construct(constructionsInput);
                for (LinkedList<Link> links:constructions) {
                    StringBuilder out = new StringBuilder();
                    out.append(links.peekFirst().start).append(" ");
                    out.append(links.peekLast().end).append(" ");
                    int min = Integer.MAX_VALUE;
                    for (Link link:links) {
                        min = Math.min(min,link.value);
                    }
                    out.append(min);
                    outs.add(out.toString());
                }
                Comparator<String> comparator = new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return Integer.parseInt(o1.split(" ")[0])-Integer.parseInt(o2.split(" ")[0]);
                    }
                };
                outs.sort(comparator);
                System.out.println(outs.size());
                for (String out:outs) {
                    System.out.println(out);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
