package org.onursert.videotimeline;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;

public class VideoTimeline extends LinearLayout {

    private View rootView;
    private LinearLayout linearLayout;

    private MediaMetadataRetriever retriever;
    private int totalDuration;

    public VideoTimeline(Context context) {
        super(context);
        init();
    }

    public VideoTimeline(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoTimeline(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.video_timeline, this, true);

        linearLayout = rootView.findViewById(R.id.linear_layout);

        retriever = new MediaMetadataRetriever();
    }

    public void setUri(Uri uri) {
        retriever.setDataSource(getContext(), uri);
        String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        if (duration != null) {
            totalDuration = Integer.valueOf(duration);
        } else {
            totalDuration = 0;
        }
    }

    public void setUrl(String url) {
        retriever.setDataSource(url, new HashMap<String, String>());
        String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        if (duration != null) {
            totalDuration = Integer.valueOf(duration);
        } else {
            totalDuration = 0;
        }
    }

    public void initialize() {
        for (int i = 10; i < 100; i += 10) {
            ImageView imageView = new ImageView(getContext());
            imageView.setId(i);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Long newPosition = totalDuration * i * 10L;
            Bitmap bitmap = retriever.getFrameAtTime(newPosition);
            imageView.setImageBitmap(bitmap);

            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    System.out.println(v.getId());
                }
            });

            linearLayout.post(new Runnable() {
                @Override
                public void run() {
                    System.out.println(linearLayout.getWidth());

                }
            });
        }
    }
}
