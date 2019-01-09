package pro.xp.com.sidebar.sortviewpager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pro.xp.com.sidebar.R;
import pro.xp.com.sidebar.model.ProductAndSuggestOrderGoodsList;

/**
 * 订货审核产品列表适配器
 *
 * @author XiePing
 */
class GoodsAuditingAddProductsAdapter extends RecyclerView.Adapter<GoodsAuditingAddProductsAdapter.ViewHolder> {

    private List<ProductAndSuggestOrderGoodsList> list;

    GoodsAuditingAddProductsAdapter(
            List<ProductAndSuggestOrderGoodsList> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_add_chioce_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ProductAndSuggestOrderGoodsList item = list.get(position);
        if (null != item.getFixedProduct()) {
            holder.name.setText(item.getFixedProduct().getProductName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView choice;
        LinearLayout addItemRl;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.content_text);
            choice = (TextView) itemView.findViewById(R.id.content_choice);
            addItemRl = (LinearLayout) itemView.findViewById(R.id.list_item_check_ll);
        }
    }
}