package com.demospotsoon.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.demospotsoon.enums.TabsType;
import com.demospotsoon.fragment.BlankFragment;
import com.demospotsoon.fragment.HomeFragment;
import com.demospotsoon.fragment.ImageFragment;
import com.demospotsoon.fragment.MilestoneFragment;
import com.demospotsoon.fragment.VideosFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
//    int TitleIcons[];
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    int tabIconsIds[];  // store ids of images of teb icons
    TabsType tabsType;

    /**
     * @param fm
     * @param mTitles
     * @param tabIconsIds
     * @param tabsType
     * Build a Constructor and assign the passed Values to appropriate values in the class.
     */
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int[] tabIconsIds, TabsType tabsType) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mTitles.length;
        this.tabIconsIds = tabIconsIds;
        this.tabsType = tabsType;
    }

    /**
     * @param position
     * @return
     * UI Update: This method return the fragment for the every position in the View Pager.
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (this.tabsType.equals(TabsType.HomeTabs)) {
            fragment = getHomeTabFragments(position);
        }
        return fragment;
    }

    /**
     * @param position
     * @return
     * For Home tab return 3 fragments
     */
    private Fragment getHomeTabFragments(int position) {
        switch (position) {
            case 0:
                VideosFragment videosFragment = new VideosFragment();
                return videosFragment;
            case 1:
                ImageFragment imageFragment = new ImageFragment();
                return imageFragment;
            case 2:
                MilestoneFragment milestoneFragment = new MilestoneFragment();
                return milestoneFragment;
            default:
                BlankFragment blankFragment = new BlankFragment();
                return blankFragment;
        }

    }

    /**
     * @param position
     * @return
     * This method return the titles for the Tabs in the Tab Strip
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    /*public int getPageIconText(int position) {
        return TitleIcons[position];
    }*/

    /**
     * @param position
     * @return
     * This method return the icons for the Tabs in the Tab Strip
     */
    public int getTabIconId(int position) {
        return tabIconsIds[position];
    }

    /**
     * @return
     * This method return the Number of tabs for the tabs Strip
     */
    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}