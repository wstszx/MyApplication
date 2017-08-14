package test.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.util.DisplayMetrics;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import test.myapplication.baseui.BookFragment;
import test.myapplication.baseui.CartoonFragment;
import test.myapplication.baseui.ListenFragment;
import test.myapplication.baseui.VideoFragment;
import test.myapplication.widget.MyDrawerLayout;

public class MainActivity extends XActivity
		implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
	private BottomNavigationView bottom_navigation_view;
	XFragmentAdapter adapter;

	//	public String[] groupStrings;
//	public String[][] childStrings;
	List<Fragment> fragmentList = new ArrayList<>();
	private ViewPager view_pager;

	/**
	 * 可以使默认的边缘滑动改为全屏滑动
	 *
	 * @param activity
	 * @param drawerLayout
	 * @param displayWidthPercentage
	 */
	private void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
		if (activity == null || drawerLayout == null) return;
		try {
			Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
//		Accessable属性是继承自AccessibleObject 类. 功能是启用或禁用安全检查
			leftDraggerField.setAccessible(true);
			ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
			Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
			edgeSizeField.setAccessible(true);
			int edgeSize = edgeSizeField.getInt(leftDragger);
			DisplayMetrics dm = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
			edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//
//		//noinspection SimplifiableIfStatement
//		if (id == R.id.action_settings) {
//			return true;
//		}
//
//		return super.onOptionsItemSelected(item);
//	}
//@SuppressWarnings是抑制的意思，取消指定的编译器警告，这里是抑制空方法体的错误
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();
		switch (id) {
			case R.id.bottom_one:
				clickTabOne();
				return true;
			case R.id.bottom_two:
				clickTabTwo();
				return true;
			case R.id.bottom_three:
				clickTabThree();
				return true;
			case R.id.bottom_four:
				clickTabFour();
				return true;
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private void clickTabFour() {
		view_pager.setCurrentItem(3,false);
	}

	private void clickTabThree() {
		view_pager.setCurrentItem(2,false);
	}

	private void clickTabTwo() {
		view_pager.setCurrentItem(1,false);
	}

	private void clickTabOne() {
		//为防止隔页切换时,滑过中间页面的问题,去除页面切换缓慢滑动的动画效果
		view_pager.setCurrentItem(0,false);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		bottom_navigation_view = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
		//修改底部导航栏位移效果
		setBottomNavigationView();
		view_pager = (ViewPager) findViewById(R.id.view_pager);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//		setDrawerLeftEdgeSize(this, drawer, 0.5f);
//		ActionBarDrawerToggle有个不带toolbar的构造器，可以隐藏旋转开关按钮，可以监听drawer的显示与隐藏。syncState会和toolbar关联，将图标放入到toolbar上
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		setupViewPager();
	}

	private void setupViewPager() {
		bottom_navigation_view.setOnNavigationItemSelectedListener(this);
		bottom_navigation_view.setSelectedItemId(R.id.bottom_one);
		view_pager.addOnPageChangeListener(this);
		fragmentList.clear();
		fragmentList.add(BookFragment.newInstance());
		fragmentList.add(VideoFragment.newInstance());
		fragmentList.add(ListenFragment.newInstance());
		fragmentList.add(CartoonFragment.newInstance());
		if (adapter == null) {
			adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList);
		}
		view_pager.setAdapter(adapter);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public Object newP() {
		return null;
	}

	protected void setBottomNavigationView() {
		BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottom_navigation_view.getChildAt(0);
		try {
			Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
			shiftingMode.setAccessible(true);
			shiftingMode.setBoolean(menuView, false);
			shiftingMode.setAccessible(false);
			for (int i = 0; i < menuView.getChildCount(); i++) {
				BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
				itemView.setShiftingMode(false);
				itemView.setChecked(itemView.getItemData().isChecked());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		switch (position) {
			case 0:
				bottom_navigation_view.setSelectedItemId(R.id.bottom_one);
				break;
			case 1:
				bottom_navigation_view.setSelectedItemId(R.id.bottom_two);
				break;
			case 2:
				bottom_navigation_view.setSelectedItemId(R.id.bottom_three);
				break;
			case 3:
				bottom_navigation_view.setSelectedItemId(R.id.bottom_four);
				break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}
}
