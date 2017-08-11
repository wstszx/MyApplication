package test.myapplication.baseui;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import test.myapplication.R;
import test.myapplication.model.GankResults;
import test.myapplication.present.PBasePager;
import test.myapplication.widget.StateView;

/**
 * Created by wstszx on 2017/7/26.
 */

public abstract class BasePagerFragment extends XLazyFragment<PBasePager> {

	@BindView(R.id.contentLayout)
	XRecyclerContentLayout contentLayout;

	StateView errorView;
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

	public abstract SimpleRecAdapter getAdapter();

	protected static final int MAX_PAGE = 10;

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
}
