package com.qzl.beatbox.bean;

/**
 * 声音管理类
 * Created by Qzl on 2016-09-20.
 */

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId;//设置为integer时，在sound没有值时可以设置其为空值

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");//分离出文件名
        String filename = components[components.length - 1];
        mName = filename.replace(".wav","");//将.wav后缀删除
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
