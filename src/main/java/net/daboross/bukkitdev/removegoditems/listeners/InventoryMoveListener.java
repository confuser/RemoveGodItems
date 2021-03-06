/*
 * Copyright (C) 2013-2014 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.removegoditems.listeners;

import net.daboross.bukkitdev.removegoditems.RGIListener;
import net.daboross.bukkitdev.removegoditems.RemoveGodItemsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryMoveListener implements RGIListener {

    private final RemoveGodItemsPlugin plugin;

    public InventoryMoveListener(RemoveGodItemsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryMove(InventoryDragEvent evt) {
        plugin.getChecker().checkItemsNextTick(evt.getWhoClicked(), evt.getInventorySlots());
    }

    @Override
    public void unregister() {
        InventoryDragEvent.getHandlerList().unregister(this);
    }

    @Override
    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
