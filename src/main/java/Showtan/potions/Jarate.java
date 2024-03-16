package Showtan.potions;

import Showtan.character.ShowtanCharacter;
import Showtan.orbs.BraveOrb;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static Showtan.ShowtanMod.makeID;

public class Jarate extends BasePotion {
    public static final String ID = makeID("Jarate");


    public Jarate() {
        super(ID, 1, PotionRarity.COMMON, PotionSize.BOTTLE, PotionColor.ENERGY);
        this.isThrown = true;
        this.targetRequired = true;
        playerClass = ShowtanCharacter.Enums.SHOWTAN;
        this.labOutlineColor = Settings.CREAM_COLOR;

    }

    @Override
    public void use(AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new WeakPower(target, this.potency, false), this.potency));
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new VulnerablePower(target, this.potency, false), this.potency));
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new PoisonPower(target, AbstractDungeon.player, this.potency), this.potency));
    }
    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
    }
}
