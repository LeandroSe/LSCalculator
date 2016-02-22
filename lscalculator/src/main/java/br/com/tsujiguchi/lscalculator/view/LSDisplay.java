package br.com.tsujiguchi.lscalculator.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import br.com.tsujiguchi.lscalculator.math.MathString;
import br.com.tsujiguchi.lscalculator.math.exception.NullResultException;

/**
 * Created by leandrose on 31/12/15.
 */
public class LSDisplay extends LinearLayout {

    private static final String TAG = "LSDisplay";

    public static final int STATE_CALCULING = 1;
    public static final int STATE_RESULT = 2;

    protected int mPaddingRight = 0;
    protected TextView mTextView1;
    protected TextView mTextView2;

    protected int mState = STATE_CALCULING;

    public LSDisplay(Context context) {
        super(context);

        init();
    }

    public LSDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaddingRight = attrs.getAttributeIntValue(android.R.attr.paddingRight, 30);

        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public LSDisplay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaddingRight = attrs.getAttributeIntValue(android.R.attr.paddingRight, 30);

        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LSDisplay(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    protected void init() {
        // LAYOUT
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);

        switch (child.getId()) {
            case android.R.id.text1:
                mTextView1 = (TextView) findViewById(android.R.id.text1);
                mTextView1.setMovementMethod(new ScrollingMovementMethod());
                mTextView1.setHorizontalFadingEdgeEnabled(true);
                mTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextView1.getTextSize() * 1.5f);
                break;
            case android.R.id.text2:
                mTextView2 = (TextView) findViewById(android.R.id.text2);
                break;
        }
    }

    public void setText1(String text1) {
        mTextView1.setText(text1);
    }

    public void setText1(double text1) {
        setText1(convertNumber(text1));
    }

    public String getText1() {
        return mTextView1.getText().toString();
    }

    public void setText2(String text2) {
        mTextView2.setText(text2);
    }

    public void setText2(double text2) {
        setText2(convertNumber((text2)));
    }

    public String getText2() {
        return mTextView2.getText().toString();
    }

    public double result() {
        try {
            if (mTextView1.getText().length() > 0) {
                double result = MathString.calc(mTextView1.getText().toString());

                setText1(result);
                setText2("");

                mState = STATE_RESULT;

                return result;
            } else {
                setText2("");

                throw new NullResultException();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void previewResult() {
        try {
            double result = MathString.calc(mTextView1.getText().toString());
            setText2(result);
        } catch (RuntimeException re) {
        }
    }

    private final String convertNumber(double number) {
        double numberInt = Math.floor(number);
        double numFraction = number - numberInt;

        if (numFraction == 0) {
            DecimalFormat formatter = new DecimalFormat("0");
            return formatter.format(number);
        } else {
            return String.valueOf(number);
        }
    }

    // OnSendCharacterListener

    public void onSendCharacter(String character) {
        if (STATE_RESULT == mState) {
            mState = STATE_CALCULING;
            setText1(character);
        } else {
            setText1(mTextView1.getText() + character);
        }

        previewResult();
    }

}
