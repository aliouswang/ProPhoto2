package com.alious.pro.simple;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.image.ImageInfo;

public class MainActivity extends AppCompatActivity {

    private ScaleSimpleDraweeView img_head;
    private Button btn_start;
    private float mScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        img_head = (ScaleSimpleDraweeView) findViewById(R.id.img_head);

        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                mScale = (float) imageInfo.getHeight() / (float) imageInfo.getWidth();
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
            }
        };
        Uri uri = Uri.parse(Photo.images[2]);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(uri)
                .build();
        img_head.setController(controller);

        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] screenLocation = new int[2];
                img_head.getLocationOnScreen(screenLocation);
                Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", img_head.getWidth()).
                        putExtra("height", img_head.getHeight()).
                        putExtra("scale", mScale)
                ;
                Log.e("prophoto", "left:" + screenLocation[0]
                        + ";top:" + screenLocation[1]
                        + ";width:" + img_head.getWidth()
                        + ";height:" + img_head.getHeight()
                );
                startActivity(intent);
                overridePendingTransition(0, 0);



            }
        });

//        btn_start = (Button) findViewById(R.id.btn_start);
//        btn_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
