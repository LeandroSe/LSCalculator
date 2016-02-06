package br.com.tsujiguchi.lscalculatorlibrary.math;

import br.com.tsujiguchi.lscalculatorlibrary.math.operador.Operador;

/**
 * Created by leandrose on 06/01/16.
 */
public class MathString {

    public static double calc(String formula) {
        Math math = new Math();

        String numero = "";
        for (int i = 0; i < formula.length(); i++) {
            switch (formula.charAt(i)) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '\u00F7':
                    if ((formula.charAt(i) == '-' && numero.length() == 0)) {
                        numero += formula.charAt(i);
                    } else {
                        math.add(Double.valueOf(numero));
                        numero = "";

                        math.add(
                                Operador.instance(
                                        String.valueOf(formula.charAt(i))
                                )
                        );
                    }
                    break;

                default:
                    numero += formula.charAt(i);
                    break;
            }
        }

        math.add(Double.valueOf(numero));
        return math.calcular();
    }


}
