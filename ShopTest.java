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
            System.out.println("WARNING: assertions disabled"
                    + " - e-run with: java -ea PlaylistTest\n");
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
    // ---- Partition : ว่าง / มีสินค้า / input ผิดเงื่อนไข ----
    private static void testCreators() {
        System.out.println(" --- Creator ---");
        // ตะกร้าสินค้าว่าง
        Shop empty = new Shop();
        check("new()-> empty",empty.size()==0);
        check("new() -> contains nothing", !empty.contains("anything"));

        // มีสินค้า
        Shop sp = new Shop(Arrays.asList("A","B","c"));
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
        check("add(A) -> return true",additem.push("A"));
        check("add(A) -> size 1",additem.size()==1);
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
        
        Shop ob = new Shop(Arrays.asList("A","B"));
        check("size is 2", ob.size()==2);
        check("find a item", ob.contains("A"));
        check("Can't find item", !ob.contains("C"));
    }
    private static void testProducer() {
        System.out.println("--- Producer ---");
       
    }
}