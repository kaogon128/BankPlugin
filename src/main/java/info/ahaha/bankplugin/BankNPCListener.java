package info.ahaha.bankplugin;

import info.ahaha.guiapi.GUIAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static info.ahaha.bankplugin.BankPlugin.bankgui;
import static info.ahaha.guiapi.GUIAPI.getNameConverter;

public class BankNPCListener implements Listener {
    @EventHandler
    public void onRightClick(NPCRightClickEvent e) {
        if (e.getNPC().getId() == 13) {
            if (!BankPlugin.getEconomy().getBanks().contains(e.getClicker().getUniqueId().toString())) {
                BankPlugin.getEconomy().createBank(e.getClicker().getUniqueId().toString(), e.getClicker());
                bankgui.addContent(new Withdraw(11));
                bankgui.addContent(new Balance(13));
                bankgui.addContent(new Deposit(15));
                GUIAPI.getList().get(getNameConverter().get("銀行")).openGUI(e.getClicker());

            } else {
                bankgui.addContent(new Withdraw(11));
                bankgui.addContent(new Balance(13));
                bankgui.addContent(new Deposit(15));
                GUIAPI.getList().get(getNameConverter().get("銀行")).openGUI(e.getClicker());

            }
        }
    }
}
