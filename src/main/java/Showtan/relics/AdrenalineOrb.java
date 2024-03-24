package Showtan.relics;

import Showtan.cards.Paraflinch;
import Showtan.character.ShowtanCharacter;
import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Showtan.ShowtanMod.makeID;

public class AdrenalineOrb extends BaseRelic {
    private static final String NAME = "AdrenalineOrb";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public AbstractCard.CardColor pool = ShowtanCharacter.Enums.CARD_COLOR;
    public RelicType relicType = RelicType.SHARED;
    protected String imageName;

    //for character specific relics
    public AdrenalineOrb() {
        super(ID, NAME, ShowtanCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        if (Paraflinch.playerHasDebuff()) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(1));
        }
    }
}