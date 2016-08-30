package com.lifeistech.android.oekaki;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    Canvas mCanvas;

    Paint mPaint;
    Path mPath;
    Bitmap mBitmap;
    ImageView mimageview;
    float x1,y1;
    int width,height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mimageview = (ImageView)findViewById(R.id.imageView);
        mimageview.setOnTouchListener(this);
        mPaint = new Paint();
        mPaint.setStrokeWidth(5.0f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mBitmap == null){
            width = mimageview.getWidth();
            height = mimageview.getHeight();

            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);

            mCanvas.drawColor(Color.WHITE);

            mimageview.setImageBitmap(mBitmap);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public boolean onTouch (View v,MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x,y);
                break;

            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_UP:
                mPath.quadTo(x1,y1,x,y);
                mCanvas.drawPath(mPath,mPaint);
                break;
        }
        x1 = x;
        y1 = y;
        mimageview.setImageBitmap(mBitmap);
        return true;
    }
}
