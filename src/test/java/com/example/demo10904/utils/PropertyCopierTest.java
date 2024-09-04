package com.example.demo10904.utils;

import com.example.demo10904.Source;
import com.example.demo10904.Target;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 19750
 * @version 1.0
 */
public class PropertyCopierTest {
    @Test
    public void testCopyBasicTypes() throws Exception {
        Source source = new Source();
        source.setIntValue(10);
        source.setStringValue("Hello");

        Target target = new Target();

        ObjectUtil.copyProperties(source, target, new String[]{"intValue", "stringValue"});

        assertEquals(10, target.getIntValue());
        assertEquals("Hello", target.getStringValue());
    }
    @Test
    public void testCopyArray() throws Exception {
        Source source = new Source();
        source.setIntArray(new int[]{1, 2, 3});
        source.setStringArray(new String[]{"One", "Two", "Three"});

        Target target = new Target();

        ObjectUtil.copyProperties(source, target, new String[]{"intArray", "stringArray"});

        assertArrayEquals(new int[]{1, 2, 3}, target.getIntArray());
        assertArrayEquals(new String[]{"One", "Two", "Three"}, target.getStringArray());
    }

    @Test
    public void testCopyCollection() throws Exception {
        Source source = new Source();
        source.setCollection(Arrays.asList(1, 2, 3));

        Target target = new Target();

        ObjectUtil.copyProperties(source, target, new String[]{"collection"});

        assertEquals(Arrays.asList(1, 2, 3), target.getCollection());

        target.getCollection().add(4);
        assertFalse(source.getCollection().contains(4));
    }
}
