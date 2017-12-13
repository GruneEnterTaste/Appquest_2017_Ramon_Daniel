package ch.derbeton.metalldetectorapp;

/**
 * Created by DerBeton on 13.12.2017.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

// http://stackoverflow.com/questions/2948212/android-layout-with-sqare-buttons
public class SquareLayout extends LinearLayout {
    private int mScale = 1;

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (width > (int)(mScale  * height + 0.5)) {
            width = (int)(mScale * height + 0.5);
        } else {
            height = (int)(width / mScale + 0.5);
        }

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        );
    }
}


