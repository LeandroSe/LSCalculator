package br.com.tsujiguchi.lscalculatorlibrary.math;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by leandrose on 09/01/16.
 */
public class MathStringTest {

    @Test
    public void testSomar() {
        assertEquals(
                5.0 + 5.0,
                MathString.calc("5+5"),
                0.001
        );
    }

    @Test
    public void testSubtrair() {
        assertEquals(
                5.0 - 1.0,
                MathString.calc("5-1"),
                0.001
        );
    }

    @Test
    public void testMultiplicar() {
        assertEquals(
                5.0 * 2.0,
                MathString.calc("5*2"),
                0.001
        );
    }

    @Test
    public void testDividir() {
        double result = 5.0 / 2.0;
        assertEquals(
                result,
                MathString.calc("5/2"),
                0.001
        );
    }

    @Test
    public void testSomarMultiplicar() {
        assertEquals(
                5.0 + 6.0 * 10.0,
                MathString.calc("5+6*10"),
                0.001
        );
    }

    @Test
    public void testSubtrairDividirSomar() {
        assertEquals(
                3.0 - 8.0 / 4.0 + 2.0,
                MathString.calc("3-8/4+2"),
                0.001
        );
    }

    public void testSomarDividirSubtrairSomarMultiplicar() {
        assertEquals(
                8.0 + 2.0 / 5.0 - 8.0 + -5.0 * 15.0,
                MathString.calc("8+2/5-8*15"),
                0.0001
        );
    }

    @Test
    public void testLong() {
        assertEquals(
                100.0 * 99.0,
                MathString.calc("100*99"),
                0.001
        );
    }

    @Test
    public void testDecimal() {
        assertEquals(
                100.51 + 15.2 * 5.8,
                MathString.calc("100.51+15.2*5.8"),
                0.001
        );
    }

}
