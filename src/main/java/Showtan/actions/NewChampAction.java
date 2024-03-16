package Showtan.actions;

import Showtan.orbs.WeedleOrb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeAllOrbsAction;
import com.megacrit.cardcrawl.actions.defect.RemoveAllOrbsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class NewChampAction extends AbstractGameAction {


    private boolean upgraded = false;
    private int magicNumber;

    public NewChampAction(int magicNumber, boolean upgraded) {
        this.magicNumber = magicNumber;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.ENERGY;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
//            int orbCount = AbstractDungeon.player.filledOrbCount();
            this.addToTop(new DrawCardAction(AbstractDungeon.player, magicNumber));
            if (this.upgraded) {
                this.addToTop(new EvokeAllOrbsAction());
            } else {
                this.addToTop(new RemoveAllOrbsAction());
            }
            addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.hand.size(), false));
        }

        this.tickDuration();
    }
}
