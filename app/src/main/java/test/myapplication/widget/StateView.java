package test.myapplication.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import test.myapplication.R;

/**
 * Created by wstszx on 2017/8/7.
 */

public class StateView extends LinearLayout {
	@BindView(R.id.tv_msg)
	TextView tvMsg;

	public StateView(Context context) {
		super(context);
		setupView(context);
	}

	public StateView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		setupView(context);
	}

	public StateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setupView(context);
	}

	private void setupView(Context context) {
		inflate(context,R.layout.view_state,this);
		KnifeKit.bind(this);
	}

	public void setMsg(String msg) {
		if (!TextUtils.isEmpty(msg)) {
			tvMsg.setText(msg);
		}
	}
}
