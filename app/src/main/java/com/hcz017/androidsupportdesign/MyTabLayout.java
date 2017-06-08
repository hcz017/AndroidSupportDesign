package com.hcz017.androidsupportdesign;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MyTabLayout extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tab_layout);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("new add"));
        mTabLayout.addTab(mTabLayout.newTab().setText("new add"));
        //fvb tabLayout之后设置adapter之后，不然后面getCount为空
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        customTabs();
    }

    public static int[] imgRes;

    private void customTabs() {
        imgRes = new int[]{R.drawable.chrysanthemum, R.drawable.hydrangeas, R.drawable.jellyfish,
                R.drawable.penguins, R.drawable.tulips};
        String[] imgNames = {"chrysanthemum", "hydrangeas", "jellyfish", "penguins", "tulips"};

        //如果用R.color 会显示黑色，不知道为什么
//        mTabLayout.setTabTextColors(Color.WHITE, R.color.colorAccent);
        mTabLayout.setTabTextColors(Color.WHITE, Color.YELLOW);
        mTabLayout.setSelectedTabIndicatorColor(Color.YELLOW);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setText(imgNames[i]).setIcon(imgRes[i]);
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeColor(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void changeColor(final int position) {
        int tabPosition = position;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgRes[tabPosition]);
//        setToolbarColor(bitmap);
        /*同步
        Palette palette = Palette.from(bitmap).generate();
        Palette.Swatch swatch = palette.getVibrantSwatch();
        mTabLayout.setBackgroundColor(swatch.getRgb());*/

        // 异步 Palette的部分
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取到充满活力的这种色调
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

                //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，标题等，使整个UI界面颜色统一
                // Set the toolbar background and text colors
                if (vibrantSwatch != null) {
                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                    toolbar.setBackgroundColor(vibrantSwatch.getRgb());
                    toolbar.setTitleTextColor(vibrantSwatch.getTitleTextColor());
                    mTabLayout.setBackgroundColor(vibrantSwatch.getRgb());
                    Window window = getWindow();
                    window.setStatusBarColor((vibrantSwatch.getRgb()));
                    window.setNavigationBarColor((vibrantSwatch.getRgb()));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private ImageView imageView;
        private TextView mVibrant;
        private TextView mDarkVibrant;
        private TextView mLightVibrant;
        private TextView mMuted;
        private TextView mDarkMuted;
        private TextView mLightMuted;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
            imageView = (ImageView) rootView.findViewById(R.id.imageView);
            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            imageView.setImageResource(imgRes[position]);
            mVibrant = (TextView) rootView.findViewById(R.id.tv_vibrant);
            mDarkVibrant = (TextView) rootView.findViewById(R.id.tv_vibrant_dark);
            mLightVibrant = (TextView) rootView.findViewById(R.id.tv_vibrant_light);
            mMuted = (TextView) rootView.findViewById(R.id.tv_muted);
            mDarkMuted = (TextView) rootView.findViewById(R.id.tv_muted_dark);
            mLightMuted = (TextView) rootView.findViewById(R.id.tv_muted_light);
            changeColor();
            return rootView;
        }

        public void changeColor() {
            int position = getArguments().getInt(ARG_SECTION_NUMBER);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgRes[position]);
            // 异步 Palette的部分
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    //获取到充满活力的这种色调
                    Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                    Palette.Swatch vibrantDarkSwatch = palette.getDarkVibrantSwatch();
                    Palette.Swatch vibrantLightSwatch = palette.getLightVibrantSwatch();
                    Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                    Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                    Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();

                    mVibrant.setBackgroundColor(vibrantDarkSwatch != null ? vibrantSwatch.getRgb() : 0xffffff);
                    mDarkVibrant.setBackgroundColor(vibrantDarkSwatch != null ? vibrantDarkSwatch.getRgb() : 0xffffff);
                    mLightVibrant.setBackgroundColor(vibrantLightSwatch != null ? vibrantLightSwatch.getRgb() : 0xffffff);
                    mMuted.setBackgroundColor(mutedSwatch != null ? mutedSwatch.getRgb() : 0xffffff);
                    mDarkMuted.setBackgroundColor(darkMutedSwatch != null ? darkMutedSwatch.getRgb() : 0xffffff);
                    mLightMuted.setBackgroundColor(lightMutedSwatch != null ? lightMutedSwatch.getRgb() : 0xffffff);
                }
            });
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return mTabLayout.getTabCount();
        }
    }
}

