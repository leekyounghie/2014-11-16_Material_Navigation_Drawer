package com.androidhuman.example.materialdrawer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.androidhuman.example.materialdrawer.expandable.Group;
import com.androidhuman.example.materialdrawer.expandable.MyExpandableListAdapter;
import com.androidhuman.example.materialdrawer.main_fragment_folder.ForuDirection_Fragment;


public class MainActivity extends ActionBarActivity {
/*
* 명식 Version001
*
* */
    Toolbar toolbar;
    /**
     * 좌측 숨겨진 메뉴와 메인화면을 담는 Layoiut
     * toolbar instanc에 toolbar의 속성을 정의하면 된다.
     * setTitle, setVisibility()등의 속성을 정의할수 있다.
     */
    DrawerLayout dlDrawer;
    /**
     * dtToggle은 상단 좌측의 그래픽 Animation
     */
    ActionBarDrawerToggle dtToggle;
    /**
     * 메인화면(바탕화면)
     */
    FrameLayout container;
    /**
     * 좌측 사이드에 숨겨진 메뉴
     */
    LinearLayout drawer;
    ExpandableListView listView;

    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        dlDrawer.setDrawerListener(dtToggle);
        coustomFragmentManager();
        showCustomTitleAndSubtitle();

    }

    private void showCustomTitleAndSubtitle() {
        getSupportActionBar().setCustomView(R.layout.toolbarstat);
        getSupportActionBar().setTitle("Custom Title");
        getSupportActionBar().setSubtitle("subtitle");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }


    public void coustomFragmentManager() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        ForuDirection_Fragment fFragment = new ForuDirection_Fragment();
        tr.add(R.id.container, fFragment);
        tr.commit();
    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        drawer = (LinearLayout) findViewById(R.id.drawer);

        createData();

        listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this, listView, groups);
        listView.setAdapter(adapter);
        removeStatusBar(true);
    }

    public void removeStatusBar(boolean remove) {
        if (remove) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 각항목에 값을 넣기위한 방법으로 지우지말고 시용
     */
    public void createData() {
        Group group = new Group("설정" + 1);
        for (int i = 1; i < 30; i++) {
            group.children.add(i + " 장 선택");
        }
        groups.append(0, group);

        Group group1 = new Group("통계" + 2);
        group1.children.add("카드별");
        group1.children.add("일별");
        group1.children.add("주별");
        group1.children.add("월별");
        groups.append(1, group1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
