package info.ahaha.bankplugin;

import java.io.*;
import java.util.Map;
import java.util.UUID;

public class SaveAndLoad {
    public static void Save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("plugins/BankPlugin/bank.data"));
            out.writeObject(Bank.Bank);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Load() {
        File build = new File("plugins/BankPlugin/bank.data");
        if (!build.exists()) {
            try {
                build.createNewFile();
            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }
        try {
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new FileInputStream(build));
            Bank.Bank = (Map<UUID, Double>) inputStream.readObject();
        } catch (IOException ev) {
            ev.printStackTrace();
        } catch (ClassNotFoundException ev) {
            ev.printStackTrace();
        }
    }
}
