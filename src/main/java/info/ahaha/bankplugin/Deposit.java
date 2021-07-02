package info.ahaha.bankplugin;

import info.ahaha.guiapi.Runs;
import info.ahaha.guiapi.abstract_GUI_Item;
import info.ahaha.guiapi.v2.AccepterAttributes;
import info.ahaha.guiapi.v2.Icon.Icon;
import info.ahaha.guiapi.v2.Icon.IconFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static info.ahaha.bankplugin.BankPlugin.playerlist;
import static info.ahaha.bankplugin.BankPlugin.thisPlugin;
import static org.bukkit.Bukkit.getServer;

public class Deposit extends Runs implements abstract_GUI_Item {

    public Deposit(int Slot) {
        icon = IconFactory.Make(Material.HOPPER, ChatColor.GRAY + ">> " + ChatColor.GOLD +""+ChatColor.BOLD+ "預ける",1," ");
        slot = Slot;
        setDefault((AccepterAttributes.augPlayer)(player)->{
            if (!playerlist.contains(player.getUniqueId().toString())) {
                playerlist.add(player.getUniqueId().toString());
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + "預ける金額を入力してください！");
                getServer().getPluginManager().registerEvents(new DepoChatListener(),thisPlugin);
            }
        });
    }

    private Icon icon;
    private int slot;

    @Override
    public ItemStack toItemStack(Player player) {

        ItemStack item = icon.toItem();
        ItemMeta meta = item.getItemMeta();
        List<String> Lore = meta.getLore();

        Lore.add(ChatColor.GRAY + "- " + ChatColor.GREEN + "所持金");
        Lore.add(ChatColor.YELLOW + String.valueOf(BankPlugin.getEconomy().getBalance(player))+" Coin");
       Lore.add(" ");
        Lore.add(ChatColor.GRAY + "- " + ChatColor.GREEN + "残高");
        Lore.add(ChatColor.YELLOW + String.valueOf(Bank.balanceBank(player.getUniqueId()))+" Coin");


        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack toDefaultItemStack() {
        return icon.toItem();
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public Runs getRuns(Player player) {
        return this;
    }
}

