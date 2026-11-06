public class MemoryAllocation
{
    //package access-reachable by memory manager
    //but not those that would use this module
    String owner;  //which process owns this memory
    long pos;      //where does it start
    long len;      //how long is the memory
    MemoryAllocation prev;
    MemoryAllocation next;




    //You might want to add additional data/methods here


    //feel free to alter the constructor if you need/want to
    public MemoryAllocation(String owner, long pos, long len, MemoryAllocation memoPrev, MemoryAllocation memoNext )

    {
	this.owner = owner;
	this.pos = pos;
	this.len=len;
    this.prev = memoPrev;
    this.next = memoNext;
    }


    public String getOwner()
    {
	return owner;
    }

    public long getPosition()
    {
	return pos;
    }

    public long getLength()
    {
	return len;
    }
    
    public MemoryAllocation getPrev(){
    return prev;
    }
    
    public MemoryAllocation getNext(){
    return next;
    }

    public String toString()
    {
	return "Alloc "+owner+" at "+pos+" for "+len; 
    }

}
