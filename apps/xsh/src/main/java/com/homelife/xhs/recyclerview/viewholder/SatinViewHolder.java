package com.homelife.xhs.recyclerview.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.homelife.xhs.R;
import com.homelife.xhs.bean.Satin;
import com.homelife.xhs.recyclerview.common.BaseViewHolder;
import com.homelife.xhs.recyclerview.common.HolderAnnotation;
import com.homelife.util.DisplayTool;
import com.homelife.util.Static;


/**
 * Created by xue on 2019/2/21.
 */
@HolderAnnotation(layoutId = R.layout.item_parent_holder)
public class SatinViewHolder extends BaseViewHolder<Satin> {

    private ImageView imageView;
    private ImageView headIv;
    private TextView nameTv;
    private TextView textTv;

    private Satin model;
    private final DisplayTool displayTool;

    public SatinViewHolder(@NonNull View itemView) {
        super(itemView);
        displayTool = new DisplayTool(Static.CONTEXT);
    }

    @Override
    public void initViews() {
        imageView = itemView.findViewById(R.id.imageview);
        headIv = itemView.findViewById(R.id.head_iv);
        nameTv = itemView.findViewById(R.id.name);
        textTv = itemView.findViewById(R.id.text_tv);
    }

    @Override
    public void bindView(Satin data, int position) {
        if (model == data) {
            return;
        }
        model = data;

        nameTv.setText(model.getName());
        textTv.setText(model.getText());

        int imgWidth = (displayTool.getwScreen() - displayTool.dip2px(34)) / 2;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = imgWidth;
        imageView.setLayoutParams(layoutParams);

        Glide.with(Static.CONTEXT).load(model.getProfile_image()).into(headIv);

        if (model.getImage1() != null) {
            if (model.getImage1().contains(".gif")) {
                Glide.with(Static.CONTEXT).load(model.getImage1()).into(imageView);
            } else {
                Glide.with(Static.CONTEXT)
                        .load(model.getImage1())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                                //使图片的高度按原图像素比例自适应
                                int imgWidth = (displayTool.getwScreen() - displayTool.dip2px(34)) / 2;
                                resource.setBounds(0, 0, imgWidth, resource.getIntrinsicHeight() * imgWidth / resource.getIntrinsicWidth());
                                imageView.setImageDrawable(resource);
                            }
                        });
            }
        }


    }

}
