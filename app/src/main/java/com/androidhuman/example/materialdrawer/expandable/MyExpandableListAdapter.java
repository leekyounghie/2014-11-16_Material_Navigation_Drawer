package com.androidhuman.example.materialdrawer.expandable;

/**
 * Created by starnamu on 2015-03-30.
 */

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidhuman.example.materialdrawer.R;

/**
 * 확장된 ListView의 Adapter이다. 주메뉴있고 Child 메뉴가 있는 View를 만들때 사용된다.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private final SparseArray<Group> groups;
    public LayoutInflater inflater;
    //    public Activity activity;
    public Context mContext;
    int lastExpandedGroupPosition = -1;
    boolean[] mGroupClickState;
    ExpandableListView mList;

    public MyExpandableListAdapter(Context context, ExpandableListView list, SparseArray<Group> groups) {
        mContext = context;
        this.groups = groups;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mGroupClickState = new boolean[groups.size()];
        mList = list;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_details, null);
        }
        text = (TextView) convertView.findViewById(R.id.textView1);
        text.setText(children);
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, children, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    /**
     * 그룹에 관련된 일을 하는 Method
     * 여기서는 다른 선택된 항목 목록에서 다른 목록을 선택시 기존 목록은 자동으로 닫힌다.
     */
    @Override
    public void onGroupExpanded(int groupPosition) {

        Log.e("", "LAST" + lastExpandedGroupPosition + ", CUR : " + groupPosition);
        // TODO Auto-generated method stub
        //그룹을 클릭했을때 열려져 있는 지 표시하는 화살표 아이콘 위/아래

        mGroupClickState[groupPosition] = !mGroupClickState[groupPosition];
        //그룹을 클릭했을때 이전그룹이 열려 있으면 닫음
        if (lastExpandedGroupPosition != -1 && lastExpandedGroupPosition != groupPosition) {
            mList.collapseGroup(lastExpandedGroupPosition);
            mGroupClickState[lastExpandedGroupPosition] = false;
        }

        lastExpandedGroupPosition = groupPosition;
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group, null);
        }
        Group group = (Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}