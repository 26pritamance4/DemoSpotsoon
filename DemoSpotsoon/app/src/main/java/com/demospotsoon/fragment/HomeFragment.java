package com.demospotsoon.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demospotsoon.R;
import com.demospotsoon.adapter.SlideAdapter;
import com.demospotsoon.adapter.ViewPagerAdapter;
import com.demospotsoon.enums.TabsType;
import com.demospotsoon.model.DataItem;
import com.demospotsoon.utils.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment{

    private List<DataItem> slideList = new ArrayList<>();
    private Context _context;
    private ViewPager sPager;
    private int currentPage = 0;

    public CharSequence Titles[];
    public int TitleIcons[] = {
            R.color.tab_video_selector,
            R.color.tab_image_selector,
            R.color.tab_milestone_selector
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setData();
        initView();
    }

    private void setData(){
        _context = getContext();
    }

    private void initView(){
        initViewPager();
        setNavigationTabBar();
    }

    private void initViewPager() {
        DataItem data1 = new DataItem();
        data1.title = "Game of thrones";
        data1.author = "Adious John";
        data1.icon = "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg";
        slideList.add(data1);

        DataItem data2 = new DataItem();
        data2.title = "The Big Bang Theory";
        data2.author = "Jastin John";
        data2.icon = "http://tvfiles.alphacoders.com/100/hdclearart-10.png";
        slideList.add(data2);

        DataItem data3 = new DataItem();
        data3.title = "The Hannibal";
        data3.author = "Master Smith";
        data3.icon = "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg";
        slideList.add(data3);

        DataItem data4 = new DataItem();
        data4.title = "The House";
        data4.author = "Little Porker";
        data4.icon = "http://cdn3.nflximg.net/images/3093/2043093.jpg";
        slideList.add(data4);


        sPager = (ViewPager) getView().findViewById(R.id.pager);
        sPager.setAdapter(new SlideAdapter(_context, slideList));
        CircleIndicator indicator = (CircleIndicator) getView().findViewById(R.id.indicator);
        indicator.setViewPager(sPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == slideList.size()) {
                    currentPage = 0;
                }
                sPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    /**
     * UI update: initialize type of tabs, tabs titles, icons and initialize viewpager and adapter.
     * Also initialize sliding tab  SlidingTabLayout
     */
    private void setNavigationTabBar() {
        TabsType tabsType = TabsType.HomeTabs;
        setTabTitles();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager(), Titles, TitleIcons, tabsType);
        ViewPager pagerTab = (ViewPager) getView().findViewById(R.id.pagerTab);
        SlidingTabLayout tabs = (SlidingTabLayout) getView().findViewById(R.id.tabs);
        pagerTab.setAdapter(adapter);
//        pagerTab.setOffscreenPageLimit(Titles.length - 1);
        // Assiging the Sliding Tab Layout View
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabView(R.layout.home_tab_view, R.id.txtId, R.id.tvIcon);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return ContextCompat.getColor(_context, R.color.colorPrimary);
            }
        });
        tabs.setViewPager(pagerTab);

//        adapter = null;
//        System.gc();

    }

    /**
     * Local Storage: Store tab titles and title icons in local variables
     */
    private void setTabTitles() {
        Titles = getResources().getStringArray(R.array.home_tab_titles);
    }
}
