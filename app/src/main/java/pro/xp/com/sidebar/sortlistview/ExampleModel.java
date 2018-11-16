package pro.xp.com.sidebar.sortlistview;

import pro.xp.com.indexbar.IndexBar.bean.BaseIndexPinyinBean;

public class ExampleModel extends BaseIndexPinyinBean {

    private String name;   //显示的数据

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTarget() {
        return name;//以名称作为索引;
    }
}
