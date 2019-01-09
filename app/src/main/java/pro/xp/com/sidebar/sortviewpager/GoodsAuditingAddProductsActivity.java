package pro.xp.com.sidebar.sortviewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pro.xp.com.sidebar.R;
import pro.xp.com.sidebar.model.ProductParamListEntity;
import pro.xp.com.sidebar.utils.AssetsUtil;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;


/**
 * 订货审核:（第四页） 继续添加订货产品页
 *
 * @author XiePing
 */
public class GoodsAuditingAddProductsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    /**
     * TabLayout中的tab标题
     */
    private List<String> mTitles;
    /**
     * 填充到ViewPager中的Fragment
     */
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        initContent();
        setData();
    }

    private void initContent() {
        mTablayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void configViews() {
        // 初始化ViewPager的适配器，并设置给它
        AddProductsTablayoutViewPagerAdapter mViewPagerAdapter = new AddProductsTablayoutViewPagerAdapter(getSupportFragmentManager(), mTitles, fragmentList);
        mViewPager.setAdapter(mViewPagerAdapter);
        // 设置ViewPager最大缓存的页面个数
        mViewPager.setOffscreenPageLimit(5);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
        mViewPager.addOnPageChangeListener(this);

        mTablayout.setTabMode(MODE_SCROLLABLE);
        // 设置TabLayout的指示器高度为0，即达到了隐藏Indicator的目的
        mTablayout.setSelectedTabIndicatorHeight(0);
//        mTablayout.setBackgroundColor(getResources().getColor(R.color.white));
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        mTablayout.setupWithViewPager(mViewPager);
        // 设置Tablayout的Tab显示ViewPager的适配器中的getPageTitle函数获取到的标题
        mTablayout.setTabsFromPagerAdapter(mViewPagerAdapter);

    }

    @Override
    public void onPageSelected(int position) {
        mTablayout.newTab().setText(mTitles.get(position));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setData() {
        //初始化标题
        mTitles = new ArrayList<>();
        //初始化填充到ViewPager中的Fragment集合
        fragmentList = new ArrayList<>();
        String fuction = AssetsUtil.getFromAssets(this, "addData.json");
        ProductParamListEntity functions = new Gson().fromJson(fuction, ProductParamListEntity.class);
        if (null != functions && null != functions.getResultList()) {
            for (int i = 0; i < functions.getResultList().size(); i++) {
                mTitles.add(functions.getResultList().get(i).getProductType());
                Bundle mBundle = new Bundle();
                mBundle.putInt("flag", i);
                mBundle.putString("productType", functions.getResultList().get(i).getProductType());
                mBundle.putSerializable("productAndSuggestOrderGoodsList", (Serializable) functions.getResultList().get(i).getProductAndSuggestOrderGoodsList());
                AddProductsTablayoutListFragment mFragment = new AddProductsTablayoutListFragment();
                mFragment.setData(mBundle);
//            mFragment.setArguments(mBundle);
                fragmentList.add(i, mFragment);
            }
        }
        configViews();
    }
}