package com.starnamu.expandablelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * Created by starnamu on 2015-03-30.
 */
public class CustomExpandableListView extends ExpandableListView {

    Context mContext;
    SparseArray<Group> groups;


    public CustomExpandableListView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CustomExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void init() {
        groups = new SparseArray<Group>();
        createData();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.expand_main, this, true);

        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(mContext, groups);
        listView.setAdapter(adapter);
    }

    public void createData() {
        for (int j = 0; j < 5; j++) {
            Group group = new Group("Test " + j);
            for (int i = 0; i < 5; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }
    }

}
