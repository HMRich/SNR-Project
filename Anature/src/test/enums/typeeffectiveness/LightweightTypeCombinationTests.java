package test.enums.typeeffectiveness;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import application.enums.Type;
import application.enums.TypeEffectiveness;
import application.interfaces.IAnature;
import test.helpers.TestObjects;
import test.helpers.TestTags;

@DisplayName("Lightweight Type Combination Tests")
@Tags({ @Tag(TestTags.UnitTest), @Tag(TestTags.Lightweight) })
public class LightweightTypeCombinationTests
{
	@DisplayName("with \"Normal\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getNormalCombos")
	void NormalType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Fire\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getFireCombos")
	void FireType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Water\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getWaterCombos")
	void WaterType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Electric\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getElectricCombos")
	void ElectricType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Grass\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getGrassCombos")
	void GrassType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Ice\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getIceCombos")
	void IceType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Fighting\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getFightingCombos")
	void FightingType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Poison\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getPoisonCombos")
	void PoisonType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Ground\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getGroundCombos")
	void GroundType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Flying\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getFlyingCombos")
	void FlyingType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Psychic\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getPsychicCombos")
	void PsychicType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Bug\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getBugCombos")
	void BugType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Rock\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getRockCombos")
	void RockType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Ghost\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getGhostCombos")
	void GhostType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Dragon\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getDragonCombos")
	void DragonType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Dark\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getDarkCombos")
	void DarkType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Steel\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getSteelCombos")
	void SteelType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}

	@DisplayName("with \"Fairy\" type against all combinations")
	@ParameterizedTest(name = "\"{0}\" vs \"{1}\"    returns effectiveness value -> \"{2}\"")
	@MethodSource("test.enums.typeeffectiveness.LightweightTypeEffectivenessTestProvider#getFairyCombos")
	void FairyType_AgainstAllCombinations_ReturnsCorrectValue(IAnature source, IAnature target, TypeEffectiveness expectedResult)
	{
		assertTrue(TypeEffectiveness.typeEffectiveness(source, target).equals(expectedResult),
				"returned \"" + TypeEffectiveness.typeEffectiveness(source, target).toString() + "\" instead.");
	}
}

class LightweightTypeEffectivenessTestProvider
{
	private static IAnature setAnatureTypes(Type type)
	{
		return TestObjects.getAnature().getClone().withPrimaryType(type).create();
	}

	static IAnature s(Type sourceType)
	{
		return setAnatureTypes(sourceType);
	}

	static IAnature t(Type targetType)
	{
		return setAnatureTypes(targetType);
	}

	static Object[] getNormalCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Normal), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Fire), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Grass), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Bug), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Rock), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Normal), t(Type.Ghost), TypeEffectiveness.NoEffect },
				new Object[] { s(Type.Normal), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Normal), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Normal), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getFireCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Fire), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Fire), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fire), t(Type.Water), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fire), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Grass), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fire), t(Type.Ice), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fire), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Bug), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fire), t(Type.Rock), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fire), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Dragon), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fire), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fire), t(Type.Steel), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fire), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getWaterCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Water), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Fire), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Water), t(Type.Water), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Water), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Grass), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Water), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Ground), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Water), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Bug), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Rock), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Water), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Dragon), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Water), t(Type.Dark), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Water), t(Type.Steel), TypeEffectiveness.Normal },
				new Object[] { s(Type.Water), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getElectricCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Electric), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Fire), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Water), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Electric), t(Type.Electric), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Electric), t(Type.Grass), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Electric), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Ground), TypeEffectiveness.NoEffect },
				new Object[] { s(Type.Electric), t(Type.Flying), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Electric), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Bug), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Rock), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Dragon), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Electric), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Steel), TypeEffectiveness.Normal },
				new Object[] { s(Type.Electric), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getGrassCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Grass), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Grass), t(Type.Fire), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Grass), t(Type.Water), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Grass), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Grass), t(Type.Grass), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Grass), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Grass), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Grass), t(Type.Poison), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Grass), t(Type.Ground), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Grass), t(Type.Flying), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Grass), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Grass), t(Type.Bug), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Grass), t(Type.Rock), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Grass), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Grass), t(Type.Dragon), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Grass), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Grass), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Grass), t(Type.Fairy), TypeEffectiveness.Normal }, };
	}

	static Object[] getIceCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Ice), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ice), t(Type.Fire), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Ice), t(Type.Water), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Ice), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ice), t(Type.Grass), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ice), t(Type.Ice), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Ice), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ice), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ice), t(Type.Ground), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ice), t(Type.Flying), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ice), t(Type.Psychic), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Ice), t(Type.Bug), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Ice), t(Type.Rock), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Ice), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ice), t(Type.Dragon), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ice), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ice), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Ice), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getFightingCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Fighting), t(Type.Normal), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fighting), t(Type.Fire), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fighting), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fighting), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fighting), t(Type.Grass), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fighting), t(Type.Ice), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fighting), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fighting), t(Type.Poison), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fighting), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fighting), t(Type.Flying), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fighting), t(Type.Psychic), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fighting), t(Type.Bug), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fighting), t(Type.Rock), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fighting), t(Type.Ghost), TypeEffectiveness.NoEffect },
				new Object[] { s(Type.Fighting), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fighting), t(Type.Dark), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fighting), t(Type.Steel), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fighting), t(Type.Fairy), TypeEffectiveness.NotEffective } };
	}

	static Object[] getPoisonCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Poison), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Fire), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Grass), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Poison), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Poison), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Poison), t(Type.Ground), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Poison), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Bug), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Rock), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Poison), t(Type.Ghost), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Poison), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Poison), t(Type.Steel), TypeEffectiveness.NoEffect },
				new Object[] { s(Type.Poison), t(Type.Fairy), TypeEffectiveness.SuperEffective } };
	}

	static Object[] getGroundCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Ground), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Fire), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ground), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Electric), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ground), t(Type.Grass), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Ground), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Poison), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ground), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Flying), TypeEffectiveness.NoEffect },
				new Object[] { s(Type.Ground), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Bug), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Ground), t(Type.Rock), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ground), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ground), t(Type.Steel), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ground), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getFlyingCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Flying), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Fire), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Electric), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Flying), t(Type.Grass), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Flying), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Fighting), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Flying), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Bug), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Flying), t(Type.Rock), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Flying), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Flying), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Flying), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getPsychicCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Psychic), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Fire), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Grass), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Fighting), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Psychic), t(Type.Poison), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Psychic), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Psychic), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Psychic), t(Type.Bug), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Rock), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Psychic), t(Type.Dark), TypeEffectiveness.NoEffect },
				new Object[] { s(Type.Psychic), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Psychic), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getBugCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Bug), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Bug), t(Type.Fire), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Bug), t(Type.Water), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Bug), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Bug), t(Type.Grass), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Bug), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Bug), t(Type.Fighting), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Bug), t(Type.Poison), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Bug), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Bug), t(Type.Flying), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Bug), t(Type.Psychic), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Bug), t(Type.Bug), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Bug), t(Type.Rock), TypeEffectiveness.Normal },
				new Object[] { s(Type.Bug), t(Type.Ghost), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Bug), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Bug), t(Type.Dark), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Bug), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Bug), t(Type.Fairy), TypeEffectiveness.NotEffective } };
	}

	static Object[] getRockCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Rock), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Rock), t(Type.Fire), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Rock), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Rock), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Rock), t(Type.Grass), TypeEffectiveness.Normal },
				new Object[] { s(Type.Rock), t(Type.Ice), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Rock), t(Type.Fighting), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Rock), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Rock), t(Type.Ground), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Rock), t(Type.Flying), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Rock), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Rock), t(Type.Bug), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Rock), t(Type.Rock), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Rock), t(Type.Ghost), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Rock), t(Type.Dragon), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Rock), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Rock), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Rock), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getGhostCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Ghost), t(Type.Normal), TypeEffectiveness.NoEffect },
				new Object[] { s(Type.Ghost), t(Type.Fire), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Ghost), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Grass), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Ghost), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Psychic), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ghost), t(Type.Bug), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Ghost), t(Type.Rock), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Ghost), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Ghost), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Dark), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Ghost), t(Type.Steel), TypeEffectiveness.Normal },
				new Object[] { s(Type.Ghost), t(Type.Fairy), TypeEffectiveness.Normal } };
	}

	static Object[] getDragonCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Dragon), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Fire), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Grass), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Bug), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Dragon), t(Type.Rock), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Dragon), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Dragon), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dragon), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Dragon), t(Type.Fairy), TypeEffectiveness.NoEffect } };
	}

	static Object[] getDarkCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Dark), t(Type.Normal), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Dark), t(Type.Fire), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Dark), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Grass), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Dark), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Fighting), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Dark), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Psychic), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Dark), t(Type.Bug), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Dark), t(Type.Rock), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Ghost), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Dark), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Dark), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Dark), t(Type.Steel), TypeEffectiveness.Normal },
				new Object[] { s(Type.Dark), t(Type.Fairy), TypeEffectiveness.NotEffective } };
	}

	static Object[] getSteelCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Steel), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Fire), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Steel), t(Type.Water), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Steel), t(Type.Electric), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Steel), t(Type.Grass), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Ice), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Steel), t(Type.Fighting), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Poison), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Bug), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Rock), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Steel), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Dragon), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Dark), TypeEffectiveness.Normal },
				new Object[] { s(Type.Steel), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Steel), t(Type.Fairy), TypeEffectiveness.SuperEffective } };
	}

	static Object[] getFairyCombos()
	{
		return new Object[] { //
				new Object[] { s(Type.Fairy), t(Type.Normal), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fairy), t(Type.Fire), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fairy), t(Type.Water), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fairy), t(Type.Electric), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fairy), t(Type.Grass), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Fairy), t(Type.Ice), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fairy), t(Type.Fighting), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fairy), t(Type.Poison), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fairy), t(Type.Ground), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fairy), t(Type.Flying), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fairy), t(Type.Psychic), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fairy), t(Type.Bug), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Fairy), t(Type.Rock), TypeEffectiveness.Normal }, //
				new Object[] { s(Type.Fairy), t(Type.Ghost), TypeEffectiveness.Normal },
				new Object[] { s(Type.Fairy), t(Type.Dragon), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fairy), t(Type.Dark), TypeEffectiveness.SuperEffective },
				new Object[] { s(Type.Fairy), t(Type.Steel), TypeEffectiveness.NotEffective },
				new Object[] { s(Type.Fairy), t(Type.Fairy), TypeEffectiveness.Normal } };
	}
}
