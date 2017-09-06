package com.liutong.livewallpaper.myapplication.AniSurfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by win10 on 2017/9/4.
 */

public class TestSurfaceView extends SurfaceView implements Runnable,SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private boolean isRunFlag;
    private Paint paint = new Paint();
    private int minCicleNum = 10;
    private int minArmNum = 10;
    private double maxCicleRadiusRateOnwidth = 0.045;
    private double centerCicleRadiusRataOnwidth = 0.06;
    private ArrayList<Cicle> arm;
    private ArrayList<ArrayList<Cicle>> arms = new ArrayList<>();
    private int surfaceWidth;
    private int surfaceHeight;

    public TestSurfaceView(Context context) {
        this(context,null);
    }

    public TestSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder = this.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isRunFlag = true;
        surfaceWidth = getWidth();
        surfaceHeight = getHeight();
        arm = generateArm(surfaceWidth/2,surfaceHeight/2);

        generateArms(surfaceWidth/2,surfaceHeight/2,10);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            isRunFlag = false;
    }

    @Override
    public void run() {
        while(isRunFlag) {
            drawFrame();
        }
    }



    private void drawFrame() {

        Canvas canvas = null;
        canvas = holder.lockCanvas();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect(0,0,this.getWidth(),this.getHeight());
        canvas.drawRect(rect,paint);

//        画中心原点
        paint.setColor(Color.BLUE);
        paint.setAlpha(1);
        float centerCicleRadius = (float) (this.getWidth() * centerCicleRadiusRataOnwidth) ;
        RectF centerRectF = new RectF(surfaceWidth/2 - centerCicleRadius,surfaceHeight/2 - centerCicleRadius,surfaceWidth/2 +centerCicleRadius,surfaceHeight/2+centerCicleRadius);
        canvas.drawArc(centerRectF,0,360,true,paint);

        for(int i=0;i<arms.size();i++) {
            ArrayList<Cicle> arm = arms.get(i);
            for (int j=0;j<arm.size();j++){
                Cicle cicle = arm.get(j);
                paint.setColor(cicle.getColor());
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2);
                cicle.moveToNextDegree();
                int radius = cicle.getRadius();

                float left = cicle.getNextPosX()-radius;
                float top = cicle.getPosY()-radius;
                float right = cicle.getNextPosX()+radius;
                float bottom = cicle.getPosY()+radius;

                RectF rf = new RectF(left,top,right,bottom);
                canvas.drawArc(rf,(float)0,(float)360,true,paint);

                if(0 == j) {
                    paint.setColor(Color.BLUE);
                    paint.setStrokeWidth(2);
                    canvas.drawLine(surfaceWidth/2,surfaceHeight/2,left+radius,top+radius,paint);
                }

            }
        }

//        for (Cicle cicle:arm){
//            paint.setColor(cicle.getColor());
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(2);
//            cicle.moveToNextDegree();
//            int radius = cicle.getRadius();
//            int posX = cicle.getPosX();
//            int posY = cicle.getPosY();
//
//            float left = cicle.getNextPosX()-radius;
//            float top = cicle.getPosY()-radius;
//            float right = cicle.getNextPosX()+radius;
//            float bottom = cicle.getPosY()+radius;
//
//            RectF rf = new RectF(left,top,right,bottom);
////            RectF rf = new RectF(cicle.getPosX()-radius,cicle.getPosY()-radius,cicle.getPosX()+radius,cicle.getPosY()+radius);
//            canvas.drawArc(rf,(float)0,(float)360,false,paint);
//        }

        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (holder != null) {
            holder.unlockCanvasAndPost(canvas);
        }

    }

    private ArrayList<Cicle> generateArm(int basPosX,int basPosY) {
        ArrayList<Cicle> arm = new ArrayList<>();

        int cicleNum = randomWithRange(minCicleNum,minCicleNum + 2);
        int width = surfaceWidth;
        int height = surfaceHeight;
        int maxRadius = (int)(width * maxCicleRadiusRateOnwidth);
        float des = (float)90/cicleNum;

        int sum = 0;
        double desAgree = 0;

        float gapAgree = (float) 360/cicleNum;

//        颜色变化

//
        for(int i=0;i<cicleNum;i++) {

            double radian = (double)(90 -desAgree)/180 * Math.PI;
           int radius = (int)(Math.sin(radian) * maxRadius);
            int posY = basPosY + sum;

            Cicle cicle = new Cicle(basPosX,posY,radius,getRandomColor());
            cicle.setMoveDegree(i*gapAgree);

            sum +=radius*2;
            desAgree += des;
            arm.add(cicle);

        }

        return arm;
    }

    private void generateArms(int basX,int basY,int num){
//        TODO 校验arms 数量
        int armNum = (num>minArmNum)?num:randomWithRange(minArmNum,minArmNum+5);
        int radius = 380;
        double gapRadian = ((double)360/armNum)/180 * Math.PI;;
        for(int i=0;i<armNum;i++) {

            double radian = gapRadian * i;
            int x = (int)(Math.sin(radian) * radius);
            int y = (int)(Math.cos(radian) * radius);

            arms.add(generateArm(basX+x,basY+y));
        }

    }

    /*
    生成半径数组
     */
    private int[] generateRadius() {
        int maxRadius = (int)(surfaceWidth * maxCicleRadiusRateOnwidth);
        int cicleNum = randomWithRange(minCicleNum,minCicleNum + 20);
        int radius[] = new int[cicleNum];
        double des = cicleNum/90;
        for(int i=0;i<cicleNum;i++) {
            radius[i] = (int)(Math.sin(90 - des*i) * maxRadius);
        }

        return radius;
    }

    private int randomWithRange(int min,int max) {
        int range = Math.abs(max - min + 1);
        return (int)(Math.random()*range) + (min<=max ? min : max);
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
