package com.kang.mvp.dagger;

import javax.inject.Singleton;

import com.kang.mvp.model.ClothHandler;

import dagger.Component;

/**
 * @author created by kangren on 2018/6/14 18:30
 */
@Singleton
@Component(modules = BaseModule.class)
public interface BaseComponent {

    //这个是为第二个Activity准备的,也就是dependencies依赖声明的方式
    ClothHandler getClothHandler();

    //@Subcomponent使用的声明方式,声明一个返回值为子组件的方法,子组件需要什么Module,就在方法参数中添加什么
    SubMainComponent getSubMainComponent(MainModule module);
}
