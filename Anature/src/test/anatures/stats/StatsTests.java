package test.anatures.stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import application.anatures.stats.StatsTestable;
import application.enums.Stat;
import application.enums.stats.Natures;
import application.interfaces.IAnature;
import application.interfaces.stats.IStats;
import test.helpers.TestObjects;
import test.helpers.TestTags;

@DisplayName("Stats Tests")
@Tag(TestTags.UnitTest)
public class StatsTests
{
	@Nested
	@DisplayName("Getter Tests")
	@Tag(TestTags.GetterTests)
	class GetterTests
	{
		@DisplayName("getLevel()")
		@Test
		void GetLevel_FromDefaultStats_ReturnsDefualtValue()
		{
			// act
			IStats sut = TestObjects.getDefaultStats();

			// assert
			assertTrue(sut.getLevel() == TestObjects.getDefaultLevel());
		}

		@DisplayName("getTotalExperiencePoints()")
		@Test
		void GetTotalExperiencePoints_FromDefaultStats_ReturnsDefualtValue()
		{
			// act
			IStats sut = TestObjects.getDefaultStats();

			// assert
			assertTrue(sut.getTotalExperiencePoints() == TestObjects.getDefaultBaseNonStat() + 1);
		}

		@DisplayName("getCurrentHitPoints()")
		@Test
		void GetCurrentHitPoints_FromDefaultStats_ReturnsDefualtValue()
		{
			// act
			IStats sut = TestObjects.getDefaultStats();

			// assert
			assertTrue(sut.getCurrentHitPoints() == TestObjects.getDefaultBaseStat());
		}

		@DisplayName("getLevelingSpeed()")
		@Test
		void GetLevelingSpeed_FromDefaultStats_ReturnsDefualtValue()
		{
			// act
			IStats sut = TestObjects.getDefaultStats();

			// assert
			assertTrue(sut.getLevelingSpeed() == TestObjects.getDefaultLevelingSpeed());
		}

		@DisplayName("getNature()")
		@Test
		void GetNature_FromDefaultStats_ReturnsDefualtValue()
		{
			// act
			IStats sut = TestObjects.getDefaultStats();

			// assert
			assertTrue(sut.getNature() == TestObjects.getDefaultNature());
		}

		@DisplayName("getTotalStat()")
		@ParameterizedTest(name = "\"{0}\" returns default base stat")
		@EnumSource(Stat.class)
		void GetTotalStat_FromDefaultStats_ReturnsDefualtValue(Stat testStat)
		{
			// act
			IStats sut = TestObjects.getDefaultStats();

			// assert
			assertTrue(sut.getTotalStat(testStat) == TestObjects.getDefaultBaseStat());
		}

		@Nested
		@DisplayName("getNatureModifierValue()")
		class getNatureModifierValue
		{
			@DisplayName("anature with \"Hardy\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getHardyModifierTests")
			void WithHardyNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Lonely\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getLonelyModifierTests")
			void WithLonelyNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Brave\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getBraveModifierTests")
			void WithBraveNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Adamant\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getAdamantModifierTests")
			void WithAdamantNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Naughty\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getNaughtyModifierTests")
			void WithNaughtyNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Docile\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getDocileModifierTests")
			void WithDocileNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Bold\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getBoldModifierTests")
			void WithBoldNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Relaxed\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getRelaxedModifierTests")
			void WithRelaxedNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Impish\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getImpishModifierTests")
			void WithImpishNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Lax\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getLaxModifierTests")
			void WithLaxNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Serious\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getSeriousModifierTests")
			void WithSeriousNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Timid\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getTimidModifierTests")
			void WithTimidNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Hasty\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getHastyModifierTests")
			void WithHastyNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Jolly\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getJollyModifierTests")
			void WithJollyNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Naive\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getNaiveModifierTests")
			void WithNaiveNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Bashful\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getBashfulModifierTests")
			void WithBashfulNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Modest\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getModestModifierTests")
			void WithModestNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Mild\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getMildModifierTests")
			void WithMildNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Quiet\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getQuietModifierTests")
			void WithQuietNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Rash\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getRashModifierTests")
			void WithRashNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Quirky\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getQuirkyModifierTests")
			void WithQuirkyNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Calm\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getCalmModifierTests")
			void WithCalmNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Gentle\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getGentleModifierTests")
			void WithGentleNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Sassy\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getSassyModifierTests")
			void WithHSassyNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}

			@DisplayName("anature with \"Careful\" Nature")
			@ParameterizedTest(name = "\"{0}\" nature on \"{1}\" stat returns -> \"{2}\"")
			@MethodSource("test.anatures.stats.StatsTestsProviders#getCarefulModifierTests")
			void WithCarefulNature_AndDefaultStats_ReturnsExpectedValue(Natures currentNature, Stat testStat, Integer expectedValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().withNature(currentNature).create();

				// assert
				assertEquals(sut.getNatureModifierValue(testStat), expectedValue);
			}
		}

		@DisplayName("getLargestStat()")
		@Test
		void GetLargestStat_FromDefaultStats_ReturnsDefualtValue()
		{
			// arrange
			IStats sut = TestObjects.getDefaultStats().getClone().withBaseHitPoints(100).create();

			// act
			Stat sutStat = sut.getLargestStat();

			// assert
			assertTrue(sutStat.equals(Stat.HitPoints));
		}
	}

	@Nested
	@DisplayName("Setter Tests")
	@Tag(TestTags.SetterTests)
	class SetterTests
	{
		@Nested
		@DisplayName("setCurrenrHitPoints()")
		class setCurrentHitPoints
		{
			@DisplayName("with negative numbers or numbers above the anatures total hit points")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@ValueSource(ints = { -100, -56, -21, -5, -1, 51, 78, 98, 129, 1000 })
			void SetCurrentHitPoints_WithNullValues_ThrowsIllegalArgumentException(int testValue)
			{
				assertThrows(IllegalArgumentException.class, () ->
				{
					TestObjects.getDefaultStats().getClone().create().setCurrentHitPoints(testValue);
				});
			}

			@DisplayName("with valid numbers numbers below the anatures total hit points")
			@ParameterizedTest(name = "set the anatures current hit points to \"{0}\"")
			@ValueSource(ints = { 0, 1, 5, 15, 34, 48, 49, 50 })
			void SetCurrentHitPoints_WithValidNumbers_SetsAnaturesCurrentHitPoints(int testValue)
			{
				// arrange
				IStats sut = TestObjects.getDefaultStats().getClone().create();

				// act
				sut.setCurrentHitPoints(testValue);

				// assert
				assertEquals(sut.getCurrentHitPoints(), testValue);
			}
		}
	}

	@Nested
	@DisplayName("Public Method Tests")
	@Tag(TestTags.FunctionalityTests)
	class PublicMethodTests
	{
		private StatsTestable mMockStats;

		class MockStats extends StatsTestable
		{
			private static final long serialVersionUID = -3133051804708586485L;
		}

		@BeforeEach
		void BeforeEachHealAnature()
		{
			mMockStats = new MockStats();
		}

		@Nested
		@DisplayName("applyDamage()")
		class applyDamage
		{
			@DisplayName("with positive integers")
			@ParameterizedTest(name = "anature takes \"{0}\" damage")
			@ValueSource(ints = { 0, 1, 34, 57, 90, 150, 250 })
			void TakeDamage_WithDamage_AnatureTakesDamage(int damage)
			{
				// arrange
				int baseHitPoints = 250;

				IAnature sut = TestObjects.getAnature().getClone()
						.withStats(TestObjects.getDefaultStats().getClone().atLevel(1).withBaseHitPoints(baseHitPoints).create()).create();

				IStats sutStats = sut.getStats();

				// act
				sut.applyDamage(damage);

				// assert
				assertTrue(sutStats.getCurrentHitPoints() == (baseHitPoints - damage));
			}

			@DisplayName("with overkill integers values")
			@ParameterizedTest(name = "anature takes \"250\" damage")
			@ValueSource(ints = { 500, 1500, 10000 })
			void TakeDamage_WithOverKillDamage_AnatureHasZeroHitPoints(int damage)
			{
				// arrange
				int baseHitPoints = 250;

				IAnature sut = TestObjects.getAnature().getClone()
						.withStats(TestObjects.getDefaultStats().getClone().atLevel(1).withBaseHitPoints(baseHitPoints).create()).create();

				IStats sutStats = sut.getStats();

				// act
				sut.applyDamage(damage);

				// assert
				assertTrue(sutStats.getCurrentHitPoints() == 0);
			}

			@DisplayName("with nonpositive or negative integers")
			@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
			@ValueSource(ints = { -1, -34, -57, -90, -150, -250 })
			void TakeDamage_WithNegatieValues_ThrowsIllegalArgumentException(int damage)
			{
				assertThrows(IllegalArgumentException.class, () ->
				{
					// arrange
					int baseHitPoints = 250;

					IAnature sut = TestObjects.getAnature().getClone()
							.withStats(TestObjects.getDefaultStats().getClone().atLevel(1).withBaseHitPoints(baseHitPoints).create()).create();

					// act
					sut.applyDamage(damage);
				});
			}
		}

		@Nested
		@DisplayName("healAnature()")
		class healAnature
		{
			@DisplayName("with vaild positive integers")
			@ParameterizedTest(name = "anature heals for \"{0}\" hitPoints")
			@ValueSource(ints = { 0, 1, 5, 15, 23, 44, 49, 50 })
			void HealAnature_WithPositiveValues_AnatureGainsHitPoints(int testHealAmount)
			{
				// act
				mMockStats.healAnature(testHealAmount);

				// assert
				assertTrue(mMockStats.setCurrentHitPointsWasCalled());
				assertEquals(mMockStats.setCurrentHitPointsWasCalledWithValue(), testHealAmount);
			}

			@DisplayName("with overheal integers values")
			@ParameterizedTest(name = "anature heals for \"50\" hitPoints")
			@ValueSource(ints = { 51, 60, 79, 120, 500, 1500, 10000 })
			void HealAnature_WithOverHealing_AnatureHasMaxHitPoints(int testHealAmount)
			{
				// arrange
				int totalHitPoints = TestObjects.getDefaultBaseStat();

				// act
				mMockStats.healAnature(testHealAmount);

				// assert
				assertTrue(mMockStats.setCurrentHitPointsWasCalled());
				assertEquals(mMockStats.setCurrentHitPointsWasCalledWithValue(), totalHitPoints);
			}
		}

		@Nested
		@DisplayName("getClone()")
		class getClone
		{
			@DisplayName("getClone() calls getLevel()")
			@Test
			void GetClone_CallsGetLevel_True()
			{
				// act
				mMockStats.getClone();

				// assert
				assertTrue(mMockStats.getLevelWasCalled());
			}

			@DisplayName("getClone() calls getLevelingSpeed()")
			@Test
			void GetClone_CallsGetLevelingSpeed_True()
			{
				// act
				mMockStats.getClone();

				// assert
				assertTrue(mMockStats.getLevelingSpeedWasCalled());
			}

			@DisplayName("getClone() calls getNature()")
			@Test
			void GetClone_CallsGetNature_True()
			{
				// act
				mMockStats.getClone();

				// assert
				assertTrue(mMockStats.getNatureWasCalled());
			}

			@DisplayName("getClone() calls getEvRoadMap()")
			@Test
			void GetClone_CallsGetEvRoadMap_True()
			{
				// act
				mMockStats.getClone();

				// assert
				assertTrue(mMockStats.getEvRoadMapWAsCalled());
			}

			@DisplayName("getClone() calls getBaseStat() 8 times")
			@Test
			void GetClone_CallsGetBaseStatEightTimes_True()
			{
				// act
				mMockStats.getClone();

				// assert
				assertTrue(mMockStats.getBaseStatWasCalled());
				assertEquals(mMockStats.getBaseWasCalledXTimes(), 8);
			}

			@DisplayName("getClone() calls getIvStat() 6 times")
			@Test
			void GetClone_CallsGetIvStatEightTimes_True()
			{
				// act
				mMockStats.getClone();

				// assert
				assertTrue(mMockStats.getIvStatWasCalled());
				assertEquals(mMockStats.getIvStatWasCalledXTimes(), 6);
			}
		}
	}
}

class StatsTestsProviders
{
	private static Integer noChange = 0;
	private static Integer positiveChange = (int) (Natures.modifier() * TestObjects.getDefaultBaseStat());
	private static Integer negativeChange = (int) (-Natures.modifier() * TestObjects.getDefaultBaseStat());

	static Object[] getHardyModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Hardy, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Hardy, Stat.Attack, noChange }, //
				new Object[] { Natures.Hardy, Stat.Defense, noChange }, //
				new Object[] { Natures.Hardy, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Hardy, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Hardy, Stat.Speed, noChange }, //
				new Object[] { Natures.Hardy, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Hardy, Stat.Evasion, noChange }, //
				//
		};
	}

	static Object[] getLonelyModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Lonely, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Lonely, Stat.Attack, positiveChange }, //
				new Object[] { Natures.Lonely, Stat.Defense, negativeChange }, //
				new Object[] { Natures.Lonely, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Lonely, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Lonely, Stat.Speed, noChange }, //
				new Object[] { Natures.Lonely, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Lonely, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getBraveModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Brave, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Brave, Stat.Attack, positiveChange }, //
				new Object[] { Natures.Brave, Stat.Defense, noChange }, //
				new Object[] { Natures.Brave, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Brave, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Brave, Stat.Speed, negativeChange }, //
				new Object[] { Natures.Brave, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Brave, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getAdamantModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Adamant, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Adamant, Stat.Attack, positiveChange }, //
				new Object[] { Natures.Adamant, Stat.Defense, noChange }, //
				new Object[] { Natures.Adamant, Stat.SpecialAttack, negativeChange }, //
				new Object[] { Natures.Adamant, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Adamant, Stat.Speed, noChange }, //
				new Object[] { Natures.Adamant, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Adamant, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getNaughtyModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Naughty, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Naughty, Stat.Attack, positiveChange }, //
				new Object[] { Natures.Naughty, Stat.Defense, noChange }, //
				new Object[] { Natures.Naughty, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Naughty, Stat.SpecialDefense, negativeChange }, //
				new Object[] { Natures.Naughty, Stat.Speed, noChange }, //
				new Object[] { Natures.Naughty, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Naughty, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getDocileModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Docile, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Docile, Stat.Attack, noChange }, //
				new Object[] { Natures.Docile, Stat.Defense, noChange }, //
				new Object[] { Natures.Docile, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Docile, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Docile, Stat.Speed, noChange }, //
				new Object[] { Natures.Docile, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Docile, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getBoldModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Bold, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Bold, Stat.Attack, negativeChange }, //
				new Object[] { Natures.Bold, Stat.Defense, positiveChange }, //
				new Object[] { Natures.Bold, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Bold, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Bold, Stat.Speed, noChange }, //
				new Object[] { Natures.Bold, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Bold, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getRelaxedModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Relaxed, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Relaxed, Stat.Attack, noChange }, //
				new Object[] { Natures.Relaxed, Stat.Defense, positiveChange }, //
				new Object[] { Natures.Relaxed, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Relaxed, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Relaxed, Stat.Speed, negativeChange }, //
				new Object[] { Natures.Relaxed, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Relaxed, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getImpishModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Impish, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Impish, Stat.Attack, noChange }, //
				new Object[] { Natures.Impish, Stat.Defense, positiveChange }, //
				new Object[] { Natures.Impish, Stat.SpecialAttack, negativeChange }, //
				new Object[] { Natures.Impish, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Impish, Stat.Speed, noChange }, //
				new Object[] { Natures.Impish, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Impish, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getLaxModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Lax, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Lax, Stat.Attack, noChange }, //
				new Object[] { Natures.Lax, Stat.Defense, positiveChange }, //
				new Object[] { Natures.Lax, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Lax, Stat.SpecialDefense, negativeChange }, //
				new Object[] { Natures.Lax, Stat.Speed, noChange }, //
				new Object[] { Natures.Lax, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Lax, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getSeriousModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Serious, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Serious, Stat.Attack, noChange }, //
				new Object[] { Natures.Serious, Stat.Defense, noChange }, //
				new Object[] { Natures.Serious, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Serious, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Serious, Stat.Speed, noChange }, //
				new Object[] { Natures.Serious, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Serious, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getTimidModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Timid, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Timid, Stat.Attack, negativeChange }, //
				new Object[] { Natures.Timid, Stat.Defense, noChange }, //
				new Object[] { Natures.Timid, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Timid, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Timid, Stat.Speed, positiveChange }, //
				new Object[] { Natures.Timid, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Timid, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getHastyModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Hasty, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Hasty, Stat.Attack, noChange }, //
				new Object[] { Natures.Hasty, Stat.Defense, negativeChange }, //
				new Object[] { Natures.Hasty, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Hasty, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Hasty, Stat.Speed, positiveChange }, //
				new Object[] { Natures.Hasty, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Hasty, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getJollyModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Jolly, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Jolly, Stat.Attack, noChange }, //
				new Object[] { Natures.Jolly, Stat.Defense, noChange }, //
				new Object[] { Natures.Jolly, Stat.SpecialAttack, negativeChange }, //
				new Object[] { Natures.Jolly, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Jolly, Stat.Speed, positiveChange }, //
				new Object[] { Natures.Jolly, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Jolly, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getNaiveModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Naive, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Naive, Stat.Attack, noChange }, //
				new Object[] { Natures.Naive, Stat.Defense, noChange }, //
				new Object[] { Natures.Naive, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Naive, Stat.SpecialDefense, negativeChange }, //
				new Object[] { Natures.Naive, Stat.Speed, positiveChange }, //
				new Object[] { Natures.Naive, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Naive, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getBashfulModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Bashful, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Bashful, Stat.Attack, noChange }, //
				new Object[] { Natures.Bashful, Stat.Defense, noChange }, //
				new Object[] { Natures.Bashful, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Bashful, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Bashful, Stat.Speed, noChange }, //
				new Object[] { Natures.Bashful, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Bashful, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getModestModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Modest, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Modest, Stat.Attack, negativeChange }, //
				new Object[] { Natures.Modest, Stat.Defense, noChange }, //
				new Object[] { Natures.Modest, Stat.SpecialAttack, positiveChange }, //
				new Object[] { Natures.Modest, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Modest, Stat.Speed, noChange }, //
				new Object[] { Natures.Modest, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Modest, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getMildModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Mild, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Mild, Stat.Attack, noChange }, //
				new Object[] { Natures.Mild, Stat.Defense, negativeChange }, //
				new Object[] { Natures.Mild, Stat.SpecialAttack, positiveChange }, //
				new Object[] { Natures.Mild, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Mild, Stat.Speed, noChange }, //
				new Object[] { Natures.Mild, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Mild, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getQuietModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Quiet, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Quiet, Stat.Attack, noChange }, //
				new Object[] { Natures.Quiet, Stat.Defense, noChange }, //
				new Object[] { Natures.Quiet, Stat.SpecialAttack, positiveChange }, //
				new Object[] { Natures.Quiet, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Quiet, Stat.Speed, negativeChange }, //
				new Object[] { Natures.Quiet, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Quiet, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getRashModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Rash, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Rash, Stat.Attack, noChange }, //
				new Object[] { Natures.Rash, Stat.Defense, noChange }, //
				new Object[] { Natures.Rash, Stat.SpecialAttack, positiveChange }, //
				new Object[] { Natures.Rash, Stat.SpecialDefense, negativeChange }, //
				new Object[] { Natures.Rash, Stat.Speed, noChange }, //
				new Object[] { Natures.Rash, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Rash, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getQuirkyModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Quirky, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Quirky, Stat.Attack, noChange }, //
				new Object[] { Natures.Quirky, Stat.Defense, noChange }, //
				new Object[] { Natures.Quirky, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Quirky, Stat.SpecialDefense, noChange }, //
				new Object[] { Natures.Quirky, Stat.Speed, noChange }, //
				new Object[] { Natures.Quirky, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Quirky, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getCalmModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Calm, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Calm, Stat.Attack, negativeChange }, //
				new Object[] { Natures.Calm, Stat.Defense, noChange }, //
				new Object[] { Natures.Calm, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Calm, Stat.SpecialDefense, positiveChange }, //
				new Object[] { Natures.Calm, Stat.Speed, noChange }, //
				new Object[] { Natures.Calm, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Calm, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getGentleModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Gentle, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Gentle, Stat.Attack, noChange }, //
				new Object[] { Natures.Gentle, Stat.Defense, negativeChange }, //
				new Object[] { Natures.Gentle, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Gentle, Stat.SpecialDefense, positiveChange }, //
				new Object[] { Natures.Gentle, Stat.Speed, noChange }, //
				new Object[] { Natures.Gentle, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Gentle, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getSassyModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Sassy, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Sassy, Stat.Attack, noChange }, //
				new Object[] { Natures.Sassy, Stat.Defense, noChange }, //
				new Object[] { Natures.Sassy, Stat.SpecialAttack, noChange }, //
				new Object[] { Natures.Sassy, Stat.SpecialDefense, positiveChange }, //
				new Object[] { Natures.Sassy, Stat.Speed, negativeChange }, //
				new Object[] { Natures.Sassy, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Sassy, Stat.Evasion, noChange }, //
		};
	}

	static Object[] getCarefulModifierTests()
	{
		return new Object[] { //
				new Object[] { Natures.Careful, Stat.HitPoints, noChange }, //
				new Object[] { Natures.Careful, Stat.Attack, noChange }, //
				new Object[] { Natures.Careful, Stat.Defense, noChange }, //
				new Object[] { Natures.Careful, Stat.SpecialAttack, negativeChange }, //
				new Object[] { Natures.Careful, Stat.SpecialDefense, positiveChange }, //
				new Object[] { Natures.Careful, Stat.Speed, noChange }, //
				new Object[] { Natures.Careful, Stat.Accuracy, noChange }, //
				new Object[] { Natures.Careful, Stat.Evasion, noChange }, //
		};
	}
}
