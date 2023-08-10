package programmers;
import java.util.*;
import java.io.*;
public class Cache {
    // arraydeque로 구현하자. deque.reemove(Stinrg)
    static String [] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
    static HashMap<String, Integer> cache;

    public static void main(String[] args) throws Exception{
        int answer = 0;
        int cacheSize = 0;
        cache = new HashMap();
        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
        }
        if (cacheSize==0){
            System.out.println(cities.length * 5);
            return;
        }
        for (String city:cities
             ) {
            // 캐시에 있으면
            if (cache.containsKey(city)){
                // aging, cache hit +1, age = 0;
                aging();
                answer += 1;
                cache.put(city, 0);
            }else { // 없으면
                // 캐시가 아직 여유있나?
                if (cache.size() < cacheSize){
                    // 그럼 aging, 추가해야지, miss +5
                    aging();
                    answer += 5;
                    cache.put(city, 0);
                }else { // LRU로 캐시 교체
                    // aging, 교체할 키 선택, 교체, miss + 5
                    aging();
                    String maxKey = doLRU();
                    cache.remove(maxKey);
                    cache.put(city, 0);
                    answer += 5;
                }
            }
        }
        System.out.println(answer);
    }
    public static void aging(){
        for (String key:cache.keySet()
             ) {
            cache.put(key, cache.get(key) + 1);
        }
    }
    public static String doLRU(){
        int max = -1;
        String maxKey = "";
        for (String k: cache.keySet()
             ) {
            if (max < cache.get(k)){
                maxKey = k;
                max = cache.get(k);
            }
        }
        return maxKey;
    }
}
