package org.geysermc.floodgateskript.conditions;


import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.Skript;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.geysermc.floodgate.FloodgateAPI;
import org.jetbrains.annotations.Nullable;

@Name("Is from Floodgate")
@Description("Condition to check if a player is a Floodgate player")
public class IsBedrockPlayer extends Condition {

    static {
        Skript.registerCondition(IsBedrockPlayer.class, "%player% (1¦is|2¦is(n't| not)) [from] floodgate");
    }

    private Expression<Player> player;

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Is player from Floodgate: " + player.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        setNegated(parseResult.mark == 1);
        return true;
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) != null) {
            return (FloodgateAPI.isBedrockPlayer(player.getSingle(e))) == isNegated();
        }
        return isNegated();
    }
}
