import java.io.*;
import java.util.*;
public class Solution{
    private static final FastReader fr = new FastReader();
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        String s = fr.next();
        StringBuilder answer = new StringBuilder();
        Queue<Node> queue = new PriorityQueue<>((a,b) -> b.s.compareTo(a.s)); //Sort the nodes in decreasing lexicographical order
        for(int i=0;i<n-1;i++) queue.offer(new Node(s.substring(i,i+2),i,i+1));
        TreeSet<Integer> remaining_indices = new TreeSet<>();
        for(int i=0;i<n;i++) remaining_indices.add(i);
        while(!queue.isEmpty()){
            Node node = queue.remove(); 
            if(remaining_indices.contains(node.left) && remaining_indices.contains(node.right)){  //If not deleted both the left and right end of the substring
                remaining_indices.remove(node.left); //Delete the left end
                remaining_indices.remove(node.right); //Delete the right end
                answer.append(node.s);
                Integer L = remaining_indices.floor(node.left); //Check if there is a non deleted index to the left of current deleted index
                Integer R = remaining_indices.ceiling(node.right); //Check if there is a non deleted index to the right of current deleted index
                if(L != null && R != null) queue.offer(new Node("" + s.charAt(L) + s.charAt(R),L,R));
            }
        }
        out.println(answer);
        out.flush();
    }

}
class Node{
    String s;
    int left;
    int right;

    public Node(String s, int left, int right) {
        this.s = s;
        this.left = left;
        this.right = right;
    }
}
class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() { return Integer.parseInt(next()); }

    long nextLong() { return Long.parseLong(next()); }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
