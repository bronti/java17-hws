package com.au.yaveyn.java.dictionary;

/**
 * Created by bronti on 09.03.17.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DictionaryTest {

    @Test
    public void testSimple() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.put("abc", "abc_val"));
        assertTrue(dict.contains("abc"));
        assertEquals(1, dict.size());
        assertEquals("abc_val", dict.get("abc"));
    }

    @Test
    public void testEmptyDict() {
        Dictionary dict = new DictionaryImpl();

        assertFalse(dict.contains("aaa"));
        assertEquals(0, dict.size());
    }

    @Test
    public void testSimpleAdd() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.put("aaa", "aaa_val"));

        assertEquals(1, dict.size());
        assertTrue(dict.contains("aaa"));
        assertEquals("aaa_val", dict.get("aaa"));
    }

    @Test
    public void testDoubleAdd() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.put("aaa", "aaa_val1"));
        assertEquals("aaa_val1", dict.put("aaa", "aaa_val2"));

        assertEquals(1, dict.size());
        assertTrue(dict.contains("aaa"));
        assertEquals("aaa_val2", dict.get("aaa"));
    }

    @Test
    public void testSimpleRemove() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.put("aaa", "aaa_val"));
        assertEquals("aaa_val", dict.remove("aaa"));

        assertEquals(0, dict.size());
        assertFalse(dict.contains("aaa"));
        assertEquals(null, dict.get("aaa"));
    }

    @Test
    public void testEmptyRemove() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.remove("aaa"));

        assertEquals(0, dict.size());
        assertFalse(dict.contains("aaa"));
        assertEquals(null, dict.get("aaa"));
    }

    @Test
    public void testDoubleRemove() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.put("aaa", "aaa_val"));
        assertEquals("aaa_val", dict.remove("aaa"));
        assertEquals(null, dict.remove("aaa"));

        assertEquals(0, dict.size());
        assertFalse(dict.contains("aaa"));
        assertEquals(null, dict.get("aaa"));
    }

    @Test
    public void testSimpleGet() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.put("aaa", "aaa_val"));
        assertEquals("aaa_val", dict.get("aaa"));
    }

    @Test
    public void testEmptyGet() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.get("aaa"));
    }

    @Test
    public void testDoubleGet() {
        Dictionary dict = new DictionaryImpl();

        assertEquals(null, dict.put("aaa", "aaa_val"));
        assertEquals("aaa_val", dict.get("aaa"));
        assertEquals("aaa_val", dict.get("aaa"));
    }
}