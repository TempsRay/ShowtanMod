package Showtan.potions;

import Showtan.character.ShowtanCharacter;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Showtan.ShowtanMod.makeID;

public class ClearMindPotion extends BasePotion {
    public static final String ID = makeID("ClearMindPotion");
    private static final Color LIQUID_COLOR = CardHelper.getColor(0, 0, 255);
    private static final Color HYBRID_COLOR = CardHelper.getColor(255, 0, 0);
    private static final Color SPOTS_COLOR = null;


    public ClearMindPotion() {
        super(ID, 1, PotionRarity.RARE, PotionSize.EYE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        this.isThrown = false;
        this.targetRequired = false;
        playerClass = ShowtanCharacter.Enums.SHOWTAN;
        this.labOutlineColor = Settings.CREAM_COLOR;
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        addToBot(new RemoveDebuffsAction(AbstractDungeon.player));
      }
    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0];
    }
}
