package qw.thirteen.larden.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

import qw.thirteen.larden.R;
import qw.thirteen.larden.adapter.CategoryPagerAdapter;
import qw.thirteen.larden.bean.CategoryBean;
import qw.thirteen.larden.view.CategoryView;
import qw.thirteen.larden.view.ChildRecyclerView;

public class SimpleCategoryViewHolder extends RecyclerView.ViewHolder {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ChildRecyclerView mCurrentRecyclerView;

    HashMap<String, CategoryView> cacheVies = new  HashMap<String, CategoryView>();


    ArrayList<CategoryView> viewList = new ArrayList<CategoryView>();


    public SimpleCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        mTabLayout = itemView.findViewById(R.id.tabs);
        mViewPager = itemView.findViewById(R.id.viewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(!viewList.isEmpty()) {
                    mCurrentRecyclerView = viewList.get(i);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void bindData(Object obj) {
        if(obj instanceof CategoryBean) {
            CategoryBean categoryBean = (CategoryBean)obj;
            viewList.clear();
            if(cacheVies.size() > categoryBean.getTabTitleList().size()) {
                cacheVies.clear();
            }
            for(String str :categoryBean.getTabTitleList()) {
                CategoryView categoryView = cacheVies.get(str);
                if(categoryView == null || categoryView.getParent() != mViewPager) {
                    categoryView = new CategoryView(mViewPager.getContext());
                    cacheVies.put(str, categoryView);
                }
                viewList.add(categoryView);
            }
            mCurrentRecyclerView = viewList.get(mViewPager.getCurrentItem());
            int lastItem = mViewPager.getCurrentItem();
            mViewPager.setAdapter(new CategoryPagerAdapter(viewList,categoryBean.getTabTitleList()));
            mTabLayout.setupWithViewPager(mViewPager);
            mViewPager.setCurrentItem(lastItem);

        }
    }

    public void destroy() {
        cacheVies.clear();
    }

    public ChildRecyclerView getCurrentChildRecyclerView() {
        return mCurrentRecyclerView;
    }
}
