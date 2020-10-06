package com.example.katerin.androidproyectov1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button imagen1,Ver_Restaurante;
    static final int code_camera=999;
    private MainActivity root=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "OnCreate",Toast.LENGTH_LONG).show();
        loadcomponents();
    }

    private void loadcomponents() {

        Ver_Restaurante=this.findViewById(R.id.restaurantes);
        imagen1=this.findViewById(R.id.imagen1);

        Ver_Restaurante.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ver_RestauranteActivity =new Intent(root,Ver_Restaurante.class);
                root.startActivity(Ver_RestauranteActivity);

            }
        }));

        imagen1.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ////CAMARA////
                Intent camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                if(camera.resolveActivity(root.getPackageManager())!=null){
                    root.startActivityForResult(camera,code_camera);
                }
                }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==code_camera && resultCode == RESULT_OK ){
            Bundle photo= data.getExtras();
            Bitmap imageBitmap =(Bitmap) photo.get("data");
            ImageView img=root.findViewById(R.id.imageView);
            img.setImageBitmap(imageBitmap);
        }
    }
}