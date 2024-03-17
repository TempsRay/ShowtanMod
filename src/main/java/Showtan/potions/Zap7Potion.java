package Showtan.potions;

import Showtan.character.ShowtanCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;

import static Showtan.ShowtanMod.makeID;

public class Zap7Potion extends BasePotion {
    public static final String ID = makeID("Zap7Potion");
    private static final int DAMAGE = 3;


    public Zap7Potion() {
        super(ID, 7, PotionRarity.COMMON, PotionSize.BOLT, PotionColor.ENERGY);
        this.isThrown = true;
        this.targetRequired = false;
        playerClass = ShowtanCharacter.Enums.SHOWTAN;
        this.labOutlineColor = Settings.CREAM_COLOR;

    }

    @Override
    public void use(AbstractCreature target) {
        DamageInfo damageInfo = new DamageInfo(AbstractDungeon.player, DAMAGE, DamageInfo.DamageType.THORNS);
        for (int i = 0; i < potency; i++) {
            AbstractCreature m = AbstractDungeon.getRandomMonster();
            if (m != null) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, damageInfo, AbstractGameAction.AttackEffect.NONE, true));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.0F));
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
        }

    }
    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + DAMAGE + potionStrings.DESCRIPTIONS[1] + potency + potionStrings.DESCRIPTIONS[2];
    }
}
