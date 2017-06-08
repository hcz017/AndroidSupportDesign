package com.hcz017.androidsupportdesign;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class TabBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "CustomBehavior";
    int offsetTotal = 0;
    boolean scrolling = false;

    public TabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "CustomBehavior: ");
    }

/*    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, "layoutDependsOn: " + dependency.getWidth());
        return dependency instanceof TabLayout;
//        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, "onDependentViewChanged: ");
        if (dependency instanceof TabLayout) {
            int height = (int) dependency.getY();
            child.setTranslationY(-height);
            dependency.setBackgroundColor(Color.YELLOW);
        }
        return true;
    }*/

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        /**
         * 这里要返回true，才会接收到后续的滑动事件
         * （理解：也就是说我们在这里可以做各种判断来确定，要操作的view是否要对下一步滑动作出反应）
         */
        Log.d(TAG, "onStartNestedScroll: ");
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d(TAG, "onNestedScroll: ");
        Toolbar toolbar = (Toolbar) coordinatorLayout.findViewById(R.id.toolbar);
//        toolbar.setBackgroundColor(Color.YELLOW);
        TabLayout tabLayout = (TabLayout) coordinatorLayout.findViewById(R.id.tabs);
        tabLayout.getChildAt(tabLayout.getSelectedTabPosition()).setMinimumWidth(100);



//        offset(child, dyConsumed, toolbar);

    }

    public void offset(View child, int dy, Toolbar toolbar) {
        int old = offsetTotal;
        int top = offsetTotal - dy;
        top = Math.max(top, -child.getHeight());
        top = Math.min(top, 0);
        offsetTotal = top;
        if (old == offsetTotal) {
            scrolling = false;
            return;
        }
        int delta = offsetTotal - old;
//        child.offsetTopAndBottom(delta);
        toolbar.offsetLeftAndRight(delta * 4);
//        child.offsetLeftAndRight(-delta);
        scrolling = true;
    }
}