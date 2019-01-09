package pro.xp.com.sidebar.model;

import java.util.List;

/**
 * @author XiePing on 2016/9/28 16:52
 *         邮箱：2975837870@qq.com
 */

public class ProductParamListEntity extends BaseResponse {
    private List<ProductParamEntity> resultList;

    public List<ProductParamEntity> getResultList() {
        return resultList;
    }

    public void setResultList(List<ProductParamEntity> resultList) {
        this.resultList = resultList;
    }
}
