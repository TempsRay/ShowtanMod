package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.powers.DragonThatCloudsPower;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DragonThatClouds extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPG_MAGIC_NUMBER = 2;
    public static final String ID = makeID("DragonThatClouds");
    public DragonThatClouds() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DragonThatCloudsPower(p, this.block), this.block));
        addToBot(new ExhaustAction(this.magicNumber, false, true, true));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DragonThatClouds();
    }
}
