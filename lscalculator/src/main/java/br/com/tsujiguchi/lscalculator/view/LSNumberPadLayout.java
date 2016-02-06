package br.com.tsujiguchi.lscalculator.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import br.com.tsujiguchi.lscalculator.R;

/**
 * Created by leandrose on 27/12/15.
 */
public class LSNumberPadLayout extends ViewGroup implements View.OnClickListener {

    private static final String TAG = "LSCalculator";

    protected LSDisplay mDisplay = null;

    protected int mRowCount;
    protected int mColumnCount;

    public LSNumberPadLayout(Context context) {
        this(context, null);
    }

    public LSNumberPadLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LSNumberPadLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(
                attrs,
                new int[]{
                        R.attr.rowCount,
                        R.attr.columnCount
                },
                defStyleAttr,
                0);
        mRowCount = a.getInt(0, 1);
        mColumnCount = a.getInt(1, 1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();

        int col = 0;
        int row = -1;

        int itemWidth = getMeasuredWidth() / mColumnCount - paddingLeft - paddingRight;
        int itemHeight = getMeasuredHeight() / mRowCount - paddingTop - paddingBottom;

        if (mColumnCount * mRowCount < getChildCount()) {
            throw new RuntimeException(
                    String.format("Deve-se possuir no máximo %d views.", mColumnCount * mRowCount)
            );
        }

        for (int i = 0; i < getChildCount(); i++) {
            LSButton v = (LSButton) getChildAt(i);
            int columnSize = v.getColumnSize();

            if (i % mColumnCount == 0) {
                ++row;
                col = 0;
                t = paddingTop + (row * itemHeight);
                b = t + itemHeight;
                l = paddingLeft;
            }
            if (row == mRowCount) {
                b = getMeasuredHeight();
            }
            if (col % mColumnCount == mColumnCount - 1) {
                r = getMeasuredWidth() - paddingRight;
            } else {
                r = l + (columnSize * itemWidth);
            }

            v.measure(
                    MeasureSpec.makeMeasureSpec(itemWidth * columnSize, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(itemHeight, MeasureSpec.EXACTLY)
            );
            v.layout(l, t, r, b);

            l += columnSize * itemWidth;

            col += columnSize;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    // View.OnClickListener

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lsCalculatorDigitResult) {
            mDisplay.setText1(mDisplay.getText2());
            mDisplay.setText2("");
        } else {
            mDisplay.onSendCharacter(((LSButton) v).getText().toString());
        }
    }

    // LSDisplay

    public void setOnSendCharacterListener(LSDisplay display) {
        mDisplay = display;
    }

}
