package br.com.tsujiguchi.lscalculator.math;

import java.util.ArrayList;

import br.com.tsujiguchi.lscalculator.math.operador.Operador;

/**
 * Created by leandrose on 09/01/16.
 */
public class Math {

    protected final ArrayList<Object> mCalculatora;
    protected boolean mIsEsperadoOperador = false;
    protected int mPosOperadorCalcular = -1;

    public Math() {
        mCalculatora = new ArrayList<>();
    }

    public void add(Integer valor) {
        if (mIsEsperadoOperador) {
            throw new RuntimeException("É esperado um operador.");
        }
        mCalculatora.add(valor.doubleValue());
        mIsEsperadoOperador = true;
    }

    public void add(Double valor) {
        if (mIsEsperadoOperador) {
            throw new RuntimeException("É esperado um operador.");
        }
        mCalculatora.add(valor);
        mIsEsperadoOperador = true;
    }

    public void add(Float valor) {
        if (mIsEsperadoOperador) {
            throw new RuntimeException("É esperado um operador.");
        }
        mCalculatora.add(valor.doubleValue());
        mIsEsperadoOperador = true;
    }

    public void add(Operador operador) {
        if (!mIsEsperadoOperador) {
            throw new RuntimeException("É esperado um valor numérico.");
        }
        mCalculatora.add(operador);
        mIsEsperadoOperador = false;
    }

    public void add(Operador.OperadorEnum operador) {
        add(Operador.instance(operador));
    }

    public double calcular() {
        if (false == mIsEsperadoOperador) {
            throw new RuntimeException("A formula deve terminar com um numero");
        }

        ArrayList<Object> list = (ArrayList) mCalculatora.clone();

        boolean isPrioridade = true;
        while (list.size() > 1) {
            double num1, num2;
            Operador operador;
            if (isPrioridade
                    && isPrioridade(list)
                    && -1 != mPosOperadorCalcular) {
                num1 = (double) list.get(mPosOperadorCalcular - 1);
                operador = (Operador) list.get(mPosOperadorCalcular);
                num2 = (double) list.get(mPosOperadorCalcular + 1);

                list.set(
                        mPosOperadorCalcular - 1,
                        calcular(num1, num2, operador.getOperadorEnum())
                );
                list.remove(mPosOperadorCalcular + 1);
                list.remove(mPosOperadorCalcular);
            } else {
                isPrioridade = false;
                num1 = (double) list.get(0);
                num2 = (double) list.get(2);
                operador = (Operador) list.get(1);

                list.set(0, calcular(num1, num2, operador.getOperadorEnum()));
                list.remove(2);
                list.remove(1);
            }
        }

        return (double) list.get(0);
    }

    public double calcular(double num1, double num2, Operador.OperadorEnum operador) {
        switch (operador) {
            case SOMAR:
                return num1 + num2;
            case SUBTRAIR:
                return num1 - num2;
            case MULTIPLICAR:
                return num1 * num2;
            case DIVIDIR:
                return num1 / num2;

            default:
                throw new RuntimeException("Operador não implementado.");
        }
    }

    protected boolean isPrioridade(ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof Operador
                    && ((Operador) obj).isPrioridade()) {
                mPosOperadorCalcular = i;
                return true;
            }
        }

        return false;
    }

}
