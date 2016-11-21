package com.zhanghao.containers;


import java.util.*;

/**
 * Created by zhanghao on 16/11/21.
 */

public class SlowMap<K, V> extends AbstractMap<K, V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();
    public V put(K key, V value){
        V oldValue = get(key);
        if(!keys.contains(key)){
            keys.add(key);
            values.add(value);
        }else {
            values.set(keys.indexOf(key), value);
        }
        return oldValue;
    }

    public V get(Object key){
        if(!keys.contains(key)){
            return null;
        } else{
            return values.get(keys.indexOf(key));
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while (ki.hasNext()){
            set.add(new MapEntry<K,V>(ki.next(), vi.next()));
        }
        return set;
    }
    public static void main(String[] args){
        SlowMap<String, String> m = new SlowMap<>();
        for(int i = 1; i <= 15; i++) {
            m.put("AAAA"+String.valueOf(i), "BBBB"+String.valueOf(i));
        }
        System.out.println(m);
        System.out.println(m.get("AAAA3"));
        System.out.println(m.entrySet());
    }
}
