package pro.xp.com.sidebar.model;

import java.io.Serializable;

/**
 * ProductAndSuggestOrderGoods {
 * fixedProduct (FixedProduct, optional): 产品 ,
 * productSuggestOrderGoodsNum (ProductSuggestOrderGoodsNum, optional): 建议订货量
 * }
 * FixedProduct {
 * proId (integer, optional): 产品ID ,
 * productBigType (string, optional): 产品大类 ,
 * productBoxNum (number, optional): 产品箱装数量 ,
 * productCd (string, optional): 产品是否可冲顶 ,
 * productCode (string, optional): 产品编码 ,
 * productDh (string, optional): 产品是否可订货 ,
 * productName (string, optional): 产品名称 ,
 * productPack (number, optional): 产品规格 ,
 * productPd (string, optional): 产品是否可盘点 ,
 * productPrice (number, optional): 产品价格 ,
 * productSc (string, optional): 产品是否可试吃 ,
 * productType (string, optional): 产品类别 ,
 * productUnit (string, optional): 产品单位 ,
 * productUnitId (integer, optional): 产品单位id ,
 * productXs (string, optional): 产品是否可销售
 * }
 * ProductSuggestOrderGoodsNum {
 * productBoxNum (number, optional): 产品箱装数量 ,
 * productCode (string, optional): 产品编号 ,
 * productLastMonthSaleNum (number, optional): 产品前30天销售数量 ,
 * productMaxSOrderGoodsNum (number, optional): 产品最大建议订货量 ,
 * productName (string, optional): 产品名称 ,
 * productPrice (number, optional): 产品价格 ,
 * productSOrderGoodsNum (number, optional): 产品建议订货量 ,
 * productStockNum (number, optional): 产品库存数量 ,
 * productSurplusNum (number, optional): 产品剩余量
 * }
 *
 * @author XiePing on 2016/9/30 13:22
 */

public class ProductSuggestOrderGoodsNum implements Serializable {
    /**
     * 审核数量（本地冗余字段）
     */
    private double orderGoodsAuditNum;

    private double productLastMonthSaleNum;

    private double productBoxNum;

    private double productStockNum;

    private String productPrice;

    private String productName;

    private String productCode;

    private double productSurplusNum;

    private double productSOrderGoodsNum;

    private double productMaxSOrderGoodsNum;

    public double getOrderGoodsAuditNum() {
        return orderGoodsAuditNum;
    }

    public void setOrderGoodsAuditNum(double orderGoodsAuditNum) {
        this.orderGoodsAuditNum = orderGoodsAuditNum;
    }

    public void setProductSOrderGoodsNum(double productSOrderGoodsNum) {
        this.productSOrderGoodsNum = productSOrderGoodsNum;
    }

    public void setProductMaxSOrderGoodsNum(double productMaxSOrderGoodsNum) {
        this.productMaxSOrderGoodsNum = productMaxSOrderGoodsNum;
    }

    public void setProductLastMonthSaleNum(double productLastMonthSaleNum) {
        this.productLastMonthSaleNum = productLastMonthSaleNum;
    }

    public double getProductLastMonthSaleNum() {
        return this.productLastMonthSaleNum;
    }

    public void setProductBoxNum(int productBoxNum) {
        this.productBoxNum = productBoxNum;
    }

    public double getProductBoxNum() {
        return this.productBoxNum;
    }

    public void setProductStockNum(double productStockNum) {
        this.productStockNum = productStockNum;
    }

    public double getProductStockNum() {
        return this.productStockNum;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPrice() {
        return this.productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductSurplusNum(double productSurplusNum) {
        this.productSurplusNum = productSurplusNum;
    }

    public double getProductSurplusNum() {
        return this.productSurplusNum;
    }

    public void setProductSOrderGoodsNum(int productSOrderGoodsNum) {
        this.productSOrderGoodsNum = productSOrderGoodsNum;
    }

    public double getProductSOrderGoodsNum() {
        return this.productSOrderGoodsNum;
    }

    public void setProductMaxSOrderGoodsNum(int productMaxSOrderGoodsNum) {
        this.productMaxSOrderGoodsNum = productMaxSOrderGoodsNum;
    }

    public double getProductMaxSOrderGoodsNum() {
        return this.productMaxSOrderGoodsNum;
    }

}