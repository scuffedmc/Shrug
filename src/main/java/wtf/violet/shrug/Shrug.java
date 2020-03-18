/*
 *     Shrug - Discord's /shrug command ported to Minecraft
 *     Copyright (C) 2020  Violet M. <vi@violet.wtf>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package wtf.violet.shrug;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.violet.shrug.command.ShrugCommand;
import wtf.violet.shrug.util.ConfigUtil;

public final class Shrug extends JavaPlugin {

    @Getter private static boolean requirePermission;
    @Getter private static boolean bold;
    @Getter private static String permission;
    @Getter private static ChatColor color;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ConfigUtil config = new ConfigUtil(getConfig(), getLogger());

        requirePermission = config.getOption("require-permission", false, false);
        permission = config.getOption("permission", "shrug.command", requirePermission);
        color = config.getEnumOption("color", ChatColor.WHITE, ChatColor.class, true);
        bold = config.getOption("bold", false, true);

        getCommand("shrug").setExecutor(new ShrugCommand());
    }

}
