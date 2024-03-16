package Showtan.cards;

import Showtan.actions.WeedlesAction;
import Showtan.character.ShowtanCharacter;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Weedles extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -1
    );

    public static final String ID = makeID("Weedles");
    public Weedles() {
        super(ID, info);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 3;
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new WeedlesAction(p, this.energyOnUse, this.upgraded, this.freeToPlayOnce));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Weedles();
    }
}
