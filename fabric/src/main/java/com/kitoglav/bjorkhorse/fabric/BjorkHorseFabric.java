package com.kitoglav.bjorkhorse.fabric;

import net.fabricmc.api.ModInitializer;

import com.kitoglav.bjorkhorse.BjorkHorseMod;

public final class BjorkHorseFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BjorkHorseMod.init();
    }
}
