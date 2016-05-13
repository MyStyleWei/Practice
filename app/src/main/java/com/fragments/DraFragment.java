package com.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weili.practice.R;

/**
 * Created by wei.li on 2016/1/21.
 */
public class DraFragment extends Fragment {

    private TabLayout tabLayout;
    private View view;
    private ImageView imageView;
    private ViewPager viewPager;
    private String[] str = {"111","222","333","444"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dra_fragment,null);
              tabLayout = (TabLayout) view.findViewById(R.id.tab);
        imageView = (ImageView) view.findViewById(R.id.img);
        viewPager = (ViewPager) view.findViewById(R.id.vp);

//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

      //  tabLayout.setTabsFromPagerAdapter(adapter);
     //   viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        imageView.animate()
                .scaleY(0.1f)
                .scaleX(0.1f)
                .alpha(0.2f)
                .setDuration(1000)
                .start();


        //setupTab();
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private PagerAdapter adapter = new PagerAdapter() {

        @Override
        public CharSequence getPageTitle(int position) {
            return str[position];
        }

        @Override
        public int getCount() {
            return str.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(getActivity());
            textView.setTextSize(30.0f);
            textView.setText(str[position]);
            container.addView(textView);
            return textView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    };

    private void setupTab(){
        tabLayout.addTab(tabLayout.newTab().setText("111"));
        tabLayout.addTab(tabLayout.newTab().setText("222"));
        tabLayout.addTab(tabLayout.newTab().setText("333"));
        tabLayout.addTab(tabLayout.newTab().setText("444"));
    }
}
