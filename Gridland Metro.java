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

class Pair {
    int first, second;
    
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Result {
    public static long gridlandMetro(int n, int m, int k, List<List<Integer>> track) {
        Map<Integer, List<Pair>> map = new HashMap<>();
        
        for (List<Integer> t : track) {
            int row = t.get(0);
            int start = t.get(1);
            int end = t.get(2);
            
            if (!map.containsKey(row)) {
                map.put(row, new ArrayList<>());
            }
            
            merge(map.get(row), start, end);
        }
        
        long ans = (long) n * m;
        
        for (List<Pair> list : map.values()) {
            for (Pair p : list) {
                ans -= p.second - p.first + 1;
            }
        }
        
        return ans;
    }
    
    private static void merge(List<Pair> list, int start, int end) {
        if (list.isEmpty()) {
            list.add(new Pair(start, end));
            return;
        }

        int lastIdx = list.size() - 1;
        Pair lastPair = list.get(lastIdx);
        
        if (start <= lastPair.second) {
            lastPair.second = Math.max(lastPair.second, end);
        } 
        
        else {
            list.add(new Pair(start, end));
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> track = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                track.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.gridlandMetro(n, m, k, track);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}