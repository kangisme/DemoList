package com.kang.mvp.dagger;

import com.kang.mvp.view.MainActivity;

import dagger.Subcomponent;

/**
 * @author created by kangren on 2018/6/15 13:57
 */
@PreActivity
@Subcomponent(modules = MainModule.class)
public interface SubMainComponent {
    void inject(MainActivity activity);
}
