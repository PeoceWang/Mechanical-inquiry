package com.example.hjlj2_8;

import java.util.ArrayList;
import java.util.List;

import com.example.expandable.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class huajianlianjie extends Activity implements
		ExpandableListView.OnChildClickListener,
		ExpandableListView.OnGroupClickListener {
	private ExpandableListView expandableListView;
	private ArrayList<Group> groupList;
	private ArrayList<List<People>> childList;

	private MyexpandableListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.huajianlianjie);
		expandableListView = (ExpandableListView) findViewById(R.id.expandablelist);

		initData();

		adapter = new MyexpandableListAdapter(this);
		expandableListView.setAdapter(adapter);

		// 展开所有group
		// for(int i=0,count=expandableListView.getCount();i<count;i++){
		// expandableListView.expandGroup(i);
		// }

		expandableListView.setOnChildClickListener(this);
		expandableListView.setOnGroupClickListener(this);

	}

	/***
	 * InitData
	 */
	void initData() {
		groupList = new ArrayList<Group>();
		Group group, group1, group2;

		group = new Group();
		group.setTitle("矩形花键");
		groupList.add(group);

		group1 = new Group();
		group1.setTitle("圆柱直齿渐开线花键");
		groupList.add(group1);

		group2 = new Group();
		group2.setTitle("圆锥直齿渐开线花键");
		groupList.add(group2);
		

		childList = new ArrayList<List<People>>();
		for (int i = 0; i < 3; i++) {
			ArrayList<People> childTemp = null;
			if (i == 0) {
				childTemp = new ArrayList<People>();

				People people = new People();
				people.setName("矩形花键基本尺寸系列");
				childTemp.add(people);

				People people1 = new People();

				people1.setName("矩形内花键形式及长度系列");
				childTemp.add(people1);

				People people2 = new People();

				people2.setName("矩形花键的尺寸公差和表面粗糙度");
				childTemp.add(people2);
				People people3 = new People();
				
				people3.setName("矩形花键的位置度、对称度公差");
				childTemp.add(people3);



			} else if (i == 1) {
				childTemp = new ArrayList<People>();
				People people = new People();
				people.setName("渐开线花键模数");
				childTemp.add(people);
				People people1 = new People();
				people1.setName("30°渐开线外花键大径尺寸系列");
				childTemp.add(people1);
				People people2 = new People();
				people2.setName("45°渐开线外花键大径尺寸系列");
				childTemp.add(people2);

			} else  {
				childTemp = new ArrayList<People>();
				People people = new People();
				people.setName("圆锥直齿渐开线花键术语");
				childTemp.add(people);
				People people1 = new People();
				people1.setName("外花径基本尺寸");
				childTemp.add(people1);
				People people2 = new People();
				people2.setName("圆锥渐开线花键尺寸");
				childTemp.add(people2);
				People people3 = new People();
				people3.setName("齿槽宽和尺厚的公差（6级用）");
				childTemp.add(people3);
				People people4 = new People();
				people4.setName("齿槽宽和尺厚的公差（7级用）");
				childTemp.add(people4);
				People people5 = new People();
				people5.setName("花键的齿向公差");
				childTemp.add(people5);
			}
			
			childList.add(childTemp);
		}

	}

	/***
	 * 数据源
	 * 
	 * @author Administrator
	 * 
	 */
	class MyexpandableListAdapter extends BaseExpandableListAdapter {
		private LayoutInflater inflater;

		public MyexpandableListAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		// 返回父列表个数
		@Override
		public int getGroupCount() {
			return groupList.size();
		}

		// 返回子列表个数
		@Override
		public int getChildrenCount(int groupPosition) {
			return childList.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {

			return groupList.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return childList.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {

			return true;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			GroupHolder groupHolder = null;
			if (convertView == null) {
				groupHolder = new GroupHolder();
				convertView = inflater.inflate(R.layout.group, null);
				groupHolder.textView = (TextView) convertView
						.findViewById(R.id.group);
				groupHolder.imageView = (ImageView) convertView
						.findViewById(R.id.image);
				groupHolder.textView.setTextSize(20);
				convertView.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) convertView.getTag();
			}

			groupHolder.textView.setText(((Group) getGroup(groupPosition))
					.getTitle());
			if (isExpanded)// ture is Expanded or false is not isExpanded
				groupHolder.imageView.setImageResource(R.drawable.expanded);
			else
				groupHolder.imageView.setImageResource(R.drawable.collapse);
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ChildHolder childHolder = null;
			if (convertView == null) {
				childHolder = new ChildHolder();
				convertView = inflater.inflate(R.layout.child, null);

				childHolder.textName = (TextView) convertView
						.findViewById(R.id.name);

				convertView.setTag(childHolder);
			} else {
				childHolder = (ChildHolder) convertView.getTag();
			}

			childHolder.textName.setText(((People) getChild(groupPosition,
					childPosition)).getName());

			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}

	@Override
	public boolean onGroupClick(final ExpandableListView parent, final View v,
			int groupPosition, final long id) {

		return false;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {

		// childList.get(groupPosition).get(childPosition).getName(), 1)
		// Toast.makeText(MainActivity.this,
		// String.valueOf((groupPosition+1)*10+childPosition+1), 0)
		// .show();

		Intent intent = new Intent(huajianlianjie.this, showhtml.class);
		Bundle bun=new Bundle();
		bun.putString("URL", "file:///android_asset/hjlj/"
				+ String.valueOf((groupPosition + 1) + "_"
						+ String.valueOf(childPosition + 1)+".html"));
         intent.putExtras(bun);

		startActivityForResult(intent,1);

		return false;
	}

	class GroupHolder {
		TextView textView;
		ImageView imageView;
	}

	class ChildHolder {
		TextView textName;
		ImageView imageView;
	}

}
