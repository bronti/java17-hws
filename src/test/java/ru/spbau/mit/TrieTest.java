package ru.spbau.mit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieTest {

    @Test
    public void testSimple() {
        Trie trie = new TrieImpl();

        assertTrue(trie.add("abc"));
        assertTrue(trie.contains("abc"));
        assertEquals(1, trie.size());
        assertEquals(1, trie.howManyStartsWithPrefix("abc"));
    }

    @Test
    public void testEmptyTrie() {
        Trie trie = new TrieImpl();

        assertFalse(trie.contains("aaa"));
        assertEquals(0, trie.size());
        assertEquals(0, trie.howManyStartsWithPrefix("abc"));
    }

    @Test
    public void testSimpleAdd() {
        Trie trie = new TrieImpl();

        assertFalse(trie.contains("aaa"));
        assertEquals(0, trie.size());

        assertTrue(trie.add("aaa"));
        assertEquals(1, trie.size());
        assertTrue(trie.contains("aaa"));

        assertFalse(trie.add("aaa"));
        assertEquals(1, trie.size());
        assertTrue(trie.contains("aaa"));
    }

    @Test
    public void testSimpleRemove() {
        Trie trie = new TrieImpl();

        assertTrue(trie.add("aaa"));

        assertTrue(trie.remove("aaa"));
        assertEquals(0, trie.size());
        assertFalse(trie.contains("aaa"));

        assertFalse(trie.remove("aaa"));
        assertEquals(0, trie.size());
        assertFalse(trie.contains("aaa"));
    }

    @Test
    public void testSimplePrefix() {
        Trie trie = new TrieImpl();

        assertTrue(trie.add("aaa"));

        assertEquals(1, trie.howManyStartsWithPrefix("aaa"));
        assertEquals(1, trie.howManyStartsWithPrefix("aa"));
        assertEquals(1, trie.howManyStartsWithPrefix("a"));
        assertEquals(1, trie.howManyStartsWithPrefix(""));

        assertTrue(trie.remove("aaa"));
        assertEquals(0, trie.howManyStartsWithPrefix("aaa"));
        assertEquals(0, trie.howManyStartsWithPrefix(""));
    }

    @Test
    public void testEmptyString() {
        Trie trie = new TrieImpl();

        assertFalse(trie.contains(""));
        assertEquals(0, trie.size());
        assertEquals(0, trie.howManyStartsWithPrefix(""));

        assertTrue(trie.add(""));
        assertTrue(trie.contains(""));
        assertEquals(1, trie.size());
        assertEquals(1, trie.howManyStartsWithPrefix(""));

        assertTrue(trie.remove(""));
        assertFalse(trie.contains(""));
        assertEquals(0, trie.size());
        assertEquals(0, trie.howManyStartsWithPrefix(""));
    }

    @Test
    public void testContainsPrefixSuffix() {
        Trie trie = new TrieImpl();

        assertTrue(trie.add("aaa"));
        assertTrue(trie.contains("aaa"));
        assertFalse(trie.contains("aaaa"));
        assertFalse(trie.contains("aa"));
    }

    @Test
    public void testNotRemovedPrefix() {
        Trie trie = new TrieImpl();

        assertTrue(trie.add("aaa"));
        assertTrue(trie.add("aaaaa"));
        assertTrue(trie.remove("aaaaa"));
        assertTrue(trie.contains("aaa"));
        assertFalse(trie.contains("aaaaa"));
    }

    @Test
    public void testContains() {
        Trie trie = new TrieImpl();

        assertTrue(trie.add("aaa"));
        assertFalse(trie.contains("aaaaa"));
        assertTrue(trie.contains("aaa"));
        assertFalse(trie.contains(""));
    }
}
