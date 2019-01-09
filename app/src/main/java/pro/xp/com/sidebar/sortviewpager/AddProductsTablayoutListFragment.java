package pro.xp.com.sidebar.sortviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pro.xp.com.indexbar.IndexBar.widget.IndexBar;
import pro.xp.com.indexbar.IndexbarConfig;
import pro.xp.com.sidebar.R;
import pro.xp.com.sidebar.model.ProductAndSuggestOrderGoodsList;

/**
 * 订货审核添加产品分页
 *
 * @author XiePing
 */
public class AddProductsTablayoutListFragment extends Fragment {
    IndexBar indexbar;
    TextView tvSideBarHint;
    IndexbarConfig indexbarConfig;//用来配置indexBar索引的
    RecyclerView orderListRv;//界面的展示列表
    private String productType;
    private GoodsAuditingAddProductsAdapter adapter;
    private List<ProductAndSuggestOrderGoodsList> list = new ArrayList<>();

    public void setData(Bundle mBundle) {
        list = (List<ProductAndSuggestOrderGoodsList>) mBundle.getSerializable("productAndSuggestOrderGoodsList");
        productType = mBundle.getString("productType");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_add_products_tab, null);
        indexbar = (IndexBar) view.findViewById(R.id.product_index_bar);
        tvSideBarHint = (TextView) view.findViewById(R.id.product_view_tv);
        orderListRv = (RecyclerView) view.findViewById(R.id.product_view_rv);
        orderListRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new GoodsAuditingAddProductsAdapter(list);
        orderListRv.setAdapter(adapter);
        int type;
        if ("全部".equals(productType)) {
            type = -1;
        } else {
            type = 0;
        }
        refreshRvByLabel(type);
        return view;
    }

    /**
     * 根据上面的标签分类来过滤显示对应的列表
     */
    public void refreshRvByLabel(int type) {
        //刷新数据
        adapter.notifyDataSetChanged();//这里对list做过滤处理后会赋给showList
        indexbarConfig = new IndexbarConfig(getActivity(),
                indexbar,
                tvSideBarHint,
                orderListRv,
                list).configDefault();
        indexbarConfig.toggleShowIndexBar(type == -1);//-1表示“全部”标签
    }
}