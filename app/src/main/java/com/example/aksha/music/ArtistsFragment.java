package com.example.aksha.music;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistsFragment extends Fragment {
    MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener complete = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                        releaseMediaPlayer();
                        // Pause playback immediately

                    }
                    else if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        releaseMediaPlayer();


                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mediaPlayer.start();
                    }
                }

                ;
            };

    public ArtistsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.song_list, container, false);


            mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
            final ArrayList<Song> songs = new ArrayList<Song>();
            songs.add(new Song("Usher", "Numb", R.drawable.usher, R.raw.usher_numb));
            songs.add(new Song("Ariana Grande", "Into You", R.drawable.dangerouswoman, R.raw.into_you));
            songs.add(new Song("Rae Sremmurd", "Black Beetles", R.drawable.sremmlife, R.raw.black_beetles));
            songs.add(new Song("Week'nd", "Star Boy", R.drawable.starboysingle, R.raw.starboy));
            songs.add(new Song("Charli XCX", "Boom Clap", R.drawable.charli_xcx, R.raw.boom_clap));

            songs.add(new Song("Gipsy Kings","Bamboleo",R.drawable.gipsy_kings,R.raw.gipsy_kings_bamboleo));

            Log.v("Songs", "Song in array list are:" + songs);


            SongAdapter SongAdapter = new SongAdapter(getActivity(), songs, R.color.bluelighter);
            ListView listView = (ListView) rootView.findViewById(R.id.activity_songs);
            listView.setAdapter(SongAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Song songs1 = songs.get(position);// gets the position of the item clicked and passes it into the songs1 var
                    //Releases the media file currently playing to play a different sound file....

                    int result = mAudioManager.requestAudioFocus(onAudioFocusChangeListener,
                            // Use the music stream.
                            AudioManager.STREAM_MUSIC,
                            // Request permanent focus.
                            AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        // Start playback
                        releaseMediaPlayer();
                        mediaPlayer = MediaPlayer.create(getActivity(), songs1.getAudioResourceId());// gets the value of the variable stored in songs 1
                        mediaPlayer.start();//and plays the audio file associated with the variable
                        //when a song is completed, it creates an object of an on completion listener which
                        // releases the media file once the song ends
                        // Request audio focus for playback
                        mediaPlayer.setOnCompletionListener(complete);
                    }

                }
            });

        return rootView;
        }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer()
    {
        if (mediaPlayer != null) {
            mediaPlayer.release();

            mediaPlayer = null;

            mAudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

}
