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

public class Withdraw extends Runs implements abstract_GUI_Item {

    public Withdraw(int Slot) {
        icon = IconFactory.Make(Material.DISPENSER, ChatColor.GRAY + ">> " + ChatColor.GOLD +""+ChatColor.BOLD+ "引き出す",1," ");
        slot = Slot;
        setDefault((AccepterAttributes.augPlayer)(player)->{
            if (!playerlist.contains(player.getUniqueId().toString())) {
                playerlist.add(player.getUniqueId().toString());
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + "引き出す金額を入力してください！");
                getServer().getPluginManager().registerEvents(new WithChatListener(),thisPlugin);
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

        Lore.add(ChatColor.GRAY + "- " + ChatColor.GREEN + "残高");
        Lore.add(ChatColor.YELLOW + String.valueOf(BankPlugin.getEconomy().bankBalance(player.getUniqueId().toString()).balance)+" Coin");


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

