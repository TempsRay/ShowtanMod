package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.orbs.KoffingOrb;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OUStall extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK_NUMBER = 5;
    private static final int UPG_BLOCK_NUMBER = 3;
    public static final String ID = makeID("OUStall");
    public OUStall() {
        super(ID, info);
        setBlock(BLOCK_NUMBER, UPG_BLOCK_NUMBER);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChannelAction(new KoffingOrb()));
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new DiscardPileToTopOfDeckAction(p));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new OUStall();
    }
}
