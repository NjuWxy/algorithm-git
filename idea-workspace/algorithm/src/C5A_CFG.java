import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class C5A_CFG {
    // number of houses and number
    // of pipes
    static int n, p;

    // Array rd stores the
    // ending vertex of pipe
    static int rd[] = new int[1100];

    // Array wd stores the value
    // of diameters between two pipes
    static int wt[] = new int[1100];

    // Array cd stores the
    // starting end of pipe
    static int cd[] = new int[1100];

    // arraylist a, b, c are used
    // to store the final output
    static List<Integer> a =
            new ArrayList<Integer>();

    static List <Integer> b =
            new ArrayList<Integer>();

    static List <Integer> c =
            new ArrayList<Integer>();

    static int ans;

    static int dfs(int w)
    {
        if (cd[w] == 0)
            return w;
        if (wt[w] < ans)
            ans = wt[w];

        return dfs(cd[w]);
    }

    // Function to perform calculations.
    static void solve(int arr[][])
    {
        int i = 0;

        while (i < p)
        {

            int q = arr[i][0];
            int h = arr[i][1];
            int t = arr[i][2];

            cd[q] = h;
            wt[q] = t;
            rd[h] = q;
            i++;
        }

        a=new ArrayList<Integer>();
        b=new ArrayList<Integer>();
        c=new ArrayList<Integer>();

        for (int j = 1; j <= n; ++j)

            /*If a pipe has no ending vertex
            but has starting vertex i.e is
            an outgoing pipe then we need
            to start DFS with this vertex.*/
            if (rd[j] == 0 && cd[j]>0) {
                ans = 1000000000;
                int w = dfs(j);

                // We put the details of
                // component in final output
                // array
                a.add(j);
                b.add(w);
                c.add(ans);
            }

        System.out.println(a.size());

        for (int j = 0; j < a.size(); ++j)
            System.out.println(a.get(j) + " "
                    + b.get(j) + " " + c.get(j));
    }

    // main function
    public static void main(String args[])
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] line0 = reader.readLine().split(" ");
                n = Integer.parseInt(line0[0]);
                p = Integer.parseInt(line0[1]);
//                LinkedList<C5A_PipeNet.Link> pipes = new LinkedList<>();
                int[][] arr = new int[p][3];
//                LinkedList<LinkedList<C5A_PipeNet.Link>> constructionsInput = new LinkedList<>();
//                List<String> outs = new ArrayList<>();
                for (int i = 0; i < p ; i++) {
                    String[] line = reader.readLine().split(" ");
//                    C5A_PipeNet.Link link = new C5A_PipeNet.Link(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]));
//                    pipes.add(link);
                    arr[i] = new int[]{Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2])};
//                    insert(constructionsInput,link);
                }
                solve(arr);
//                LinkedList<LinkedList<C5A_PipeNet.Link>> constructions = construct(constructionsInput);
//                for (LinkedList<C5A_PipeNet.Link> links:constructions) {
//                    StringBuilder out = new StringBuilder();
//                    out.append(links.peekFirst().start).append(" ");
//                    out.append(links.peekLast().end).append(" ");
//                    int min = Integer.MAX_VALUE;
//                    for (C5A_PipeNet.Link link:links) {
//                        min = Math.min(min,link.value);
//                    }
//                    out.append(min);
//                    outs.add(out.toString());
//                }
//                Collections.sort(outs);
//                System.out.println(outs.size());
//                for (String out:outs) {
//                    System.out.println(out);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        n = 9;
//        p = 6;
//
//        // set the value of the araray
//        // to zero
//        for(int i = 0; i < 1100; i++)
//            rd[i] = cd[i] = wt[i] = 0;
//
//        int arr[][] = { { 7, 4, 98 },
//                { 5, 9, 72 },
//                { 4, 6, 10 },
//                { 2, 8, 22 },
//                { 9, 7, 17 },
//                { 3, 1, 66 } };
//        solve(arr);
    }
}
