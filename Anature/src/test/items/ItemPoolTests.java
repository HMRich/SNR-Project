package test.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import application.enums.ItemIds;
import application.interfaces.IHealthPotion;
import application.pools.ItemPool;

@DisplayName("Item Pool Tests")
class ItemPoolTests
{

//	@Test
//	@DisplayName("")
//	void MethodName_StateUnderTest_ExpectedBehavior()
//	{
//		// arrange
//		
//		// act
//		
//		// assert
//		
//	}

	@Test
	@DisplayName("Potion Retrival Test")
	void GetHealthPotion_UsingItemIdsPotion_ReturnsPotion()
	{
		// act
		IHealthPotion sut = ItemPool.getHealthPotion(ItemIds.Potion);

		// assert
		assertEquals(ItemIds.Potion, sut.getItemId());
	}

	@Test
	@DisplayName("Great Potion Retrival Test")
	void GetHealthPotion_UsingItemIdsGreatPotion_ReturnsGreatPotion()
	{
		// act
		IHealthPotion sut = ItemPool.getHealthPotion(ItemIds.Great_Potion);

		// assert
		assertEquals(ItemIds.Great_Potion, sut.getItemId());
	}

	@Test
	@DisplayName("Ultra Potion Retrival Test")
	void GetHealthPotion_UsingItemIdsUltraPotion_ReturnsUltraPotion()
	{
		// act
		IHealthPotion sut = ItemPool.getHealthPotion(ItemIds.Ultra_Potion);

		// assert
		assertEquals(ItemIds.Ultra_Potion, sut.getItemId());
	}

	@Test
	@DisplayName("Master Potion Retrival Test")
	void GetHealthPotion_UsingItemIdsMasterPotion_ReturnsMasterPotion()
	{
		// act
		IHealthPotion sut = ItemPool.getHealthPotion(ItemIds.Master_Potion);

		// assert
		assertEquals(ItemIds.Master_Potion, sut.getItemId());
	}
}
