package info.ahaha.bankplugin;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bank {
    public static Map<UUID, Double> Bank = new HashMap<>();

    public static Map<UUID, Double> getBank() {
        return Bank;
    }

    public static void createBank(Player player) {
        if (!getBank().containsKey(player.getUniqueId())) {
            Bank.put(player.getUniqueId(), 0.0);
        }
    }

    public static void withdrawBank(UUID uuid, double amount) {

        Bank.put(uuid, Bank.get(uuid).doubleValue() - amount);
    }

    public static void depositBank(UUID uuid, double amount) {

        Bank.put(uuid, Bank.get(uuid).doubleValue() + amount);

    }

    public static Double balanceBank(UUID uuid) {
        return Bank.get(uuid);
    }
}
