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
      if (size > end){
         return null;
      }
      if (head == null){
         MemoryAllocation n = new MemoryAllocation(requester, 0, size, null, null);
         head = n;
         return head;
      } else if ( head.next == null && size + head.getLength() <= end){
         MemoryAllocation n = new MemoryAllocation(requester, head.getPosition() + head.getLength(), size, head, null);
         head.next = n;
         return n;
      } else {
         MemoryAllocation current = head;
         while (current.next != null) {
   
            if(current.getLength() + current.next.getPosition() <= size){
               MemoryAllocation n = new MemoryAllocation(requester, current.getLength() + current.getPosition(), size, current, current.next);
               current.next = n;
               n.next.prev = n;      
               return n;
            }

            current = current.next;
         }
         if(current.next == null){
            if(current.getPosition() + current.getLength() <= size){
               MemoryAllocation n = new MemoryAllocation(requester, current.getPosition() + current.getLength(), size, current, null);
               current.next = n;
               return n;
            }
         }

      return null;
         
         
      }
   }


    
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
      if (mem == null){
         return;
      }

      MemoryAllocation current = head;
      if (current == mem){
         head = mem.next;
         head.prev = null;
         return;
      }

      while( current.next != null ){
         if ( current == mem ){
            current.prev.next = current.next;
            current.next.prev = current.prev;
            break;
         }
         current = current.next;
      }
      if (current.next == null){
         current.prev.next = null;
      }
   }
    



}
