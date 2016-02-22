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

import br.com.tsujiguchi.lscalculator.math.MathString;

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

        try {
            if (text1.length() > 0) {
                setText2(String.valueOf(MathString.calc(text1)));
            } else {
                setText2("");
            }
        } catch (Exception e) {
        }

        invalidate();
        requestLayout();
    }

    public String getText1() {
        return mTextView1.getText().toString();
    }

    public void setText2(String text2) {
        mTextView2.setText(text2);
    }

    public String getText2() {
        return mTextView2.getText().toString();
    }

//    public double getResult(){
//
//    }

    // OnSendCharacterListener

    public void onSendCharacter(String character) {
        setText1(mTextView1.getText() + character);
    }

}
