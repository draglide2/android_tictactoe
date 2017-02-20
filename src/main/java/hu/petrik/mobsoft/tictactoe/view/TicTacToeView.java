package hu.petrik.mobsoft.tictactoe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import hu.petrik.mobsoft.tictactoe.model.TicTacToeModel;

/**
 * Created by Wulfrith on 20/02/2017.
 */

public class TicTacToeView extends View {
    Paint paintBg;
    Paint paintLine;
    public TicTacToeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);
        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
        drawGameArea(canvas);
        drawPlayers(canvas);
    }
    private void drawGameArea(Canvas canvas) {
        //Keret
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        //Vízszintes
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3, paintLine);
        canvas.drawLine(0, getHeight() / 3 * 2, getWidth(), getHeight() / 3 * 2, paintLine);
        //Függőleges
        canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(), paintLine);
        canvas.drawLine(getWidth() / 3 * 2, 0, getWidth() / 3 * 2, getHeight(), paintLine);
    }
    private void drawPlayers(Canvas canvas) {
        int stepX = getWidth() / 3;
        int stepY = getHeight() / 3;
        int offsetX, offsetY;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                offsetX = stepX * i;
                offsetY = stepY * j;

                if (TicTacToeModel.getInstance().getFieldContent(i,j) == TicTacToeModel.CIRCLE) {
                    canvas.drawCircle(offsetX + stepX/2, offsetY + stepY/2,
                            getWidth()/6, paintLine);
                }
                else if (TicTacToeModel.getInstance().getFieldContent(i,j) == TicTacToeModel.CROSS) {

                    canvas.drawLine(0 + offsetX, 0 + offsetY, stepX + offsetX, stepY + offsetY, paintLine);
                    canvas.drawLine(0 + offsetX, stepY + offsetY, stepY + offsetX, 0 + offsetY, paintLine);
                }
            }
        }

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int
            heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int tX = ((int) event.getX()) / (getWidth() / 3);
            int tY = ((int) event.getY()) / (getHeight() / 3);
            if (tX < 3 && tY < 3 && TicTacToeModel.getInstance().getFieldContent(tX,tY)
                    == TicTacToeModel.EMPTY) {
                TicTacToeModel.getInstance().setFieldContent(tX,tY,TicTacToeModel.getInstance
                        ().getNextPlayer());
                TicTacToeModel.getInstance().changeNextPlayer();
                invalidate();
            }
        }
        return super.onTouchEvent(event);
    }

}
