package test.myapplication.widget;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * Created by wstszx on 2017/7/13.
 */

public class MyDrawerLayout extends DrawerLayout {


	private GestureDetector gestureDetector;

	public MyDrawerLayout(Context context) {
		super(context);
		init();
	}

	public MyDrawerLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
//		初始化手势
		gestureDetector = new GestureDetector(this.getContext(), onGestureListener);
	}

	private android.view.GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
		@Override
		public boolean onDown(MotionEvent motionEvent) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent motionEvent) {

		}

		@Override
		public boolean onSingleTapUp(MotionEvent motionEvent) {
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
			return false;
		}

		@Override
		public void onLongPress(MotionEvent motionEvent) {

		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
			float startX = e1.getX();
			float startY = e1.getY();
			float endX = e2.getX();
			float endY = e2.getY();
			if (endX > startX && (endX - startX) > Math.abs(endY - startY)) {
				Log.d("MyDrawerLayout", "startX:" + startX);
				Log.d("MyDrawerLayout", "endX:" + endX);
				Log.d("MyDrawerLayout", "startY:" + startY);
				Log.d("MyDrawerLayout", "endY:" + endY);
				return true;
			}
			return false;
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (gestureDetector.onTouchEvent(ev)) {
//			若向左滑动
			if (!isDrawerOpen(Gravity.LEFT)) {
				openDrawer(Gravity.LEFT);
			}
		}
		return super.onTouchEvent(ev);
	}
}
