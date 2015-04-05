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

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-03-31.
 */
public class ForuDirection_Fragment extends Fragment {

    ArrayList<View> arrayList = new ArrayList<View>();

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
                "left",
                "right",
                "top",
                "bottom",
                "view",


        };
        private final String[] TEXTS_2 = {
                "왼쪽",
                "오른쪽",
                "천장",
                "바닥",
                "보다",

        };

        private final int[] COLORS = {
                0xaa0000ff, 0xaa0000ff, 0xaaff0000, 0xaaff0000, 0xaa00ff00
        };
        private final int PACKED_VERTICAL = TEXTS.length;
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
                if (Math.abs(velocityX) > Math.abs(velocityY)) {//horizental viewpager
/*
                    Math.abs(velocityX) > Math.abs(velocityY) 여기 글에보면 if문에 보면 x축과 y축을 절대값으료 비교하는 값이 있는데
                    velocity 값은 터치 가속도를 의미하는 값으로 y 가속도보다  x 가속도가 크면 실행한다는 의미로 여기 지역if 맨밑에
                    mScroller.startScroll(sx, sy, distance, 0, DURATION); 이것을 실행해서 x축(horizontal pager)가 작동하게함

*/


                    if (/*1번 수식*/sx == 0 && velocityX > 0 /*||2번  sy != 0 && (Math.abs(velocityX) > Math.abs(velocityY))*/) {
                        //Log.d(TAG, "sy :" + sy + "velocityX * sx : " + velocityX);
                        return false; /*부정 리턴값으로 이게 페이지를 더이상 못넘기게 하는 값임
                         sx == 0 && velocityX
                        sx,sy 는 일종의 좌표값으로 sx=0 라는 것은 맨첫페이지를 뜻하며 여기서  velocityX > 0 일때 부정을 리턴 해준다는 의미는
                        맨 첫페이지가 1페이지라라고 하면 왼쪽의 화면(0페이지)로 못하게 하는 값

                        sy != 0 && (Math.abs(velocityX) > Math.abs(velocityY))

                        이건 vertical 뷰페이져에서 horizental작동하지 못하도록 하는 수식임


                        이해가 되지 않을 경우 저 수식을 하나씩 지워보는것도 좋음
                        */
                    }
//                DURATION = (int) (1000 * w / Math.abs(velocityX));
                    int distance = velocityX < 0 ? w : -w;


                    mScroller.startScroll(sx, sy, distance, 0, DURATION); //값이라 가로
                    
                } else {//vertical viewpager
                    if (/*1번 수식*/sy == 0 && velocityY > 0 /*||2.번 수식 sy != 0 && velocityY < 0*/) {

                        //Log.d(TAG, "sx :" + sx + "velocityY * sy : " + sy);

                        return false;

                        /*
                        * sy == 0 && velocityY > 0 첫페이지 버티칼에서 에서 (윗페이지 를 못뜨게 하는 수식)
                        *
                        * sy != 0 && velocityY < 0 vertical에서 첫페이지가 넘어가고 2번째 페이지에서 더이상 vertical뷰가 작동하지 못하게 하는 수식
                        *
                        *
                        *  이해 안가면 수식을 삭제하보고 적용해보시도록
                        * */
                    }
//                DURATION = (int) (1000 * h / Math.abs(velocityY));

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
                tv.setTag(i * 2);
                tv.setTextSize(32);
                tv.setTypeface(Typeface.DEFAULT_BOLD);
                tv.setTextColor(0xffeeeeee);
                tv.setText(TEXTS[i]);
                tv.setBackgroundColor(COLORS[i]);
                addView(tv);

                TextView tv2 = new TextView(context);
                tv2.setTag((i * 2) + 1);
                tv2.setTextSize(32);
                tv2.setTypeface(Typeface.DEFAULT_BOLD);
                tv2.setTextColor(0xffeeeeee);
                tv2.setText(TEXTS_2[i]);
                tv2.setBackgroundColor(COLORS[i]);
                addView(tv2);

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
            Log.d(TAG, "onTouchEvent");
            mDetector.onTouchEvent(event);
            return true;
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int cnt = getChildCount();
            Log.d(TAG, "r :" + r); // 화면 1
            Log.d(TAG, "b :" + b); // 화면 높이






            Vertical_Viewpager_onLayout(l, t, r, b);
        }



        public void Vertical_Viewpager_onLayout(int l, int t, int r, int b) {
            int cnt = getChildCount();
            Log.d(TAG, "r :" + r); // 화면 폭
            Log.d(TAG, "b :" + b); // 화면 높이

            for (int i = 0; i < cnt; i++) {


                View child = getChildAt(i);// 배치 값

                child.layout(l, i * b, r, (i + 1) * b);

                for (int j = i; j < cnt; j = j + 2)
                {

                    //child.layout(r,0,2*r,b);
                    //child.layout((j/2) * r, i * b, ((j/2 + 1) * r), (i+1) * b);

                }
            }
        }
    }
}


