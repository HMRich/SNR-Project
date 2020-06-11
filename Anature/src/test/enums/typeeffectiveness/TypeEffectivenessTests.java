package test.enums.typeeffectiveness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import application.enums.TypeEffectiveness;
import test.helpers.TestTags;

@DisplayName("Type Effectiveness Tests")
@Tag(TestTags.UnitTest)
public class TypeEffectivenessTests
{
	@Nested
	@DisplayName("getEffectiveness()")
	class getEffectiveness
	{
		@DisplayName("getEffectiveness() on \"NotSet\"")
		@Test
		public void GetEffectiveness_OnNotSet_ReturnsNegativeOne()
		{
			// assert
			assertEquals(TypeEffectiveness.NotSet.getEffectiveness(), -1);
		}

		@DisplayName("getEffectiveness() on \"NoEffect\"")
		@Test
		public void GetEffectiveness_OnNoEffect_ReturnsZero()
		{
			// assert
			assertEquals(TypeEffectiveness.NoEffect.getEffectiveness(), 0);
		}

		@DisplayName("getEffectiveness() on \"SeriouslyNotEffective\"")
		@Test
		public void GetEffectiveness_OnSeriouslyNotEffective_ReturnsZeroPointTwentyFive()
		{
			// assert
			assertEquals(TypeEffectiveness.SeriouslyNotEffective.getEffectiveness(), 0.25);
		}

		@DisplayName("getEffectiveness() on \"NotEffective\"")
		@Test
		public void GetEffectiveness_OnNotEffective_ReturnsZeroPointFive()
		{
			// assert
			assertEquals(TypeEffectiveness.NotEffective.getEffectiveness(), 0.5);
		}

		@DisplayName("getEffectiveness() on \"Normal\"")
		@Test
		public void GetEffectiveness_OnNormal_ReturnsOne()
		{
			// assert
			assertEquals(TypeEffectiveness.Normal.getEffectiveness(), 1);
		}

		@DisplayName("getEffectiveness() on \"SuperEffective\"")
		@Test
		public void GetEffectiveness_OnSuperEffective_ReturnsTwo()
		{
			// assert
			assertEquals(TypeEffectiveness.SuperEffective.getEffectiveness(), 2);
		}

		@DisplayName("getEffectiveness() on \"ExtremelyEffective\"")
		@Test
		public void GetEffectiveness_OnExtremelyEffective_ReturnsFour()
		{
			// assert
			assertEquals(TypeEffectiveness.ExtremelyEffective.getEffectiveness(), 4);
		}
	}

	@Nested
	@DisplayName("isAtOrAboveThreshold()")
	class isAtOrAboveThreshold
	{
		@ParameterizedTest(name = "with \"{0}\" returns -> \"{1}\"")
		@MethodSource("test.enums.typeeffectiveness.TypeEffectivenessTestProvider#getIsAtOrAboveThresholdTests")
		public void decrementEffectiveness_WithEffectivenessValue_ReturnsDecrementedEffectivenessValue(TypeEffectiveness testValue,
				TypeEffectiveness testThreshold, boolean expectedResult)
		{
			// assert
			assertEquals(testValue.isAtOrAboveThreshold(testThreshold), expectedResult);
		}
	}

	@Nested
	@DisplayName("decrementEffectiveness()")
	class decrementEffectiveness
	{
		@ParameterizedTest(name = "with \"{0}\" returns -> \"{1}\"")
		@MethodSource("test.enums.typeeffectiveness.TypeEffectivenessTestProvider#getDecrementEffectivenessTests")
		public void decrementEffectiveness_WithEffectivenessValue_ReturnsDecrementedEffectivenessValue(TypeEffectiveness toDecrement,
				TypeEffectiveness expectedEffectiveness)
		{
			// assert
			assertTrue(TypeEffectiveness.decrementEffectiveness(toDecrement).equals(expectedEffectiveness));
		}
	}
}

class TypeEffectivenessTestProvider
{
	static Object[] getIsAtOrAboveThresholdTests()
	{
		return new Object[] { //
				new Object[] { TypeEffectiveness.ExtremelyEffective, TypeEffectiveness.ExtremelyEffective, true },
				new Object[] { TypeEffectiveness.ExtremelyEffective, TypeEffectiveness.SuperEffective, true },
				new Object[] { TypeEffectiveness.ExtremelyEffective, TypeEffectiveness.Normal, true },
				new Object[] { TypeEffectiveness.ExtremelyEffective, TypeEffectiveness.SeriouslyNotEffective, true },
				new Object[] { TypeEffectiveness.ExtremelyEffective, TypeEffectiveness.NoEffect, true },
				new Object[] { TypeEffectiveness.ExtremelyEffective, TypeEffectiveness.ExtremelyEffective, true },
				//
				new Object[] { TypeEffectiveness.SuperEffective, TypeEffectiveness.ExtremelyEffective, false },
				new Object[] { TypeEffectiveness.SuperEffective, TypeEffectiveness.SuperEffective, true },
				new Object[] { TypeEffectiveness.SuperEffective, TypeEffectiveness.Normal, true },
				new Object[] { TypeEffectiveness.SuperEffective, TypeEffectiveness.NotEffective, true },
				new Object[] { TypeEffectiveness.SuperEffective, TypeEffectiveness.SeriouslyNotEffective, true },
				new Object[] { TypeEffectiveness.SuperEffective, TypeEffectiveness.NoEffect, true },
				//
				new Object[] { TypeEffectiveness.Normal, TypeEffectiveness.ExtremelyEffective, false },
				new Object[] { TypeEffectiveness.Normal, TypeEffectiveness.SuperEffective, false },
				new Object[] { TypeEffectiveness.Normal, TypeEffectiveness.Normal, true },
				new Object[] { TypeEffectiveness.Normal, TypeEffectiveness.NotEffective, true },
				new Object[] { TypeEffectiveness.Normal, TypeEffectiveness.SeriouslyNotEffective, true },
				new Object[] { TypeEffectiveness.Normal, TypeEffectiveness.NoEffect, true },
				//
				new Object[] { TypeEffectiveness.NotEffective, TypeEffectiveness.ExtremelyEffective, false },
				new Object[] { TypeEffectiveness.NotEffective, TypeEffectiveness.SuperEffective, false },
				new Object[] { TypeEffectiveness.NotEffective, TypeEffectiveness.Normal, false },
				new Object[] { TypeEffectiveness.NotEffective, TypeEffectiveness.NotEffective, true },
				new Object[] { TypeEffectiveness.NotEffective, TypeEffectiveness.SeriouslyNotEffective, true },
				new Object[] { TypeEffectiveness.NotEffective, TypeEffectiveness.NoEffect, true },
				//
				new Object[] { TypeEffectiveness.SeriouslyNotEffective, TypeEffectiveness.ExtremelyEffective, false },
				new Object[] { TypeEffectiveness.SeriouslyNotEffective, TypeEffectiveness.SuperEffective, false },
				new Object[] { TypeEffectiveness.SeriouslyNotEffective, TypeEffectiveness.Normal, false },
				new Object[] { TypeEffectiveness.SeriouslyNotEffective, TypeEffectiveness.NotEffective, false },
				new Object[] { TypeEffectiveness.SeriouslyNotEffective, TypeEffectiveness.SeriouslyNotEffective, true },
				new Object[] { TypeEffectiveness.SeriouslyNotEffective, TypeEffectiveness.SeriouslyNotEffective, true },
				//
				new Object[] { TypeEffectiveness.NoEffect, TypeEffectiveness.ExtremelyEffective, false },
				new Object[] { TypeEffectiveness.NoEffect, TypeEffectiveness.SuperEffective, false },
				new Object[] { TypeEffectiveness.NoEffect, TypeEffectiveness.Normal, false },
				new Object[] { TypeEffectiveness.NoEffect, TypeEffectiveness.NotEffective, false },
				new Object[] { TypeEffectiveness.NoEffect, TypeEffectiveness.SeriouslyNotEffective, false },
				new Object[] { TypeEffectiveness.NoEffect, TypeEffectiveness.NoEffect, true }, };
	}

	static Object[] getDecrementEffectivenessTests()
	{
		return new Object[] { //
				new Object[] { TypeEffectiveness.ExtremelyEffective, TypeEffectiveness.SuperEffective },
				new Object[] { TypeEffectiveness.SuperEffective, TypeEffectiveness.Normal },
				new Object[] { TypeEffectiveness.Normal, TypeEffectiveness.NotEffective },
				new Object[] { TypeEffectiveness.NotEffective, TypeEffectiveness.SeriouslyNotEffective },
				new Object[] { TypeEffectiveness.SeriouslyNotEffective, TypeEffectiveness.NoEffect },
				new Object[] { TypeEffectiveness.NoEffect, TypeEffectiveness.NoEffect }, };
	}
}
