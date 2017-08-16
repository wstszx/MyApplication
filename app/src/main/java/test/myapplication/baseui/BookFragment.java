package test.myapplication.baseui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xrecyclerview.XRecyclerView;
import test.myapplication.R;

/**
 * Created by wstszx on 2017/8/14.
 */

public class BookFragment extends BasePagerFragment {
	private volatile static BookFragment instance = null;
	Unbinder unbinder;


	@Override
	public String getType() {
		return "all";
	}

	@Override
	public void setLayoutManager(XRecyclerView recyclerView) {
		recyclerView.verticalLayoutManager(context);
	}


	@Override
	public SimpleRecAdapter getAdapter() {
		return null;
	}

	public static BookFragment newInstance() {
		if (instance == null) {
//			先检查实例是否存在,如果不存在进入下面的同步块
			synchronized (BookFragment.class) {
				if (instance == null) {
//					再次检查实例是否存在，如果不存在才真正的创建实例
					instance = new BookFragment();
				}
			}
		}
		return instance;
	}



}
