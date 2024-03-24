package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class Shedinja extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC_NUMBER = 3;
    private static final int UPG_MAGIC_NUMBER = -1;
    private static final int INTANGIBLE_COUNT = 1;
    public static final String ID = makeID("Shedinja");
    public Shedinja() {
        super(ID, info);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, INTANGIBLE_COUNT), INTANGIBLE_COUNT));
        this.addToBot(new ApplyPowerAction(p, p, new PoisonPower(p, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Shedinja();
    }
}
