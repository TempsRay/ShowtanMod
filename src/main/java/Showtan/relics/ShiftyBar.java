package Showtan.relics;

import Showtan.character.ShowtanCharacter;
import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.Iterator;

import static Showtan.ShowtanMod.makeID;

public class ShiftyBar extends BaseRelic {
    private static final String NAME = "ShiftyBar";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public AbstractCard.CardColor pool = ShowtanCharacter.Enums.CARD_COLOR;
    public RelicType relicType = RelicType.SHARED;
    protected String imageName;
    private static final int SHIV_AMT = 2;

    //for character specific relics
    public ShiftyBar() {
        super(ID, NAME, ShowtanCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + SHIV_AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public void onUsePotion() {
        this.flash();
        this.addToBot(new MakeTempCardInHandAction(new Shiv(), SHIV_AMT, false));
    }
}