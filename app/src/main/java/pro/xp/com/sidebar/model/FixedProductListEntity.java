package pro.xp.com.sidebar.model;

import java.io.Serializable;

/**
 * @author xiep  on 2016/9/28 16:54
 *         邮箱：2975837870@qq.com
 *         <p>
 *         proId (integer, optional): 产品ID ,
 *         productBigType (string, optional): 产品大类 ,
 *         productBoxNum (number, optional): 产品箱装数量 ,
 *         productCd (string, optional): 产品是否可冲顶 ,
 *         productCode (string, optional): 产品编码 ,
 *         productDh (string, optional): 产品是否可订货 ,
 *         productName (string, optional): 产品名称 ,
 *         productPack (number, optional): 产品规格 ,
 *         productPd (string, optional): 产品是否可盘点 ,
 *         productPrice (number, optional): 产品价格 ,
 *         productSc (string, optional): 产品是否可试吃 ,
 *         productType (string, optional): 产品类别 ,
 *         productUnit (string, optional): 产品单位 ,
 *         productUnitId (integer, optional): 产品单位id ,
 *         productXs (string, optional): 产品是否可销售
 */
@SuppressWarnings("all")
public class FixedProductListEntity implements Serializable {
    /**
     * 是否已选（本地冗余字段）
     */
    private boolean isChoice;

    private Long proId;

    private String productName;

    private String productCode;

    private String productUnit;

    private int productUnitId;

    private String productPrice;

    private String productXs;

    private String productDh;

    private String productPd;

    private String productSc;

    private String productCd;

    private double productBoxNum;

    private String productBigType;

    private double productPack;

    private String productType;
    /**
     * 产品状态
     */
    private String productStatus;

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public Long getProId() {
        return this.proId;
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

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductUnit() {
        return this.productUnit;
    }

    public void setProductUnitId(int productUnitId) {
        this.productUnitId = productUnitId;
    }

    public int getProductUnitId() {
        return this.productUnitId;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPrice() {
        return this.productPrice;
    }

    public void setProductBoxNum(double productBoxNum) {
        this.productBoxNum = productBoxNum;
    }

    public void setProductBigType(String productBigType) {
        this.productBigType = productBigType;
    }

    public String getProductBigType() {
        return this.productBigType;
    }

    public void setProductPack(double productPack) {
        this.productPack = productPack;
    }

    public double getProductPack() {
        return this.productPack;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return this.productType;
    }

    public boolean getIsChoice() {
        return this.isChoice;
    }

    public void setIsChoice(boolean isChoice) {
        this.isChoice = isChoice;
    }

    public String getProductXs() {
        return productXs;
    }

    public void setProductXs(String productXs) {
        this.productXs = productXs;
    }

    public String getProductDh() {
        return productDh;
    }

    public void setProductDh(String productDh) {
        this.productDh = productDh;
    }

    public String getProductPd() {
        return productPd;
    }

    public void setProductPd(String productPd) {
        this.productPd = productPd;
    }

    public String getProductSc() {
        return productSc;
    }

    public void setProductSc(String productSc) {
        this.productSc = productSc;
    }

    public String getProductCd() {
        return productCd;
    }

    public void setProductCd(String productCd) {
        this.productCd = productCd;
    }

    public double getProductBoxNum() {
        return productBoxNum;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}