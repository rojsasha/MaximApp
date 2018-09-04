package alex.example.com.maximapp.ui.divisions;

import android.content.Context;
import android.widget.ExpandableListView;

public class SecondLevelExpadedListView extends ExpandableListView {
    public SecondLevelExpadedListView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
}
