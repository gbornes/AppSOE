package com.example.usuario.appsoe;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Usuario on 11/6/2018.
 */

public class HelpSliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public HelpSliderAdapter(Context context){

        this.context = context;

    }

    //Arrays
    public int[] slide_images = {

            R.drawable.img_help_yes,
            R.drawable.img_help_no,
            R.drawable.img_help_return,
            R.drawable.img_help_compdatos,
    };

    public String[] slide_headings = {

            "SÍ",
            "NO",
            "ANTERIOR",
            "DATOS"
    };

    public String[] slide_descs = {
            "Presiona este boton si tu respuesta es Afirmativa",
            "Presiona este boton si tu respuesta es Negativa",
            "Presiona este boton para Regresar a la pregunta anterior",
            "   Presiona este boton para cargar tus datos personales,      o al finalizar el Test haz click en 'Dejanos tus datos!'"
    };




    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_help_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading =  (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);

    }


}
