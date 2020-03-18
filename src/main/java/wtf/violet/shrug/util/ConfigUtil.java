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

package wtf.violet.shrug.util;

import org.bukkit.configuration.ConfigurationSection;

import java.util.logging.Logger;

// TODO: Stop copying this across projects and put it in a library

/**
 * ConfigUtil: Violet's copy-pasted config loader thing.
 * @author Violet M. vi@violet.wtf
 */
public final class ConfigUtil {

    private ConfigurationSection section;
    private Logger logger;

    public ConfigUtil(ConfigurationSection section, Logger logger) {
        this.section = section;
        this.logger = logger;
    }

    public <T> T getOption(String key, T fallback, boolean warn) {
        Object value = section.get(key);

        if (value == null) {
            value = fallback;
            if (warn) {
                badConfig("Unspecified", key, fallback);
            }
        }

        try {
            return (T) value;
        } catch (Throwable rock) {
            badConfig("Bad type for", key, fallback);
        }

        return fallback;
    }


    public <E extends Enum<E>> E getEnumOption(
        String key,
        E fallback,
        Class<E> enumClass,
        boolean warn
    ) {
        String value = getOption(key, fallback.toString(), warn).toUpperCase();

        try {
            return E.valueOf(enumClass, value);
        } catch (IllegalArgumentException exception) {
            badConfig("Not a valid enum input: " + value + " for", key, fallback.toString());
            return fallback;
        }
    }

    private void badConfig(String prefix, String option, Object fallback) {
        logger.warning(prefix + " option: " + option + ", using fallback: " + fallback);
    }

}
