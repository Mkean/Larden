package qw.thirteen.larden.bean;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CategoryBean {
    ArrayList<String> tabTitleList = new ArrayList<String>();

    public ArrayList<String> getTabTitleList() {
        return tabTitleList;
    }

    public void setTabTitleList(ArrayList<String> tabTitleList) {
        this.tabTitleList = tabTitleList;
    }

    @NonNull
    @Override
    public String toString() {
        return tabTitleList.toString();
    }
}
