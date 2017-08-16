package test.myapplication.net;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by wstszx on 2017/7/26.
 */

public class Api {
	// TODO: 2017/8/1 到时候来修改基础api
	public static final String API_BASE_URL = "http://gank.io/api/";

	private static GankService gankService;

	public static GankService getGankService() {
		if (gankService == null) {
			synchronized (Api.class) {
				if (gankService == null) {
					gankService = XApi.getInstance().getRetrofit(API_BASE_URL, true).create(GankService.class);
				}
			}
		}
		return gankService;
	}
}
