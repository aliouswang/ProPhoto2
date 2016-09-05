package com.alious.pro.photo.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.alious.pro.photo.library.R;
import com.alious.pro.photo.library.interfaces.INineGridAdapter;

import java.util.ArrayList;

/**
 * Nine grid view group,
 *
 * Created by aliouswang on 16/9/5.
 */
public class NineGridViewGroup extends ViewGroup{

    public static final int DEFAULT_MAX_SIZE = 9;
    public static final int DEFAULT_COLUMN_SIZE = 3;
    public static final float DEFAULT_RATIO = 1.0f;

    private int mMaxSize;
    private int mColumnSize;
    private int mHorizontalGap;
    private int mVerticalGap;
    private float mRatio;

    private int mCellSize;
    private int mRowCount;
    private int mColumnCount;

    private ArrayList<Point> mPoints;

    private INineGridAdapter mGridAdapter;

    public NineGridViewGroup(Context context) {
        this(context, null);
    }

    public NineGridViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineGridViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.NineGridViewGroup);
        this.mMaxSize = typedArray.getInt(R.styleable.NineGridViewGroup_maxSize,
                DEFAULT_MAX_SIZE);
        this.mColumnSize = typedArray.getInt(R.styleable.NineGridViewGroup_columnSize,
                DEFAULT_COLUMN_SIZE);
        this.mHorizontalGap = typedArray.getDimensionPixelSize(R.styleable.NineGridViewGroup_horizontal_gap,
                getResources().getDimensionPixelSize(R.dimen.default_horizontal_gap));
        this.mVerticalGap = typedArray.getDimensionPixelSize(R.styleable.NineGridViewGroup_vertical_gap,
                getResources().getDimensionPixelSize(R.dimen.default_vertical_gap));
        this.mRatio = typedArray.getFloat(R.styleable.NineGridViewGroup_ratio,
                DEFAULT_RATIO);
        typedArray.recycle();
    }

    public void setGridAdapter(INineGridAdapter gridAdapter) {
        mGridAdapter = gridAdapter;
        calculateCellPoint();
        requestLayout();
    }

    private void calculateCellPoint() {
        int count = getCount();
        if (count > 0) {
            mPoints = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                Point point = new Point();
                point.row = i % mColumnSize;
                point.column = i / mColumnSize;
                mPoints.add(point);
            }
            mColumnCount = mColumnSize;
            mRowCount = (int) Math.ceil(count / mColumnSize);
        }else {
            mPoints = new ArrayList<>();
        }
    }

    private Point getPointByPosition(int pos) {
        return mPoints.get(pos);
    }

    /**
     * If is only one image
     *
     * @return
     */
    public boolean isSingle() {
        if (mGridAdapter != null) {
            return mGridAdapter.getCount() == 1;
        }else return false;
    }

    public int getCount() {
        return mGridAdapter != null ? mGridAdapter.getCount() : 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (mGridAdapter.getCount() <= 0) {
            setMeasuredDimension(sWidth, sHeight);
        }else {
            int totalSpace = sWidth - getPaddingLeft() - getPaddingRight();
            if (isSingle()) {
                mCellSize = totalSpace;
            }else {
                mCellSize = (totalSpace - mHorizontalGap * (mColumnSize - 1)) / mColumnSize;
            }
            float mCellHeight = mCellSize * mRatio;
            float totalHeight = mCellHeight * mRowCount + getPaddingTop() + getPaddingBottom()
                    + (mRowCount - 1) * mVerticalGap;
            setMeasuredDimension(sWidth, (int)totalHeight);
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    private class Point {
        int row;
        int column;
    }
}