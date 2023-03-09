package artifacts.common.item.curio.feet;

import artifacts.common.capability.SwimHandler;
import artifacts.common.init.ModGameRules;
import artifacts.common.item.curio.CurioItem;
import be.florens.expandability.api.forge.LivingFluidCollisionEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.eventbus.api.Event;

public class AquaDashersItem extends CurioItem {

    public AquaDashersItem() {
        addListener(LivingFluidCollisionEvent.class, this::onFluidCollision);
    }

    @Override
    protected boolean isCosmetic() {
        return !ModGameRules.AQUA_DASHERS_ENABLED.get();
    }

    private void onFluidCollision(LivingFluidCollisionEvent event, LivingEntity wearer) {
        if (ModGameRules.AQUA_DASHERS_ENABLED.get() && wearer.isSprinting() && wearer.fallDistance < 6 && !wearer.isUsingItem() && !wearer.isCrouching()) {
            wearer.getCapability(SwimHandler.CAPABILITY).ifPresent(handler -> {
                if (!handler.isWet() && !handler.isSwimming()) {
                    event.setResult(Event.Result.ALLOW);
                    if (event.getFluidState().is(FluidTags.LAVA)) {
                        if (!wearer.fireImmune() && !EnchantmentHelper.hasFrostWalker(wearer)) {
                            wearer.hurt(DamageSource.HOT_FLOOR, 1);
                        }
                    }
                }
            });
        }
    }

    public boolean isSprinting(LivingEntity entity) {
        return isEquippedBy(entity)
                && ModGameRules.AQUA_DASHERS_ENABLED.get()
                && entity.isSprinting()
                && entity.fallDistance < 6
                && !entity.getCapability(SwimHandler.CAPABILITY).map(SwimHandler::isWet).orElse(true);
    }

    private boolean isSprintingOnFluid(LivingEntity entity) {
        if (isSprinting(entity)) {
            BlockPos pos = new BlockPos(Mth.floor(entity.getX()), Mth.floor(entity.getY() - 0.2), Mth.floor(entity.getZ()));
            return !entity.level.getBlockState(pos).getFluidState().isEmpty();
        }
        return false;
    }
}
