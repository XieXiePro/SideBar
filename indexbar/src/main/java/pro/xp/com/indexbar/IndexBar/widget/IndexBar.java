package pro.xp.com.indexbar.IndexBar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pro.xp.com.indexbar.IndexBar.bean.BaseIndexPinyinBean;
import pro.xp.com.indexbar.IndexBar.helper.IIndexBarDataHelper;
import pro.xp.com.indexbar.IndexBar.helper.IndexBarDataHelperImpl;
import pro.xp.com.indexbar.R;


/**
 * 介绍：索引右侧边栏
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * CSDN：http://blog.csdn.net/zxt0601
 * 时间： 16/09/04.
 */

public class IndexBar extends View {
    /**
     * 自定义排序器
     */
    public interface CustomComparator{
        int compare(BaseIndexPinyinBean lhs, BaseIndexPinyinBean rhs);
    }

    private static final String TAG = "zxt/IndexBar";

    //#在最后面（默认的数据源）
    public static String[] INDEX_STRING = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    //是否需要根据实际的数据来生成索引数据源（例如 只有 A B C 三种tag，那么索引栏就 A B C 三项）
    private boolean isNeedRealIndex;
    //索引数据源
    private List<String> mIndexDatas;

    private Set<String> mClickableIndexs = new HashSet<>();//可点击的有效索引
    //View的宽高
    private int mWidth, mHeight;
    //每个index区域的高度
    private int mGapHeight;

    private Paint mPaint;

    //手指按下时的背景色
    private int mPressedBackground;

    //以下是帮助类
    //汉语->拼音，拼音->tag
    private IIndexBarDataHelper mDataHelper;

    //以下边变量是外部set进来的
    private TextView mPressedShowTextView;//用于特写显示正在被触摸的index值
    private boolean isSourceDatasAlreadySorted;//源数据 已经有序？
    private List<? extends BaseIndexPinyinBean> mSourceDatas;//Adapter的数据源
    private LinearLayoutManager mLayoutManager;
    private int mHeaderViewCount = 0;

    private RecyclerView.ItemDecoration mNullDecoration = null;//高度为0的分割线



    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    //以下是我自己加的初始化配置的一些方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void setNummDecoration(RecyclerView.ItemDecoration nullDecoration){
        this.mNullDecoration = nullDecoration;
    }

    public boolean hasNullDecoration(){
        return mNullDecoration != null;
    }

    //以上是我自己加的初始化配置的一些方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public int getHeaderViewCount() {
        return mHeaderViewCount;
    }

    /**
     * 设置Headerview的Count
     *
     * @param headerViewCount
     * @return
     */
    public IndexBar setHeaderViewCount(int headerViewCount) {
        mHeaderViewCount = headerViewCount;
        return this;
    }

    public boolean isSourceDatasAlreadySorted() {
        return isSourceDatasAlreadySorted;
    }

    /**
     * 源数据 是否已经有序
     *
     * @param sourceDatasAlreadySorted
     * @return
     */
    public IndexBar setSourceDatasAlreadySorted(boolean sourceDatasAlreadySorted) {
        isSourceDatasAlreadySorted = sourceDatasAlreadySorted;
        return this;
    }

    public IIndexBarDataHelper getDataHelper() {
        return mDataHelper;
    }

    /**
     * 设置数据源帮助类
     *
     * @param dataHelper
     * @return
     */
    public IndexBar setDataHelper(IIndexBarDataHelper dataHelper) {
        mDataHelper = dataHelper;
        return this;
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        int textSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());//默认的TextSize
        mPressedBackground = Color.BLACK;//默认按下是纯黑色
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IndexBar, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            //modify 2016 09 07 :如果引用成AndroidLib 资源都不是常量，无法使用switch case
            if (attr == R.styleable.IndexBar_indexBarTextSize) {
                textSize = typedArray.getDimensionPixelSize(attr, textSize);
            } else if (attr == R.styleable.IndexBar_indexBarPressBackground) {
                mPressedBackground = typedArray.getColor(attr, mPressedBackground);
            }
        }
        typedArray.recycle();

        initIndexDatas();


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.BLACK);

        //设置index触摸监听器
        setmOnIndexPressedListener(new onIndexPressedListener() {
            @Override
            public void onIndexPressed(int index, String text) {
                if (mPressedShowTextView != null) { //显示hintTexView
                    mPressedShowTextView.setVisibility(View.VISIBLE);
                    mPressedShowTextView.setText(text);
                }
                //滑动Rv
                if (mLayoutManager != null) {
                    int position = getPosByTag(text);
                    if (position != -1) {
                        mLayoutManager.scrollToPositionWithOffset(position, 0);
                    }
                }
            }

            @Override
            public void onMotionEventEnd() {
                //隐藏hintTextView
                if (mPressedShowTextView != null) {
                    mPressedShowTextView.setVisibility(View.GONE);
                }
            }
        });

        mDataHelper = new IndexBarDataHelperImpl();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //取出宽高的MeasureSpec  Mode 和Size
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidth = 0, measureHeight = 0;//最终测量出来的宽高

        //得到合适宽度：
        Rect indexBounds = new Rect();//存放每个绘制的index的Rect区域
        String index;//每个要绘制的index内容
        for (int i = 0; i < mIndexDatas.size(); i++) {
            index = mIndexDatas.get(i);
            mPaint.getTextBounds(index, 0, index.length(), indexBounds);//测量计算文字所在矩形，可以得到宽高
            measureWidth = Math.max(indexBounds.width(), measureWidth);//循环结束后，得到index的最大宽度
            measureHeight = Math.max(indexBounds.height(), measureHeight);//循环结束后，得到index的最大高度，然后*size
        }
        measureHeight *= mIndexDatas.size();
        switch (wMode) {
            case MeasureSpec.EXACTLY:
                measureWidth = wSize;
                break;
            case MeasureSpec.AT_MOST:
                measureWidth = Math.min(measureWidth, wSize);//wSize此时是父控件能给子View分配的最大空间
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        //得到合适的高度：
        switch (hMode) {
            case MeasureSpec.EXACTLY:
                measureHeight = hSize;
                break;
            case MeasureSpec.AT_MOST:
                measureHeight = Math.min(measureHeight, hSize);//wSize此时是父控件能给子View分配的最大空间
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        setMeasuredDimension(measureWidth, measureHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int t = getPaddingTop();//top的基准点(支持padding)
        String index;//每个要绘制的index内容
        for (int i = 0; i < mIndexDatas.size(); i++) {
            index = mIndexDatas.get(i);
            if(mSourceDatas != null && mSourceDatas.size() > 0){
                for(BaseIndexPinyinBean bean : mSourceDatas){
                    if(bean.getBaseIndexTag().equals(index)){//匹配到，说明有效，设黑色、添加入Set
                        mClickableIndexs.add(index);
                        mPaint.setColor(Color.BLACK);
                        break;
                    }else{//匹配不到，就灰色
                        mPaint.setColor(Color.GRAY);
                    }
                }
            }
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();//获得画笔的FontMetrics，用来计算baseLine。因为drawText的y坐标，代表的是绘制的文字的baseLine的位置
            int baseline = (int) ((mGapHeight - fontMetrics.bottom - fontMetrics.top) / 2);//计算出在每格index区域，竖直居中的baseLine值
            //搞不懂你这baseline是个什么算法，反正我用baseline = mGapHeight*3/4也一样可以实现差不多的效果。。。算了。
            canvas.drawText(index, mWidth / 2 - mPaint.measureText(index) / 2, t + mGapHeight * i + baseline, mPaint);//调用drawText，居中显示绘制index
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                setBackgroundColor(mPressedBackground);//手指按下时背景变色
                //注意这里没有break，因为down时，也要计算落点 回调监听器
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                //通过计算判断落点在哪个区域：
                int pressI = (int) ((y - getPaddingTop()) / mGapHeight);
                //边界处理（在手指move时，有可能已经移出边界，防止越界）
                if (pressI < 0) {
                    pressI = 0;
                } else if (pressI >= mIndexDatas.size()) {
                    pressI = mIndexDatas.size() - 1;
                }
                //回调监听器
                if (null != mOnIndexPressedListener && pressI > -1 && pressI < mIndexDatas.size()) {
                    if(!mClickableIndexs.contains(mIndexDatas.get(pressI))){//如果不属于有效索引（灰色），那就什么都不做
                        return true;
                    }
                    mOnIndexPressedListener.onIndexPressed(pressI, mIndexDatas.get(pressI));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            default:
//                setBackgroundResource(android.R.color.transparent);//手指抬起时背景恢复透明
                //回调监听器
                if (null != mOnIndexPressedListener) {
                    mOnIndexPressedListener.onMotionEventEnd();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        //add by zhangxutong 2016 09 08 :解决源数据为空 或者size为0的情况,
        if (null == mIndexDatas || mIndexDatas.isEmpty()) {
            return;
        }
        computeGapHeight();
    }

    /**
     * 当前被按下的index的监听器
     */
    public interface onIndexPressedListener {
        void onIndexPressed(int index, String text);//当某个Index被按下

        void onMotionEventEnd();//当触摸事件结束（UP CANCEL）
    }

    private onIndexPressedListener mOnIndexPressedListener;

    public onIndexPressedListener getmOnIndexPressedListener() {
        return mOnIndexPressedListener;
    }

    public void setmOnIndexPressedListener(onIndexPressedListener mOnIndexPressedListener) {
        this.mOnIndexPressedListener = mOnIndexPressedListener;
    }

    /**
     * 显示当前被按下的index的TextView
     *
     * @return
     */

    public IndexBar setmPressedShowTextView(TextView mPressedShowTextView) {
        this.mPressedShowTextView = mPressedShowTextView;
        return this;
    }

    public IndexBar setmLayoutManager(LinearLayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
        return this;
    }

    /**
     * 一定要在设置数据源{@link #setmSourceDatas(List)}之前调用
     *
     * @param needRealIndex
     * @return
     */
    public IndexBar setNeedRealIndex(boolean needRealIndex) {
        isNeedRealIndex = needRealIndex;
        initIndexDatas();
        return this;
    }

    /**
     * 添加自定义比较器（用于排序）
     *
     * 食用方法：
     * indexbar.setCustomComparator(new IndexBar.CustomComparator() {
            @Override //产品对比，如果是索引相同，那就用产品编号来排序（升序），否则用拼音排序
            public int compare(BaseIndexPinyinBean lhs, BaseIndexPinyinBean rhs) {
                    if(lhs.getBaseIndexTag().compareTo(rhs.getBaseIndexTag()) == 0){
                    return ((ProductEntity) lhs).getItemId().compareTo(((ProductEntity) rhs).getItemId());
                    }else{
                    return lhs.getBaseIndexPinyin().compareTo(rhs.getBaseIndexPinyin());
                    }
            }
            });//设置RecyclerView的LayoutManager
     * @param comparator
     */
    public IndexBar setCustomComparator(CustomComparator comparator){
        mDataHelper.setComparator(comparator);
        return this;
    }

    private void initIndexDatas() {
        if (isNeedRealIndex) {
            mIndexDatas = new ArrayList<>();
        } else {
            mIndexDatas = Arrays.asList(INDEX_STRING);
        }
    }

    public IndexBar setmSourceDatas(List<? extends BaseIndexPinyinBean> mSourceDatas) {
        this.mSourceDatas = mSourceDatas;
        initSourceDatas();//对数据源进行初始化
        return this;
    }


    /**
     * 初始化原始数据源，并取出索引数据源
     *
     * @return
     */
    private void initSourceDatas() {
        //add by zhangxutong 2016 09 08 :解决源数据为空 或者size为0的情况,
        if (null == mSourceDatas || mSourceDatas.isEmpty()) {
            return;
        }
        if (!isSourceDatasAlreadySorted) {
            //排序sourceDatas
            mDataHelper.sortSourceDatas(mSourceDatas);
        } else {
            //汉语->拼音
            mDataHelper.convert(mSourceDatas);
            //拼音->tag
            mDataHelper.fillInexTag(mSourceDatas);
        }
        if (isNeedRealIndex) {
            mDataHelper.getSortedIndexDatas(mSourceDatas, mIndexDatas);
            computeGapHeight();
        }
        //sortData();
    }

    /**
     * 以下情况调用：
     * 1 在数据源改变
     * 2 控件size改变时
     * 计算gapHeight
     */
    private void computeGapHeight() {
        mGapHeight = (mHeight - getPaddingTop() - getPaddingBottom()) / mIndexDatas.size();
    }

    /**
     * 对数据源排序
     */
    private void sortData() {

    }


    /**
     * 根据传入的pos返回tag
     *
     * @param tag
     * @return
     */
    private int getPosByTag(String tag) {
        //add by zhangxutong 2016 09 08 :解决源数据为空 或者size为0的情况,
        if (null == mSourceDatas || mSourceDatas.isEmpty()) {
            return -1;
        }
        if (TextUtils.isEmpty(tag)) {
            return -1;
        }
        for (int i = 0; i < mSourceDatas.size(); i++) {
            if (tag.equals(mSourceDatas.get(i).getBaseIndexTag())) {
                return i + getHeaderViewCount();
            }
        }
        return -1;
    }

}
