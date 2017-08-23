package mallcollection.joinearn.com.mymall.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mallcollection.joinearn.com.mymall.R;
import mallcollection.joinearn.com.mymall.model.bean.Token;
import mallcollection.joinearn.com.mymall.utils.MyImageCache;

/**
 * Created by Tianpeng on 2017-08-17.
 */

public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.TokenHolder>{
    private List<Token> mData;
    private Context mContext;
    private MyClickListener mClickListener;
    public TokenAdapter(Context context){
        mContext = context;
    }

    public void setData(List<Token> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(MyClickListener listener){
        mClickListener = listener;
    }

    @Override
    public TokenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TokenHolder holder = new TokenHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_token_item, parent, false), mClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(TokenHolder holder, int position) {
        holder.itemImg.setTag(mData.get(position).getItem_img());

        MyImageCache.IMAGE_CACHE.get(mData.get(position).getItem_img(), holder.itemImg);
        holder.typeImg.setImageResource("天猫".equals(mData.get(position).getShop_type()) ? R.drawable.tmall_icon : R.drawable.taobao_icon);
        holder.shopName.setText(mData.get(position).getShop_name());
        holder.discount.setText(mData.get(position).getDiscount_content());
        holder.itemName.setText(mData.get(position).getItem_name());
        holder.price.setText("¥"+mData.get(position).getItem_price());
        holder.saleCount.setText("月销"+mData.get(position).getItem_sale_count());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class TokenHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView shopName;
        private TextView discount;
        private ImageView itemImg;
        private ImageView typeImg;
        private TextView itemName;
        private TextView price;
        private TextView saleCount;
        private MyClickListener mListener;
        public TokenHolder(View itemView, MyClickListener listener) {
            super(itemView);
            mListener = listener;
            itemImg = (ImageView)itemView.findViewById(R.id.token_item_img);
            typeImg = (ImageView)itemView.findViewById(R.id.token_img_tmall_taobao);
            shopName = (TextView)itemView.findViewById(R.id.token_shop_name);
            discount = (TextView)itemView.findViewById(R.id.token_item_discount);
            price = (TextView)itemView.findViewById(R.id.token_item_price);
            saleCount = (TextView)itemView.findViewById(R.id.token_sale_count);
            itemName = (TextView)itemView.findViewById(R.id.token_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null){
                mListener.onClick(view, getPosition());
            }
        }
    }

    public interface MyClickListener {
        public void onClick(View view, int index);
    }
}
