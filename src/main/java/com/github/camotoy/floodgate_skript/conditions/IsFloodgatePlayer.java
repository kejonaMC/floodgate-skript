package com.github.camotoy.floodgate_skript.conditions;

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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Is from Floodgate")
@Description("Condition to check if a player is a Floodgate player")
public class IsFloodgatePlayer extends Condition {

    static {
        Skript.registerCondition(IsFloodgatePlayer.class, "%player% (1¦is|2¦is(n't| not)) [from] floodgate");
    }

    private Expression<Player> player;

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "Is player from Floodgate: " + player.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        setNegated(parseResult.mark == 1);
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        Player bukkitPlayer = player.getSingle(e);
        if (bukkitPlayer != null) {
            return (FloodgateAPI.isBedrockPlayer(bukkitPlayer)) == isNegated();
        }
        return isNegated();
    }
}
