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

package wtf.violet.shrug.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wtf.violet.shrug.Shrug;

public final class ShrugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (
            Shrug.isRequirePermission() &&
                !(sender.hasPermission(Shrug.getPermission()) || sender.isOp())
        ) {
            error(sender, "You don't have permission to shrug ¯\\_(ツ)_/¯");
            return true;
        }

        if (sender instanceof Player) {
            String message = "¯\\_(ツ)_/¯";

            if (Shrug.isBold()) {
                message = ChatColor.BOLD + message;
            }

            message = Shrug.getColor() + message;

            if (args.length > 0) {
                message = String.join(" ", args) + " " + message;
            }

            ((Player) sender).chat(message);
        } else {
            error(sender, "You must have arms to shrug. (Players only)");
        }

        return true;
    }

    private static void error(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.RED + message);
    }

}
