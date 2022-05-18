package ru.dimk.cachehw;


import java.util.*;

public class MyCache<K, V> implements HwCache<K, V> {
    //Надо реализовать эти методы
    private WeakHashMap<K, V> cache = new WeakHashMap<>();
    private List<HwListener> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        listeners.forEach(hwListener -> hwListener.notify(key, value, "put"));
    }

    @Override
    public void remove(K key) {
        listeners.forEach(hwListener -> hwListener.notify(key, null, "remove"));
        cache.remove(key);
    }

    @Override
    public V get(K key) {
        listeners.forEach(hwListener -> hwListener.notify(key, null, "get"));
        return cache.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }
}
