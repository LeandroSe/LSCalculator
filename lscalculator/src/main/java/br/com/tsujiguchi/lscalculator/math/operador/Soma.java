package br.com.tsujiguchi.lscalculator.math.operador;

/**
 * Created by leandrose on 09/01/16.
 */
public class Soma extends Operador {

    @Override
    public String getOperador() {
        return "+";
    }

    @Override
    public OperadorEnum getOperadorEnum() {
        return OperadorEnum.SOMAR;
    }

    @Override
    public boolean isPrioridade() {
        return false;
    }

}
