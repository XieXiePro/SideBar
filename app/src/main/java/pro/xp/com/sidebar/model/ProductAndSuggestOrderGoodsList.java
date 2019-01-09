package pro.xp.com.sidebar.model;

import java.io.Serializable;

import pro.xp.com.indexbar.IndexBar.bean.BaseIndexPinyinBean;

/**
 * fixedProduct (FixedProduct, optional): 产品 ,
 * productBillCode (string, optional): 产品BillCode/资材调拨时用 ,
 * productCapitalReceiptsStock (integer, optional): 产品库存（资材库存） ,
 * productSuggestOrderGoodsNum (ProductSuggestOrderGoodsNum, optional): 建议订货量
 *
 * @author XiePing
 */
public class ProductAndSuggestOrderGoodsList extends BaseIndexPinyinBean implements Serializable {

    private ProductSuggestOrderGoodsNum productSuggestOrderGoodsNum;

    private FixedProductListEntity fixedProduct;

    private String productBillCode;

    private double productCapitalReceiptsStock;

    private long createTime;

    public void setProductSuggestOrderGoodsNum(ProductSuggestOrderGoodsNum productSuggestOrderGoodsNum) {
        this.productSuggestOrderGoodsNum = productSuggestOrderGoodsNum;
    }

    public ProductSuggestOrderGoodsNum getProductSuggestOrderGoodsNum() {
        return this.productSuggestOrderGoodsNum;
    }

    public void setFixedProduct(FixedProductListEntity fixedProduct) {
        this.fixedProduct = fixedProduct;
    }

    public FixedProductListEntity getFixedProduct() {
        return this.fixedProduct;
    }

    public double getProductCapitalReceiptsStock() {
        return productCapitalReceiptsStock;
    }

    public void setProductCapitalReceiptsStock(double productCapitalReceiptsStock) {
        this.productCapitalReceiptsStock = productCapitalReceiptsStock;
    }

    public String getProductBillCode() {
        return productBillCode;
    }

    public void setProductBillCode(String productBillCode) {
        this.productBillCode = productBillCode;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getTarget() {
        return fixedProduct.getProductName();//以名称作为索引
    }
}