package com.kang.mvp.dagger;

import javax.inject.Named;

import android.util.Log;

import com.kang.mvp.model.Cloth;
import com.kang.mvp.model.Clothes;
import com.kang.mvp.model.Shoe;
import com.orhanobut.logger.Logger;

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
    @Named("white")
    public Cloth getWhiteColor() {
        Cloth cloth = new Cloth();
        cloth.setColor("白色");
        Logger.d("get white color cloth");
        return cloth;
    }

    @Provides
    public Shoe getShoe() {
        Logger.d("get shoe ...");
        return new Shoe();
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
