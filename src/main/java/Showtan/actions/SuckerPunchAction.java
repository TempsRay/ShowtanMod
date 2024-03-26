package Showtan.actions;

import Showtan.ShowtanMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class SuckerPunchAction extends AbstractGameAction {
    public static final String ACTION_ID = ShowtanMod.makeID("SuckerPunchAction");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ACTION_ID);
    public static final String[] TEXT = uiStrings.TEXT;
    private int damage;

    private AbstractMonster targetMonster;
    private AbstractCreature player;

    public SuckerPunchAction(int damage, AbstractMonster m, AbstractCreature p) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.damage = damage;
        this.targetMonster = m;
        this.player = p;
    }
    @Override
    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            this.addToBot(new DamageAction(this.targetMonster, new DamageInfo(this.player, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            addToBot(new DrawCardAction(AbstractDungeon.player, 1));
            addToBot(new GainEnergyAction(1));
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
        }

        this.isDone = true;
    }
}
