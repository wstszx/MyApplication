package test.myapplication.baseui;

import android.os.Bundle;

import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xrecyclerview.XRecyclerView;
import test.myapplication.R;
import test.myapplication.present.PBasePager;

/**
 * Created by wstszx on 2017/8/14.
 */

public class CartoonFragment extends BasePagerFragment{

	private volatile static CartoonFragment instance = null;
	@Override
	public void initData(Bundle savedInstanceState) {

	}

	@Override
	protected String getType() {
		return "all";
	}

	@Override
	protected void setLayoutManager(XRecyclerView recyclerView) {
		recyclerView.verticalLayoutManager(context);
	}

	@Override
	public int getLayoutId() {
		return R.layout.cartoon_fragment;
	}


	@Override
	public SimpleRecAdapter getAdapter() {
		return null;
	}

	public static CartoonFragment newInstance() {
		if (instance == null) {
//			先检查实例是否存在,如果不存在进入下面的同步块
			synchronized (BookFragment.class) {
				if (instance == null) {
//					再次检查实例是否存在，如果不存在才真正的创建实例
					instance = new CartoonFragment();
				}
			}
		}
		return instance;
	}
}
