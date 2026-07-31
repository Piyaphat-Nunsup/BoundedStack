import java.util.*;
public class Shop {
    /**
     * ฟังก์ชันรับค่า String ของสินค้า
     * 6821651493 ปิยพัทธ์ หนุนทรัพย์
     */
    public  static final int Max_items = 50;
    private final List<String> items;
    //Abstraction Function:
    // AF(items) = ลำดับของสินค้าในตะกร้า items.get(0),items.get(1), ... ตามลำดับ
    
    // Representation Invariant:
    //ต้องมีสินค้าอยู่จริง
    //ไม่มีสินค้าใด null
    //ไม่มีสินค้าที่เป็นสตริงว่าง
    //มีได้ไม่เกิน 50 ชิ้น

    // Safety from rep exposure:
    //   items เป็น private final
    //   คัดลอกทั้งขาเข้าและขาออก
     private void checkRep() {
        assert items != null : "Items is null";
        assert items.size() <= Max_items;
        Set<String> seen = new HashSet<>();
        for(String s : items){
            assert s != null;
            assert !s.isEmpty();
            assert seen.add(s);
        }
     // ======== Creator ========
    /**
     * สร้างตะกร้าสินค้าว่าง
     */
    }
    public Shop(){
        this.items = new ArrayList<>();
        checkRep();
    }
    /**
     * 
     * @param condition สินค้าในตะกร้าไม่เกิน 50 ชิ้น ไม่เป็น null
     * @throws IllegalArgumentException ถ้าผิดเงื่อนไข
     */
    public Shop(List<String> condition){
        if(condition==null) throw new IllegalArgumentException();
        if(condition.size()>Max_items) throw new IllegalArgumentException();
         Set<String> seen = new HashSet<>();
         for(String s : condition){
            if (s==null||s.isEmpty()) throw new IllegalArgumentException();
            if (!seen.add(s)) throw new IllegalArgumentException();
         }
        this.items = new ArrayList<>(condition);   
        checkRep();  
    }
    /**
     * คืนค่าจำนวนสินค้า
     */
    public int size(){
        return items.size();
    }
    /**
     * ตรวจสอบว่าสินค้ามีอยู่จริงมั้ย
     * @param item สินค้า
     * @return ส่งค่าสิน้คากลับ
     */
    public boolean contains(String item) {
        return items.contains(item);   
    }
    /**
     * เพิ่มสินค้าลงในตะกร้า
     * @param item ต้องไม่เป็น null และไม่ว่างเปล่า
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้าตะกร้าสินค้าเต็มแล้ว
     * @throws IllegalArgumentException ถ้า item เป็น null หรือไม่มี item
     */
    public boolean push(String item) {
       if (item == null || item.isEmpty())throw new IllegalArgumentException();
       if (items.size()>Max_items) return false;
       if (items.contains(item)) return false;
       items.add(item);
       checkRep();
       return true;
    }
    public List<String> items() {
        return new ArrayList<>(items);   
    }
    /**
     * ลบสินค้าออกจากตะกร้า
     * @return ชื่อสินค้าที่อยู่บนสุด
     * @throws EmptyStackException เมื่อตะกร้าว่าง
     */
    public String pop(){
        if(items.isEmpty()) {
            throw new IllegalArgumentException();
        }
        checkRep();
        return items.remove(items.size()-1);
    }
    /**
     * อ่านชื่อสินค้าโดยไม่ลบออก
     * @return ส่งชื่อ items กลับ
     */
    public String peek(){
        if (isEmpty()) throw new IllegalArgumentException();
        return items.get(items.size()-1);
    }
    /**
     * แก้ชื่อสินค้า
     * @param item ชื่อสินค้าเดิม
     * @param newitem ชื่อสินค้าใหม่
     * @return ชื่อสินค้าที่เปลี่ยนใหม่
    */
    public String moreItem(String item,String newitem){
        String new_item = item + newitem;
        return new_item;
    }
    // ตะกร้าสินค้าเต็ม
    public boolean isFull(){
         return items.size() >= Max_items;
    }
    // ตะกร้าสินค้าว่าง
    public boolean isEmpty(){
        return items.isEmpty();
    }
}