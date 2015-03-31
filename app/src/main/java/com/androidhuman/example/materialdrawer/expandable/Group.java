package com.androidhuman.example.materialdrawer.expandable;

/**
 * Created by starnamu on 2015-03-30.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 각각의 메뉴의 Child에 보여질 항목을 담을 그릇
 */

public class Group {

    public final List<String> children = new ArrayList<String>();
    public String string;

    public Group(String string) {
        this.string = string;
    }
}