package com.kang.mvp.model;

/**
 * @author created by kangren on 2018/6/14 18:10
 */
public class ClothHandler {
    public Clothes handle(Cloth cloth) {
        return new Clothes(cloth);
    }
}
