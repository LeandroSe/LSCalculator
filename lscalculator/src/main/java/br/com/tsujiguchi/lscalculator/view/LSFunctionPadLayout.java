package br.com.tsujiguchi.lscalculator.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import br.com.tsujiguchi.lscalculator.R;

/**
 * Created by leandrose on 29/12/15.
 */
public class LSFunctionPadLayout extends LinearLayout implements
        View.OnClickListener, View.OnLongClickListener {

    protected LSDisplay mDisplay = null;

    public LSFunctionPadLayout(Context context) {
        super(context);
    }

    public LSFunctionPadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LSFunctionPadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setOnClickListener(this);
            if (getChildAt(i).getId() == R.id.lsCalculatorFunctionDelete) {
                getChildAt(i).setOnLongClickListener(this);
            }
        }
    }

    // View.OnClickListener

    @Override
    public void onClick(View v) {
        final int lsCalculatorFunctionDelete = R.id.lsCalculatorFunctionDelete;
        if (v.getId() == R.id.lsCalculatorFunctionDelete) {
            String text = mDisplay.getText1();
            if (text.length() > 0) {
                mDisplay.setText1(
                        text.substring(
                                0,
                                text.length() - 1
                        )
                );
            }
        } else {
            mDisplay.onSendCharacter(((Button) v).getText().toString());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        mDisplay.setText1("");
        mDisplay.setText2("");
        return true;
    }

    // LSDisplay

    public void setOnSendCharacterListener(LSDisplay display) {
        mDisplay = display;
    }

}
