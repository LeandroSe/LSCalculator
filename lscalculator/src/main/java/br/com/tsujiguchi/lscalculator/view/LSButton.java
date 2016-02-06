package br.com.tsujiguchi.lscalculator.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import br.com.tsujiguchi.lscalculator.R;

/**
 * Created by leandrose on 30/12/15.
 */
public class LSButton extends Button {

    private static final String TAG = "LSButton";

    private int mColumnSize;

    public LSButton(Context context) {
        this(context, null);
    }

    public LSButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LSButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(
                attrs,
                new int[]{
                        R.attr.columnSize
                },
                defStyleAttr,
                0
        );
        mColumnSize = a.getInt(0, 1);
    }

    public int getColumnSize() {
        return mColumnSize;
    }

}
