package com.au.yaveyn.java.dictionary;

import com.au.yaveyn.java.dictionary.Dictionary;

/**
 * Created by bronti on 09.03.17.
 */
public class DictionaryImpl implements Dictionary {

    private final static double MAX_LOAD = 1.5;

    private Node[] data = new Node[0];
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean contains(String key) {
        return getNode(key) != null;
    }

    public String get(String key) {
        Node result = getNode(key);
        if (result == null) {
            return null;
        }
        else {
            return result.value;
        }
    }

    public String put(String key, String new_value) {
        Node same = getNode(key);
        if (same != null) {
            String result = same.value;
            same.value = new_value;
            return result;
        }

        checkedRehash();
        forcedPut(key, new_value);
        return null;
    }

    public String remove(String key) {
        int binNum = getBinNum(key);
        if (binNum < 0 || data[binNum] == null) {
            return null;
        }
        Node curr = data[binNum];
        if (curr.key.equals(key)) {
            String result = curr.value;
            data[binNum] = curr.next;
            --size;
            return result;
        }
        while (curr.next != null) {
            if (curr.next.key.equals(key)) {
                String result = curr.next.value;
                curr.next = curr.next.next;
                --size;
                return result;
            }
            curr = curr.next;
        }
        return null;
    }

    public void clear() {
        data = new Node[0];
        size = 0;
    }

    public DictionaryImpl() {
    }

    private class Node {
        final String key;
        String value;

        Node next = null;

        public Node(String key_, String value_) {
            key = key_;
            value = value_;
        }

        public Node(String key_, String value_, Node next_) {
            this(key_, value_);
            next = next_;
        }
    }

    private int getBinNum(String key) {
        if (data.length == 0) {
            return -1;
        }
        return (((key.hashCode() % data.length) + data.length) % data.length);
    }

    private Node getBinHead(String key) {
        int binNum = getBinNum(key);
        if (binNum < 0) {
            return null;
        }
        return data[binNum];
    }

    private Node getNode(String key) {
        Node curr = getBinHead(key);
        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    private void forcedPut(String key, String value) {
        int binNum = getBinNum(key);
        data[binNum] = new Node(key, value, data[binNum]);
        ++size;
    }

    private void checkedRehash() {
        if (size + 1 <= MAX_LOAD * data.length) {
            return;
        }
        if (data.length == Integer.MAX_VALUE) {
            return;
        }
        Node[] oldData = data;

        int newBinCount = (int) Math.min(Integer.MAX_VALUE, (long) data.length * 2 + 1);

        data = new Node[newBinCount];
        size = 0;
        for (Node head: oldData) {
            while (head != null) {
                forcedPut(head.key, head.value);
                head = head.next;
            }
        }
    }
}
