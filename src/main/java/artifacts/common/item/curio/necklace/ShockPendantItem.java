package artifacts.common.item.curio.necklace;

import artifacts.common.init.ModGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ShockPendantItem extends PendantItem {

    public ShockPendantItem() {
        super(ModGameRules.SHOCK_PENDANT_STRIKE_CHANCE);
        addListener(LivingHurtEvent.class, this::onLivingHurt);
    }

    @Override
    protected boolean isCosmetic() {
        return ModGameRules.SHOCK_PENDANT_STRIKE_CHANCE.get() <= 0;
    }

    private void onLivingHurt(LivingHurtEvent event, LivingEntity wearer) {
        if (!event.getEntity().level.isClientSide
                && event.getAmount() >= 1
                && event.getSource() == DamageSource.LIGHTNING_BOLT) {
            event.setCanceled(true);
        }
    }

    @Override
    protected void applyEffect(LivingEntity target, LivingEntity attacker) {
        if (attacker.level.canSeeSky(new BlockPos(attacker.position()))) {
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(attacker.level);
            if (lightningBolt != null) {
                lightningBolt.moveTo(Vec3.atBottomCenterOf(attacker.blockPosition()));
                lightningBolt.setCause(attacker instanceof ServerPlayer player ? player : null);
                attacker.level.addFreshEntity(lightningBolt);
            }
        }
    }
}
