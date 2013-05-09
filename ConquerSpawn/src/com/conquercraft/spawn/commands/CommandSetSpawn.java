package com.conquercraft.spawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.conquercraft.spawn.ConquerSpawn;

public class CommandSetSpawn implements CommandExecutor
{
	ConquerSpawn plugin;

	public CommandSetSpawn(ConquerSpawn plugin)
	{
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arg3)
	{
		//Variables
		Player player = (Player) sender;
		
		// Makes sure that the person who sent the command is a player
		if (player instanceof Player)
		{
			// Makes sure the player has permission to run the command
			if (player.hasPermission("conquerspawn.setspawn"))
			{
				// Figure out the player's location
				Location location = player.getLocation();
				double xpos = location.getX();
				double ypos = location.getY();
				double zpos = location.getZ();
				String world = location.getWorld().getName();
				// Set the location to the config for storage
				ConquerSpawn.spawnConfig.set("spawn.cords.x",xpos);
				ConquerSpawn.spawnConfig.set("spawn.cords.y",ypos);
				ConquerSpawn.spawnConfig.set("spawn.cords.z",zpos);
				ConquerSpawn.spawnConfig.set("spawn.cords.world",world);
				// Tell the player the spawn was set
				player.sendMessage(ChatColor.DARK_AQUA + "[ConquerSpawn]" + ChatColor.AQUA + " Spawn point set!");
			}
			// If player doesnt have the permission, return true
			return true;
		}
		// If the sender isnt a player (probablly console, so color codes) Only return false if the command's arguments are incorrect
		sender.sendMessage("[ConquerSpawn] This command only works for players!");
		return true;
	}

}
