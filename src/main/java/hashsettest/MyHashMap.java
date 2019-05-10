package hashsettest;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 9:28 2019/3/20
 * @Modified By:
 */
class MyHashMap {
    int[][] hashMap;


    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        this.hashMap = new int[10000][1];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        hashMap[key][0] = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        if (hashMap[key][0] == 0) {
            return -1;
        }
        return hashMap[key][0];
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        hashMap[key][0] = 0;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
