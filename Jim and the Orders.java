import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> mp = new LinkedHashMap<Integer, Integer>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        for(int i = 1; i <= n; i++) {
            int order = sc.nextInt();
            int prep = sc.nextInt();
            mp.put(i, order + prep);
        }
        
        Set<Entry<Integer, Integer>> set = mp.entrySet();
        List<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(set);
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
            public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        
        for(Entry<Integer, Integer> e : list) {
            System.out.print(e.getKey() + " ");
        }
        
        sc.close();
    }
}
