package com.androidhuman.example.materialdrawer.main_fragment_folder;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.androidhuman.example.materialdrawer.R;

/**
 * Created by starnamu on 2015-03-31.
 */
public class ForuDirection_Fragment extends Fragment {

    public ForuDirection_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.counterfragment, container, false);

        FourDirectionLayout FDL = new FourDirectionLayout(getActivity());
        FrameLayout foruDirectrionFrame = (FrameLayout) root.findViewById(R.id.foruDirectrionFrame);
        foruDirectrionFrame.addView(FDL);
        return root;
    }


    public class FourDirectionLayout extends ViewGroup {
        private final String[] TEXTS = {
                "left view, left swipe only TAG 0",
                "right view, right swipe only TAG 1",
                "top view, top swipe only TAG 2",
                "bottom view, bottom swipe only TAG 3",
                "central view, swipe to the left, right, top or bottom TAG 4",

        };
        private final int[] COLORS = {
                0xaa0000ff, 0xaa0000ff, 0xaaff0000, 0xaaff0000, 0xaa00ff00
        };
        private final int[] PACKED_OFFSETS = { //페이지 이동 계산 수 2개씩 짤라서 (x,y)좌표값
                -1, 0, 1, 0, 0, -1, 0, 1, 0, 0
        };
        String TAG = "FourDirectionLayout";
        private GestureDetector.OnGestureListener mListener = new GestureDetector.SimpleOnGestureListener() {
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d(TAG, "onFling");
                if (!mScroller.isFinished()) {
                    return false;
                }
                int sx = getScrollX();
                int sy = getScrollY();
                int w = getWidth();
                int h = getHeight();
                int DURATION = 500;//페이지 전환 시간
                // check if horizontal/vertical fling
                if (Math.abs(velocityX) > Math.abs(velocityY)) {


                    if (sx == 0 && velocityX > 0 || sy != 0 && (Math.abs(velocityX) > Math.abs(velocityY))) {
                        Log.d(TAG, "sy :" + sy + "velocityX * sx : " + velocityX);
                        return false;
                    }
//                DURATION = (int) (1000 * w / Math.abs(velocityX));
                    int distance = velocityX < 0 ? w : -w;
                    Log.d(TAG, "velocityX : " + velocityX);
                    Log.d(TAG, "sx : " + sx);
                    Log.d(TAG, "sy : " + sy);
                    Log.d(TAG, "distance : " + distance);

                    mScroller.startScroll(sx, sy, distance, 0, DURATION); //값이라 가로
                } else {
                    if (sy == 0 && velocityY > 0 || sy != 0 && velocityY < 0) {

                        //Log.d(TAG, "sx :" + sx + "velocityY * sy : " + sy);

                        return false;
                    }
//                DURATION = (int) (1000 * h / Math.abs(velocityY));
                    Log.d(TAG, "X : " + Math.abs(velocityX) + "Y : " + Math.abs(velocityY));
                    int distance = velocityY < 0 ? h : -h;
                    mScroller.startScroll(sx, sy, 0, distance, DURATION); //y값이라 세로
                }
                invalidate();
                return true;
            }
        };
        private GestureDetector mDetector;
        private Scroller mScroller;

        public FourDirectionLayout(Context context) {
            super(context);
            for (int i = 0; i < TEXTS.length; i++) {
                TextView tv = new TextView(context);
                tv.setTag(i);
                tv.setTextSize(32);
                tv.setTypeface(Typeface.DEFAULT_BOLD);
                tv.setTextColor(0xffeeeeee);
                tv.setText(TEXTS[i]);
                tv.setBackgroundColor(COLORS[i]);
                addView(tv);
            }
            mDetector = new GestureDetector(context, mListener);
            mScroller = new Scroller(context);
        } // 각 페이지 지정

        @Override
        public void computeScroll() {
            if (mScroller.computeScrollOffset()) {
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
                invalidate();
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //  Log.d(TAG, "onTouchEvent");
            mDetector.onTouchEvent(event);
            return true;
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int cnt = getChildCount();
            Log.d(TAG, "onLayout");
            Log.d(TAG, "changed" + changed);
            Log.d(TAG, "l :" + l);
            Log.d(TAG, "t :" + t);
            Log.d(TAG, "r :" + r); // 화면 폭
            Log.d(TAG, "b :" + b); // 화면 높이
            getChildAt(0).layout(0, 0, r, b);
            getChildAt(1).layout(0, b, r, 2 * b);
            getChildAt(2).layout(r, 0, 2 * r, b);
            getChildAt(3).layout(r, b, 2 * r, 2 * b);
            getChildAt(4).layout(2 * r, 0, 3 * r, b);


        /*for (int i = 0; i < cnt ; i++) {
            View child = getChildAt(i);// 배치 값


            int idx = (Integer) child.getTag() << 1;
            int xOffset = (r - l) * PACKED_OFFSETS[idx];
            int yOffset = (b - t) * PACKED_OFFSETS[idx + 1];
            child.layout(l + xOffset, t + yOffset, r + xOffset, b + yOffset);

            Log.d(TAG, "view group의 각 페이지마다 좌표값 입력 (x,y - x,y) 시작점 끝점 : "+child);
            Log.d(TAG, "child 각 각각 한개의 페이지 : "+child.getTag());

        }*/
        }
    }
}


