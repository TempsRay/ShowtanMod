package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.powers.ProudOfItsPowerPower;
import Showtan.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class ProudOfItsPower extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 1;
    private static final String TEXT = "I am proud of my power!";
    public static final String ID = makeID("ProudOfItsPower");
    public ProudOfItsPower() {
        super(ID, info);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT, true));
        this.addToBot(new ApplyPowerAction(p, p, new ProudOfItsPowerPower(p, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ProudOfItsPower();
    }
}
