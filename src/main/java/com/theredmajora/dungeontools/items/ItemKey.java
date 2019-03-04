package com.theredmajora.dungeontools.items;

public class ItemKey extends ItemDungeon
{
	public String type;
	
	public ItemKey(String type)
	{
		super("key_" + type);
		this.type = type;
		if(type != "basic") this.setMaxStackSize(1);
	}
	
	public String getType()
	{
		return type;
	}
}
