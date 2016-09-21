package com.qzl.beatbox.bean;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * BeatBox资源管理类
 * Created by Qzl on 2016-09-20.
 */
public class BeatBox {
    private static final String TAG = "BeatBox";//日记标记
    private static final String SOUNDS_FOLDER = "sample_sounds";//存储声音资源
    private static final int MAX_SOUNDS = 5;//定义最大可同时播放的数量

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;//提供了一个构造器，该构造器可以指定它总共支持多少个声音

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        //第一个参数指定支持多少个声音；第二个参数指定声音类型：第三个参数指定声音品质。
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }

    //播放音乐
    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        /**
         * 该方法的第一个参数指定播放哪个声音；leftVolume、rightVolume指定左、右的音量：
         * priority指定播放声音的优先级，数值越大，优先级越高；loop指定是否循环，0为不循环，-1为循环；
         * rate指定播放的比率，数值可从0.5到2， 1为正常比率
         */
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }

    //释放SoundPool
    public void release(){
        mSoundPool.release();
    }
    private void loadSounds(){
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);//列出指定目录中的所有文件名
            Log.i(TAG, "Found "+soundNames.length+" sounds");
        } catch (IOException e) {
            Log.e(TAG, "没有找到资源文件", e);
            return;
        }

        for (String filename : soundNames) {
            try {
                //重新构建路劲
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                //创建管理对象
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG, "loadSounds:未能成功加载音乐", e);
            }
        }
    }

    //加载音频
    private void  load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());//打开文件
        //从afd 所对应的文件中加载声音
        int soundId = mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }
    //获取音频集合
    public List<Sound> getSounds(){
        return mSounds;
    }
}
