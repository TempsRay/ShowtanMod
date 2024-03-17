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
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static Showtan.ShowtanMod.makeID;

public class WorkUpPotion extends BasePotion {
    public static final String ID = makeID("WorkUpPotion");
    private static final Color LIQUID_COLOR = CardHelper.getColor(214, 167, 212);
    private static final Color HYBRID_COLOR = null;
    private static final Color SPOTS_COLOR = CardHelper.getColor(168, 50, 162);


    public WorkUpPotion() {
        super(ID, 1, PotionRarity.UNCOMMON, PotionSize.S, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        this.isThrown = false;
        this.targetRequired = false;
        playerClass = ShowtanCharacter.Enums.SHOWTAN;
        this.labOutlineColor = Settings.CREAM_COLOR;

    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.potency), this.potency));
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, this.potency), this.potency));
    }
    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
    }
}
