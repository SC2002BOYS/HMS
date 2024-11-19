package Controller;
import java.util.Map;

public interface IAdminUpdateInven {
    void updateInventory();
    Map<String, Integer> loadInventory();
    void saveInventory(Map<String, Integer> inventory);
}
