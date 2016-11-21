package com.zhanghao.containers;

import java.util.Map;

/**
 * Created by zhanghao on 16/11/21.
 */
public class MapEntry<K, V> implements Map.Entry<K, V>{
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            V result = value;
            value = v;
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MapEntry)) return false;

            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;

            if (key != null ? !key.equals(mapEntry.key) : mapEntry.key != null) return false;
            return !(value != null ? !value.equals(mapEntry.value) : mapEntry.value != null);

        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
}
