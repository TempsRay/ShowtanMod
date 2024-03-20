package Showtan.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.Iterator;

// Single target (CardTarget.ENEMY) cards do not show orb evoke values correctly in the base game
public class UpdateSingleTargetInputPatch {

    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "updateSingleTargetInput"
    )
    public static class SingleTargetOrbEvokePatch
    {

        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance) {
            if (__instance.hoveredCard.showEvokeValue) {
                if (__instance.hoveredCard.showEvokeOrbCount == 0) {
                    Iterator var2 = __instance.orbs.iterator();

                    while(var2.hasNext()) {
                        AbstractOrb o = (AbstractOrb)var2.next();
                        o.showEvokeValue();
                    }
                } else {
                    int tmpShowCount = __instance.hoveredCard.showEvokeOrbCount;
                    int emptyCount = 0;
                    Iterator var4 = __instance.orbs.iterator();

                    AbstractOrb o;
                    while(var4.hasNext()) {
                        o = (AbstractOrb)var4.next();
                        if (o instanceof EmptyOrbSlot) {
                            ++emptyCount;
                        }
                    }

                    tmpShowCount -= emptyCount;
                    if (tmpShowCount > 0) {
                        var4 = __instance.orbs.iterator();

                        while(var4.hasNext()) {
                            o = (AbstractOrb)var4.next();
                            o.showEvokeValue();
                            --tmpShowCount;
                            if (tmpShowCount <= 0) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}