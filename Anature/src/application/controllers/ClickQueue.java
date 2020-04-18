package application.controllers;

public class ClickQueue
{
	private class Node
	{
		private Runnable mData;
		@SuppressWarnings("unused")
		private String mName; // Used to identify what a runnable is during Debug mode
		private Node mNext;

		public Node(Runnable toRun, String name)
		{
			mData = toRun;
			mName = name;
			mNext = null;
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

	public void enqueue(Runnable val, String name)
	{
		Node node = new Node(val, name);
		
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
	
	public void enqueueToFront(Runnable val, String name)
	{
		Node node = new Node(val, name);
		
		if(mSize == 0)
		{
			mHead = node;
			mTail = node; 
		}
		
		else
		{
			node.mNext = mHead;
			mHead = node;
		}

		mSize++;
	}

	public void clear()
	{
		mSize = 0;
		mHead = null;
		mTail = null;	
	}
	
	public int size()
	{
		return mSize;
	}
	
	public String upNextName()
	{
		if(mHead == null)
		{
			return null;
		}
		
		return mHead.mName;
	}
}
