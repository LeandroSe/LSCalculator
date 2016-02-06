package br.com.tsujiguchi.lscalculatorlibrary.math;

import org.junit.Test;

import br.com.tsujiguchi.lscalculatorlibrary.math.operador.Operador;

import static org.junit.Assert.*;

/**
 * Created by leandrose on 09/01/16.
 */
public class MathTest {

    @Test
    public void testSomar() {
        Math math = new Math();
        math.add(5.0);
        math.add(Operador.instance(Operador.OperadorEnum.SOMAR));
        math.add(5.0);

        assertEquals(
                5.0 + 5.0,
                math.calcular(),
                0.001
        );
    }

    @Test
    public void testSubtrair() {
        Math math = new Math();
        math.add(5);
        math.add(Operador.instance(Operador.OperadorEnum.SUBTRAIR));
        math.add(1);

        assertEquals(
                5.0 - 1.0,
                math.calcular(),
                0.001
        );
    }

    @Test
    public void testMultiplicar() {
        Math math = new Math();
        math.add(5);
        math.add(Operador.instance(Operador.OperadorEnum.MULTIPLICAR));
        math.add(2);

        assertEquals(
                5.0 * 2.0,
                math.calcular(),
                0.001
        );
    }

    @Test
    public void testDividir() {
        Math math = new Math();
        math.add(5);
        math.add(Operador.instance(Operador.OperadorEnum.DIVIDIR));
        math.add(2);

        double result = 5.0 / 2.0;
        assertEquals(
                result,
                math.calcular(),
                0.001
        );
    }

    @Test
    public void testSomarMultiplicar() {
        Math math = new Math();
        math.add(5.0);
        math.add(Operador.OperadorEnum.SOMAR);
        math.add(6.0);
        math.add(Operador.instance(Operador.OperadorEnum.MULTIPLICAR));
        math.add(10.0);

        assertEquals(
                5.0 + 6.0 * 10.0,
                math.calcular(),
                0.001
        );
    }

    @Test
    public void testSubtrairDividirSomar() {
        Math math = new Math();
        math.add(3);
        math.add(Operador.OperadorEnum.SUBTRAIR);
        math.add(8);
        math.add(Operador.OperadorEnum.DIVIDIR);
        math.add(4);
        math.add(Operador.OperadorEnum.SOMAR);
        math.add(2);

        assertEquals(
                3.0 - 8.0 / 4.0 + 2.0,
                math.calcular(),
                0.001
        );
    }

    public void testSomarDividirSubtrairSomarMultiplicar() {
        Math math = new Math();
        math.add(8);
        math.add(Operador.OperadorEnum.SOMAR);
        math.add(2);
        math.add(Operador.OperadorEnum.DIVIDIR);
        math.add(5);
        math.add(Operador.OperadorEnum.SUBTRAIR);
        math.add(8);
        math.add(Operador.OperadorEnum.SOMAR);
        math.add(-5);
        math.add(Operador.OperadorEnum.MULTIPLICAR);
        math.add(15);

        assertEquals(
                8.0 + 2.0 / 5.0 - 8.0 + -5.0 * 15.0,
                math.calcular(),
                0.0001
        );
    }

    @Test
    public void testLong() {
        Math math = new Math();
        math.add(100);
        math.add(Operador.OperadorEnum.MULTIPLICAR);
        math.add(99);

        assertEquals(
                100.0 * 99.0,
                math.calcular(),
                0.001
        );
    }

    @Test
    public void testDecimal() {
        Math math = new Math();
        math.add(100.51);
        math.add(Operador.OperadorEnum.SOMAR);
        math.add(15.2);
        math.add(Operador.OperadorEnum.MULTIPLICAR);
        math.add(5.8);

        assertEquals(
                100.51 + 15.2 * 5.8,
                math.calcular(),
                0.001
        );
    }

}
