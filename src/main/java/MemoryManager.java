public class MemoryManager
{
   protected MemoryAllocation head;
   long end;
   protected final String Free = "Free";


    /* size- how big is the memory space.  
     *  Memory starts at 0

     *
     */
   public MemoryManager(long size)
   {
      end = size;
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size,String requester)
   {
      if (size <= 0 || size > end){
         return null;
      }

      if (head == null){
         MemoryAllocation n = new MemoryAllocation(requester, 0, size, null, null);
         head = n;
         return head;

      } 
      
      if (head.getPosition() >= size){
         MemoryAllocation n = new MemoryAllocation(requester, 0, size, null, head);
         head.prev = n;
         head = n;
         return n;

      } 

      MemoryAllocation current = head;
      while (current != null) {
   
         if(current.next != null){
            if(current.next.getPosition() - (current.getLength() + current.getPosition()) >= size){
               MemoryAllocation n = new MemoryAllocation(requester, current.getLength() + current.getPosition(), size, current, current.next);
               current.next.prev = n;
               current.next = n;
               return n;
            }
         } else {
            if(current.getPosition() + current.getLength() + size <= end){
               MemoryAllocation n = new MemoryAllocation(requester, current.getLength() + current.getPosition(), size, current, current.next);
               current.next = n;
               return n;
            }
         }
         current = current.next;
      }
         return null;
   }



    
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
      if (mem == null || head == null){
         return;
      }

      if (head == mem){
         
         head = mem.next;
         if (head != null){
            head.prev = null;
         }
         mem.next = null;
         mem.prev = null;
         return;
      }

      if (mem.prev != null){
         mem.prev.next = mem.next;
      }
      if (mem.next != null){
         mem.next.prev = mem.prev;
      }

      mem.next = null;
      mem.prev = null;
   }
}