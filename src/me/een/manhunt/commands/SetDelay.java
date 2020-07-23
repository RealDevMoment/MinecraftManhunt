package me.een.manhunt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.een.manhunt.Main;

public class SetDelay implements CommandExecutor {
	
	public static int delay;
	
	public SetDelay(Main main) {
		main.getCommand("setdelay").setExecutor(this);
		delay = 0;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		if(!(arg0 instanceof Player)) {
			return true;
		}
		
		Player p = (Player) arg0;
		
		if(arg3.length != 0) {
			delay = Integer.parseInt(arg3[0]);
			p.sendMessage("Set delay to: " + delay + " seconds");
		} else {
			p.sendMessage("Specifiy an amount of seconds!");
		}
		
		return false;
	}

}
