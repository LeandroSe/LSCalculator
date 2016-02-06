package br.com.tsujiguchi.lscalculator.view;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by leandrose on 31/12/15.
 */
public class LSSizeFitTextView extends TextView {

    private static final String TAG = "LSSizeFitTextView";

    protected TextPaint mTextPaint;

    protected Paint mTestPaint;
    protected float mMaxSize;
    protected float mMinSize;

    public LSSizeFitTextView(Context context) {
        this(context, null);
    }

    public LSSizeFitTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LSSizeFitTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTextPaint = new TextPaint(TextPaint.LINEAR_TEXT_FLAG);

        mTestPaint = new Paint();
        mTestPaint.set(mTextPaint);

        setSingleLine(true);
    }

    protected float applyDimension(float size) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                size,
                getContext().getResources().getDisplayMetrics()
        );
    }

    /* Re size the font so the specified text fits in the text box
     * assuming the text box is the specified width.
     */
    private void refitText(String text, int textWidth) {
        if (textWidth <= 0)
            return;
        int targetWidth = textWidth - getPaddingLeft() - getPaddingRight();

        mTestPaint.set(mTextPaint);
        // TEST MAX
        mTestPaint.setTextSize(
                applyDimension(mMaxSize)
        );
        if (mTestPaint.measureText(text) <= targetWidth) {
            setTextSize(mMaxSize);
            return;
        }

        // TEST MEIO
        float size = (mMinSize + mMaxSize) / 2;
        mTestPaint.setTextSize(
                applyDimension(size)
        );
        if (mTestPaint.measureText(text) > targetWidth) {
            // MEIO - MIN
            for (; size >= mMinSize; size = size - 0.5f) {
                mTestPaint.setTextSize(
                        applyDimension(size)
                );
                if (mTestPaint.measureText(text) < targetWidth) {
                    break;
                }
            }
        } else {
            // MEIO - MAX
            for (; size <= mMaxSize; size = size + 0.5f) {
                mTestPaint.setTextSize(
                        applyDimension(size)
                );
                if (mTestPaint.measureText(text) < targetWidth) {
                    break;
                }
            }
        }

        // Use lo so that we undershoot rather than overshoot
        setTextSize(size);
        return;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        refitText(this.getText().toString(), parentWidth);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    protected void onTextChanged(final CharSequence text, final int start, final int before, final int after) {
        refitText(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
    }

    public void setSize(float min, float max) {
        mMinSize = min;
        mMaxSize = max;
    }

    public int getTextWidth() {
        return (int) mTestPaint.measureText(getText().toString());
    }

    public int getScrollRange() {
        invalidate();
        requestLayout();
        return computeHorizontalScrollRange();
    }

}
