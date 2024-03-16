package Showtan.orbs;

import Showtan.ShowtanMod;
import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;

import java.util.Iterator;

import static Showtan.ShowtanMod.orbPath;

public class KoffingOrb extends CustomOrb {

    public static final String ORB_ID = ShowtanMod.makeID("KoffingOrb");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    private static final int PASSIVE_AMOUNT = 1;
    private static int EVOKE_AMOUNT = 2;

    public KoffingOrb() {
        super(ORB_ID, orbString.NAME, PASSIVE_AMOUNT, EVOKE_AMOUNT, DESCRIPTIONS[1], DESCRIPTIONS[3], orbPath("KoffingOrb.png"));
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
        this.evokeAmount = this.passiveAmount * 2;
    }

    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_FAST_2"));
        if (this.passiveAmount * 2 > 0) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

                while(var1.hasNext()) {
                    AbstractMonster m = (AbstractMonster)var1.next();
                    if (!m.isDead && !m.isDying) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new PoisonPower(m, AbstractDungeon.player, this.evokeAmount), this.evokeAmount));
                    }
                }
            }
        }
    }

    @Override
    public void onEndOfTurn() {
        if (this.passiveAmount > 0) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();

                while(var1.hasNext()) {
                    AbstractMonster m = (AbstractMonster)var1.next();
                    if (!m.isDead && !m.isDying) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new PoisonPower(m, AbstractDungeon.player, this.passiveAmount), this.passiveAmount));
                    }
                }
            }
        }
    }

    @Override
    public AbstractOrb makeCopy() {
        return new KoffingOrb();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("INTIMIDATE", 0.1f);
    }
}
