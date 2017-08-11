package test.myapplication;

import android.app.Activity;
import android.os.Bundle;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import test.myapplication.widget.MyDrawerLayout;

public class MainActivity extends XActivity
		implements NavigationView.OnNavigationItemSelectedListener {

//	@BindView(R.id.view_pager)
//	ViewPager viewPager;
//	public String[] groupStrings;
//	public String[][] childStrings;
	List<Fragment> fragmentList = new ArrayList<>();

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//
//	}
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
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
//@SuppressWarnings是抑制的意思，取消指定的编译器警告，这里是抑制空方法体的错误
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera) {
			// Handle the camera action
		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//		ExpandableListView expandable_list_view = (ExpandableListView) findViewById(R.id.expandable_list_view);
		setSupportActionBar(toolbar);
//		groupStrings = new String[]{"西游记", "水浒传", "三国演义", "红楼梦"};
//		childStrings = new String[][]{
//				{"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
//				{"宋江", "林冲", "李逵", "鲁智深"},
//				{"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
//				{"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}
//		};
//		expandable_list_view.setAdapter(new MyExpandableListAdapter(this,groupStrings,childStrings));
		MyDrawerLayout drawer = (MyDrawerLayout) findViewById(R.id.drawer_layout);
//		setDrawerLeftEdgeSize(this, drawer, 0.5f);
//		ActionBarDrawerToggle有个不带toolbar的构造器，可以隐藏旋转开关按钮，可以监听drawer的显示与隐藏。syncState会和toolbar关联，将图标放入到toolbar上
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		fragmentList.clear();
//		fragmentList.add(OneFragment);
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public Object newP() {
		return null;
	}

}
