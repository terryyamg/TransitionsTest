package com.terryyamg.transitionstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewGroup transitionsContainer;
    private Scene scene1, scene2;
    private Button btnShowBottomText, btnSlide;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        scene1 = Scene.getSceneForLayout(transitionsContainer, R.layout.activity_main, this); //初始畫面
        scene2 = Scene.getSceneForLayout(transitionsContainer, R.layout.scene2, this); //第二畫面
        setting();
    }

    private void setting() {
        btnShowBottomText = (Button) transitionsContainer.findViewById(R.id.btnShowBottomText);
        tv1 = (TextView) transitionsContainer.findViewById(R.id.tv1);
        btnSlide = (Button) transitionsContainer.findViewById(R.id.btnSlide);
    }

    public void showBottomText(View view) {
        Log.i("show", "showBottomText");
        TransitionManager.beginDelayedTransition(transitionsContainer); //預設 new Fade()
        toggleVisibility(tv1);
    }

    public void slide(View view) {
        Log.i("show", "slide");
        TransitionManager.beginDelayedTransition(transitionsContainer, new Slide()); //滑動 ex:Slide(Gravity.RIGHT)..
        toggleVisibility(btnShowBottomText);
    }

    public void explode(View view) {
        Log.i("show", "explode");
        TransitionManager.beginDelayedTransition(transitionsContainer, new Explode()); //爆炸 隨意方向
        toggleVisibility(btnShowBottomText, btnSlide);
    }

    public void goToScene1(View view) {
        TransitionManager.go(scene1); //回到初始頁面
        setting();
    }

    public void goToScene2(View view) {
        TransitionManager.go(scene2); //前往第二頁面
        setting();
    }

    private static void toggleVisibility(View... views) {
        for (View view : views) {
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.GONE : View.VISIBLE); //隱藏或顯示
        }
    }

}
