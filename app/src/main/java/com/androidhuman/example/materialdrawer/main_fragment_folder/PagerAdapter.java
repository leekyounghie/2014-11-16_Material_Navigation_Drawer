package com.androidhuman.example.materialdrawer.main_fragment_folder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by youmyeongsic on 15. 4. 5..
 */
public class PagerAdapter extends FragmentStatePagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       PageFragment pageFragment = new PageFragment();
       pageFragment.setCurrent_Text(Vocabulary.Word[position]);

        return pageFragment;
    }

    @Override
    public int getCount() {
        return Vocabulary.Word.length;
    }
}
