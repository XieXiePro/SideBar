package pro.xp.com.sidebar.sortlistview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.urovo.wugumofang.view.indexBar.IndexbarConfig;

import java.util.ArrayList;
import java.util.List;

import pro.xp.com.indexbar.IndexBar.widget.IndexBar;
import pro.xp.com.sidebar.R;

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

        showList = filledData(getResources().getStringArray(R.array.date));
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
}