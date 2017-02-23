package ru.spbau.mit;


/**
 * Created by bronti on 20.02.17.
 */
public class TrieImpl implements Trie {
    private static final int LETTERS_COUNT = 52;
    private int wordCount = 0;
    private boolean isTerminal = false;
    private boolean isRedundant = false;
    private TrieImpl[] children = new TrieImpl[LETTERS_COUNT];

    public TrieImpl() {
    }

    private static int charToInd(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + LETTERS_COUNT / 2;
        }
        throw new IllegalArgumentException();
    }

    private static int indToChar(int i) {
        if (i >= 0 && i < LETTERS_COUNT / 2) {
            return 'a' + i;
        }
        if (i >= LETTERS_COUNT / 2 && i < LETTERS_COUNT) {
            return 'A' + i - LETTERS_COUNT / 2;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean add(String element) {
        return addSuffix(element, 0);
    }

    private boolean addSuffix(String element, int at) {
        if (at > element.length() || at < 0) {
            throw new IllegalArgumentException();
        }
        if (at >= element.length()) {
            if (isTerminal) {
                return false;
            } else {
                isTerminal = true;
                wordCount++;
                return true;
            }
        }
        int currInd = charToInd(element.charAt(at));
        if (children[currInd] == null) {
            children[currInd] = new TrieImpl();
        }
        boolean res = children[currInd].addSuffix(element, at + 1);
        if (res) {
            wordCount++;
        }
        return res;
    }

    @Override
    public boolean contains(String element) {
        return containsSuffix(element, 0);
    }

    private boolean containsSuffix(String element, int at) {
        if (at > element.length() || at < 0) {
            throw new IllegalArgumentException();
        }
        if (at == element.length()) {
            return isTerminal;
        }
        int currInd = charToInd(element.charAt(at));
        return children[currInd] != null
                && children[currInd].containsSuffix(element, at + 1);
    }

    @Override
    public boolean remove(String element) {
        return removeSuffix(element, 0);
    }

    private boolean removeSuffix(String element, int at) {
        if (at > element.length() || at < 0) {
            throw new IllegalArgumentException();
        }
        if (at == element.length()) {
            if (!isTerminal) {
                return false;
            } else {
                isTerminal = false;
                wordCount--;
                isRedundant = true;
                for (TrieImpl child : children) {
                    if (child != null) {
                        isRedundant = false;
                        break;
                    }
                }
                return true;
            }
        }
        int currInd = charToInd(element.charAt(at));
        if (children[currInd] == null) {
            return false;
        }
        boolean res = children[currInd].removeSuffix(element, at + 1);
        if (children[currInd].isRedundant) {
            children[currInd] = null;
        }
        if (res) {
            --wordCount;
        }
        return res;
    }

    @Override
    public int size() {
        return wordCount;
    }

    @Override
    public int howManyStartsWithPrefix(String prefix) {
        return howManyStartsWithPrefixSuffix(prefix, 0);
    }

    private int howManyStartsWithPrefixSuffix(String prefix, int at) {
        if (at > prefix.length() || at < 0) {
            throw new IllegalArgumentException();
        }
        if (at == prefix.length()) {
            return wordCount;
        }
        int currInd = charToInd(prefix.charAt(at));
        if (children[currInd] == null) {
            return 0;
        }
        return children[currInd].howManyStartsWithPrefixSuffix(prefix, at + 1);
    }
}
