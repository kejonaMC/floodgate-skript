package org.geysermc.floodgateskript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.geysermc.floodgate.FloodgateAPI;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Name("get UUID of Floodgate player")
@Description("returns the UUID of a Floodgate player")
public class GetFloodgateUUID extends SimpleExpression<UUID> {

    static {
        Skript.registerExpression(GetFloodgateUUID.class, UUID.class, ExpressionType.COMBINED,
                "[the] uuid of [the] [floodgate] %player%");
    }

    private Expression<Player> player;

    @Nullable
    @Override
    protected UUID[] get(Event e) {
        if (player.getSingle(e) != null) {
            if (FloodgateAPI.isBedrockPlayer(player.getSingle(e))) {
                return new UUID[] {FloodgateAPI.getPlayer(player.getSingle(e)).getCorrectUniqueId()};
            }
        }
        return new UUID[] {null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends UUID> getReturnType() {
        return UUID.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "Get Floodgate player's UUID: " + player.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        return true;
    }
}
