package Showtan.relics;

import Showtan.character.ShowtanCharacter;
import Showtan.orbs.WeedleOrb;
import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Showtan.ShowtanMod.makeID;

public class ShowtanStarterRelic extends BaseRelic {
    private static final String NAME = "ShowtanStarterRelic";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public AbstractCard.CardColor pool = ShowtanCharacter.Enums.CARD_COLOR;
    public RelicType relicType = RelicType.SHARED;
    protected String imageName;

    private static final int HEAL_AMT = 2;
    private static final int DRAW_AMT = 1;

    //for character specific relics
    public ShowtanStarterRelic() {
        super(ID, NAME, ShowtanCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + DRAW_AMT + this.DESCRIPTIONS[1] + HEAL_AMT + this.DESCRIPTIONS[2];
    }


    @Override
    public void atPreBattle() {
        this.flash();
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new DrawCardAction(AbstractDungeon.player, DRAW_AMT));
        AbstractDungeon.player.channelOrb(new WeedleOrb());
    }

    @Override
    public void onVictory() {
        this.flash();
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(HEAL_AMT);
        }
    }
}