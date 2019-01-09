package pro.xp.com.indexbar

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import pro.xp.com.indexbar.IndexBar.bean.BaseIndexPinyinBean
import pro.xp.com.indexbar.IndexBar.widget.IndexBar
import pro.xp.com.indexbar.RecycleViewDivider
import pro.xp.com.indexbar.suspension.SuspensionDecoration

/**
 * @author Sasuke on 2018/9/23.
 * indexBar配置器
 * 把相关配置抽出来共用
 *
 * 使用方法
 * eg.
 *
 IndexbarConfig(this,
wgmfPager?.find(R.id.product_index_bar),
wgmfPager?.find(R.id.tvSideBarHint),
wgmfPager?.find(R.id.proLv),
mainPre.data.wgmfShowDatas).toggleShowIndexBar(label == "全部")
 */
class IndexbarConfig(val mContext: Context, val indexBar: IndexBar, val tvSideBarHint: TextView, val proLv: RecyclerView, val mProList: List<BaseIndexPinyinBean>) {
    val mNullDecoration: RecyclerView.ItemDecoration by lazy {
        RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, 0, Color.GRAY)
    }//高度为0的分割线


    fun configDefault(): IndexbarConfig{
        //先配置recyclerView相关参数。
        proLv.apply {
            layoutManager = LinearLayoutManager(mContext, OrientationHelper.VERTICAL, false)
            if (tag == null) tag = SuspensionDecoration(mContext, mProList)
            removeItemDecoration(tag as SuspensionDecoration)
            if (!indexBar.hasNullDecoration()){
                addItemDecoration(mNullDecoration)//上面那个lazy懒加载的decoration不算数，在这里add进indexbar里才有用。
                indexBar.setNummDecoration(mNullDecoration);
            }
        }

        //再配置indexbar相关参数
        indexBar.apply {
            setmPressedShowTextView(tvSideBarHint)
            setNeedRealIndex(false)//设为false表示不需要根据数据源来显示索引，而是显示【全部】索引，A~Z
            setmLayoutManager(proLv.layoutManager as LinearLayoutManager)//设置RecyclerView的LayoutManager
            visibility = View.GONE
        }
        return this
    }

    //如果一个页面是动态的，有时需要显示索引，有时不需要，那就可以调用这个方法。
    fun toggleShowIndexBar(isShowIndexBar: Boolean){
        configDefault()

        if (isShowIndexBar){
            configDefault()
            proLv.run {
                if (indexBar.hasNullDecoration()) removeItemDecoration(mNullDecoration)
                addItemDecoration(tag as SuspensionDecoration)
            }
            indexBar.visibility = View.VISIBLE
            indexBar.setmSourceDatas(mProList).invalidate()
        }
    }

    //下面的方法其实都是跟indexBar自带的方法一样的，抽出来再套一层而已。

    fun setmPressedShowTextView(tvSideBarHint: TextView): IndexBar{
        indexBar.setmPressedShowTextView(tvSideBarHint)
        return indexBar
    }

    fun setNeedRealIndex(isNeedRealIndex: Boolean): IndexBar{
        indexBar.setNeedRealIndex(isNeedRealIndex)
        return indexBar
    }

    fun setmLayoutManager(layoutManager: LinearLayoutManager): IndexBar{
        indexBar.setmLayoutManager(layoutManager)
        return indexBar
    }

    fun setCustomComparator(comparator: IndexBar.CustomComparator): IndexBar{
        indexBar.setCustomComparator(comparator)
        return indexBar
    }
}