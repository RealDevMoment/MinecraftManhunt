package me.een.manhunt.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import me.een.manhunt.Main;
import me.een.manhunt.commands.Start;

public class CompassPointer implements Listener {

	public static Player t;
	private Main plugin;

	public CompassPointer(Main main) {
		this.plugin = main;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {

		if (t == null) {
			return;
		}

		Player p = e.getPlayer();

		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getItemInHand().getType() == Material.COMPASS && p != t) {
			p.setCompassTarget(t.getLocation());
			p.sendMessage(ChatColor.GREEN + "Currently tracking " + ChatColor.WHITE + t.getName());
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		for (int i = 0; i < e.getDrops().size(); i++) {
			if (e.getDrops().get(i).getType() == Material.COMPASS) {
				e.getDrops().remove(i);
			}
		}
		
		if(t != null && e.getEntity().getPlayer() == t) {
			e.setDeathMessage(ChatColor.RED + e.getDeathMessage() + ", Hunter wins!");
		}
	}
	
	@EventHandler
	public void onAchivementGet(EntityDeathEvent e) {
		if(e.getEntityType() == EntityType.ENDER_DRAGON) {
			if(e.getEntity().getKiller() == t) {
				Bukkit.broadcastMessage(ChatColor.GREEN + "Speed Runner wins!");
			}
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				if (t != null && p != t) {
					p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
				}
			}
		}, 40L);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(p != t && Start.move == false) {
			e.setCancelled(true);
		}
	}
}
