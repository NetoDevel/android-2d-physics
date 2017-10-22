package br.com.netodevel.canvas_study_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Impossible view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.view = new Impossible(this);
        setContentView(this.view);
    }

    protected void onResume() {
        super.onResume();
        view.resume();
    }

}
