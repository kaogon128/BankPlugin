package info.ahaha.bankplugin;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static info.ahaha.bankplugin.BankPlugin.*;
import static info.ahaha.bankplugin.BankPlugin.bankgui;

public class DepoChatListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        boolean isNumeric = true;
        for (int i = 0; i < e.getMessage().length(); i++) {
            if (!Character.isDigit(e.getMessage().charAt(i))) {
                isNumeric = false;

                e.getPlayer().sendMessage(ChatColor.RED + "数字を入力してください!");
                playerlist.remove(e.getPlayer().getUniqueId().toString());
                e.setCancelled(true);
                HandlerList.unregisterAll(this);
                return;
            }
        }
        if (getEconomy().getBalance(e.getPlayer()) < Double.parseDouble(e.getMessage())) {
            e.getPlayer().sendMessage(ChatColor.RED + "所持金が足りません");
            playerlist.remove(e.getPlayer().getUniqueId().toString());
            e.setCancelled(true);
            HandlerList.unregisterAll(this);
            return;

        } else {
            BankPlugin.getEconomy().withdrawPlayer(e.getPlayer(), Double.parseDouble(e.getMessage()));
            Bank.depositBank(e.getPlayer().getUniqueId(), Double.parseDouble(e.getMessage()));

            e.getPlayer().sendMessage(ChatColor.GOLD + e.getMessage() + " Coin" + ChatColor.GREEN + " 預けました!");
            e.getPlayer().sendMessage(ChatColor.GRAY+"--------------------------------");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.GREEN +"残高 : "+ChatColor.GOLD + Bank.balanceBank(e.getPlayer().getUniqueId()) + " Coin");
            e.getPlayer().sendMessage(ChatColor.GREEN +"所持金 : "+ChatColor.GOLD + BankPlugin.getEconomy().getBalance(e.getPlayer()) + " Coin");
            e.getPlayer().sendMessage("");
            e.getPlayer().sendMessage(ChatColor.GRAY+"--------------------------------");
        }
        playerlist.remove(e.getPlayer().getUniqueId().toString());
        e.setCancelled(true);


        HandlerList.unregisterAll(this);
    }


}
