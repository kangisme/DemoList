package com.kang.mvp.dagger;

import javax.inject.Named;

import com.kang.mvp.model.Cloth;
import com.kang.mvp.model.Clothes;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @Named("red")
    public Cloth getRedColor() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @PreActivity
    @Provides
    @Named("blue")
    public Cloth getBlueColor() {
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }

    @Provides
    public Cloth getDefaultColor() {
        Cloth cloth = new Cloth();
        cloth.setColor("Default Color");
        return cloth;
    }

    @Provides
    public Clothes getClothes(@Named("blue") Cloth cloth) {
        return new Clothes(cloth);
    }
}