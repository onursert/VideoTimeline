package org.onursert.videotimeline;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private VideoTimeline videoTimeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoTimeline = findViewById(R.id.videoTimelineLayout);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.bunny;
        videoTimeline.setUri(Uri.parse(path));
        //videoTimeline.setUrl("http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");

        videoTimeline.initialize();
    }
}
