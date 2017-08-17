package test.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.WindowManager;

import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by wstszx on 2017/7/28.
 */

public class App extends Application {
	private static Context context;
	public final static float DESIGN_WIDTH = 750; //绘制页面时参照的设计图宽度
	@Override
	public void onCreate() {
		super.onCreate();
		XApi.registerProvider(new NetProvider() {
			@Override
			public Interceptor[] configInterceptors() {
				return new Interceptor[0];
			}

			@Override
			public void configHttps(OkHttpClient.Builder builder) {

			}

			@Override
			public CookieJar configCookie() {
				return null;
			}

			@Override
			public RequestHandler configHandler() {
				return null;
			}

			@Override
			public long configConnectTimeoutMills() {
				return 0;
			}

			@Override
			public long configReadTimeoutMills() {
				return 0;
			}

			@Override
			public boolean configLogEnable() {
				return true;
			}

			@Override
			public boolean handleError(NetError error) {
				return false;
			}
		});
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

	public static Context getContext() {
		return context;
	}

}
