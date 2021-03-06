package com.alious.pro.photo.library.utils;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.alious.pro.photo.library.R;
import com.alious.pro.photo.library.widget.RatioPhotoDraweeView;
import com.alious.pro.photo.library.widget.RatioSimpleDraweeView;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.squareup.picasso.Picasso;

public class ImageLoadUtil {

    public static void loadWithFresco(SimpleDraweeView draweeView, String imageUrl) {
        draweeView.setImageURI(Uri.parse(imageUrl + ""));
    }

    public static void loadScaleWithFresco(Context context, final RatioSimpleDraweeView scaleDraweeView, String imageUrl) {
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                float mScale = (float) imageInfo.getHeight() / (float) imageInfo.getWidth();
                scaleDraweeView.setRatio(mScale);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
            }
        };
        Uri uri = Uri.parse(imageUrl);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(uri)
                .build();
        scaleDraweeView.setController(controller);
    }

    public static void loadScaleWithFrescoAnim(Context context, final RatioSimpleDraweeView scaleDraweeView, String imageUrl) {
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                float mScale = (float) imageInfo.getHeight() / (float) imageInfo.getWidth();
                scaleDraweeView.setRatio(mScale);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
            }
        };
        boolean isCacheInDisk = Fresco.getImagePipelineFactory()
                .getMainDiskStorageCache().hasKey(new SimpleCacheKey(imageUrl));
        if (isCacheInDisk) {
            Uri uri = Uri.parse(imageUrl);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setControllerListener(controllerListener)
                    .setUri(uri)
                    .build();
            scaleDraweeView.setController(controller);
        }else {
            Uri uri = Uri.parse(imageUrl);
            GenericDraweeHierarchyBuilder hierarchyBuilder
                    = new GenericDraweeHierarchyBuilder(context.getResources());
            GenericDraweeHierarchy hierarchy =
                    hierarchyBuilder.
                            setPlaceholderImage(R.drawable.ic_default_loading)
                            .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setControllerListener(controllerListener)
                    .setUri(uri)
                    .build();
            scaleDraweeView.setController(controller);
            scaleDraweeView.setHierarchy(hierarchy);
        }

    }


    public static void loadScaleWithFresco(Context context, final RatioPhotoDraweeView scaleDraweeView, String imageUrl) {
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                float mScale = (float) imageInfo.getHeight() / (float) imageInfo.getWidth();
                scaleDraweeView.setRatio(mScale);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
            }
        };
        Uri uri = Uri.parse(imageUrl);
        GenericDraweeHierarchyBuilder hierarchyBuilder
                = new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy =
                hierarchyBuilder.
                        setPlaceholderImage(R.drawable.ic_default_loading)
                        .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(uri)
                .build();
        scaleDraweeView.setController(controller);
        scaleDraweeView.setHierarchy(hierarchy);
    }

    public static void loadWithPicasso(Context context, ImageView imageView, String imageUrl) {
        Picasso.with(context).load(imageUrl).into(imageView);
    }
}
