package pro.xp.com.sidebar.sortlistview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import pro.xp.com.indexbar.IndexBar.widget.IndexBar;
import pro.xp.com.indexbar.IndexbarConfig;
import pro.xp.com.sidebar.R;
import pro.xp.com.sidebar.model.ProductParamListEntity;
import pro.xp.com.sidebar.utils.AssetsUtil;

public class ExampleActivity extends Activity {
    IndexBar indexbar;
    TextView tvSideBarHint;
    IndexbarConfig indexbarConfig;//用来配置indexBar索引的
    RecyclerView orderListRv;//界面的展示列表

    private ExampleAdapter adapter;
    private List<ExampleModel> showList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_frg);

        indexbar = (IndexBar) findViewById(R.id.product_index_bar);
        tvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);
        orderListRv = (RecyclerView) findViewById(R.id.proLv);
        orderListRv.setLayoutManager(new LinearLayoutManager(this));

        showList = filledData();
//        showList = filledData(getResources().getStringArray(R.array.date));
        adapter = new ExampleAdapter(showList);
        orderListRv.setAdapter(adapter);
        refreshRvByLabel(-1);
    }


    /**
     * 根据上面的标签分类来过滤显示对应的列表
     */
    public void refreshRvByLabel(int type) {
        //刷新数据
        adapter.notifyDataSetChanged();//这里对list做过滤处理后会赋给showList
        indexbarConfig = new IndexbarConfig(this,
                indexbar,
                tvSideBarHint,
                orderListRv,
                showList).configDefault();
        indexbarConfig.toggleShowIndexBar(type == -1);//-1表示“全部”标签
    }

    private List<ExampleModel> filledData(String[] datas) {
        for (String data : datas) {
            ExampleModel sortModel = new ExampleModel();
            sortModel.setName(data);
            showList.add(sortModel);
        }
        return showList;
    }

    private List<ExampleModel> filledData() {
        String fuction = AssetsUtil.getFromAssets(this, "addData.json");
        ProductParamListEntity functions = new Gson().fromJson(fuction, ProductParamListEntity.class);
        if (null != functions && null != functions.getResultList()) {
            for (int i = 0; i < functions.getResultList().size(); i++) {
                for (int j = 0; j < functions.getResultList().get(i).getProductAndSuggestOrderGoodsList().size(); j++) {
                    ExampleModel sortModel = new ExampleModel();
                    sortModel.setName(functions.getResultList().get(i).getProductAndSuggestOrderGoodsList().get(j).getFixedProduct().getProductName());
                    showList.add(sortModel);
                }
            }
        }
        return showList;
    }
}