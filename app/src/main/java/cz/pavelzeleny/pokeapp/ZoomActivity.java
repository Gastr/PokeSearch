package cz.pavelzeleny.pokeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ZoomActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_zoom_activity);

        ImageView imageZoom = findViewById(R.id.imageZoom);
        Intent intent = getIntent();
        Picasso.get().load(intent.getStringExtra("url")).into(imageZoom);
    }
}
