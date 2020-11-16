package com.example.base_project.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.base_project.R;
import com.example.base_project.base.BaseActivity;
import com.example.base_project.main.model.Movie;
import com.example.base_project.nowPlaying.view.NowPlayingHomeFragment;
import com.example.base_project.popular.view.PopularHomeFragment;
import com.example.base_project.topRated.view.TopRatedHomeFragment;
import com.example.base_project.upcoming.view.UpcomingHomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public static final String ID_FILM = "ID_FILM";

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.view_pager_main)
    ViewPager viewPagerMain;
    @BindView(R.id.tv_title_screen)
    TextView titleScreenTextView;
    @BindView(R.id.view_search)
    RelativeLayout viewSearch;
    @BindView(R.id.rl_search)
    RelativeLayout searchRlayout;
    @BindView(R.id.img_search)
    ImageView searchImageView;
    @BindView(R.id.edt_search)
    EditText searchEditText;
    @BindView(R.id.img_back)
    ImageView backImageView;
    @BindView(R.id.rv_search)
    RecyclerView searchList;

    private int postionTab = 0;
    private ArrayList<AHBottomNavigationItem> listBottomNavigation;
    private MainViewModel viewModel;

    private UpcomingHomeFragment upcomingHomeFragment;
    private TopRatedHomeFragment topRatedHomeFragment;
    private PopularHomeFragment popularHomeFragment;
    private NowPlayingHomeFragment nowPlayingHomeFragment;

    private SearcAdapter searcAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAct() {
        searchRlayout.setVisibility(View.GONE);
        viewSearch.setVisibility(View.GONE);
        viewModel = getViewModel(MainViewModel.class);
        initViewModel();
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setCurrentItem(postionTab);
        updateBottomNavigation();
        initView();
    }

    private void initView() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (searchEditText.getText() != null && searchEditText.getText().length() > 0) {
                    String textSearch = searchEditText.getText().toString();
                    viewModel.searchFilm(textSearch);
                } else {
                    viewSearch.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initViewModel() {
        viewModel.getTitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String title) {
                if (title != null) {
                    titleScreenTextView.setText(title);
                }
            }
        });

        viewModel.getListFilmSearch().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies.size() > 0) {
                    viewSearch.setVisibility(View.VISIBLE);
                    if (searcAdapter == null) {
                        searcAdapter = new SearcAdapter(MainActivity.this, new SearcAdapter.OnClickListener() {
                            @Override
                            public void onClickItem(int position) {

                            }
                        });
                        searcAdapter.setListData(movies);
                        searchList.setAdapter(searcAdapter);
                    } else {
                        searcAdapter.setListData(movies);
                    }
                } else {
                    viewSearch.setVisibility(View.GONE);
                }
            }
        });
    }

    @SuppressLint("NewApi")
    private void updateBottomNavigation() {
        listBottomNavigation = new ArrayList<>();
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Upcoming", R.drawable.ic_upcoming);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Top Rated", R.drawable.ic_top_rated);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Popular", R.drawable.ic_popularity);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Now Playing", R.drawable.ic_now_playing);

        listBottomNavigation.add(item1);
        listBottomNavigation.add(item2);
        listBottomNavigation.add(item3);
        listBottomNavigation.add(item4);

        bottomNavigation.setAccentColor(Color.parseColor("#ffffff"));
        bottomNavigation.setInactiveColor(Color.parseColor("#ffffff"));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#000000"));

        bottomNavigation.removeAllItems();
        bottomNavigation.addItems(listBottomNavigation);

        initViewPager();
    }

    private void initViewPager() {
        final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        if (upcomingHomeFragment == null) {
                            upcomingHomeFragment = UpcomingHomeFragment.getInstance();
                        }
                        fragment = upcomingHomeFragment;
                        break;
                    case 1:
                        if (topRatedHomeFragment == null) {
                            topRatedHomeFragment = TopRatedHomeFragment.getInstance();
                        }
                        fragment = topRatedHomeFragment;
                        break;
                    case 2:
                        if (popularHomeFragment == null) {
                            popularHomeFragment = PopularHomeFragment.getInstance();
                        }
                        fragment = popularHomeFragment;
                        break;
                    case 3:
                        if (nowPlayingHomeFragment == null) {
                            nowPlayingHomeFragment = NowPlayingHomeFragment.getInstance();
                        }
                        fragment = nowPlayingHomeFragment;
                        break;

                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        };

        setupViewPager(adapter);
    }

    private void setupViewPager(FragmentPagerAdapter adapter) {
        viewPagerMain.setAdapter(adapter);
        viewPagerMain.setOffscreenPageLimit(adapter.getCount());

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPagerMain.setCurrentItem(position);
                return true;
            }
        });

        viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position);
                switch (position) {
                    case 0:
                        viewModel.getTitle().setValue("Upcoming");
                        break;
                    case 1:
                        viewModel.getTitle().setValue("Top Rated");
                        break;
                    case 2:
                        viewModel.getTitle().setValue("Popular");
                        break;
                    case 3:
                        viewModel.getTitle().setValue("Now Playing");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.img_search, R.id.img_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_search:
                searchRlayout.setVisibility(View.VISIBLE);
                break;
            case R.id.img_back:
                searchRlayout.setVisibility(View.GONE);
                viewSearch.setVisibility(View.GONE);
                break;
        }
    }

}