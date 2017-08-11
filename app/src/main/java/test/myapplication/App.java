package test.myapplication;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by wstszx on 2017/7/28.
 */

public class App extends Application {
	public final static float DESIGN_WIDTH = 750; //绘制页面时参照的设计图宽度
	@Override
	public void onCreate() {
		super.onCreate();
		resetDensity();
	}
	//横竖屏的切换等Configuration变化会导致更新Resources，需要重新处理一下
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		resetDensity();
	}
	//更改DisplayMetrics为我们想要的与屏幕宽度相关的比例
	private void resetDensity() {
		Point size = new Point();
		((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
		getResources().getDisplayMetrics().xdpi = size.x/DESIGN_WIDTH*72f;
	}

	//将pt转换为px值
	public float pt2px(int value){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value, getResources().getDisplayMetrics());
	}
}
