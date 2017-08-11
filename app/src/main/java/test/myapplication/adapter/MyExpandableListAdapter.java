package test.myapplication.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import test.myapplication.R;

/**
 * Created by wstszx on 2017/7/14.
 */

public class MyExpandableListAdapter implements ExpandableListAdapter {
	private Context context;
	private String[] groupStrings;
	private String[][] childStrings;
	public MyExpandableListAdapter(Context context, String[] groupStrings,String[][] childStrings) {
		this.context=context;
		this.groupStrings=groupStrings;
		this.childStrings=childStrings;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver dataSetObserver) {

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

	}
	//        获取分组的个数
	@Override
	public int getGroupCount() {
		return groupStrings.length;
	}
	//        获取指定分组中的子选项的个数
	@Override
	public int getChildrenCount(int i) {
		return childStrings[i].length;
	}
	//        获取指定的分组数据
	@Override
	public Object getGroup(int i) {
		return groupStrings[i];
	}
	//        获取指定分组中的指定子选项数据
	@Override
	public Object getChild(int i, int i1) {
		return childStrings[i][i1];
	}
	//        获取指定分组的ID, 这个ID必须是唯一的
	@Override
	public long getGroupId(int i) {
		return i;
	}
	//        获取子选项的ID, 这个ID必须是唯一的
	@Override
	public long getChildId(int i, int i1) {
		return i1;
	}
	//        分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。
	@Override
	public boolean hasStableIds() {
		return true;
	}
	//        获取显示指定分组的视图
	@Override
	public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
		GroupViewHolder groupViewHolder;
		if (convertView==null){
			convertView= LayoutInflater.from(context).inflate(R.layout.expandablelistview_group,viewGroup,false);
			groupViewHolder=new GroupViewHolder();
			groupViewHolder.tvTitle= (TextView) convertView.findViewById(R.id.group_title);
			convertView.setTag(groupViewHolder);
		}else {
			groupViewHolder= (GroupViewHolder) convertView.getTag();
		}
		groupViewHolder.tvTitle.setText(groupStrings[i]);
		return convertView;
	}
	//        获取显示指定分组中的指定子选项的视图
	@Override
	public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
		ChildViewHolder childViewHolder;
		if (convertView==null){
			convertView= LayoutInflater.from(context).inflate(R.layout.expandablelistview_item,viewGroup,false);
			childViewHolder=new ChildViewHolder();
			childViewHolder.tvTitle= (TextView) convertView.findViewById(R.id.item_title);
			convertView.setTag(childViewHolder);
		}else {
			childViewHolder= (ChildViewHolder) convertView.getTag();
		}
		childViewHolder.tvTitle.setText(childStrings[i][i1]);
		return convertView;
	}
	//        指定位置上的子元素是否可选中
	@Override
	public boolean isChildSelectable(int i, int i1) {
		return true;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void onGroupExpanded(int i) {

	}

	@Override
	public void onGroupCollapsed(int i) {

	}

	@Override
	public long getCombinedChildId(long l, long l1) {
		return 0;
	}

	@Override
	public long getCombinedGroupId(long l) {
		return 0;
	}

	static class GroupViewHolder {
		TextView tvTitle;
	}
	static class ChildViewHolder {
		TextView tvTitle;
	}
}
