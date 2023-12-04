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
     * Complete the 'bigSorting' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY unsorted as parameter.
     */

    public static List<String> bigSorting(List<String> unsorted) {
        Collections.sort(unsorted, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return compareStrings(s1, s2);
            }
        });
        return unsorted;
    }
    
    public static int compareStrings(String s1, String s2) {
        if(s1.length() < s2.length()) {
            return -1;
        }
        
        else if(s1.length() > s2.length()) {
            return 1;
        }
        
        for(int i = 0; i < s1.length(); i++) {
            if((int) s1.charAt(i) < (int) s2.charAt(i)) {
                return -1;
            }
            if((int) s1.charAt(i) > (int) s2.charAt(i)) {
                return 1;
            }
        }
        
        return 0;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> unsorted = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bigSorting(unsorted);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
