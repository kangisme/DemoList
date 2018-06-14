package com.kang.mvp.dagger;

import com.kang.mvp.model.Cloth;
import com.kang.mvp.model.ClothHandler;

import dagger.Module;
import dagger.Provides;

/**
 * @author created by kangren on 2018/6/14 18:21
 */
@Module
public class SecondModule {

    @PreActivity
    @Provides
    public Cloth getBlueCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }
}
