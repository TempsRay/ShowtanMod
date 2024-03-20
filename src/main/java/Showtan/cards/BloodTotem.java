package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.orbs.TotemOrb;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodTotem extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC_NUMBER = 2;
    public static final String ID = makeID("BloodTotem");
    public BloodTotem() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC_NUMBER);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = magicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new ChannelAction(new TotemOrb()));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BloodTotem();
    }
}
