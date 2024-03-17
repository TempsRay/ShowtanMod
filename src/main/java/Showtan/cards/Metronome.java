package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Metronome extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1
    );
    public static final String ID = makeID("Metronome");
    public Metronome() {
        super(ID, info);
        setCostUpgrade(0);
        setExhaust(true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        double r = Math.random();
        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat();

        c.setCostForTurn(0);
        addToBot(new MakeTempCardInHandAction(c, true));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Metronome();
    }
}
