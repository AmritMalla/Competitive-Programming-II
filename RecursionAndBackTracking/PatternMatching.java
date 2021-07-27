import java.io.*;
import java.util.*;

public class Main {

    public static void solution(String str, String pattern, Map<Character,String> map){
        if(pattern.length() == 0){
            if(str.length() > 0) return;
            for(Map.Entry<Character, String> entry: map.entrySet()){
                System.out.print(entry.getKey() + " -> " + entry.getValue() + ", ");
            }
            System.out.println(".");
            return;
        }

        char key = pattern.charAt(0);
        String restOfPattern = pattern.substring(1);
        if(map.containsKey(key)){
            String val = map.get(key);
            if(val.length() <= str.length()){
                String prefix = str.substring(0, val.length());
                if(val.equals(prefix)){
                    solution(str.substring(val.length()), restOfPattern, map);
                }
            }
        }else{
            for(int i = 0;i<str.length();i++){
                String prefix = str.substring(0,i + 1);
                map.put(key, prefix);
                solution(str.substring(i + 1), restOfPattern, map);
                map.remove(key);
            }
        }

    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        String pattern = scn.next();
        Map<Character,String> map = new LinkedHashMap<>();
        solution(str,pattern,map);
    }
}