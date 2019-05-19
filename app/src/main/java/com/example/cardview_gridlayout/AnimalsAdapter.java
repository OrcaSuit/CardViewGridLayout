package com.example.cardview_gridlayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.ViewHolder> {
    private String[] mDataSet;
    private Context mContext;
    private Random mRandom = new Random();

    public AnimalsAdapter(Context context, String[] DataSet){
        mDataSet = DataSet;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public LinearLayout mLinearLayout;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.tv);
            mLinearLayout = (LinearLayout)v.findViewById(R.id.ll);
        }
    }

    public AnimalsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        holder.mTextView.setText(mDataSet[position]);

        int color = getRandomHSVColor();

        holder.mTextView.setBackgroundColor(getLighterColor(color));

        holder.mTextView.setTextColor(getReverseColor(color));

        holder.mLinearLayout.setBackground(getGradientDrawable());

        applyEmbossMaskFilter(holder.mTextView);
    }

    public int getItemCount() {
        return mDataSet.length;
    }

    protected void applyEmbossMaskFilter(TextView tv){
        EmbossMaskFilter embossMaskFilter = new EmbossMaskFilter(
                new float[]{1f,5f,1f},
                0.8f,
                8,
                7f
        );
        tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        tv.getPaint().setMaskFilter(embossMaskFilter);
    }

    protected int getRandomHSVColor() {

        int hue = mRandom.nextInt(361);
        float saturation = 1.0f;
        float value = 1.0f;
        int alpha = 255;
        int color = Color.HSVToColor(alpha, new float[]{hue, saturation, value});

        return color;
    }

    protected GradientDrawable getGradientDrawable() {
        GradientDrawable gradient = new GradientDrawable();
        gradient.setGradientType(GradientDrawable.SWEEP_GRADIENT);
        gradient.setColor(new int[]{getRandomHSVColor(), getRandomHSVColor(),getRandomHSVColor()});
        return gradient;
    }

    protected int getDarkerColor(int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = 0.8f * hsv[2];
        return Color.HSVToColor(hsv);
    }

    protected int getReverserColor(int color){
        float[] hsv = new float[3];
        Color.RGBToHSV(
                Color.red(color),
                Color.green(color),
                Color.blue(color),
                hsv
        );
                hsv[0] = (hsv[0] + 180) % 360;
                return Color.HSVToColor(hsv);
    }
}
