import java.util.*;
public class ShopTest {
    /**
     * Test runner
     */
    private static int passed = 0;
    private static int failed = 0;
    
    private static void check(String name,boolean condition){
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }
    public static void main(String[] args){
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled"+ " - e-run with: java -ea PlaylistTest\n");
        }
        System.out.println("--- items in the cart ---\n");

        testCreators();
        testPush();
        testPop();
        testObservers();
        testProducer();
        
        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }
    // ---- Partition : ตะกร้าสินค้าว่าง / มีสินค้า / ตะกร้าสินค้าไม่เป็น null
    private static void testCreators() {
        System.out.println(" --- Creator ---");
        // ตะกร้าสินค้าว่าง
        Shop empty = new Shop();
        check("new()-> empty",empty.size()==0);
        check("new() -> contains nothing", !empty.contains("anything"));

        // มีสินค้า
        Shop sp = new Shop(Arrays.asList("doll","ball","cloth"));
        check("Items in cart == 1",sp.contains("doll"));
        check("Items in cart == 2",sp.contains("ball"));
        check("Items in cart == 3",sp.contains("cloth"));
        check("new(item) -> size 3",sp.size() ==3);
       
        // input ผิดเงื่อนไข
        boolean threwNull = false;
        try {
          new Shop(null);
        } catch (Exception e) {
          threwNull = true;
        }
        check("new(null) -> throws IllegalArgumentException", threwNull);
    }
    // Mutator : การเพิ่มสินค้า
    private static void testPush() {
        System.out.println("\n --- Add items ---");
        
        Shop additem = new Shop();
        additem.push("Cloths");
        check("add(Cloths) -> return true",additem.contains("Cloths"));
        check("add(Cloths) -> size 1",additem.size()==1);
        check("Items is Cloths", additem.peek() == "Cloths");

        boolean threwempty = false;
        try {
            additem.push("");
        } catch (Exception e) {
            threwempty = true;
        }
        check("Cart is empty", threwempty);

        Shop full = new Shop();
        for (int i = 0; i < Shop.Max_items; i++) {
            full.push("Shop"+i);
        }
        check("When is full", full.isFull());
        check("Can't fill item", full.size() == Shop.Max_items);
        check("Cart stay max!!!", full.size() == Shop.Max_items);
    }
    /**
     *  Mutator : การลบสินค้า
     *  stack ลดลงเมื่อ pop
     *  ตัวที่โดน pop ไปแล้วจะไม่เจออีก
     *  stack ว่างเมื่อ pop ทุกตัว
    */ 
     private static void testPop(){
    System.out.println("=== Pop Test ===");

    Shop shop = new Shop(Arrays.asList("A","B","C"));
    check("pop returns C",shop.pop().equals("C"));
    check("size decreases",shop.size() == 2);
    check("C was removed",!shop.contains("C"));
    check("next pop returns B",shop.pop().equals("B"));
    check("next pop returns A",shop.pop().equals("A"));
    check("stack is empty",shop.size() == 0);

    }
    // ดึงค่าสินค้ามาโชว์
    private static void testObservers() {
        System.out.println("--- Observer ---");
        
        Shop ob = new Shop();
        check("Item in cart -> Cloth",ob.push("Cloth"));
        check("Item in cart -> Cloth,Jean",ob.push("Jean"));
        check("size is -> 2", ob.size()==2);
        check("find a item -> Cloth", ob.contains("Cloth"));
        check("Can't find item -> Jeanbaggy", !ob.contains("Jeanbaggy"));
    }
    // แก้ไขจากชื่อสินค้าเดิม
    private static void testProducer() {
        System.out.println("--- Producer ---");

        Shop newitem = new Shop(Arrays.asList("Cloth","Jean","telephone"));
        check("new item is Jeanbaggy", newitem.moreItem("Jean", "baggy").equals("Jeanbaggy"));
    }
}