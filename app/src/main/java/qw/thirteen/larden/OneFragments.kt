package qw.thirteen.larden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import qw.thirteen.larden.adapter.MultiTypeAdapter
import qw.thirteen.larden.bean.CategoryBean
import qw.thirteen.larden.view.ParentRecyclerView
import java.util.*

/**
 * @Author:
 * @Description:
 */
class OneFragments : Fragment() {


    private val mDataList = ArrayList<Any>()

    private val adapter by lazy { MultiTypeAdapter(mDataList) }

    private lateinit var javaRecyclerView: ParentRecyclerView

    var strArray = arrayOf("推荐", "视频", "直播", "图片", "精华", "热门")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.one_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        javaRecyclerView = view.findViewById<ParentRecyclerView>(R.id.recyclerView)

        javaRecyclerView.apply {
            adapter = this@OneFragments.adapter
            initLayoutManager(this@OneFragments.context)
        }

        refresh()
    }

    private fun refresh() {
        mDataList.clear()
        for (i in 0..7) {
            mDataList.add("parent item text $i")
        }
        val categoryBean = CategoryBean()
        categoryBean.tabTitleList.clear()
        categoryBean.tabTitleList.addAll(listOf(*strArray))
        mDataList.add(categoryBean)
        adapter.notifyDataSetChanged()
    }
}