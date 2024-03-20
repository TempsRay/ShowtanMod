package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;

public class Dabears100 extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    private static final int BASE_MAGICNUMBER = 3;
    private static final int UPG_MAGICNUMBER = 1;

    public static final String ID = makeID("Dabears100");
    public Dabears100() {
        super(ID, info);
        setMagic(BASE_MAGICNUMBER, UPG_MAGICNUMBER);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = this.magicNumber;
    }

    @Override
    public void update() {
        this.showEvokeOrbCount = this.magicNumber;
        super.update();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            AbstractOrb orb = new Lightning();
            this.addToBot(new ChannelAction(orb));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Dabears100();
    }
}
