package ch.derbeton.metalldetectorapp;

/**
 * Created by DerBeton on 13.12.2017.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.*;

/**
 * Die DrawingView ist für die Darstellung und Verwaltung der Zeichenfläche
 * zuständig.
 */
public class DrawingView extends View {

    private static final int GRID_SIZE = 13;

    private Path drawPath = new Path();
    private Paint drawPaint = new Paint();
    private Paint linePaint = new Paint();
    private Paint drawRect = new Paint();
    private Paint pathPaint = new Paint();
    private boolean isErasing = false;
    private int stepSizeX;
    private int stepSizeY;


    private ArrayList<Integer> pressedX = new ArrayList<>();
    private ArrayList<Integer> pressedY = new ArrayList<>();

    private ArrayList<Integer> getdrawX = new ArrayList<>();
    private ArrayList<Integer> getdrawY = new ArrayList<>();

    private ArrayList<String> pressedColor = new ArrayList<>();
    private ArrayList<String> pxcolor = new ArrayList<>();

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);


        drawRect.setAntiAlias(true);
        drawRect.setStrokeWidth(20);
        drawRect.setStyle(Paint.Style.FILL);
        drawRect.setStrokeJoin(Paint.Join.ROUND);
        drawRect.setStrokeCap(Paint.Cap.ROUND);

        pathPaint.setAntiAlias(true);
        pathPaint.setStrokeWidth(20);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);
        pathPaint.setStrokeCap(Paint.Cap.ROUND);

        linePaint.setColor(0xFF666666);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(1.0f);
        linePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        final int maxX = canvas.getWidth();
        final int maxY = canvas.getHeight();

        stepSizeX = (int) Math.ceil((double) maxX / GRID_SIZE);
        stepSizeY = (int) Math.ceil((double) maxY / GRID_SIZE);

        // TODO Zeichne das Gitter


        // All 13 X Lines
        canvas.drawLine(0, 0, canvas.getWidth(), 0, linePaint);
        canvas.drawLine(0, stepSizeY, canvas.getWidth(), stepSizeY, linePaint);
        canvas.drawLine(0, 2*stepSizeY, canvas.getWidth(), 2*stepSizeY, linePaint);
        canvas.drawLine(0, 3*stepSizeY, canvas.getWidth(), 3*stepSizeY, linePaint);
        canvas.drawLine(0, 4*stepSizeY, canvas.getWidth(), 4*stepSizeY, linePaint);
        canvas.drawLine(0, 5*stepSizeY, canvas.getWidth(), 5*stepSizeY, linePaint);
        canvas.drawLine(0, 6*stepSizeY, canvas.getWidth(), 6*stepSizeY, linePaint);
        canvas.drawLine(0, 7*stepSizeY, canvas.getWidth(), 7*stepSizeY, linePaint);
        canvas.drawLine(0, 8*stepSizeY, canvas.getWidth(), 8*stepSizeY, linePaint);
        canvas.drawLine(0, 9*stepSizeY, canvas.getWidth(), 9*stepSizeY, linePaint);
        canvas.drawLine(0, 10*stepSizeY, canvas.getWidth(), 10*stepSizeY, linePaint);
        canvas.drawLine(0, 11*stepSizeY, canvas.getWidth(), 11*stepSizeY, linePaint);
        canvas.drawLine(0, 12*stepSizeY, canvas.getWidth(), 12*stepSizeY, linePaint);

        // All 13 X Lines
        canvas.drawLine(0, 0, 0, canvas.getHeight(), linePaint);
        canvas.drawLine(stepSizeX, 0, stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(2*stepSizeX, 0, 2*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(3*stepSizeX, 0, 3*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(4*stepSizeX, 0, 4*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(5*stepSizeX, 0, 5*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(6*stepSizeX, 0, 6*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(7*stepSizeX, 0, 7*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(8*stepSizeX, 0, 8*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(9*stepSizeX, 0, 9*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(10*stepSizeX, 0, 10*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(11*stepSizeX, 0, 11*stepSizeX, canvas.getHeight(), linePaint);
        canvas.drawLine(12*stepSizeX, 0, 12*stepSizeX, canvas.getHeight(), linePaint);


        //test ausmalen der Felder

        for (int i = 0;i<getdrawX.size();i++){



                canvas.drawRect(getdrawX.get(i) * stepSizeX, getdrawY.get(i) * stepSizeY,
                        (getdrawX.get(i) + 1) * stepSizeX, (getdrawY.get(i) + 1) * stepSizeY,
                        drawPaint);

            //drawPaint.setColor(pxcolor.get(i));
            drawPaint.setColor(Color.parseColor(pxcolor.get(i)));



        }


        // Zeichnet einen Pfad der dem Finger folgt
        canvas.drawPath(drawPath, pathPaint);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);

                // TODO wir müssen uns die berührten Punkte zwischenspeichern

                pressedX.add((int)touchX);
                pressedY.add((int)touchY);

                pressedColor.add(String.format("#%06X", (0xFFFFFF & drawPaint.getColor())));


                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);

                // TODO wir müssen uns die berührten Punkte zwischenspeichern

                    pressedX.add((int)touchX);
                    pressedY.add((int)touchY);

                    pressedColor.add(String.format("#%06X", (0xFFFFFF & drawPaint.getColor())));


                break;
            case MotionEvent.ACTION_UP:

                // TODO Jetzt können wir die zwischengespeicherten Punkte auf das
                // Gitter umrechnen und zeichnen, bzw. löschen, falls wir isErasing
                // true ist (optional)

                // 1. Ausrechnen auf welchem Feld der Finger Ling X/Y

                invalidate();

                for (int i = 0;i<pressedX.size();i++){

                    getdrawX.add(pressedX.get(i)/stepSizeX);

                    getdrawY.add(pressedY.get(i)/stepSizeY);

                    pxcolor.add(pressedColor.get(i));

                    System.out.println(pxcolor.get(i));

                    //System.out.println(pressedX.get(i));

                    //System.out.println(pressedY.get(i));

                }

                // 2. Ausmalen der Felder


                // Ausgabe der Feldnummer nur zu testzwecken
                //System.out.printf("FeldX : %d %n", feldX);
                //System.out.printf("FeldY : %d %n", feldY);


                //drawPath.moveTo(touchX, touchY);

                // Ende Mein

                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void startNew() {

        // TODO Gitter löschen

        invalidate();
    }

    public void setErase(boolean isErase) {
        isErasing = isErase;
        if (isErasing) {
            drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        } else {
            drawPaint.setXfermode(null);
        }
    }

    public boolean isErasing() {
        return isErasing;
    }

    public void setColor(String color) {
        invalidate();
        drawPaint.setColor(Color.parseColor(color));
        pathPaint.setColor(Color.parseColor(color));
    }
}
