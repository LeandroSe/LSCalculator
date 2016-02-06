package br.com.tsujiguchi.lscalculator.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by leandrose on 31/12/15.
 */
public class LSImagemButton extends ImageButton {

    public LSImagemButton(Context context) {
        this(context, null);
    }

    public LSImagemButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LSImagemButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
