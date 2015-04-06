package com.androidhuman.example.materialdrawer.main_fragment_folder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by youmyeongsic on 15. 4. 5..
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<WordCard> wordCardArrayList = new ArrayList<WordCard>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);



        for (int i = 0; i < Vocabulary.Word.length; i++) {
            WordCard wordCard = new WordCard();
            wordCard.setWord(Vocabulary.Word[i]);
            wordCard.setLevel(1);
            wordCardArrayList.add(wordCard);
        }


    }


    @Override
    public Fragment getItem(int position) {
        PageFragment pageFragment = new PageFragment(wordCardArrayList.get(position));


        return pageFragment;
    }

    @Override
    public int getCount() {
        return wordCardArrayList.size();
    }
}
