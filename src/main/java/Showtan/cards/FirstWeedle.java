package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.orbs.WeedleOrb;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//DEPRECATED
public class FirstWeedle extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 1;
    public static final String ID = makeID("FirstWeedle");
    public FirstWeedle() {
        super(ID, info);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = upgraded ? 1 : 2;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new ChannelAction(new WeedleOrb()));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FirstWeedle();
    }
}
