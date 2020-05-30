package test.anatures;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import application.anatures.movesets.MoveSet;
import application.anatures.movesets.NullMoveSet;
import application.enums.Gender;
import application.enums.Species;
import application.enums.Type;
import application.interfaces.IAnature;
import test.TestObjects;

@DisplayName("Anature Tests")
class AnatureTests
{

	/*
	 * TESTING METHOD setName()
	 */

	@DisplayName("setName() with White Space")
	@ParameterizedTest
	@ValueSource(strings =
	{ "", " ", "   ", "            ", "\b", "\t", "\n", "\f", "\r" })
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

	@DisplayName("setName() with Null")
	@Test
	void SetName_WithNull_ThrowsIllegalArgumentException()
	{
		// assert
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withName(null);
		});
	}

	@DisplayName("setName() with Real Names")
	@ParameterizedTest
	@ValueSource(strings =
	{ "Hunter", "Gabe", "Jesse", "Colton", "Katherine" })
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

	/*
	 * TESTING METHOD setOwnerName()
	 */

	@DisplayName("setOwnerName() with White Space")
	@ParameterizedTest
	@ValueSource(strings =
	{ "", " ", "   ", "            ", "\b", "\t", "\n", "\f", "\r" })
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

	@DisplayName("setOwnerName() with Null")
	@Test
	void SetOwnerName_WithNull_ThrowsIllegalArgumentException()
	{
		// assert
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withOwnerName(null);
		});
	}

	@DisplayName("setOwnerName() with Real Names")
	@ParameterizedTest
	@ValueSource(strings =
	{ "Hunter", "Gabe", "Jesse", "Colton", "Katherine" })
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

	/*
	 * TESTING METHOD setIsShiny()
	 */

	@DisplayName("setIsShiny() with true/false")
	@ParameterizedTest
	@ValueSource(booleans =
	{ true, false })
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

	/*
	 * TESTING METHOD setSpecies()
	 */

	@DisplayName("setSpecies() with Null")
	@Test
	void SetSpecies_WithNull_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withSpecies(null);
		});
	}

	@DisplayName("setSpecies() with Species.NotSet")
	@Test
	void SetSpecies_WithNotSet_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withSpecies(Species.NotSet);
		});
	}

	@DisplayName("setSpecies() with Real Values")
	@ParameterizedTest
	@EnumSource(Species.class)
	void SetSpecies_WithEachType_SetsSpeciesValue(Species testSpecies)
	{
		// dead check TODO Get Rid of NotSet in Enums unless it is needed
		if(testSpecies.equals(Species.NotSet))
			return;

		// act
		IAnature sut = (IAnature) TestObjects.getAnature()
				.getClone()
				.withSpecies(testSpecies)
				.create();

		// assert
		assertTrue(sut.getSpecies()
				.equals(testSpecies));
	}

	/*
	 * TESTING METHOD setGender()
	 */

	@DisplayName("setGender() with Null")
	@Test
	void SetGender_WithNull_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withGender(null);
		});
	}

	@DisplayName("setGender() with Gender.NotSet")
	@Test
	void SetGender_WithNotSet_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withGender(Gender.NotSet);
		});
	}

	@DisplayName("setGedner() with Real Values")
	@ParameterizedTest
	@EnumSource(Gender.class)
	void SetGender_WithEachType_SetsGenderValue(Gender testGender)
	{
		// dead check
		if(testGender.equals(Gender.NotSet))
			return;

		// act
		IAnature sut = (IAnature) TestObjects.getAnature()
				.getClone()
				.withGender(testGender)
				.create();

		// assert
		assertTrue(sut.getGender()
				.equals(testGender));
	}

	/*
	 * TESTING METHOD setPrimaryType()
	 */

	@DisplayName("setPrimaryType() with Null")
	@Test
	void SetPrimaryType_WithNull_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withPrimaryType(null);
		});
	}

	@DisplayName("setPrimaryType() with Type.NotSet")
	@Test
	void SetPrimaryType_WithNotSet_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withPrimaryType(Type.NotSet);
		});
	}

	@DisplayName("setPrimaryType() with Real Values")
	@ParameterizedTest
	@EnumSource(Type.class)
	void SetPrimaryType_WithEachType_ThrowsIllegalArgumentException(Type testType)
	{
		// dead check
		if(testType.equals(Type.NotSet))
			return;

		// act
		IAnature sut = (IAnature) TestObjects.getAnature()
				.getClone()
				.withPrimaryType(testType)
				.create();

		// assert
		assertTrue(sut.getPrimaryType()
				.equals(testType));
	}

	/*
	 * TESTING METHOD setSecondaryType()
	 */

	@DisplayName("setSecondaryType() with Null")
	@Test
	void SetSecondaryType_WithNull_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withSecondaryType(null);
		});
	}

	@DisplayName("setSecondaryType() with Real Values")
	@ParameterizedTest
	@EnumSource(Type.class)
	void SetSecondaryType_WithEachType_ThrowsIllegalArgumentException(Type testType)
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

	/*
	 * TESTING METHOD getMoveSet()
	 */

	@DisplayName("setMoveSet() with Null")
	@Test
	void SetMoveSet_WithNull_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withMoveSet(null);
		});
	}

	@DisplayName("setMoveSet() with NullMoveSet")
	@Test
	void SetMoveSet_WithNullMoveSet_ThrowsIllegalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () ->
		{
			TestObjects.getAnature()
					.getClone()
					.withMoveSet(NullMoveSet.getNullMoveSet());
		});
	}

	@DisplayName("setMoveSet() with Real MoveSet")
	@Test
	void SetMoveSet_WithRealMoveSet_SetsRealMoveSet()
	{
		// arrange
		MoveSet realMoveSet = TestObjects.getMoveSet()
				.getClone();
		realMoveSet.setMove(1, TestObjects.getTackle());

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
