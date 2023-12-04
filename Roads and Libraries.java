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
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */
     
     public static boolean[] visited;
     public static ArrayList<Integer> arr[]; 

    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        arr = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        long cnt = 0;
        long ans = 0;
        
        for(int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < cities.size(); i++) {
            int x = cities.get(i).get(0);
            int y = cities.get(i).get(1);
            arr[x].add(y);
            arr[y].add(x);
        }
        
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                cnt++;
                long res = dfs(i);
                ans += Math.min(c_road * res + c_lib, (res + 1) * c_lib);
            }
        }
        
        return ans;
    }
    
    public static long dfs(int node) {
        long res = 0;
        
        for(int i = 0; i < arr[node].size(); i++) {
            if(!visited[arr[node].get(i)]) {
                visited[arr[node].get(i)] = true;
                res++;
                res += dfs(arr[node].get(i));
            }
        }
        
        return res;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int c_lib = Integer.parseInt(firstMultipleInput[2]);

                int c_road = Integer.parseInt(firstMultipleInput[3]);

                List<List<Integer>> cities = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        cities.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                long result = Result.roadsAndLibraries(n, c_lib, c_road, cities);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
