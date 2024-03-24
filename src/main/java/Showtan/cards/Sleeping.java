package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;

public class Sleeping extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC_NUMBER = 1;
    public static final String ID = makeID("Sleeping");
    public Sleeping() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, this.magicNumber, false), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Sleeping();
    }
}
