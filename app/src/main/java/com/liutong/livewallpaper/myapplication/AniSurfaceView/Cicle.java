package com.liutong.livewallpaper.myapplication.AniSurfaceView;

import android.graphics.Color;

/**
 * Created by win10 on 2017/9/5.
 */

public class Cicle {

    private int posX;
    private int posY;
    private int radius;
    private int color = Color.RED;
    private float moveDegree = 0;
    private float moveGap = 10;
    private int amplitude = 80;

    public Cicle(int posX, int posY, int radius) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
    }

    public Cicle(int posX, int posY, int radius, int color) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.color = color;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getMoveDegree() {
        return moveDegree;
    }

    public void setMoveDegree(float moveDegree) {
        this.moveDegree = moveDegree;
    }

    public float getMoveGap() {
        return moveGap;
    }

    public void setMoveGap(float moveGap) {
        this.moveGap = moveGap;
    }

    public void moveToNextDegree(){
        float nextMoveDegree = getMoveDegree() + getMoveGap();
        if(nextMoveDegree > 360) {
            setMoveDegree(nextMoveDegree % 360);
        }else {
            setMoveDegree(nextMoveDegree);
        }
    }

    public float getNextPosX()  {
           return (float) (getPosX() + Math.sin(getMoveDegree()/180*Math.PI)*amplitude);
    }

    public float getNextPosY() {
        return (float) (getPosY() + Math.sin(getMoveDegree()/180*Math.PI)*amplitude);
    }
}
