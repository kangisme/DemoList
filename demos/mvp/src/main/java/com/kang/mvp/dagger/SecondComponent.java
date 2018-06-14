package com.kang.mvp.dagger;

import com.kang.mvp.view.SecondActivity;

import dagger.Component;

/**
 * @author created by kangren on 2018/6/14 18:21
 */
@PreActivity
@Component(modules = SecondModule.class)
public interface SecondComponent {
    void inject(SecondActivity activity);
}
