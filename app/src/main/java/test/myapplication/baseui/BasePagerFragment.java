package test.myapplication.baseui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import test.myapplication.R;
import test.myapplication.model.GankResults;
import test.myapplication.present.PBasePager;
import test.myapplication.widget.StateView;

/**
 * Created by wanglei on 2016/12/31.
 */

public abstract class BasePagerFragment extends XLazyFragment<PBasePager> {

	@BindView(R.id.xrecycler_contentLayout)
	XRecyclerContentLayout contentLayout;

	StateView errorView;

	protected static final int MAX_PAGE = 10;
	Unbinder unbinder;


	@Override
	public void initData(Bundle savedInstanceState) {
		initAdapter();
		getP().loadData(getType(), 1);
	}

	private void initAdapter() {
		XLog.d("", "initAdapter");
		setLayoutManager(contentLayout.getRecyclerView());
		contentLayout.getRecyclerView()
				.setAdapter(getAdapter());
		contentLayout.getRecyclerView()
				.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
					@Override
					public void onRefresh() {
						getP().loadData(getType(), 1);
					}

					@Override
					public void onLoadMore(int page) {
						getP().loadData(getType(), page);
					}
				});


		if (errorView == null) {
			errorView = new StateView(context);
		}
		contentLayout.errorView(errorView);
//		// TODO: 2017/8/15 修改加载样式，到时修改第二个参数
		contentLayout.loadingView(View.inflate(getContext(), 0, null));

		contentLayout.getRecyclerView().useDefLoadMoreView();
	}

	public abstract SimpleRecAdapter getAdapter();

	public abstract void setLayoutManager(XRecyclerView recyclerView);

	public abstract String getType();


	public void showError(NetError error) {
		if (error != null) {
			switch (error.getType()) {
				case NetError.ParseError:
					errorView.setMsg("数据解析异常");
					break;

				case NetError.AuthError:
					errorView.setMsg("身份验证异常");
					break;

				case NetError.BusinessError:
					errorView.setMsg("业务异常");
					break;

				case NetError.NoConnectError:
					errorView.setMsg("网络无连接");
					break;

				case NetError.NoDataError:
					errorView.setMsg("数据为空");
					break;

				case NetError.OtherError:
					errorView.setMsg("其他异常");
					break;
			}
			contentLayout.showError();
		}
	}

	public void showData(int page, GankResults model) {
		if (page > 1) {
			getAdapter().addData(model.getResults());
		} else {
			getAdapter().setData(model.getResults());
		}

		contentLayout.getRecyclerView().setPage(page, MAX_PAGE);

		if (getAdapter().getItemCount() < 1) {
			contentLayout.showEmpty();
			return;
		}
	}


	@Override
	public int getLayoutId() {
		return R.layout.fragment_base_pager;
	}

	@Override
	public PBasePager newP() {
		return new PBasePager();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder = ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}
}
