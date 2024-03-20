package Showtan.cards;

import Showtan.actions.NewChampAction;
import Showtan.character.ShowtanCharacter;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NewChamp extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            0
    );
    private static final int MAGIC_NUMBER = 5;
    private static final int UPG_MAGIC_NUMBER = 2;
    public static final String ID = makeID("NewChamp");
    public NewChamp() {
        super(ID, info);
        setMagic(MAGIC_NUMBER);
        setExhaust(true);
        this.showEvokeValue = this.upgraded;
        this.showEvokeOrbCount = 0;
    }

    @Override
    public void update() {
        this.showEvokeValue = this.upgraded;
        super.update();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new NewChampAction(this.magicNumber, this.upgraded));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new NewChamp();
    }
}
