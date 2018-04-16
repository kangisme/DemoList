package com.kang.mvp;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    Cloth provideCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @Provides
    public Clothes getClothes(Cloth cloth) {
        return new Clothes(cloth);
    }
}
