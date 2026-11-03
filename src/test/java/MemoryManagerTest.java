import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MemoryManager{

    MemoryManager storage = new MemoryManager(100);

    @Test
    void Memorymanager(){

        
        // 0 - 10
        MemoryAllocation richard = storage.requestMemory(10, "Richard");
        assertEquals("Richard", richard.getOwner());
        assertEquals(0, richard.getPosition());
        assertEquals(10, richard.getLength());

        // 0 - 10   to   11 - 31
        MemoryAllocatioin notRichard = storare.requestMemory(20, "NotRichard");
        assertEquals("NotRichard", richard.getOwner());
        assertEquals(11, notRichard.getPosition());
        assertEquals(31, notRichard.getLength());

        storage.returnMemory(richard);

        // 0 - 5    to   11 - 31
        MemoryAllocation smallRichard = storage.requestMemory(5, "smallRichard");
        assertEquals(0, smallRichard.getPosition());

        storage.returnMemory(smallRichard);
        storage.returnMemory(notRichard);


        // 0 - 101 ( Bigger than size)
        assertNull(storage.requestMemory(101, "fullRichard"));
        // 0 - 100
        MemoryAllocation fullRichard = storage.requestMemory(100, "fullRichard2");
        assertNull(storage.requestMemory(1, "fullRichard3"));
        
    }
}