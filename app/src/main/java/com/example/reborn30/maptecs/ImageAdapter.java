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

            case "bioblioteca":
                mImageIds = new int[]{R.drawable.bibl_1,R.drawable.bio_2,R.drawable.biob_2,R.drawable.rosa_de_los_vientos};
                break;
                //salones
            case  "100":
                mImageIds = new int[]{R.drawable.s_100_1,R.drawable.s_100_2,R.drawable.s_100_3,R.drawable.rosa_de_los_vientos};

                break;
            case "200":
                mImageIds = new int[]{R.drawable.s_200_1,R.drawable.s_200_2,R.drawable.s_200_3,R.drawable.rosa_de_los_vientos};

                break;
            case "300":
                mImageIds = new int[]{R.drawable.s_300_1,R.drawable.s_300_2,R.drawable.s_300_3,R.drawable.rosa_de_los_vientos};

                break;
            case "400":
                mImageIds = new int[]{R.drawable.s_400_1,R.drawable.s_400_2,R.drawable.s_400_3,R.drawable.rosa_de_los_vientos};

                break;
            case "500":
                mImageIds = new int[]{R.drawable.s_500_1,R.drawable.s_500_2,R.drawable.s_500_3,R.drawable.rosa_de_los_vientos};

                break;
            case "600":
                mImageIds = new int[]{R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos};

                break;
            case "Q":
                mImageIds = new int[]{R.drawable.iedificioq,R.drawable.q_1,R.drawable.q_2,R.drawable.q_4};
                break;
            case "cafeteria":
                mImageIds = new int[]{R.drawable.icafeteria,R.drawable.c_2,R.drawable.c_3,R.drawable.rosa_de_los_vientos};

                break;
            case "sc":
                mImageIds = new int[]{R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos};

                break;
            case "ic":
                mImageIds = new int[]{R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos};

                break;
            case "bc":
                mImageIds = new int[]{R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos};

                break;
            case "mc":
                mImageIds = new int[]{R.drawable.cord_mt_1,R.drawable.cord_mt_2,R.drawable.cord_mt_3,R.drawable.rosa_de_los_vientos};

                break;
            case "bio":
                mImageIds = new int[]{R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos};

                break;
            case "teatro":
                mImageIds = new int[]{R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos};

                break;
            case "metal":
                mImageIds = new int[]{R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos};

                break;
            case "audio":
                mImageIds = new int[]{R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos,R.drawable.rosa_de_los_vientos};
                break;
            case "misc_r":
                mImageIds = new int[]{R.drawable.misc_r_1,R.drawable.misc_r_2,R.drawable.misc_r_3,R.drawable.rosa_de_los_vientos};
                break;
            case "misc_m":
                mImageIds = new int[]{R.drawable.misc_m_1,R.drawable.misc_m_2,R.drawable.misc_m_3,R.drawable.rosa_de_los_vientos};
                break;
            case "misc_p":
                mImageIds = new int[]{R.drawable.misc_p_1,R.drawable.misc_p_2,R.drawable.misc_p_3,R.drawable.rosa_de_los_vientos};
                break;
            case "misc_au":
                mImageIds = new int[]{R.drawable.misc_au_1,R.drawable.misc_au_2,R.drawable.misc_au_3,R.drawable.rosa_de_los_vientos};
                break;
            case "direccion":

                break;
            case "est_1":

                break;
            case "est_2":

                break;
            case "est_3":

                break;
            case "gym":

                break;
            case "lab_h":

                break;
            case "lab_q":
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
