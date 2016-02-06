package br.com.tsujiguchi.lscalculatorlibrary.math.operador;

/**
 * Created by leandrose on 09/01/16.
 */
public class Divisao extends Operador {

    @Override
    public String getOperador() {
        return "/";
    }

    @Override
    public OperadorEnum getOperadorEnum() {
        return OperadorEnum.DIVIDIR;
    }

    @Override
    public boolean isPrioridade() {
        return true;
    }

}
