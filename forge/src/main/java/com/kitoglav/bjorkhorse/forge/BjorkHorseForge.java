package com.kitoglav.bjorkhorse.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.kitoglav.bjorkhorse.BjorkHorseMod;

@Mod(BjorkHorseMod.MOD_ID)
public final class BjorkHorseForge {
    public BjorkHorseForge() {
        EventBuses.registerModEventBus(BjorkHorseMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        BjorkHorseMod.init();
    }
}
