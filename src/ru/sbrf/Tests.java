package ru.sbrf;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class Tests {

    @Test
    public void testCheck() {

        String res = new HashSet<>(Arrays.asList("A", "B", "C", "D")).toString();

        assertEquals(res, Checker.check());

    }
}

