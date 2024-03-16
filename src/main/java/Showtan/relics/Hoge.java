package Showtan.relics;

import Showtan.character.ShowtanCharacter;
import Showtan.orbs.WeedleOrb;
import basemod.helpers.RelicType;
import com.evacipated.cardcrawl.mod.stslib.relics.OnPlayerDeathRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Showtan.ShowtanMod.makeID;

public class Hoge extends BaseRelic implements OnPlayerDeathRelic {
    private static final String NAME = "Hoge";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public AbstractCard.CardColor pool = ShowtanCharacter.Enums.CARD_COLOR;
    public RelicType relicType = RelicType.SHARED;
    protected String imageName;

    private final int HEAL_AMT = 1;

    //for character specific relics
    public Hoge() {
        super(ID, NAME, ShowtanCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + HEAL_AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
        }
    }

    @Override
    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
        this.flash();
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));

        AbstractDungeon.player.heal(this.HEAL_AMT, true);
        this.setCounter(-2);
        return false;
    }
}