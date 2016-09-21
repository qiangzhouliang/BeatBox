package com.qzl.beatbox.activity;

import android.support.v4.app.Fragment;

import com.qzl.beatbox.fragment.BeatBoxFragment;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
