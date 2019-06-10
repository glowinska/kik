package com.example.kik;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.example.kik.gamelogics.Field;
import com.example.kik.gamelogics.Game;
import com.example.kik.gamelogics.Judge;
import com.example.kik.tools.Tools;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SurfaceHolder fieldHolder;

    private Judge judge;

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SurfaceView field = findViewById(R.id.surfaceView);
        fieldHolder = field.getHolder();

        field.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                int cellX = Tools.map(x, 0, field.getWidth(), 0, 3);
                int cellY = Tools.map(y, 0, field.getHeight(), 0, 3);
                Log.d(TAG, "onCreate: x=" + cellX + " y=" + cellY);
                judge.move(cellX, cellY);
                return true;
            }
            return false;
        });

        fieldHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.GRAY);
                Paint paint = new Paint();
                paint.setARGB(255, 0, 0, 0);

                float width = canvas.getWidth();
                float height = canvas.getHeight();

                float x = width / 3;
                float y = height / 3;

                float w = 4;
                canvas.drawRect(x - w / 2, 0, x + w / 2, height, paint);
                canvas.drawRect(2 * x - w / 2, 0, 2 * x + w / 2, height, paint);
                canvas.drawRect(0, y - w / 2, width, y + w / 2, paint);
                canvas.drawRect(0, 2 * y - w / 2, width, 2 * y + w / 2, paint);

                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });

        ImageView cells[] = new ImageView[9];

        cells[0] = findViewById(R.id.cell1);
        cells[1] = findViewById(R.id.cell2);
        cells[2] = findViewById(R.id.cell3);
        cells[3] = findViewById(R.id.cell4);
        cells[4] = findViewById(R.id.cell5);
        cells[5] = findViewById(R.id.cell6);
        cells[6] = findViewById(R.id.cell7);
        cells[7] = findViewById(R.id.cell8);
        cells[8] = findViewById(R.id.cell9);

        Drawable cross = getResources().getDrawable(R.drawable.cross_136x136);
        Drawable nought = getResources().getDrawable(R.drawable.nought_113x113);
        Drawable end = getResources().getDrawable(R.drawable.end);

        ImageView turnImage = findViewById(R.id.turnImage);

        Game game = new Game(new Field(cells, turnImage, cross, nought, end));

        judge = new Judge(game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.mnu_newgame:
                judge.newGame();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
