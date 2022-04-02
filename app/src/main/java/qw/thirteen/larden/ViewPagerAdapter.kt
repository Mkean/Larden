package qw.thirteen.larden

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @Author:
 * @Description:
 */
class ViewPagerAdapter(
    fm: FragmentManager,
    private val fragments: MutableList<Fragment>,
    private val titles: MutableList<String>,
) : FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (!titles.isNullOrEmpty() && position < titles.size) {
            return titles[position]
        }
        return super.getPageTitle(position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        try {
            super.destroyItem(container, position, `object`)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}