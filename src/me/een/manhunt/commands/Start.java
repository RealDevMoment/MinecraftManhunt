package me.een.manhunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.een.manhunt.Main;

public class Start implements CommandExecutor {
	
	public static Player t;
	
	Main plugin;
	
	public static boolean move = true;
	
	public Start(Main main) {
		main.getCommand("start").setExecutor(this);
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(!(arg0 instanceof Player)) {
			return true;
		}
	
		int delay = SetDelay.delay;
		
		for(Player p2 : Bukkit.getOnlinePlayers()) {
			if(p2 != t) {
				move = false;
				p2.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, delay * 20, 255));
			}
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				for(Player p2 : Bukkit.getOnlinePlayers()) {
					if(p2 != t) {
						move = true;
						p2.removePotionEffect(PotionEffectType.BLINDNESS);
					}
				}
			}
		}, delay * 20);
		
		return false;
	}

}
