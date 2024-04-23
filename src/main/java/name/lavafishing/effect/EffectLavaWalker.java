package name.lavafishing.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class EffectLavaWalker extends StatusEffect {
    public EffectLavaWalker(){
        super(StatusEffectCategory.BENEFICIAL, 0xCC3300);//药水效果是有益的还是有害的, 显示的颜色
    }

    // 这个方法在每个 tick 都会调用，以检查是否应应用药水效果
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // 在我们的例子中，为了确保每一 tick 药水效果都会被应用，我们只要这个方法返回 true 就行了。
        return true;
    }

    //通过食用蒸汽飞鱼或岩浆行者药水获得。
    //可以在岩浆上跑跳，
    // 并且挖掘速度不受滞空惩罚，
    // 如果在岩浆中会快速上浮。
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

    }
}
