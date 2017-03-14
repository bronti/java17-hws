package ru.spbau.mit;


import java.io.*;

/**
 * Created by bronti on 20.02.17.
 */
public class TrieImpl implements Trie, StreamSerializable {
    private static final int LETTERS_COUNT = 52;
    private int wordCount = 0;
    private boolean isTerminal = false;
    private TrieImpl[] children = new TrieImpl[LETTERS_COUNT];

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TrieImpl))
            return false;
        TrieImpl other = (TrieImpl) obj;
        if (isTerminal != other.isTerminal || wordCount != other.wordCount) {
            return false;
        }
        for (int i = 0; i < LETTERS_COUNT; i++) {
            if (children[i] == other.children[i]) {
                continue;
            }
            if (children[i] == null || other.children[i] == null) {
                return false;
            }
            if (! children[i].equals(other.children[i])) {
                return false;
            }
        }
        return true;
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

    private static char indToChar(int i) {
        if (i >= 0 && i < LETTERS_COUNT / 2) {
            return (char)('a' + i);
        }
        if (i >= LETTERS_COUNT / 2 && i < LETTERS_COUNT) {
            return (char)('A' + i - LETTERS_COUNT / 2);
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
                return true;
            }
        }
        int currInd = charToInd(element.charAt(at));
        if (children[currInd] == null) {
            return false;
        }
        boolean res = children[currInd].removeSuffix(element, at + 1);
        if (children[currInd].wordCount == 0) {
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

    private void dfsOut(char label, int num, DataOutputStream out) throws IOException {
        int nextNum = num;
        out.writeInt(num);
        out.writeChar(label);
        out.writeBoolean(isTerminal);
        out.writeInt(wordCount);
        for (int ind = 0; ind < LETTERS_COUNT; ind++) {
            if (children[ind] == null) {
                continue;
            }
            children[ind].dfsOut(indToChar(ind), ++nextNum, out);
            out.writeInt(num);
        }
    }

    @Override
    public void serialize(OutputStream out) throws IOException {
        DataOutputStream output = new DataOutputStream(out);
        dfsOut((char)0, 1, output);
        output.writeInt(0);
        output.flush();
    }

    private void dfsIn(int parentNum, int currentNum, DataInputStream in) throws IOException {
        isTerminal = in.readBoolean();
        wordCount = in.readInt();
        int nextNum = in.readInt();
        while (nextNum != parentNum) {
            int childInd = charToInd(in.readChar());
            children[childInd] = new TrieImpl();
            children[childInd].dfsIn(currentNum, nextNum, in);
            nextNum = in.readInt();
        }
    }

    @Override
    public void deserialize(InputStream in) throws IOException {
        DataInputStream input = new DataInputStream(in);
        children = new TrieImpl[LETTERS_COUNT];
        int firstNum = input.readInt();
        input.readChar();
        dfsIn(0, firstNum, input);
    }
}
