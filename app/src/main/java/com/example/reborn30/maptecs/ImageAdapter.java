package com.example.reborn30.maptecs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.Arrays;

//esta clase hace el pequeno slideshow de imagenes para la escuela
public class ImageAdapter extends PagerAdapter {

    private Context _context;
    String _nombre;
   // private int[] mImageIds = new int[]{R.drawable.home,R.drawable.corridor1,R.drawable.corridor2,R.drawable.explanada};
    //
    private int[] _mImageIds(String nombre){
       int[] mImageIds = null;
        switch (nombre){
            case "Tomas":
                 mImageIds = new int[]{R.drawable.home,R.drawable.corridor1,R.drawable.corridor2,R.drawable.explanada};
                break;
            case "Otay":
                mImageIds = new int[]{R.drawable.otay1,R.drawable.otay2,R.drawable.otay4,R.drawable.otay};
                break;
        }
      return mImageIds;
    }
    //
    public ImageAdapter(Context context,String nombre){
        _context = context;
        _nombre = nombre;
    }

    @Override
    public int getCount() {
        return _mImageIds(_nombre).length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(_context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(_mImageIds(_nombre)[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      //  super.destroyItem(container, position, object);
    container.removeView((ImageView)object);
    }

}
