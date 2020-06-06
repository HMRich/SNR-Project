package test.anatures.stats;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import application.enums.Stat;
import application.interfaces.IAnature;
import application.interfaces.stats.IStats;
import test.helpers.TestTags;
import test.helpers.TestObjects;

@DisplayName("Stats Tests")
@Tag(TestTags.UnitTest)
public class StatsTests
{
	@Nested
	@DisplayName("Getter Tests")
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
			assertTrue(sut.getTotalExperiencePoints() == TestObjects.getDefaultBaseNonStat());
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

		@DisplayName("getNatureModifierValue()")
		@Test
		@EnumSource(Stat.class)
		void GetNatureModifierValue_FromDefaultStats_ReturnsDefualtValue(Stat testStat)
		{
			// arrange
			IStats sut = TestObjects.getDefaultStats();

			int sutNatureModifierValue = sut.getNatureModifierValue(testStat);
			int sutExpectedValue = (int) (TestObjects.getDefaultNature()
					.getModifier() * TestObjects.getDefaultBaseStat());

			// assert
			assertTrue(sutNatureModifierValue == sutExpectedValue);
		}

		@DisplayName("getLargestStat()")
		@Test
		void GetLargestStat_FromDefaultStats_ReturnsDefualtValue()
		{
			// arrange
			IStats sut = TestObjects.getDefaultStats()
					.getClone()
					.withBaseAccuracy(100)
					.create();

			// act
			Stat sutStat = sut.getLargestStat();

			// assert
			assertTrue(sutStat.equals(Stat.Accuracy));
		}
	}

	@Nested
	@DisplayName("takeDamage()")
	class takeDamage
	{
		@DisplayName("with positive integers")
		@ParameterizedTest(name = "anature takes \"{0}\" damage")
		@ValueSource(ints = { 1, 34, 57, 90, 150, 250 })
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
		@ValueSource(ints = { 0, -34, -57, -90, -150, -250 })
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
		@DisplayName("with positive integers")
		@ParameterizedTest(name = "anature heals for \"{0}\" hitPoints")
		@ValueSource(ints = { 1, 34, 57, 90, 150, 250 })
		void HealAnature_WithPositiveValues_AnatureGainsHitPoints(int healAmount)
		{
			// arrange
			int baseHitPoints = 250;

			IAnature sut = TestObjects.getAnature().getClone()
					.withStats(TestObjects.getDefaultStats().getClone().atLevel(1).withBaseHitPoints(baseHitPoints).create()).create();

			IStats sutStats = sut.getStats();
			sutStats.setCurrentHitPoints(0);

			// act
			sut.applyHeal(healAmount);

			// assert
			assertTrue(sutStats.getCurrentHitPoints() == healAmount);
		}

		@DisplayName("with overheal integers values")
		@ParameterizedTest(name = "anature heals for \"{0}\" hitPoints")
		@ValueSource(ints = { 500, 1500, 10000 })
		void HealAnature_WithOverHealing_AnatureHasMaxHitPoints(int healAmount)
		{
			// arrange
			int baseHitPoints = 250;

			IAnature sut = TestObjects.getAnature().getClone()
					.withStats(TestObjects.getDefaultStats().getClone().atLevel(1).withBaseHitPoints(baseHitPoints).create()).create();

			IStats sutStats = sut.getStats();
			sutStats.setCurrentHitPoints(0);

			// act
			sut.applyHeal(healAmount);

			// assert
			assertTrue(sutStats.getCurrentHitPoints() == sutStats.getTotalStat(Stat.HitPoints));
		}

		@DisplayName("with nonpositive or negative integers")
		@ParameterizedTest(name = "value \"{0}\" throws IllegalArgumentException")
		@ValueSource(ints = { 0, -34, -57, -90, -150, -250 })
		void HealAnature_WithNegatieValues_ThrowsIllegalArgumentException(int healAmount)
		{
			assertThrows(IllegalArgumentException.class, () ->
			{
				// arrange
				int baseHitPoints = 250;

				IAnature sut = TestObjects.getAnature().getClone()
						.withStats(TestObjects.getDefaultStats().getClone().atLevel(1).withBaseHitPoints(baseHitPoints).create()).create();

				// act
				sut.applyHeal(healAmount);
			});
		}
	}
}
