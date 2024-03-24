package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Dig extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    private static final int MAGIC_NUMBER = 3;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final int DEBUFF_AMT = 1;

    public static final String ID = makeID("Dig");
    public Dig() {
        super(ID, info);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, DEBUFF_AMT, false), DEBUFF_AMT));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, DEBUFF_AMT, false), DEBUFF_AMT));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Dig();
    }
}
