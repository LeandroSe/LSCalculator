package br.com.tsujiguchi.lscalculator.math.operador;

/**
 * Created by leandrose on 09/01/16.
 */
public class Subtrair extends Operador {

    @Override
    public String getOperador() {
        return "-";
    }

    @Override
    public OperadorEnum getOperadorEnum() {
        return OperadorEnum.SUBTRAIR;
    }

    @Override
    public boolean isPrioridade() {
        return false;
    }
    
}
