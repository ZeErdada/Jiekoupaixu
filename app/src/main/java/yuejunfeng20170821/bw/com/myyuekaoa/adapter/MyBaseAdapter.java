package yuejunfeng20170821.bw.com.myyuekaoa.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import yuejunfeng20170821.bw.com.myyuekaoa.R;
import yuejunfeng20170821.bw.com.myyuekaoa.bean.ClassBean;

/**
 * Created by admin on 2017/8/23.
 */

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<ClassBean.ResultBean.RowsBean> list;

    public MyBaseAdapter(Context context, List<ClassBean.ResultBean.RowsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item,null);
            holder.iv= (ImageView) view.findViewById(R.id.iv_image);
            holder.tv1= (TextView) view.findViewById(R.id.tv_name);
            holder.tv2= (TextView) view.findViewById(R.id.tv_title);
            holder.tv3= (TextView) view.findViewById(R.id.tv_back);
            holder.tv4= (TextView) view.findViewById(R.id.tv_tags);
            holder.tv5= (TextView) view.findViewById(R.id.tv_type);
            holder.tv6= (TextView) view.findViewById(R.id.tv_value);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.tv1.setText(list.get(i).info.loupan_name);
        holder.tv2.setText(list.get(i).info.region_title);
        holder.tv6.setText(list.get(i).info.new_price_value);
        holder.tv3.setText(list.get(i).info.new_price_back);
        holder.tv4.setText(list.get(i).info.tags);
        holder.tv5.setText(list.get(i).info.sale_title);
        setImage(list.get(i).info.default_image,context,holder.iv);
        return view;
    }
     public static void setImage(String url , Context context,ImageView imageView){
                 ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
                 ImageLoader imageLoader = ImageLoader.getInstance();
                 imageLoader.init(config);
                 DisplayImageOptions diosplay =new DisplayImageOptions.Builder()
                         .showImageOnLoading(R.mipmap.ic_launcher)
                         .showImageForEmptyUri(R.mipmap.ic_launcher)
                         .showImageOnFail(R.mipmap.ic_launcher)
                         .cacheInMemory(true)
                         .cacheOnDisk(true)
                         .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                         .bitmapConfig(Bitmap.Config.RGB_565)
                         .build();
                 imageLoader.displayImage(url,imageView,diosplay);
             }


    class ViewHolder{
        ImageView iv;
        TextView tv1,tv2,tv3,tv4,tv5,tv6;

    }


}
