package madroid.listapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import madroid.listapp.MainActivity.Listitems;

import java.util.List;

import madroid.listapp.R;

/**
 * Created by madhusudhan on 10/5/16.
 */
public class CustomListAdopter extends BaseAdapter {

    LayoutInflater inflater;
    List<Listitems> items;
    Context cntx;

    public CustomListAdopter(Context cntx,Activity ctx, List<Listitems> items) {
        super();
        this.inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.cntx = cntx;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        Listitems items =(Listitems) this.getItem(position);

        if(convertView==null)
            vi = inflater.inflate(R.layout.child_view, null);
        ImageView img= (ImageView) vi.findViewById(R.id.imagename);
        TextView txt = (TextView) vi.findViewById(R.id.title);
        TextView description = (TextView) vi.findViewById(R.id.description);
        // Loader image - will be shown before loading image
        int loader = R.drawable.loader;

        // Imageview to show
        ImageView image = img;

        // Image url
        String image_url = "http://api.androidhive.info/images/sample.jpg";

        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(cntx);

        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView
        imgLoader.DisplayImage(image_url, loader, image);
        txt.setText(items.title);
        description.setText(items.description);


        return vi;
    }
}
