package com.github.camotoy.floodgate_skript.expressions;

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
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("get Bedrock platform")
@Description("Returns the device that the Floodgate player is playing on")
public class GetBedrockPlatform extends SimpleExpression<String> {

    static {
        Skript.registerExpression(GetBedrockPlatform.class, String.class, ExpressionType.COMBINED,
                "[the] [bedrock] (platform|device) of [the] [floodgate] %player%");
    }

    private Expression<Player> player;

    @Override
    protected String[] get(@NotNull Event e) {
        Player bukkitPlayer = player.getSingle(e);
        if (bukkitPlayer != null) {
            FloodgatePlayer floodgatePlayer = FloodgateApi.getInstance().getPlayer(bukkitPlayer.getUniqueId());
            if (floodgatePlayer != null) {
                return new String[] {floodgatePlayer.getDeviceOs().toString()};
            }
        }
        return new String[] {"Java"};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "Get Floodgate player's platform: " + player.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        return true;
    }
}
