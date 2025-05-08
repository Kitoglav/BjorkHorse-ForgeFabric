package com.kitoglav.bjorkhorse;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public final class BjorkHorseMod {
    public static final String MOD_ID = "bjorkhorse";
    public static final Identifier AGREE_SOUND_ID = Identifier.of(BjorkHorseMod.MOD_ID, "agree");
    @Nullable
    public static SoundEvent AGREE_SOUND_EVENT;
    public static void init() {

    }
}
