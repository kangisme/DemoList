package com.kang.mvp;

import javax.inject.Named;

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
    public Clothes getClothes(Cloth cloth) {
        return new Clothes(cloth);
    }
}
