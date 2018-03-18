package com.example.nan.a4;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TriangleView triangleView = new TriangleView(this);
        TriangleModel triangleModel = new TriangleModel();
        TriangleViewController controller = new TriangleViewController();

        controller.setModel(triangleModel);
        controller.setView(triangleView);

        triangleView.setModel(triangleModel);
        triangleView.setController(controller);

        triangleModel.addSubscriber(triangleView);

        triangleView.setOnTouchListener(controller);
        setContentView(R.layout.activity_main);

        LinearLayout layout = findViewById(R.id.bigView);
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;

        layout.addView(triangleView,new LinearLayout.LayoutParams(width,height));

    }
}
