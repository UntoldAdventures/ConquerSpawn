package com.conquercraft.spawn.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.conquercraft.spawn.ConquerSpawn;

public class CommandSpawn implements CommandExecutor
{

	ConquerSpawn plugin;

	public CommandSpawn(ConquerSpawn plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arg3)
	{
		// Variables
		Player player = (Player) sender;

		// Makes sure that the person who sent the command is a player
		if (player instanceof Player)
		{
			// Makes sure the player has permission to run the command
			if (player.hasPermission("conquerspawn.spawn"))
			{
				// Check if a spawn is set
				if (ConquerSpawn.spawnConfig.contains("spawn.cords.x"))
				{
					// Get the server
					Server server = player.getServer();
					// Get the spawn cords
					Double xpos = ConquerSpawn.spawnConfig.getDouble("spawn.cords.x");
					Double ypos = ConquerSpawn.spawnConfig.getDouble("spawn.cords.y");
					Double zpos = ConquerSpawn.spawnConfig.getDouble("spawn.cords.z");
					World world = server.getWorld(ConquerSpawn.spawnConfig.getString("spawn.cords.world"));

					// Turn it into a Location
					Location spawn = new Location(world, xpos, ypos, zpos);
					
					// Teleport the player
					player.teleport(spawn);
				}
				// Let the player know a spawn isn't set
				player.sendMessage((ChatColor.DARK_AQUA + "[ConquerSpawn]" + ChatColor.AQUA + " No Spawn Set!"));
			}
			// If player doesnt have the permission, return true
			return true;
		}
		// If the sender isnt a player (probablly console, so color codes) Only
		// return false if the command's arguments are incorrect
		sender.sendMessage("[ConquerSpawn] This command only works for players!");
		return true;
	}

}
