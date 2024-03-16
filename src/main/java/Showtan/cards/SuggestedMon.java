package Showtan.cards;

import Showtan.character.ShowtanCharacter;
import Showtan.orbs.BraveOrb;
import Showtan.orbs.KoffingOrb;
import Showtan.orbs.TotemOrb;
import Showtan.orbs.WeedleOrb;
import Showtan.util.CardStats;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.*;

import java.util.ArrayList;

// DEPRECATED
public class SuggestedMon extends BaseCard{


    private static final CardStats info = new CardStats(
            ShowtanCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPG_MAGIC_NUMBER = 1;

    public static final String ID = makeID("Weedle");
    public SuggestedMon() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = this.upgraded ? 2 : 1;
    }

    private AbstractOrb getShitmonOrb(boolean useCardRng) {
        ArrayList<AbstractOrb> orbs = new ArrayList();
        orbs.add(new WeedleOrb());
        orbs.add(new BraveOrb());
        orbs.add(new TotemOrb());
        orbs.add(new KoffingOrb());
        return useCardRng ? (AbstractOrb)orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1)) : (AbstractOrb)orbs.get(MathUtils.random(orbs.size() - 1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        for(int i = 0; i < this.magicNumber; i++) {
            addToBot(new ChannelAction(getShitmonOrb(false)));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SuggestedMon();
    }
}
