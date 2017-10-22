package br.com.netodevel.canvas_study_android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by neto on 21/10/17.
 */

public class Impossible extends SurfaceView implements Runnable {

    private boolean running = false;
    private Thread renderThread = null;
    private SurfaceHolder holder;
    private Paint paint;

    private int posx = 50;
    private int posy = 10;
    private int vx = 30;
    private int vy = 30;

    private static final int HALF_THE_BALL = 25;

    public Impossible(Context context) {
        super(context);
        paint = new Paint();
        holder = getHolder();
    }

    @Override
    public void run() {
        while (running) {
            if(!holder.getSurface().isValid())
                continue;

            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.BLUE);

            update();

            canvas.drawCircle(posx, posy, 50, paint);

            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void update() {
        this.posx += vx;
        this.posy += vy;

        if (posx > (getWidth() - HALF_THE_BALL)) {
            posx = (getWidth() - HALF_THE_BALL);
            vx = -30;
        } else if (posx < HALF_THE_BALL) {
            posx = 0;
            vx = 30;
        }

        if (posy > (getHeight() - HALF_THE_BALL)) {
            posy = (getHeight() - HALF_THE_BALL);
            vy = -30;
        } else if (posy < HALF_THE_BALL) {
            posy = 0;
            vy = 30;
        }
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

}
