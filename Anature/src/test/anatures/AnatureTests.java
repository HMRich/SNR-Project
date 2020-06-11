package test.anatures;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import application.anatures.Anature;
import application.anatures.AnatureVariables;
import application.anatures.abillities.NullAbility;
import application.anatures.movesets.MoveSet;
import application.anatures.movesets.MoveSetTestable;
import application.anatures.movesets.NullMoveSet;
import application.anatures.stats.NullStats;
import application.anatures.stats.StatsTestable;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;
import application.interfaces.stats.IStats;
import application.pools.AbilityPool;
import test.helpers.TestObjects;
import test.helpers.TestTags;
import test.testableObjects.AnatureTestable;

@DisplayName("Anature Tests")
@Tag(TestTags.UnitTest)
public class AnatureTests
{
	/*
	 * BUILDER TESTS
	 */

	@DisplayName("Move Builder Test")
	@Test
	@BeforeAll
	static void MoveBuilderTest()
	{
		assertDoesNotThrow(() ->
		{
			TestObjects.getDefaultTackle();
		}, "Move Builder threw an Exception");
	}

	@DisplayName("Move Builder Test")
	@Test
	@BeforeAll
	static void StatBuilderTest()
	{
		assertDoesNotThrow(() ->
		{
			TestObjects.getDefaultStats();
		}, "Stat Builder threw an Exception");
	}

	@DisplayName("Move Builder Test")
	@Test
	@BeforeAll
	static void AnatureBuilderTest()
	{
		assertDoesNotThrow(() ->
		{
			TestObjects.getAnature();
		}, "Anature Builder threw an Exception");
	}

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
					TestObjects.getAnature().getClone().setName(testString);
				});
			}

			@DisplayName("with Valid Names")
			@ParameterizedTest(name = "sets anatures name to \"{0}\"")
			@ValueSource(strings = { "Hunter", "Gabe", "Jesse", "Colton", "Katherine" })
			void SetName_WithRealName_SetsTheName(String testString)
			{
				// act
				Anature sut = TestObjects.getAnature().getClone().setName(testString);

				// assert
				assertTrue(sut.getName().equals(testString));
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
					TestObjects.getAnature().getClone().setOwnerName(testString);
				});
			}

			@DisplayName("with Valid Names")
			@ParameterizedTest(name = "sets anatures owners name to \"{0}\"")
			@ValueSource(strings = { "Hunter", "Gabe", "Jesse", "Colton", "Katherine" })
			void SetOwnerName_WithRealName_SetsTheOwnerName(String testString)
			{
				// act
				Anature sut = TestObjects.getAnature().getClone().setOwnerName(testString);

				// assert
				assertTrue(sut.getOwner().equals(testString));
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
				Anature sut = TestObjects.getAnature().getClone().setIsShiny(testBoolean);

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
					TestObjects.getAnature().getClone().setSpecies(testSpecies);
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
				Anature sut = TestObjects.getAnature().getClone().setSpecies(testSpecies);

				// assert
				assertTrue(sut.getSpecies().equals(testSpecies));
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
					TestObjects.getAnature().getClone().setGender(testGender);
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
				Anature sut = TestObjects.getAnature().getClone().setGender(testGender);

				// assert
				assertTrue(sut.getGender().equals(testGender));
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
					TestObjects.getAnature().getClone().setPrimaryType(testType);
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
				Anature sut = TestObjects.getAnature().getClone().setPrimaryType(testType);

				// assert
				assertTrue(sut.getPrimaryType().equals(testType));
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
					TestObjects.getAnature().getClone().setSecondaryType(null);
				});
			}

			@DisplayName("with Real Values")
			@ParameterizedTest(name = "sets secondary type to \"{0}\"")
			@EnumSource(value = Type.class)
			void SetSecondaryType_WithEachType_SetsEachType(Type testType)
			{
				// act
				Anature sut = TestObjects.getAnature().getClone().setSecondaryType(testType);

				// assert
				assertTrue(sut.getSecondaryType().equals(testType));
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
					TestObjects.getAnature().getClone().setMoveSet(null);
				});
			}

			@DisplayName("with New MoveSet sets New MoveSet")
			@Test
			void SetMoveSet_WithRealMoveSet_SetsRealMoveSet()
			{
				// arrange
				MoveSet realMoveSet = TestObjects.getDefaultMoveSet().getClone();
				realMoveSet.setMove(1, TestObjects.getDefaultTackle());

				// act
				Anature sut = TestObjects.getAnature().getClone().setMoveSet(realMoveSet);

				// assert
				assertTrue(sut.getMoveSet().equals(realMoveSet));
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
					TestObjects.getAnature().getClone().setAbility(testAbility);
				});
			}

			@DisplayName("with New Ability")
			@ParameterizedTest(name = "sets ability to \"{0}\"")
			@MethodSource("test.anatures.AnatureTestsProviders#setAbilities")
			void SetAbility_WithRealAbility_SetsAbility(IAbility testAbility)
			{
				// act
				Anature sut = TestObjects.getAnature().getClone().setAbility(testAbility);

				// assert
				assertTrue(sut.getAbility().equals(testAbility));
			}
		}

		@Nested
		@DisplayName("setStatus()")
		class setStatus
		{
			@DisplayName("with Null or Null equivalent value")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@NullSource
			void SetStatus_WithNotSet_ThrowsIllegalArgumentException(StatusEffects testStatusEffect)
			{
				// assert
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getAnature().getClone().setStatus(testStatusEffect);
				});
			}

			@DisplayName("with StatusEffect Values")
			@ParameterizedTest(name = "sets StatusEffect to \"{0}\"")
			@EnumSource(StatusEffects.class)
			void SetStatus_WithEachStatus_SetsEachStatus(StatusEffects testStatus)
			{
				// act
				Anature sut = TestObjects.getAnature().getClone().setStatus(testStatus);

				// assert
				assertTrue(sut.getStatus().equals(testStatus));
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
					TestObjects.getAnature().getClone().setStats(testStats);
				});
			}

			@DisplayName("with New Stats")
			@Test
			void SetStats_WithRealStats_SetsStats()
			{
				// arrange
				IStats newStats = TestObjects.getDefaultStats().getClone().create();

				// act
				Anature sut = TestObjects.getAnature().getClone().setStats(newStats);

				// assert
				assertTrue(sut.getStats().equals(newStats));
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
					TestObjects.getAnature().getClone().setIndexNumber(testNumber);
				});
			}

			@DisplayName("with values >= 0")
			@ParameterizedTest(name = "sets index number to \"{0}\"")
			@ValueSource(ints = { 0, 1, 42, 89, 100, 258746 })
			void SetIndexNumber_WithValueEqualToOrGreaterThanZero_SetsIndexNumber(int testNumber)
			{
				// act
				Anature sut = TestObjects.getAnature().getClone().setIndexNumber(testNumber);

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
			assertTrue(TestObjects.getAnature().getName().equals(TestObjects.getDefaultAnatureName()));
		}

		@DisplayName("getOwner()")
		@Test
		void GetOwner_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getOwner().equals(TestObjects.getDefaultOwnerName()));
		}

		@DisplayName("isShiny()")
		@Test
		void IsShiny_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().isShiny() == TestObjects.getDefaultShinyValue());
		}

		@DisplayName("getSpecies()")
		@Test
		void GetSpecies_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getSpecies().equals(TestObjects.getDefaultSpecies()));
		}

		@DisplayName("getGender()")
		@Test
		void GetGender_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getGender().equals(TestObjects.getDefaultGender()));
		}

		@DisplayName("getPrimaryType()")
		@Test
		void GetPrimaryType_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getPrimaryType().equals(TestObjects.getDefaultPrimaryType()));
		}

		@DisplayName("getSecondaryType()")
		@Test
		void GetSecondaryType_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getSecondaryType().equals(TestObjects.getDefaultSecondaryType()));
		}

		@DisplayName("getMoveSet()")
		@Test
		void GetMoveSet_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getMoveSet().deepEquals(TestObjects.getDefaultMoveSet()));
		}

		@DisplayName("getAbility()")
		@Test
		void GetAbility_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getAbility().equals(TestObjects.getDefaultAbility()));
		}

		@DisplayName("getStatus()")
		@Test
		void GetStatus_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getStatus().equals(TestObjects.getDefaultStatusEffect()));
		}

		@DisplayName("getStats()")
		@Test
		void GetStats_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getStats().deepEquals(TestObjects.getDefaultStats()));
		}

		@DisplayName("getIndexNumber()")
		@Test
		void GetIndexNumber_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getIndexNumber() == TestObjects.getDefaultIndexNumber());
		}

		@DisplayName("getCatchRate()")
		@Test
		void GetCatchRate_FromDefaultAnature_ReturnsDefaultValue()
		{
			// assert
			assertTrue(TestObjects.getAnature().getCatchRate() == TestObjects.getDefaultCatchRate());
		}
	}

	@Nested
	@DisplayName("Public Method Tests")
	class PublicMethodTests
	{
		/*
		 * MOCK OBJECTS
		 */

		private MockAnature mMockAnature;
		private MockAnatureStats mMockAnatureStats;
		private MockAnatureMoveSet mMockAnatureMoveSet;

		MockAnature getMockAnature()
		{
			return mMockAnature;
		}

		MockAnatureStats getMockAnatureStats()
		{
			return mMockAnatureStats;
		}

		MockAnatureMoveSet getMockAnatureMoveSet()
		{
			return mMockAnatureMoveSet;
		}

		@BeforeEach
		void BeforeEachTest()
		{
			mMockAnature = new MockAnature(TestObjects.getDefaultAnatureVariables());
			mMockAnatureStats = new MockAnatureStats();
			mMockAnatureMoveSet = new MockAnatureMoveSet();
		}

		/*
		 * MOCK CLASSES
		 */

		class MockAnature extends AnatureTestable
		{
			public MockAnature(AnatureVariables context)
			{
				super(context);
			}

			private static final long serialVersionUID = 5562999580260106159L;

			private boolean mGetStatsCalled;
			private boolean mGetMoveSetCalled;

			@Override
			public IStats getStats()
			{
				mGetStatsCalled = true;
				return getMockAnatureStats();
			}

			@Override
			public MoveSet getMoveSet()
			{
				mGetMoveSetCalled = true;
				return getMockAnatureMoveSet();
			}

			boolean getStatsWasCalled()
			{
				return mGetStatsCalled;
			}

			boolean getMoveSetWasCalled()
			{
				return mGetMoveSetCalled;
			}
		}

		class MockAnatureStats extends StatsTestable
		{
			private static final long serialVersionUID = 5562999524345627159L;
		}

		class MockAnatureMoveSet extends MoveSetTestable
		{
			private static final long serialVersionUID = 5562923452627706159L;
		}

		/*
		 * PUBLIC METHOD TESTS
		 */

		@DisplayName("setName()")
		@Test
		void UpdateName_CallsSetNameMethod_UpdatesName()
		{
			// act
			getMockAnature().setName("<Test Name>");

			// assert
			assertTrue(getMockAnature().setNameWasCalled());
		}

		@DisplayName("setStatus()")
		@Test
		void UpdateStatus_CallsSetStatusMethod_UpdatesStatus()
		{
			// act
			getMockAnature().setStatus(StatusEffects.None);

			// assert
			assertTrue(getMockAnature().setStatusWasCalled());
		}

		@Nested
		@DisplayName("getTypes()")
		class getTypes
		{
			@DisplayName("with Types Fire, Fighting")
			@Test
			void GetTypes_CallsGetTypes_ReturnsAnatureTypes()
			{
				// arrange
				Anature sut = TestObjects.getAnature().getClone().setPrimaryType(Type.Fire).setSecondaryType(Type.Fighting);

				ArrayList<Type> sutTypes = sut.getTypes();

				// assert
				assertTrue(sutTypes.contains(Type.Fire) && sutTypes.contains(Type.Fighting));
			}

			@DisplayName("with Types Water, NotSet")
			@Test
			void GetTypes_CallsGetTypes_ReturnsOnlyWater()
			{
				// arrange
				Anature sut = TestObjects.getAnature().getClone().setPrimaryType(Type.Water).setSecondaryType(Type.NotSet);

				ArrayList<Type> sutTypes = sut.getTypes();

				// assert
				assertTrue(sutTypes.contains(Type.Water) && !sutTypes.contains(Type.NotSet));
			}

		}

		@DisplayName("resetTempStats()")
		@Test
		void ResetTempStats_CallsMockAnatureStatsResetTempStatsMethod_True()
		{
			// act
			getMockAnature().resetTempStats();

			// assert
			assertTrue(mMockAnatureStats.resetTempStatsWasCalled());
		}

		@DisplayName("applyDamage()")
		@Test
		void ApplyDamage_CallsMockStatsApplyDamage_True()
		{
			// act
			getMockAnature().applyDamage(10);

			// assert
			assertTrue(mMockAnatureStats.applyDamageWasCalled());
		}

		@Nested
		@DisplayName("applyHeal()")
		class applyHeal
		{
			@DisplayName("calls stats applyHeal method")
			@Test
			void ApplyHeal_CallsMockStatsApplyHeal_True()
			{
				// act
				getMockAnature().applyHeal(10);

				// assert
				assertTrue(mMockAnatureStats.applyHealWasCalled());
			}

			@DisplayName("calls anatures getName method")
			@Test
			void ApplyHeal_CallsMockAnaturesGetName_True()
			{
				// act
				getMockAnature().applyHeal(10);

				// assert
				assertTrue(getMockAnature().getNameWasCalled());
			}

			@DisplayName("returns string object")
			@Test
			void ApplyHeal_ReturnsStringObject_True()
			{
				// act
				String sut = getMockAnature().applyHeal(10);

				// assert
				assertTrue(sut instanceof String);
			}
		}

		@DisplayName("restore()")
		@Test
		void Restore_CallsMockStatsApplyHealWithMaxIntegerValue_CalledMockStatsApplyHealWithMaxIntegerValue()
		{
			// act
			getMockAnature().restore();

			// assert
			assertTrue(mMockAnatureStats.applyHealWasCalledWithMaxIntValue() && mMockAnatureMoveSet.refreshAllMovePointsWasCalled());
		}

		@DisplayName("getHitPointsPercent()")
		@Test
		void GetHitPointsPercent_CallsGetHitPointsPercent_ReturnsHitPointPerecnt()
		{
			// act
			getMockAnature().getHitPointsPercent();

			// assert
			assertTrue(getMockAnatureStats().getHitPointsPercentWasCalled());
		}

		@Nested
		@DisplayName("getClone()")
		class getClone
		{
			@DisplayName("getName() was called")
			@Test
			void GetName_WasCalled_True()
			{
				// act
				getMockAnature().getName();

				// assert
				assertTrue(getMockAnature().getNameWasCalled());
			}

			@DisplayName("getOwner() was called")
			@Test
			void GetOwner_WasCalled_True()
			{
				// act
				getMockAnature().getName();

				// assert
				assertTrue(getMockAnature().getNameWasCalled());
			}

			@DisplayName("isShiny() was called")
			@Test
			void IsShiny_WasCalled_True()
			{
				// act
				getMockAnature().isShiny();

				// assert
				assertTrue(getMockAnature().isShinyWasCalled());
			}

			@DisplayName("getSpecies() was called")
			@Test
			void GetSpecies_WasCalled_True()
			{
				// act
				getMockAnature().getSpecies();

				// assert
				assertTrue(getMockAnature().getSpeciesWasCalled());
			}

			@DisplayName("getGender() was called")
			@Test
			void GetGender_WasCalled_True()
			{
				// act
				getMockAnature().getGender();

				// assert
				assertTrue(getMockAnature().getGenderWasCaled());
			}

			@DisplayName("getPrimaryType() was called")
			@Test
			void GetPrimaryType_WasCalled_True()
			{
				// act
				getMockAnature().getPrimaryType();

				// assert
				assertTrue(getMockAnature().getPrimaryTypeWasCalled());
			}

			@DisplayName("getSecondaryType() was called")
			@Test
			void GetSecondaryType_WasCalled_True()
			{
				// act
				getMockAnature().getSecondaryType();

				// assert
				assertTrue(getMockAnature().getSecondaryTypeWasCalled());
			}

			@DisplayName("getMoveSet() was called")
			@Test
			void GetMoveSet_WasCalled_True()
			{
				// act
				getMockAnature().getMoveSet();

				// assert
				assertTrue(getMockAnature().getMoveSetWasCalled());
			}

			@DisplayName("getAbility() was called")
			@Test
			void GetAbility_WasCalled_True()
			{
				// act
				getMockAnature().getAbility();

				// assert
				assertTrue(getMockAnature().getAbilityWasCalled());
			}

			@DisplayName("getStatus() was called")
			@Test
			void GetStatus_WasCalled_True()
			{
				// act
				getMockAnature().getStatus();

				// assert
				assertTrue(getMockAnature().getStatusWasCalled());
			}

			@DisplayName("getStats() was called")
			@Test
			void GetStats_WasCalled_True()
			{
				// act
				getMockAnature().getStats();

				// assert
				assertTrue(getMockAnature().getStatsWasCalled());
			}

			@DisplayName("getIndexNumber() was called")
			@Test
			void GetIndexNumber_WasCalled_True()
			{
				// act
				getMockAnature().getIndexNumber();

				// assert
				assertTrue(getMockAnature().getIndexNumberWasCalled());
			}

			@DisplayName("getCatchRate() was called")
			@Test
			void GetCatchRate_WasCalled_True()
			{
				// act
				getMockAnature().getCatchRate();

				// assert
				assertTrue(getMockAnature().getCatchRateWasCalled());
			}
		}

		@Nested
		@DisplayName("getFrontSprite()")
		class getFrontSprite
		{
//			@DisplayName("with Anature Species")
//			@ParameterizedTest(name = "\"{0}\" does not throw Exception")
//			@EnumSource(value = Species.class,
//						names = { "NotSet" },
//						mode = EnumSource.Mode.EXCLUDE)
//			void GetFrontSprite_WithSpecies_DoesNotThrowException(Species species)
//			{
//				assertDoesNotThrow(() ->
//				{
//					// arrange
//					Anature sut = (Anature) TestObjects.getAnature()
//							.getClone()
//							.setSpecies(species)
//							;
//
//					// act
//					sut.getFrontSprite();
//				}, "when getting the front image for " + species.toString() + " an Excpetion was thrown.");
//			}

//			@DisplayName("with Anature Species")
//			@ParameterizedTest(name = "\"{0}\" returns instance of Image")
//			@EnumSource(value = Species.class,
//			names = { "NotSet" },
//			mode = EnumSource.Mode.EXCLUDE)
//			void GetFrontSprite_WithSpecies_ReturnsInstanceOfImage(Species species)
//			{
//				// arrange
//				Anature sut = (Anature) TestObjects.getAnature()
//						.getClone()
//						.setSpecies(species)
//						;
//
//				// assert
//				assertTrue(sut.getFrontSprite() instanceof Image);
//			}
		}

		@Nested
		@DisplayName("getBackSprite()")
		class getBackSprite
		{
//			@DisplayName("with Anature Species")
//			@ParameterizedTest(name = "\"{0}\" does not throw Exception")
//			@EnumSource(value = Species.class,
//						names = { "NotSet" },
//						mode = EnumSource.Mode.EXCLUDE)
//			void GetBackSprite_WithSpecies_DoesNotThrowException(Species species)
//			{
//				assertDoesNotThrow(() ->
//				{
//					// arrange
//					Anature sut = (Anature) TestObjects.getAnature()
//							.getClone()
//							.setSpecies(species)
//							;
//
//					// act
//					sut.getFrontSprite();
//				}, "when getting the front image for " + species.toString() + " an Excpetion was thrown.");
//			}

//			@DisplayName("with Anature Species")
//			@ParameterizedTest(name = "\"{0}\" returns instance of Image")
//			@EnumSource(value = Species.class,
//			names = { "NotSet" },
//			mode = EnumSource.Mode.EXCLUDE)
//			void GetBackSprite_WithSpecies_ReturnsInstanceOfImage(Species species)
//			{
//				// arrange
//				Anature sut = (Anature) TestObjects.getAnature()
//						.getClone()
//						.setSpecies(species)
//						;
//
//				// assert
//				assertTrue(sut.getFrontSprite() instanceof Image);
//			}
		}

		@DisplayName("getMoveAnimationType()")
		@Test
		void GetMoveAnimationType_CallsMockAnautreMovesetsGetMoveAnimationType_True()
		{
			// act
			getMockAnature().getMoveAnimationType(0);

			// assert
			assertTrue(getMockAnatureMoveSet().getMoveAnimationTypeWasCalled());
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
