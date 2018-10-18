package com.example.usuario.appsoe;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Help extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout nDotLayout;

    private TextView[] nDots;

    private HelpSliderAdapter helpSliderAdapter;

    private Button nNextBtn;
    private Button nPrevBtn;

    private int nCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        nDotLayout = (LinearLayout) findViewById(R.id.dotslayout);

        nNextBtn =(Button) findViewById(R.id.nextBtn);
        nPrevBtn = (Button)findViewById(R.id.prevBtn);

        helpSliderAdapter = new HelpSliderAdapter(this);

        mSlideViewPager.setAdapter(helpSliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        //OnclickListeners (para cuando hago click en "atras" o "Siguiente"
        nNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nCurrentPage == 3){
                    finish();
                } else{
                    mSlideViewPager.setCurrentItem(nCurrentPage +1);
                }
            }
        });

        nPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(nCurrentPage -1);
            }
        });
    }



    public void addDotsIndicator(int position){

        nDots = new TextView[4];
        nDotLayout.removeAllViews();

        for (int i = 0; i < nDots.length; i++){
            nDots[i] = new TextView(this);
            nDots[i].setText(Html.fromHtml("&#8226;"));
            nDots[i].setTextSize(35);
            nDots[i].setTextColor(getResources().getColor(R.color.ColorPrimaryTransparent));

            nDotLayout.addView(nDots[i]);
        }

        if (nDots.length > 0){
            nDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);

            nCurrentPage = position;

            if (position == 0){
                nNextBtn.setEnabled(true);
                nPrevBtn.setEnabled(false);
                nPrevBtn.setVisibility(View.INVISIBLE);

                nNextBtn.setText("Siguiente");
                nPrevBtn.setText("");
            } else if (position == nDots.length -1){

                nNextBtn.setEnabled(true);
                nPrevBtn.setEnabled(true);
                nPrevBtn.setVisibility(View.VISIBLE);

                nNextBtn.setText("Ok");
                nPrevBtn.setText("Atras");
            } else {

                nNextBtn.setEnabled(true);
                nPrevBtn.setEnabled(true);
                nPrevBtn.setVisibility(View.VISIBLE);

                nNextBtn.setText("Siguiente");
                nPrevBtn.setText("Atras");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    };


    public void Exit (View view){
        finish();
    }
}
