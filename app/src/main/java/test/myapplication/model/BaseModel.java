package test.myapplication.model;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by wstszx on 2017/8/7.
 */

public class BaseModel implements IModel {

	protected boolean error;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error=error;
	}
	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean isAuthError() {
		return false;
	}

	@Override
	public boolean isBizError() {
		return error;
	}

	@Override
	public String getErrorMsg() {
		return null;
	}
}
