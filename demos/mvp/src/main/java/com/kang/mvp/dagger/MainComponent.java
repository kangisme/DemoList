package com.kang.mvp.dagger;

import com.kang.mvp.view.MainActivity;

import dagger.Component;

@PreActivity
@Component(modules = MainModule.class, dependencies = BaseComponent.class)
public interface MainComponent {
    /**
     * 必须让Component知道需要往哪个类中注入
     * 这个方法名可以是其它的，但是推荐用inject
     * 目标类MainActivity必须精确，不能用它的父类
     */
    void inject(MainActivity activity);
}
