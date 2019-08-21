package com.example.reborn30.maptecs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

//esta clase hace el pequeno slideshow de imagenes para la escuela
public class ImageAdapter extends PagerAdapter {
    private Context _context;
    private int[] mImageIds = new int[]{R.drawable.home,R.drawable.corridor1,R.drawable.corridor2,R.drawable.explanada};
    ImageAdapter(Context context){
        _context = context;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
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
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      //  super.destroyItem(container, position, object);
    container.removeView((ImageView)object);
    }

}
