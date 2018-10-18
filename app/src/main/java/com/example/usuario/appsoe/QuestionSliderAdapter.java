package com.example.usuario.appsoe;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Usuario on 12/6/2018.
 */

public class QuestionSliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public QuestionSliderAdapter (Context context){

        this.context = context;

    }

    //Arrays
    public int[] slide_image_question = {

            R.drawable.img_question_1,
            R.drawable.img_question_2,
            R.drawable.img_question_3,
            R.drawable.img_question_4,
            R.drawable.img_question_5,
            R.drawable.img_question_6,
            R.drawable.img_question_7,
            R.drawable.img_question_8,
            R.drawable.img_question_9,
            R.drawable.img_question_10,
            R.drawable.img_question_11,
            R.drawable.img_question_12,
            R.drawable.img_question_13,
            R.drawable.img_question_14,
            R.drawable.img_question_15
    };

    public String[] slide_txt_question = {

            "¿Te gustan las computadoras?",
            "¿Pasas mucho tiempo frente    a tu PC/Notebook?",
            "¿Te gustaría programar para una empresa como Microsoft?",
            "¿Te gustaría diseñar y armar instalaciones que utilizan sistemas eléctricos?",
            "¿Te gustaría contribuir a un  mejor rendimiento eléctrico   de una empresa?",
            "¿Te gustaría trabajar en plantas industriales?",
            "¿Te gustaría conocer los   procesos para la producción   de bienes industrializados?",
            "¿Te gustaría trabajar en la construcción de grandes edificios?",
            "¿Te gustaría planificar carreteras, ferrocarriles, puentes, presas, puertos?",
            "¿Te gustaría trabajar en una planta de hidrocarburos?",
            "¿Te gustaría saber convertir materias primas a través de   la aplicación de la química?",
            "¿Te gustaría trabajar en una planta creando vehículos motorizados?",
            "¿Te gustaría saber como funciona un escalera rodante, un ascensor o un montacarga?",
            "¿Te gustan las Matemáticas?",
            "¿Sos una persona que tratas    de buscar una solución en forma creativa?",
    };

    //SET PROPUESTO POR ANTO
    //1) Sistemas     1.1 ¿Te gustan las computadoras?  //5ptos SISTEMAS, 2ptos ELECTRICA; 0 todas las demas
    //2) Sistemas     1.2 ¿Pasas mucho tiempo frente a tu PC/Notebook?   //6 ptos SISTEMAS, 0pts todas las demas
    //3) Sistemas     1.3 ¿Te gustaría programar un robot para una empresa como Microsoft?
    //4) Eléctrica    2.1 ¿Te gustaría diseñar y armar instalaciones que utilizan sistemas eléctricos?
    //5) Eléctrica    2.2 ¿Te gustaría contribuir a un mejor rendimiento eléctrico de una empresa?
    //6) Industrial   3.1 ¿Te gustaría trabajar en plantas industriales? (o en plantas donde se transformen los de recursos naturales en bienes industrializados y servicios?)
    //7) Industrial   3.2 ¿Te gustaría saber cómo son los procesos para la producción de bienes industrializados en una planta?
    //8) Civil        4.1 ¿Te gustaría trabajar en la construcción de grandes edificios?
    //9) Civil        4.2 ¿Te gustaría planificar carreteras, ferrocarriles, puentes, canales, presas, puertos, aeropuertos, diques?
    //10) Química     5.1 ¿Te gustaría trabajar en una planta de hidrocarburos?
    //11) Química     5.2 ¿Te gustaría saber como convertir materias primas o productos químicos a través de la aplicación de la química, la física, la biología y la matemática?
    //12) Mecánica    6.1 ¿Te gustaría trabajar en una planta creando vehículosmotorizados (terrestres, aéreos y marítimos)?
    //13) Mecánica    6.2 ¿Te gustaría saber como funciona un escalera rodante, un ascensor o un montacarga?
    //14) (comodines) 7 ¿Te gustan las Matemáticas?
    //15) (comodines) 8 ¿Sos una persona que tratas de buscar una solución en forma creativa?

    // El algoritmo es asi: selecciona en el primer camino 1.1 1.2 salta a los comodines si selecciono
    // todo si directo pasa sistemas. En casi de que la primera pregunta no de sistemas pasa a la
    //siguiente especialidad si da que no sigue a la siguiente y se repite. El comodin sirve para
    //cualquier especialidad

    // SET ORIGINAL
    //"Cuando vas a tomar algo...",
    //        "¿Te gustaría diseñar y producir máquinas para grandes fábricas?",
    //        "¿Te gustaría perfeccionar maquinarias de producción?",
    //        "¿Te gustaría evaluar y operar procesos de producción?",
    //        "¿En el colegio te gustaba la materia química?",
    //        "¿Te gustaría desarrollar maquinaria para grandes empresas?",
    //        "¿Te gustan las Matemáticas?",
    //        "¿Te consideras una persona práctica?",
    //        "¿Te gustaría realizar productos o servicios que mejoren la calidad de vida de las personas?",
    //        "¿Te atrae la idea de trabajar desarrollando sistemas de comunicación?",
    //        "¿Podes distinguir de qué trata esta imagen?",
    //        "¿Te gustaría saber cómo funcionan internamente las máquinas?",
    //        "¿Te gustaría contribuir a un mejor rendimiento de una empresa?",
    //        "¿Sos una persona creativa?",
    //        "¿Te gustaría diseñar sistemas eléctricos?",
    //        "¿Te gustaría programar en distintos lenguajes de programación?",
    //        "¿Te gustaría trabajar en una planta de producción de automóviles?",
    //        "¿Te gustaría trabajar para la industria petroquímica?",
    //        "¿Tenes liderazgo al trabajar en grupo?",
    //        "¿Te gustaría contribuir al uso eficiente de la energía eléctrica para el desarrollo industrial?",
    //        "¿Consideras que las energías renovables son el futuro?",
    //        "¿Quieres aprender los fundamentos físicos del electromagnetismo?"

    @Override
    public int getCount() {
        return slide_txt_question.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_questions, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image_question);
        TextView slideHeading =  (TextView) view.findViewById(R.id.slide_txt_question);

        slideImageView.setImageResource(slide_image_question[position]);
        slideHeading.setText(slide_txt_question[position]);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);

    }


}