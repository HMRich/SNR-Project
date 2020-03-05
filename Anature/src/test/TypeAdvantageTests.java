package test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import application.Anature;
import application.AnatureBuilder;
import application.TypeAdvantage;
import application.enums.AttackEffectiveness;
import application.enums.Species;
import application.enums.Type;

@DisplayName("Type Advantage Tests")
public class TypeAdvantageTests
{
	private static Anature baseAnature;

	@BeforeAll
	public static void generateAnature()
	{
		baseAnature = AnatureBuilder.createAnature(Species.Null, 0);
		baseAnature.setPrimaryType(null);
		baseAnature.setSecondaryType(null);
	}

	@TestTemplate
	@ExtendWith(TestTempalte.class)
	public void testEquals(TypeEffectivenessTestCase testCase)
	{
		Assert.assertEquals(TypeAdvantage.advantageType(testCase.attacker, testCase.defender), testCase.expectedEffectiveness);
	}

	private TypeEffectivenessTestCase createTestCase(Type[] sourceTypes, Type[] targetTypes, AttackEffectiveness expectedResult)
	{
		Anature sourceAnature = baseAnature.getClone();
		for(Type type : sourceTypes)
		{
			sourceAnature.setPrimaryType(type);
		}
		Anature targetAnature = baseAnature.getClone();
		for(Type type : targetTypes)
		{
			targetAnature.setPrimaryType(type);
		}

		return new TypeEffectivenessTestCase(sourceAnature, targetAnature, expectedResult);
	}

	private class TestTempalte implements TestTemplateInvocationContextProvider
	{
		@Override
		public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext arg0)
		{
			return Stream.of(
					addCase(createTestCase(new Type[] {Type.Normal}, new Type[] {Type.Normal}, AttackEffectiveness.Normal))
					);
		}

		@Override
		public boolean supportsTestTemplate(ExtensionContext arg0)
		{
			return true;
		}

		private TestTemplateInvocationContext addCase(TypeEffectivenessTestCase testCase)
		{
			return new TestTemplateInvocationContext()
			{
				@Override
				public String getDisplayName(int invocationIndex)
				{
					return testCase.testCaseToString();
				}

				@Override
				public List<Extension> getAdditionalExtensions()
				{
					return Collections.singletonList(new ParameterResolver()
					{
						@Override
						public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
						{
							return parameterContext.getParameter().getType().equals(String.class);
						}

						@Override
						public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
						{
							return testCase;
						}
					});
				}
			};
		}

	}
}
