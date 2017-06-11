package com.example.aksha.music;

/**
 * Created by aksha on 24-01-2017.
 */

public class Song
{
    private String mSongName;

    private String mArtistName;

    private int mAudioResourceId;

    private int mImageResourceId =NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Song(String sName, String aName, int audioResourceId)
    {

        mSongName=sName;
        mArtistName= aName;
        mAudioResourceId = audioResourceId;

    }

    public Song(String sName, String aName, int iRId,int audioResourceId)
    {
        mSongName=sName;
        mArtistName= aName;
        mAudioResourceId = audioResourceId;
        mImageResourceId = iRId;

    }
    public String getSongName()
    {
        return mSongName;
    }
    public String getArtistName()
    {
        return mArtistName;
    }
    public int getImageResourceId() {return mImageResourceId;}
    public int getAudioResourceId() {return mAudioResourceId;}

    public boolean hasImage()
    {
        return (mImageResourceId)!= NO_IMAGE_PROVIDED;
    }
}
