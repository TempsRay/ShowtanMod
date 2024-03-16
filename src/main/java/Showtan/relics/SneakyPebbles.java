package Showtan.relics;

import Showtan.character.ShowtanCharacter;
import basemod.helpers.RelicType;
import com.evacipated.cardcrawl.mod.stslib.relics.OnPlayerDeathRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Iterator;

import static Showtan.ShowtanMod.makeID;

public class SneakyPebbles extends BaseRelic {
    private static final String NAME = "SneakyPebbles";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public AbstractCard.CardColor pool = ShowtanCharacter.Enums.CARD_COLOR;
    public RelicType relicType = RelicType.SHARED;
    protected String imageName;
    private final float MODIFIER_AMT = 0.125F;

    //for character specific relics
    public SneakyPebbles() {
        super(ID, NAME, ShowtanCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 100 * this.MODIFIER_AMT + this.DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        if (!AbstractDungeon.getCurrRoom().eliteTrigger) {
            this.flash();
            Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

            while (var1.hasNext()) {
                AbstractMonster m = (AbstractMonster) var1.next();
                if ((m.type != AbstractMonster.EnemyType.BOSS)) {
                    m.currentHealth = (int) ((float) m.maxHealth * (1.0F - this.MODIFIER_AMT));
                    m.healthBarUpdatedEvent();
                }
            }

            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }
}