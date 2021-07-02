package info.ahaha.bankplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static info.ahaha.bankplugin.BankPlugin.getEconomy;
import static info.ahaha.bankplugin.BankPlugin.playerlist;

public class WithChatListener implements Listener {
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
            e.getPlayer().sendMessage(ChatColor.RED + "残高が足りません");
            return;
        } else {
            BankPlugin.getEconomy().depositPlayer(e.getPlayer(), Double.parseDouble(e.getMessage()));
            BankPlugin.getEconomy().bankWithdraw(e.getPlayer().getUniqueId().toString(), Double.parseDouble(e.getMessage()));
            e.getPlayer().sendMessage(ChatColor.GOLD + e.getMessage() + " Coin" + ChatColor.GREEN + " 引き出しました！");
        }
        playerlist.remove(e.getPlayer().getUniqueId().toString());
        e.setCancelled(true);


        HandlerList.unregisterAll(this);
    }
}
