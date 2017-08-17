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
import mallcollection.joinearn.com.mymall.model.bean.Brand;
import mallcollection.joinearn.com.mymall.utils.MyImageCache;

/**
 * Created by Tianpeng on 2017-08-17.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandHolder>{
    private List<Brand> mData;
    private Context mContext;
    private MyClickListener mClickListener;
    public BrandAdapter(Context context){
        mContext = context;
    }

    public void setData(List<Brand> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(MyClickListener listener){
        mClickListener = listener;
    }

    @Override
    public BrandAdapter.BrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BrandHolder holder = new BrandHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_brand_item, parent, false), mClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(BrandAdapter.BrandHolder holder, int position) {
        holder.img.setTag(mData.get(position).getIcon());
        MyImageCache.IMAGE_CACHE.get("http://www.jilun.net/"+mData.get(position).getIcon(), holder.img);
        holder.name.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class BrandHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView name;
        private MyClickListener mListener;
        public BrandHolder(View itemView, MyClickListener listener) {
            super(itemView);
            mListener = listener;
            img = (ImageView)itemView.findViewById(R.id.brand_item_image);
            name = (TextView)itemView.findViewById(R.id.brand_item_name);
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
