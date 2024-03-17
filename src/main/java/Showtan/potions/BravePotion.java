package Showtan.potions;

import Showtan.ShowtanMod;
import Showtan.character.ShowtanCharacter;
import Showtan.orbs.BraveOrb;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.lang.reflect.Field;

import static Showtan.ShowtanMod.makeID;

public class BravePotion extends BasePotion {
    public static final String ID = makeID("BravePotion");
    private static final Color LIQUID_COLOR = CardHelper.getColor(252, 0, 0);
    private static final Color HYBRID_COLOR = CardHelper.getColor(0, 0, 255);
    private static final Color SPOTS_COLOR = CardHelper.getColor(255, 255, 255);


    public BravePotion() {
        super(ID, 2, PotionRarity.RARE, PotionSize.SPHERE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        this.isThrown = false;
        this.targetRequired = false;
        playerClass = ShowtanCharacter.Enums.SHOWTAN;
        this.labOutlineColor = Settings.CREAM_COLOR;

    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (int i = 0; i < this.potency; i++) {
                addToBot(new ChannelAction(new BraveOrb()));
            }
        }
    }
    @Override
    public String getDescription() {
        return potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
    }
}
