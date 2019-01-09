package pro.xp.com.sidebar.model;


import com.google.gson.annotations.SerializedName;

/**
 * 请求返回实体基类
 *
 * @author xiep on 2016/2/17 16:28
 */
@SuppressWarnings("all")
public class BaseResponse {
    /**
     * 0.系统正常 1.系统异常
     */
    @SerializedName(value = "ret", alternate = {"sysCode"})
    public String retCode;
    /**
     * 操作错误提示信息
     */
    @SerializedName(value = "retInfo", alternate = {"msgContent", "msgInfo"})
    public String retInfo;
    /**
     * 0.操作成功 1.操作失败
     */
    public String msgCode;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "retCode='" + retCode + '\'' +
                ", retInfo='" + retInfo + '\'' +
                ", msgCode='" + msgCode + '\'' +
                '}';
    }
}