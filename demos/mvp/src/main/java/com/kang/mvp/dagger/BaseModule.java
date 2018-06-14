package com.kang.mvp.dagger;

import com.kang.mvp.model.ClothHandler;

import dagger.Module;
import dagger.Provides;

/**
 * @author created by kangren on 2018/6/14 18:30
 */
@Module
public class BaseModule {
    @PreActivity
    @Provides
    public ClothHandler getClothHandler() {
        return new ClothHandler();
    }
}
