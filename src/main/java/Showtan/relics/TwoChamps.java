package Showtan.relics;

import Showtan.character.ShowtanCharacter;
import Showtan.orbs.WeedleOrb;
import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Showtan.ShowtanMod.makeID;

public class TwoChamps extends BaseRelic {
    private static final String NAME = "TwoChamps";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public AbstractCard.CardColor pool = ShowtanCharacter.Enums.CARD_COLOR;
    public RelicType relicType = RelicType.SHARED;
    protected String imageName;

    private static final int HEAL_AMT = 4;
    private static final int DRAW_AMT = 2;
    private static final int ORB_AMT = 3;

    //for character specific relics
    public TwoChamps() {
        super(ID, NAME, ShowtanCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + DRAW_AMT + this.DESCRIPTIONS[1] + ORB_AMT + this.DESCRIPTIONS[2] + HEAL_AMT + this.DESCRIPTIONS[3];
    }


    @Override
    public void atPreBattle() {
        this.flash();
        addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new DrawCardAction(AbstractDungeon.player, DRAW_AMT));
        addToBot(new IncreaseMaxOrbAction(ORB_AMT));
        AbstractDungeon.player.channelOrb(new WeedleOrb());
        AbstractDungeon.player.channelOrb(new WeedleOrb());
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(ShowtanStarterRelic.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(ShowtanStarterRelic.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(ShowtanStarterRelic.ID);
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