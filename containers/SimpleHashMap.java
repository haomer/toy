package com.zhanghao.containers;

import java.util.*;

/**
 * Created by zhanghao on 16/11/21.
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
    static final int SIZE = 997;

    //散列表
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    public V put(K key, V value){
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K, V>>();

        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()){
            MapEntry<K, V> iPair = it.next();
            if(iPair.getKey().equals(key)){
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        if(!found)
            buckets[index].add(pair);
        return oldValue;
    }
    public V get(Object key){
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null)
            return null;
        for(MapEntry<K, V> iPair : buckets[index]){
            if(iPair.getKey().equals(key))
                return iPair.getValue();
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Entry<K, V>>();
        for(LinkedList<MapEntry<K,V>> bucket : buckets){
            if (bucket == null) continue;
            for(MapEntry<K,V> mpair : bucket){
                set.add(mpair);
            }
        }
        return set;
    }
    public static void main(String[] args){
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        for(int i = 1; i <= 15; i++) {
            simpleHashMap.put("AAAA"+String.valueOf(i), "BBBB"+String.valueOf(i));
        }
        System.out.println(simpleHashMap);
        System.out.println(simpleHashMap.get("AAAA3"));
        System.out.println(simpleHashMap.entrySet());
    }
}
