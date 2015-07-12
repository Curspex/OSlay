package com.adamedw.oslay.command;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.adamedw.oslay.OSlay;

public class CommandOSlay implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length < 1) return false;

		UUID uuid = args[0].length() > 16 ? UUID.fromString(args[0]) : null;
		if (uuid == null)
		{
			@SuppressWarnings("deprecation")
			OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
			args[0] = player.getName();
			uuid = player.getUniqueId();
		}
		Player player = Bukkit.getPlayer(uuid);
		if (player != null && player.isOnline())
		{
			sender.sendMessage(ChatColor.GREEN + args[0] + " was already online, and has been killed.");
			player.setHealth(0);
			return true;
		}
		boolean contains = OSlay.get().getPending().contains(uuid);
		if (contains)
		{
			OSlay.get().getPending().remove(uuid);
		}
		else
		{
			OSlay.get().getPending().add(uuid);
		}
		sender.sendMessage(contains ? ChatColor.RED + args[0] + " removed from oslay." : ChatColor.GREEN + args[0] + " added to oslay.");

		return true;
	}


}