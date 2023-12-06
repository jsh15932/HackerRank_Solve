import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'journeyToMoon' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY astronaut
     */

    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
       int[] parent = new int[n];
       long[] num = new long[n];
       
       for(int i = 0; i < parent.length; i++) {
           parent[i] = i;
           num[i] = 1;
       }
       
       for(List<Integer> list : astronaut) {
           int x = find(list.get(0), parent);
           int y = find(list.get(1), parent);
           
           if(x != y) {
               union(x, y, parent, num);
           }
       }
       
       long total = 0;
       long res = -1;
       
       for(int i = 0; i < parent.length; i++) {
           if(i == parent[i]) {
               if(res == -1) {
                   res = num[i];
               }
               
               else {
                   total += res * num[i];
                   res += num[i];
               }
           }
       }
       
       return total;
    }
    
    static void union(int x, int y, int[] parent, long[] num) {
        if(num[x] <= num[y]) {
            parent[x] = y;
            num[y] += num[x];
        }
        
        else {
            parent[y] = x;
            num[x] += num[y];
        }
    }
    
    static int find(int node, int[] parent) {
        if(node == parent[node]) {
            return node;
        }
        
        return parent[node] = find(parent[node], parent);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int p = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> astronaut = new ArrayList<>();

        IntStream.range(0, p).forEach(i -> {
            try {
                astronaut.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.journeyToMoon(n, astronaut);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}