package com.barangol.home.doodlz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public  class MyRect  {
    private Paint paint;
    private Rect rect;
    private int x1,y1,x2,y2,sizex,sizey;
    private static final int border = 3;
    private Color color;
    private int x,y; // координаты в сетке
    private Canvas canvas;


    public void setColor(Color color) {
        this.color = color;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void moveRect(int x, int y){
        x1 = x-sizex/2;
        y1 = y-sizey/2;
        x2 = x + sizex/2;
        y2 = y + sizey/2;;

        onDraw(canvas,rect,paint);

    }


    public MyRect(Context context, Canvas canvas, int PosX, int PosY, int sizex, int sizey) {
        //super(context);
        rect = new Rect();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setStyle(Paint.Style.FILL);
        this.canvas = canvas;
        this.sizex = sizex;
        this.sizey = sizey;
        x1 = PosX+border;
        y1 = PosY+border;
        x2 = PosX + sizex - border;
        y2 = PosY + sizey - border;
       // this.color = color;


        onDraw(canvas,rect,paint);


      //  super(context);
    }





    public void onDraw(Canvas canvas, Rect rect, Paint paint) {
        rect.set(x1,y1,x2,y2);
        canvas.drawRect(rect,paint);
        // super.onDraw(canvas);
    }


}