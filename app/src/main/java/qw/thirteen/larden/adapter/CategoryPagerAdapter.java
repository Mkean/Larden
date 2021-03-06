package qw.thirteen.larden.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import qw.thirteen.larden.view.CategoryView;

public class CategoryPagerAdapter extends PagerAdapter {

    private ArrayList<CategoryView> mViewList;
    private ArrayList<String> mTabList;

    private CategoryView mCurrentPrimaryItem = null;


    public CategoryPagerAdapter(ArrayList<CategoryView> viewList, ArrayList<String> tabList) {
        mViewList = viewList;
        mTabList = tabList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        CategoryView categoryView = mViewList.get(position);
        if (container == categoryView.getParent()) {
            container.removeView(categoryView);
        }
        container.addView(categoryView);
        return categoryView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        CategoryView categoryView = (CategoryView) object;
        if (categoryView != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.onUserVisibleChange(false);
            }
        }
        categoryView.onUserVisibleChange(true);
        mCurrentPrimaryItem = categoryView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }
}
