package Showtan.orbs;

import Showtan.ShowtanMod;
import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;

import static Showtan.ShowtanMod.orbPath;

public class WeedleOrb extends CustomOrb {

    public static final String ORB_ID = ShowtanMod.makeID("WeedleOrb");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    private static final int PASSIVE_AMOUNT = 2;
    private static final int EVOKE_AMOUNT = 5;

    public WeedleOrb() {
        super(ORB_ID, orbString.NAME, PASSIVE_AMOUNT, EVOKE_AMOUNT, DESCRIPTIONS[1], DESCRIPTIONS[3], orbPath("WeedleOrb.png"));
        updateDescription();
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        applyFocus(); // Apply Focus (Look at the next method)
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + evokeAmount + DESCRIPTIONS[3];
    }

    @Override
    public void applyFocus() {
        AbstractPower power = AbstractDungeon.player.getPower("Focus");
        if (power != null) {
            this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
        } else {
            this.passiveAmount = this.basePassiveAmount;
        }
        this.evokeAmount = this.passiveAmount * 3;
    }

    @Override
    public void onEvoke() {
        float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.LIGHTNING), speedTime));
        AbstractDungeon.actionManager.addToBottom( // 2.Damage all enemies
                new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(evokeAmount, true, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        // The damage matrix is how orb damage all enemies actions have to be assigned. For regular cards that do damage to everyone, check out cleave or whirlwind - they are a bit simpler.

        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_FAST_2"));
        // For a list of sound effects you can use, look under com.megacrit.cardcrawl.audio.SoundMaster - you can see the list of keys you can use there. As far as previewing what they sound like, open desktop-1.0.jar with something like 7-Zip and go to audio. Reference the file names provided. (Thanks fiiiiilth)
    }

    @Override
    public void onEndOfTurn() {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(passiveAmount, true, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));

        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_FAST_3"));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new WeedleOrb();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("INTIMIDATE", 0.1f);
    }
}
