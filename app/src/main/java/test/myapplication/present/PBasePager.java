package test.myapplication.present;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import test.myapplication.baseui.BasePagerFragment;
import test.myapplication.model.GankResults;
import test.myapplication.net.Api;

/**
 * Created by wstszx on 2017/7/26.
 */

public class PBasePager extends XPresent<BasePagerFragment> {
	private static final int PAGE_SIZE = 10;

	public void loadData(String type, final int page) {
		Api.getGankService().getGankData(type, PAGE_SIZE, page)
				.compose(XApi.<GankResults>getApiTransformer())
				.compose(XApi.<GankResults>getScheduler())
				.compose(getV().<GankResults>bindToLifecycle())
				.subscribe(new ApiSubscriber<GankResults>() {
					@Override
					protected void onFail(NetError error) {
						getV().showError(error);
					}

					@Override
					public void onNext(GankResults gankResults) {
						getV().showData(page, gankResults);
					}
				});
	}
}
