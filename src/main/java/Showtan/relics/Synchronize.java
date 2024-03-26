package Showtan.relics;

import Showtan.character.ShowtanCharacter;
import basemod.helpers.RelicType;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static Showtan.ShowtanMod.makeID;

public class Synchronize extends BaseRelic implements OnReceivePowerRelic {
    private static final String NAME = "Synchronize";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public AbstractCard.CardColor pool = ShowtanCharacter.Enums.CARD_COLOR;
    public RelicType relicType = RelicType.SHARED;
    protected String imageName;
    private static final int DEBUFF_AMT = 1;

    //for character specific relics
    public Synchronize() {
        super(ID, NAME, ShowtanCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + DEBUFF_AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        if (abstractPower.type == AbstractPower.PowerType.DEBUFF) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDeadOrEscaped()) {
                    if (abstractPower.ID.equals(WeakPower.POWER_ID)) {
                        this.flash();
                        this.addToBot(new RelicAboveCreatureAction(m, this));
                        this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, DEBUFF_AMT, false), 1, true));
                    }
                    if(abstractPower.ID.equals(VulnerablePower.POWER_ID)) {
                        this.flash();
                        this.addToBot(new RelicAboveCreatureAction(m, this));
                        this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, DEBUFF_AMT, false), 1, true));
                    }
                    if(abstractPower.ID.equals(FrailPower.POWER_ID)) {
                        this.flash();
                        this.addToBot(new RelicAboveCreatureAction(m, this));
                        this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new FrailPower(m, DEBUFF_AMT, false), 1, true));
                    }
                }
            }


        }
        return true;
    }
}