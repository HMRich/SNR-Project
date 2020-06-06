package test.enums.typeeffectiveness;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import application.enums.Type;
import application.enums.TypeEffectiveness;
import application.interfaces.IAnature;
import test.helpers.TestObjects;

public class GenerateLightWeightCombinationTests
{
	public static void main(String... args) throws IOException
	{
		ArrayList<Type> typesPrimary = Type.getTypes();
		typesPrimary.remove(Type.NotSet);

		FileWriter bw = new FileWriter("./generatedTestLog.txt");
		PrintWriter pw = new PrintWriter(bw);

		for(Type outerType : typesPrimary)
		{
			for(Type innerTypePrimary : typesPrimary)
			{

				pw.println("new Object[] { s(Type." + outerType.toString() + "), s(Type." + innerTypePrimary.toString() + "), TypeEffectiveness."
						+ getTypeEffectiveness(outerType, innerTypePrimary) + " },");

			}
		}

		pw.close();
	}

	public static String getTypeEffectiveness(Type source, Type targetPrimary)
	{
		IAnature sourceAnature = TestObjects.getAnature().getClone().withPrimaryType(source).create();
		IAnature targetAnature = TestObjects.getAnature().getClone().withPrimaryType(targetPrimary).create();

		return TypeEffectiveness.typeEffectiveness(sourceAnature, targetAnature).toString().replace(" ", "");
	}
}
