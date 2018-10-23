package com.barangol.home.doodlz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.barangol.home.doodlz.MyRect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DoubleView extends View {

    private static final float TOUCH_TOLERANCE = 10;

    private Bitmap bitmap; // Область рисования для вывода или сохранения
    private Canvas bitmapCanvas; // Используется для рисования на Bitmap
    private final Paint paintScreen; // Используется для вывода Bitmap на экран
    private final Paint paintLine; // Используется для рисования линий на Bitmap
    private final Map<Integer, Path> pathMap = new HashMap<>();
    private final Map<Integer, Point> previousPointMap = new HashMap<>();
    private static final int fieldSizeX = 10;
    private static final int fieldSizeY = 15;
    private static final int boarder = 10;
    private static final int lineThin =1;
    private MyRect[][] field;
    private Integer sizeX;
    private Integer sizeY;
    private Integer columnSize,rowSize;
    private Path path = new Path();
    private Integer x=0,y=0;
    private Integer posInFieldX=0,posInFieldY=0;
    MyRect myRect;

    public DoubleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintScreen = new Paint();
        paintLine = new Paint();
        paintLine.setAntiAlias(true); // Сглаживание краев
        paintLine.setColor(Color.BLACK); // По умолчанию черный цвет
        paintLine.setStyle(Paint.Style.STROKE); // Сплошная линия
        paintLine.setStrokeWidth(5); // Толщина линии по умолчанию
        paintLine.setStrokeCap(Paint.Cap.ROUND); // Закругленные концы
        field = new MyRect[fieldSizeX][fieldSizeY];

       // Log.d("Mylog", sizeX.toString());
    //int[] a[] = new int[1][1];
        String s = 2 + "dfdf" +2;
        ///s = s;
        ArrayList<String > strings = new ArrayList<String>();
        strings.ensureCapacity(20);
        PersonComparator pcomp = new PersonComparator();
        TreeSet<Person> people = new TreeSet<Person>(pcomp);
        people.add(new Person("Tom"));



    }
//    private int sumAll(int... values){

    public void drowField(){
         columnSize = (sizeX-boarder*2)/fieldSizeX;
        rowSize = (sizeY-boarder*2)/fieldSizeY;
        Log.d("Mylog",  rowSize.toString()+" "+columnSize.toString());
        path.reset();
        path.moveTo(boarder,boarder);
        path.lineTo(sizeX-boarder,boarder);
        path.lineTo(sizeX-boarder,sizeY-boarder);
        path.lineTo(boarder,sizeY-boarder);
        path.lineTo(boarder,boarder);
      for(int i=1;i<fieldSizeX;i++) {
           path.moveTo(columnSize*i + boarder, boarder);
           path.lineTo(columnSize*i + boarder, sizeY - boarder);
       }
       for(int i=1;i<fieldSizeY;i++){
           path.moveTo(boarder,rowSize*i +boarder);
           path.lineTo(sizeY-boarder, rowSize*i+boarder);
       }
        path.close();
        bitmapCanvas.drawPath(path,paintLine);
        Paint p = new Paint();

        p.setStrokeWidth(lineThin);
        p.setTextSize(100);
    //    bitmapCanvas.drawText("dfsdfsdf", 150, 150,p);



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(),Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmap.eraseColor(Color.WHITE); // Bitmap стирается белым цветом
        sizeX = getWidth();
        sizeY = getHeight();
        Log.d("Mylog", sizeY.toString());
        drowField();
        drowPosition();


     //   field[1][1] = new MyRect(getContext(),bitmapCanvas,100,100,200);

    }
    public void clear(){
        Path path;
        Rect rect = new Rect();
        bitmap.eraseColor(Color.WHITE);

   /*     rect.set(100,100,200,200);
        paintLine.setStyle(Paint.Style.FILL);
        pathMap.clear();
        previousPointMap.clear();
        bitmap.eraseColor(Color.WHITE);
        MyRect myRect = new MyRect(getContext(),bitmapCanvas,100,100,100);

        int[][] nums = new int[][]
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };

      //  myRect.onDraw(bitmapCanvas,rect,paintLine);
      //  bitmapCanvas.drawRect(rect,paintLine);
*/
        drowField();
        invalidate();
        }
     public void drowPosition (){
      //  Integer posx = (int) x;
     //   Integer posy = (int) y;
        Paint p = new Paint();

         p.setStrokeWidth(3);
         p.setTextSize(50);
         p.setAlpha(200);
         String s = x.toString()+ "  "+y.toString()+ " " + posInFieldX.toString()+ " "+ posInFieldY.toString();
         bitmapCanvas.drawText(s, 50, sizeY-50,p);

     }


    public void setDrowingColor(int color){
        paintLine.setColor(color);
    }

    public int  getDrowingColor(){
        return paintLine.getColor();
    }
    public void setLineWidth(int width){
        paintLine.setStrokeWidth(width);
    }
    public int getLineWidht(){
        return (int) paintLine.getStrokeWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paintScreen);
        for(Integer key: pathMap.keySet()){
            canvas.drawPath(pathMap.get(key),paintLine);
        }

    }
    public int getPosInFieldX(int x){
       //  columnSize = (sizeX-boarder*2)/fieldSizeX;
        return Math.round(x/columnSize);

    }
    public int getPosInFieldY(int y){
     //    rowSize = (sizeY-boarder*2)/fieldSizeY;
        return Math.round(y/rowSize);

    }
    public void addRect(){
         myRect = new MyRect(getContext(),bitmapCanvas,(fieldSizeX-1)*columnSize+boarder,(fieldSizeY-1)*rowSize+boarder,columnSize,rowSize);
    }
    public void seToPlace(int posx,int posy){


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
       x = Math.round(event.getX());
       y = Math.round(event.getX());
       posInFieldX = getPosInFieldX(x);
       posInFieldY = getPosInFieldY(y);
    //   myRect.moveRect();

      //double d=0.0;
      Log.d("My",x.toString());




        clear();
        drowField();
        drowPosition();
        myRect.moveRect(Math.round(event.getX()),Math.round(event.getY()));
      //  clear();
    //    Toast.makeText(getContext(), "touch", Toast.LENGTH_SHORT).show();

        //return super.onTouchEvent(event);
  /*      int action = event.getActionMasked();
        int actionIndex = event.getActionIndex();
        if( action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN){
            touchStarted(event.getX(actionIndex),event.getY(actionIndex),event.getPointerId(actionIndex));
        }
        else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP){
           touchEnded(event.getPointerId(actionIndex));

        }
        else {
            touchMoved(event);
        }
        invalidate();
      //  Log.d("MyLog", "touch");

    */
        return true;
    }
private void touchStarted(float x, float y, int lineID){
        Path path;
        Point point;
        if (pathMap.containsKey(lineID)){
            path = pathMap.get(lineID);
            path.reset();
            point = previousPointMap.get(lineID);

        }
        else{
            path = new Path();
            pathMap.put(lineID,path);
            point = new Point();
            previousPointMap.put(lineID,point);
        }
        path.moveTo(x,y);
        point.x = (int)x;
        point.y = (int)y;

}
    private void touchMoved(MotionEvent event) {
        // for each of the pointers in the given MotionEvent
        for (int i = 0; i < event.getPointerCount(); i++) {
            // get the pointer ID and pointer index
            int pointerID = event.getPointerId(i);
            int pointerIndex = event.findPointerIndex(pointerID);

            // if there is a path associated with the pointer
            if (pathMap.containsKey(pointerID)) {
                // get the new coordinates for the pointer
                float newX = event.getX(pointerIndex);
                float newY = event.getY(pointerIndex);

                // get the path and previous point associated with
                // this pointer
                Path path = pathMap.get(pointerID);
                Point point = previousPointMap.get(pointerID);

                // calculate how far the user moved from the last update
                float deltaX = Math.abs(newX - point.x);
                float deltaY = Math.abs(newY - point.y);

                // if the distance is significant enough to matter
                if (deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE) {
                    // move the path to the new location
                    path.quadTo(point.x, point.y, (newX + point.x) / 2,
                            (newY + point.y) / 2);

                    // store the new coordinates
                    point.x = (int) newX;
                    point.y = (int) newY;
                }
            }
        }
    }
    private void touchEnded(int lineID) {
        Path path = pathMap.get(lineID); // get the corresponding Path
        bitmapCanvas.drawPath(path, paintLine); // draw to bitmapCanvas
        path.reset(); // reset the Path
    }



}


