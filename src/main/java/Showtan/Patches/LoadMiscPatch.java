package Showtan.Patches;

import Showtan.cards.Donnel;
import Showtan.cards.DrunkenFist;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import javassist.CtBehavior;

public class LoadMiscPatch {
    @SpirePatch(
            clz = CardLibrary.class,
            method = "getCopy",
            paramtypez = { String.class, int.class, int.class }
    )
    public static class MiscPatch
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "retVal" }
        )
        public static void LoadMisc(String key, int upgradeTime, int misc, AbstractCard retVal)
        {
            //  Apparently I don't need this since CustomCard now has a method patched for you to that timing called onMiscLoaded
            if (retVal.cardID.equals(Donnel.ID))
            {
                retVal.damage = misc;
                retVal.baseDamage = misc;
                retVal.initializeDescription();
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "misc");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}