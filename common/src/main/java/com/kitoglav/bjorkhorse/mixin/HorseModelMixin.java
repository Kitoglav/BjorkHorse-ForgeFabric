package com.kitoglav.bjorkhorse.mixin;

import com.kitoglav.bjorkhorse.api.IHorseAgree;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(HorseEntityModel.class)
public abstract class HorseModelMixin<T extends AbstractHorseEntity> extends AnimalModel<T> {
    @Shadow @Final protected ModelPart head;
    @Shadow @Final protected ModelPart body;
    @Shadow @Final private ModelPart leftFrontLeg;
    @Shadow @Final private ModelPart rightFrontLeg;
    @Shadow @Final private ModelPart leftHindLeg;
    @Shadow @Final private ModelPart rightHindLeg;
    @Shadow @Final private ModelPart tail;
    @Shadow @Final private ModelPart rightHindBabyLeg;
    @Shadow @Final private ModelPart leftHindBabyLeg;
    @Shadow @Final private ModelPart rightFrontBabyLeg;
    @Shadow @Final private ModelPart leftFrontBabyLeg;
    /**
     * @author Kitoglav
     * @reason add agreeing logic
     */
    @Overwrite
    public void animateModel(T arg, float f, float g, float h) {
        super.animateModel(arg, f, g, h);
        float i = MathHelper.lerpAngleDegrees(h, arg.prevBodyYaw, arg.bodyYaw);
        float j = MathHelper.lerpAngleDegrees(h, arg.prevHeadYaw, arg.headYaw);
        float k = MathHelper.lerp(h, arg.prevPitch, arg.getPitch());
        float l = j - i;
        float m = k * 0.017453292F;
        if (l > 20.0F) {
            l = 20.0F;
        }

        if (l < -20.0F) {
            l = -20.0F;
        }

        if (g > 0.2F) {
            m += MathHelper.cos(f * 0.8F) * 0.15F * g;
        }

        float n = arg.getEatingGrassAnimationProgress(h);
        float o = arg.getAngryAnimationProgress(h);
        float p = 1.0F - o;
        float q = arg.getEatingAnimationProgress(h);
        boolean bl = arg.tailWagTicks != 0;
        float r = (float) arg.age + h;
        this.head.pivotY = 4.0F;
        this.head.pivotZ = -12.0F;
        this.body.pitch = 0.0F;
        this.head.pitch = 0.5235988F + m;
        this.head.yaw = l * 0.017453292F;
        float s = arg.isTouchingWater() ? 0.2F : 1.0F;
        float t = MathHelper.cos(s * f * 0.6662F + 3.1415927F);
        float u = t * 0.8F * g;
        float v = (1.0F - Math.max(o, n)) * (0.5235988F + m + q * MathHelper.sin(r) * 0.05F);
        this.head.pitch = o * (0.2617994F + m) + n * (2.1816616F + MathHelper.sin(r) * 0.05F) + v;
        float p1 = ((IHorseAgree)arg).bjorkhorse$getAgreeProgress(h);
        this.head.pitch += MathHelper.sin(p1 * (float) Math.PI * 24) * 0.6F;
        this.head.yaw = o * l * 0.017453292F + (1.0F - Math.max(o, n)) * this.head.yaw;
        this.head.pivotY = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.head.pivotY;
        this.head.pivotZ = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.head.pivotZ;
        this.body.pitch = o * -0.7853982F + p * this.body.pitch;
        float w = 0.2617994F * o;
        float x = MathHelper.cos(r * 0.6F + 3.1415927F);
        this.leftFrontLeg.pivotY = 2.0F * o + 14.0F * p;
        this.leftFrontLeg.pivotZ = -6.0F * o - 10.0F * p;
        this.rightFrontLeg.pivotY = this.leftFrontLeg.pivotY;
        this.rightFrontLeg.pivotZ = this.leftFrontLeg.pivotZ;
        float y = (-1.0471976F + x) * o + u * p;
        float z = (-1.0471976F - x) * o - u * p;
        this.leftHindLeg.pitch = w - t * 0.5F * g * p;
        this.rightHindLeg.pitch = w + t * 0.5F * g * p;
        this.leftFrontLeg.pitch = y;
        this.rightFrontLeg.pitch = z;
        this.tail.pitch = 0.5235988F + g * 0.75F;
        this.tail.pivotY = -5.0F + g;
        this.tail.pivotZ = 2.0F + g * 2.0F;
        if (bl) {
            this.tail.yaw = MathHelper.cos(r * 0.7F);
        } else {
            this.tail.yaw = 0.0F;
        }

        this.rightHindBabyLeg.pivotY = this.rightHindLeg.pivotY;
        this.rightHindBabyLeg.pivotZ = this.rightHindLeg.pivotZ;
        this.rightHindBabyLeg.pitch = this.rightHindLeg.pitch;
        this.leftHindBabyLeg.pivotY = this.leftHindLeg.pivotY;
        this.leftHindBabyLeg.pivotZ = this.leftHindLeg.pivotZ;
        this.leftHindBabyLeg.pitch = this.leftHindLeg.pitch;
        this.rightFrontBabyLeg.pivotY = this.rightFrontLeg.pivotY;
        this.rightFrontBabyLeg.pivotZ = this.rightFrontLeg.pivotZ;
        this.rightFrontBabyLeg.pitch = this.rightFrontLeg.pitch;
        this.leftFrontBabyLeg.pivotY = this.leftFrontLeg.pivotY;
        this.leftFrontBabyLeg.pivotZ = this.leftFrontLeg.pivotZ;
        this.leftFrontBabyLeg.pitch = this.leftFrontLeg.pitch;
        boolean bl2 = arg.isBaby();
        this.rightHindLeg.visible = !bl2;
        this.leftHindLeg.visible = !bl2;
        this.rightFrontLeg.visible = !bl2;
        this.leftFrontLeg.visible = !bl2;
        this.rightHindBabyLeg.visible = bl2;
        this.leftHindBabyLeg.visible = bl2;
        this.rightFrontBabyLeg.visible = bl2;
        this.leftFrontBabyLeg.visible = bl2;
        this.body.pivotY = bl2 ? 10.8F : 0.0F;
    }
}
