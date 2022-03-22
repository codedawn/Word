package com.codedawn.word.util;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaUtil {

    private static MediaPlayer mediaPlayer ;


    public static void play(String url) {
        //复用media
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }else {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mediaPlayer.reset();
                    return true;
                }
            });
        }
        try {
            mediaPlayer.setDataSource(ConstantData.SPEECH_URL+url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
//                    if (mediaPlayer != null) {
//                        mediaPlayer.release();
//                        mediaPlayer = null;
//                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playLocalFile(int sourceAddress) {
        //复用media
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        } else{
            mediaPlayer = new MediaPlayer();
        }
        try {
            AssetFileDescriptor fileDescriptor = WordApplication.getContext().getResources().openRawResourceFd(sourceAddress);
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
//                    if (mediaPlayer != null) {
//                        mediaPlayer.release();
//                        mediaPlayer = null;
//                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
