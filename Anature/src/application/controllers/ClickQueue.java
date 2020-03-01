package application.controllers;

public class ClickQueue
{
	private class Node
	{
		private Runnable mData;
		private Node mNext;

		public Node(Runnable toRun)
		{
			this.mData = toRun;
			this.mNext = null;
		}
	}

	private Node mHead, mTail;
	private int mSize;
	
	public ClickQueue()
	{
		mHead = null;
		mTail = null;
		mSize = 0;
	}

	public boolean isEmpty()
	{
		if((mHead == null) && (mTail == null) || mSize == 0)
			return true;

		return false;
	}
	
	public void emptyQueue()
	{
		mHead = null;
		mTail = null;
		mSize = 0;
	}

	public void enqueue(Runnable val)
	{
		Node node = new Node(val);
		
		if(mSize == 0)
		{
			mHead = node;
		}
		
		else
		{
			mTail.mNext = node;
		}

		mTail = node;
		mSize++;
	}

	public Runnable dequeue()
	{
		if(mHead == null)
			return null;
		
		Runnable val;
		
		val = mHead.mData;
		mHead = mHead.mNext;
		mSize--;
		
		if(mSize == 0)
			mTail = null;
		
		return val;
	}

	public void clear()
	{
		mSize = 0;
		mHead = null;
		mTail = null;	
	}
	
	public void addToFront(Runnable val)
	{
		Node n = new Node(val);
		n.mNext = mHead;
		mHead = n;
		mSize++;
	}
	
	
	
	
	
}
