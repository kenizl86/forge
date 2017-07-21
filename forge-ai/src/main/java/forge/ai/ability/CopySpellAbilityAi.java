package forge.ai.ability;

import forge.ai.SpecialCardAi;
import forge.ai.SpellAbilityAi;
import forge.game.player.Player;
import forge.game.spellability.SpellAbility;

import java.util.List;

public class CopySpellAbilityAi extends SpellAbilityAi {

    @Override
    protected boolean canPlayAI(Player aiPlayer, SpellAbility sa) {
        // the AI should not miss mandatory activations (e.g. Precursor Golem trigger)
        return sa.isMandatory() || "Always".equals(sa.getParam("AILogic"));
    }

    @Override
    protected boolean doTriggerAINoCost(Player aiPlayer, SpellAbility sa, boolean mandatory) {
        // the AI should not miss mandatory activations (e.g. Precursor Golem trigger)
        return mandatory || "Always".equals(sa.getParam("AILogic"));
    }

    @Override
    public boolean chkAIDrawback(final SpellAbility sa, final Player aiPlayer) {
        // NOTE: Other SAs that use CopySpellAbilityAi (e.g. Chain Lightning) are currently routed through
        // generic method SpellAbilityAi#chkDrawbackWithSubs and are handled there.

        if ("ChainOfSmog".equals(sa.getParam("AILogic"))) {
            return SpecialCardAi.ChainOfSmog.consider(aiPlayer, sa);
        }

        return super.chkAIDrawback(sa, aiPlayer);
    }

    @Override
    public SpellAbility chooseSingleSpellAbility(Player player, SpellAbility sa, List<SpellAbility> spells) {
        return spells.get(0);
    }
}

