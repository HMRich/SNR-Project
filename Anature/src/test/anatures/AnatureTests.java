package test.anatures;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import application.anatures.abillities.NullAbility;
import application.anatures.movesets.MoveSet;
import application.anatures.movesets.NullMoveSet;
import application.anatures.stats.NullStats;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import application.interfaces.stats.IStats;
import application.pools.AbilityPool;
import test.TestObjects;

@DisplayName("Anature Tests")
class AnatureTests
{
	/*
	 * SETTER TESTS
	 */

	@Nested
	@DisplayName("Setter Tests")
	class SetterTests
	{
		@Nested
		@DisplayName("setName()")
		class setName
		{
			@DisplayName("with null or White Space")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullAndEmptySource
			@ValueSource(strings = { " ", "   ", "            ", "\b", "\t", "\n", "\f", "\r" })
			void SetName_WithWhiteSpaceNames_ThrowsIllegalArgumentException(String testString)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withName(testString);
				});
			}

			@DisplayName("with Valid Names")
			@ParameterizedTest(name = "sets anatures name to \"{0}\"")
			@ValueSource(strings = { "Hunter", "Gabe", "Jesse", "Colton", "Katherine" })
			void SetName_WithRealName_SetsTheName(String testString)
			{
				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withName(testString)
						.create();

				// assert
				assertTrue(sut.getName()
						.equals(testString));
			}
		}

		@Nested
		@DisplayName("setOwnerName()")
		class setOwnerName
		{
			@DisplayName("with Null or White Space")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullAndEmptySource
			@ValueSource(strings = { " ", "   ", "            ", "\b", "\t", "\n", "\f", "\r" })
			void SetOwnerName_WithWhiteSpaceNames_ThrowsIllegalArgumentException(String testString)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withOwnerName(testString);
				});
			}

			@DisplayName("with Valid Names")
			@ParameterizedTest(name = "sets anatures owners name to \"{0}\"")
			@ValueSource(strings = { "Hunter", "Gabe", "Jesse", "Colton", "Katherine" })
			void SetOwnerName_WithRealName_SetsTheOwnerName(String testString)
			{
				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withOwnerName(testString)
						.create();

				// assert
				assertTrue(sut.getOwner()
						.equals(testString));
			}
		}

		@Nested
		@DisplayName("setIsShiny()")
		class setIsShiny
		{
			@DisplayName("with true/false")
			@ParameterizedTest(name = "sets isShiny to \"{0}\"")
			@ValueSource(booleans = { true, false })
			void SetIsShiny_WithTrueOrFalse_SetsIsShinyValue(boolean testBoolean)
			{
				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.isShiny(testBoolean)
						.create();

				// assert
				assertTrue(sut.isShiny() == testBoolean);
			}
		}

		@Nested
		@DisplayName("setSpecies()")
		class setSpecies
		{
			@DisplayName("with Null or Null equivalent value")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullSource
			@EnumSource(value = Species.class,
						names = { "NotSet" },
						mode = EnumSource.Mode.INCLUDE)
			void SetSpecies_WithNotSet_ThrowsIllegalArgumentException(Species testSpecies)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withSpecies(testSpecies);
				});
			}

			@DisplayName("with Species Values")
			@ParameterizedTest(name = "sets species to \"{0}\"")
			@EnumSource(value = Species.class,
						names = { "NotSet" },
						mode = EnumSource.Mode.EXCLUDE)
			void SetSpecies_WithEachType_SetsSpeciesValue(Species testSpecies)
			{
				// exclude check
				if(testSpecies.equals(Species.NotSet))
					Assert.fail("Null equivalent Species value was not excluded from test.");

				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withSpecies(testSpecies)
						.create();

				// assert
				assertTrue(sut.getSpecies()
						.equals(testSpecies));
			}
		}

		@Nested
		@DisplayName("setGender()")
		class setGeder
		{

			@DisplayName("with Null or Null equivalent value")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullSource
			@EnumSource(value = Gender.class,
						names = { "NotSet" },
						mode = EnumSource.Mode.INCLUDE)
			void SetGender_WithNotSet_ThrowsIllegalArgumentException(Gender testGender)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withGender(testGender);
				});
			}

			@DisplayName("with Gender Values")
			@ParameterizedTest(name = "sets gender to \"{0}\"")
			@EnumSource(value = Gender.class,
						names = { "NotSet" },
						mode = EnumSource.Mode.EXCLUDE)
			void SetGender_WithEachType_SetsGenderValue(Gender testGender)
			{
				// exclude check
				if(testGender.equals(Gender.NotSet))
					Assert.fail("Null equivalent Gender value was not excluded from test.");

				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withGender(testGender)
						.create();

				// assert
				assertTrue(sut.getGender()
						.equals(testGender));
			}
		}

		@Nested
		@DisplayName("setPrimaryType()")
		class setPrimaryType
		{
			@DisplayName("with Null or Null equivalent value")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullSource
			@EnumSource(value = Type.class,
						names = { "NotSet" },
						mode = EnumSource.Mode.INCLUDE)
			void SetPrimaryType_WithNotSet_ThrowsIllegalArgumentException(Type testType)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withPrimaryType(testType);
				});
			}

			@DisplayName("with Type Values")
			@ParameterizedTest(name = "sets primary type to \"{0}\"")
			@EnumSource(value = Type.class,
						names = { "NotSet" },
						mode = EnumSource.Mode.EXCLUDE)
			void SetPrimaryType_WithEachType_ThrowsIllegalArgumentException(Type testType)
			{
				// dead check
				if(testType.equals(Type.NotSet))
					Assert.fail("Null equivalent Type value was not excluded from test.");

				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withPrimaryType(testType)
						.create();

				// assert
				assertTrue(sut.getPrimaryType()
						.equals(testType));
			}
		}

		@Nested
		@DisplayName("setSecondaryType()")
		class setSecondaryType
		{
			@DisplayName("value \"null\" throws IllegalArgumentException")
			@Test
			void SetSecondaryType_WithNull_ThrowsIllegalArgumentException()
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withSecondaryType(null);
				});
			}

			@DisplayName("with Real Values")
			@ParameterizedTest(name = "sets secondary type to \"{0}\"")
			@EnumSource(value = Type.class)
			void SetSecondaryType_WithEachType_SetsEachType(Type testType)
			{
				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withSecondaryType(testType)
						.create();

				// assert
				assertTrue(sut.getSecondaryType()
						.equals(testType));
			}
		}

		@Nested
		@DisplayName("setMoveSet()")
		class setMoveSet
		{
			@DisplayName("with Null or Null equivalent value")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullSource
			@MethodSource("test.anatures.AnatureTestsProviders#setNullMoveSet")
			void SetMoveSet_WithNull_ThrowsIllegalArgumentException(MoveSet moveSet)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withMoveSet(null);
				});
			}

			@DisplayName("with New MoveSet sets New MoveSet")
			@Test
			void SetMoveSet_WithRealMoveSet_SetsRealMoveSet()
			{
				// arrange
				MoveSet realMoveSet = TestObjects.getDefaultMoveSet()
						.getClone();
				realMoveSet.setMove(1, TestObjects.getDefaultTackle());

				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withMoveSet(realMoveSet)
						.create();

				// assert
				assertTrue(sut.getMoveSet()
						.equals(realMoveSet));
			}
		}

		@Nested
		@DisplayName("setAbility()")
		class setAbility
		{
			@DisplayName("with Null or Null equivalent value")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullSource
			@MethodSource("test.anatures.AnatureTestsProviders#setNullAbility")
			void SetAbility_WithNullAbility_ThrowsIllegalArgumentException(IAbility testAbility)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withAbility(testAbility);
				});
			}

			@DisplayName("with New Ability")
			@ParameterizedTest(name = "sets ability to \"{0}\"")
			@MethodSource("test.anatures.AnatureTestsProviders#setAbilities")
			void SetAbility_WithRealAbility_SetsAbility(IAbility testAbility)
			{
				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withAbility(testAbility)
						.create();

				// assert
				assertTrue(sut.getAbility()
						.equals(testAbility));
			}
		}

		@Nested
		@DisplayName("setStatus()")
		class setStatus
		{
			@DisplayName("with Null or Null equivalent value")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullSource
			@EnumSource(value = StatusEffects.class,
						names = { "NotSet" },
						mode = EnumSource.Mode.INCLUDE)
			void SetStatus_WithNotSet_ThrowsIllegalArgumentException(StatusEffects testStatusEffect)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withStatus(StatusEffects.NotSet);
				});
			}

			@DisplayName("with StatusEffect Values")
			@ParameterizedTest(name = "sets StatusEffect to \"{0}\"")
			@EnumSource(value = StatusEffects.class,
						names = { "NotSet" },
						mode = EnumSource.Mode.EXCLUDE)
			void SetStatus_WithEachStatus_SetsEachStatus(StatusEffects testStatus)
			{
				// exclude check
				if(testStatus.equals(StatusEffects.NotSet))
					Assert.fail("Null equivalent StatusEffect value was not excluded from test.");

				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withStatus(testStatus)
						.create();

				// assert
				assertTrue(sut.getStatus()
						.equals(testStatus));
			}
		}

		@Nested
		@DisplayName("setStats()")
		class setStats
		{
			@DisplayName("with Null or Null equivalent value")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullSource
			@MethodSource("test.anatures.AnatureTestsProviders#setNullStats")
			void SetStats_WithNullStats_ThrowsIllegalArgumentException(IStats testStats)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withStats(testStats);
				});
			}

			@DisplayName("with New Stats")
			@Test
			void SetStats_WithRealStats_SetsStats()
			{
				// arrange
				IStats newStats = TestObjects.getDefaultStats()
						.getClone()
						.create();

				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withStats(newStats)
						.create();

				// assert
				assertTrue(sut.getStats()
						.equals(newStats));
			}
		}

		@Nested
		@DisplayName("setIndexNumber()")
		class setIndexNumber
		{
			@DisplayName("with values < 0")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@ValueSource(ints = { -1, -42, -89, -100, -258746 })
			void SetIndexNumber_WithValueLessThanZero_ThrowsIllegalArgumentException(int testNumber)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature()
							.getClone()
							.withIndexNumber(testNumber);
				});
			}

			@DisplayName("with values >= 0")
			@ParameterizedTest(name = "sets index number to \"{0}\"")
			@ValueSource(ints = { 0, 1, 42, 89, 100, 258746 })
			void SetIndexNumber_WithValueEqualToOrGreaterThanZero_SetsIndexNumber(int testNumber)
			{
				// act
				IAnature sut = (IAnature) TestObjects.getAnature()
						.getClone()
						.withIndexNumber(testNumber)
						.create();

				// assert
				assertTrue(sut.getIndexNumber() == testNumber);
			}
		}
	}

	@Nested
	@DisplayName("Getter Tests")
	class GetterTests
	{
		@DisplayName("getName()")
		@Test
		void GetName_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getName()
					.equals(TestObjects.getDefaultAnatureName()));
		}

		@DisplayName("getOwner()")
		@Test
		void GetOwner_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getOwner()
					.equals(TestObjects.getDefaultOwnerName()));
		}

		@DisplayName("isShiny()")
		@Test
		void IsShiny_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.isShiny() == TestObjects.getDefaultShinyValue());
		}

		@DisplayName("getSpecies()")
		@Test
		void GetSpecies_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getSpecies()
					.equals(TestObjects.getDefaultSpecies()));
		}

		@DisplayName("getGender()")
		@Test
		void GetGender_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getGender()
					.equals(TestObjects.getDefaultGender()));
		}

		@DisplayName("getPrimaryType()")
		@Test
		void GetPrimaryType_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getPrimaryType()
					.equals(TestObjects.getDefaultPrimaryType()));
		}

		@DisplayName("getSecondaryType()")
		@Test
		void GetSecondaryType_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getSecondaryType()
					.equals(TestObjects.getDefaultSecondaryType()));
		}

		@DisplayName("getMoveSet()")
		@Test
		void GetMoveSet_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getMoveSet()
					.equals(TestObjects.getDefaultMoveSet()));
		}

		@DisplayName("getAbility()")
		@Test
		void GetAbility_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getAbility()
					.equals(TestObjects.getDefaultAbility()));
		}

		@DisplayName("getStatus()")
		@Test
		void GetStatus_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getStatus()
					.equals(TestObjects.getDefaultStatusEffect()));
		}

		@DisplayName("getStats()")
		@Test
		void GetStats_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getStats()
					.equals(TestObjects.getDefaultStats()));
		}

		@DisplayName("getIndexNumber()")
		@Test
		void GetIndexNumber_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature()
					.getIndexNumber() == TestObjects.getDefaultIndexNumber());
		}
	}

}

class AnatureTestsProviders
{
	/*
	 * MoveSet Provider Methods
	 */

	static Stream<MoveSet> setNullMoveSet()
	{
		return Stream.of(NullMoveSet.getNullMoveSet());
	}

	/*
	 * Ability Provider Methods
	 */

	static Stream<IAbility> setNullAbility()
	{
		return Stream.of(NullAbility.getNullAbility());
	}

	static Stream<IAbility> setAbilities()
	{
		Stream<IAbility> abilities = Stream.of();

		for(IAbility ability : AbilityPool.getAbilities())
		{
			abilities = Stream.concat(Stream.of(ability), abilities);
		}

		return abilities;
	}

	/*
	 * Stats Provider Methods
	 */

	static Stream<IStats> setNullStats()
	{
		return Stream.of(NullStats.getNullStats());
	}
}
