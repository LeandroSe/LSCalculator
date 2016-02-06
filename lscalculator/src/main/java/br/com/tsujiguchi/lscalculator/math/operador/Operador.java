package br.com.tsujiguchi.lscalculatorlibrary.math.operador;

/**
 * Created by leandrose on 09/01/16.
 */
public abstract class Operador {

    public abstract String getOperador();

    public abstract OperadorEnum getOperadorEnum();

    public abstract boolean isPrioridade();

    public static Operador instance(String operador) {
        switch (operador) {
            case "+":
                return new Soma();
            case "-":
                return new Subtrair();
            case "*":
                return new Multiplicacao();
            case "/":
            case "÷":
                return new Divisao();

            default:
                throw new IllegalArgumentException("Operador não implementado.");
        }
    }

    public static Operador instance(OperadorEnum operadorEnum) {
        switch (operadorEnum) {
            case SOMAR:
                return new Soma();
            case SUBTRAIR:
                return new Subtrair();
            case MULTIPLICAR:
                return new Multiplicacao();
            case DIVIDIR:
                return new Divisao();

            default:
                throw new IllegalArgumentException("Operador não implementado.");
        }
    }

    public enum OperadorEnum {
        SOMAR, SUBTRAIR, MULTIPLICAR, DIVIDIR
    }

}
