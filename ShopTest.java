
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        testAdd();
        testRemove();
        testObservers();
        testProducer();
        testExposure();

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
        System.out.println("--- Creator ---");

        Shop empty = new Shop();
        check("new()-> empty",empty.size()==0);
        check("new() -> contains nothing", !empty.contains("anything"));

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
    // ----- Mutator : add ----
    private static void testAdd() {
        System.out.println("\n --- Add items ---");
        
        Shop additem = new Shop();
        check("add(A) -> return true",additem.add("A"));
        check("add(A) -> size 1",additem.size()==1);
    }
    private static void testRemove() {
        
    }

    private static void testObservers() {
        
    }
    private static void testProducer() {
       
    }
    private static void testExposure() {
       
    }
   
}
