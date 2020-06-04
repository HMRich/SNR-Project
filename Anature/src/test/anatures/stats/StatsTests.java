package test.anatures.stats;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import application.enums.Stat;
import application.interfaces.IAnature;
import application.interfaces.stats.IStats;
import test.TestObjects;

public class StatsTests
{
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

			IAnature sut = TestObjects.getAnature()
					.getClone()
					.withStats(TestObjects.getDefaultStats()
							.getClone()
							.atLevel(1)
							.withBaseHitPoints(baseHitPoints)
							.create())
					.create();

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

			IAnature sut = TestObjects.getAnature()
					.getClone()
					.withStats(TestObjects.getDefaultStats()
							.getClone()
							.atLevel(1)
							.withBaseHitPoints(baseHitPoints)
							.create())
					.create();

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

				IAnature sut = TestObjects.getAnature()
						.getClone()
						.withStats(TestObjects.getDefaultStats()
								.getClone()
								.atLevel(1)
								.withBaseHitPoints(baseHitPoints)
								.create())
						.create();

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

			IAnature sut = TestObjects.getAnature()
					.getClone()
					.withStats(TestObjects.getDefaultStats()
							.getClone()
							.atLevel(1)
							.withBaseHitPoints(baseHitPoints)
							.create())
					.create();

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

			IAnature sut = TestObjects.getAnature()
					.getClone()
					.withStats(TestObjects.getDefaultStats()
							.getClone()
							.atLevel(1)
							.withBaseHitPoints(baseHitPoints)
							.create())
					.create();

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

				IAnature sut = TestObjects.getAnature()
						.getClone()
						.withStats(TestObjects.getDefaultStats()
								.getClone()
								.atLevel(1)
								.withBaseHitPoints(baseHitPoints)
								.create())
						.create();

				// act
				sut.applyHeal(healAmount);
			});
		}
	}
}
