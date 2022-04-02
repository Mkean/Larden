package qw.thirteen.larden

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

class MainActivity : AppCompatActivity() {

    private val magic: MagicIndicator by lazy { findViewById(R.id.magic) }
    private val viewPager: ViewPager by lazy { findViewById(R.id.viewPager) }

    private val titles = mutableListOf<String>("看商机", "寻好物")

    private val fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
        initIndicator()
        initViewPager()
    }

    private var mCommonNavigator: CommonNavigator? = null

    private fun initIndicator() {
        mCommonNavigator = CommonNavigator(this)
        mCommonNavigator!!.adapter = object : CommonNavigatorAdapter() {
            override fun getCount() = titles.size

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                return ColorTransitionPagerTitleView(this@MainActivity).run {
                    text = titles[index]
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    gravity = Gravity.CENTER
                    selectedColor = Color.parseColor("#000000")
                    normalColor = Color.parseColor("#000000")
                    setOnClickListener {
                        viewPager.currentItem = index
                    }
                    this
                }
            }

            override fun getIndicator(context: Context?): IPagerIndicator {
                return LinePagerIndicator(this@MainActivity).run {
                    mode = LinePagerIndicator.MODE_EXACTLY
                    lineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        20f,
                        resources.displayMetrics)
                    setColors(Color.parseColor("#ff8a66"))
                    this
                }
            }
        }

        magic.navigator = mCommonNavigator
        ViewPagerHelper.bind(magic, viewPager)

    }

    private fun initViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, titles)
        viewPager.adapter = viewPagerAdapter

    }

    private fun initFragments() {
        if (fragments.isNotEmpty()) {
            fragments.clear()
        }
        fragments.add(OneFragments())
        fragments.add(TwoFragment())
    }
}
