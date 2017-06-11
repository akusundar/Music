package com.example.aksha.music;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aksha on 24-01-2017.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    private int mBackgroundColorId;

    public SongAdapter(Activity context, ArrayList<Song> song,int BackgroundColorId)

    {
        super(context,0,song);
        mBackgroundColorId = BackgroundColorId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Song currentSong = getItem(position);

        TextView songTextView = (TextView) listItemView.findViewById(R.id.list_songs);

        songTextView.setText(currentSong.getSongName());

        TextView artistTextView = (TextView) listItemView.findViewById(R.id.list_artist);

        artistTextView.setText(currentSong.getArtistName());

        ImageView albumArt = (ImageView) listItemView.findViewById(R.id.album_art);

        if(currentSong.hasImage())
        {
        albumArt.setImageResource(currentSong.getImageResourceId());
            albumArt.setVisibility(View.VISIBLE);
        }
        else
        {
           albumArt.setVisibility(View.GONE);
        }
        View textContainer = (View) listItemView.findViewById(R.id.background_color);

        int color = ContextCompat.getColor(getContext(),mBackgroundColorId);
// you don't need to make any changes to the songs adapter view after adding in an aufio file because,
// adapter views only handle the UI and not any of the audio/ video files
        textContainer.setBackgroundColor(color);
        return listItemView;
    }

}
