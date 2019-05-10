package hashsettest;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:12 2019/3/18
 * @Modified By:
 */
class MyHashSet {
    int[] collectors;
    int index = 0;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        this.collectors = new int[10000];

    }

    public void add(int key) {
        if (index > 9999) {
            return;
        }
        if (1 <= key && key <= 1000000) {
            collectors[index] = key;
            index++;
        }

    }

    public void remove(int key) {
        for (int i = 0; i < collectors.length; i++) {
            if (collectors[i] == key) {
                collectors[i] = 0;
            }

        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        for (int i = 0; i < collectors.length; i++) {
            if (collectors[i] == key) {
                return true;
            }

        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
