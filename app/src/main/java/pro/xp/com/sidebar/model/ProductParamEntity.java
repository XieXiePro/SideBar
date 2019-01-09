package pro.xp.com.sidebar.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author XiePing on 2016/9/28 16:53
 *         邮箱：2975837870@qq.com
 *         <p>
 *         fixedProductList (Array[FixedProduct], optional): 产品集合 ,
 *         productType (string, optional): 产品分类
 */

public class ProductParamEntity implements Serializable {
    private List<ProductAndSuggestOrderGoodsList> productAndSuggestOrderGoodsList;

    private String productType;

    public void setProductAndSuggestOrderGoodsList(List<ProductAndSuggestOrderGoodsList> productAndSuggestOrderGoodsList) {
        this.productAndSuggestOrderGoodsList = productAndSuggestOrderGoodsList;
    }

    public List<ProductAndSuggestOrderGoodsList> getProductAndSuggestOrderGoodsList() {
        return this.productAndSuggestOrderGoodsList;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return this.productType;
    }
}
