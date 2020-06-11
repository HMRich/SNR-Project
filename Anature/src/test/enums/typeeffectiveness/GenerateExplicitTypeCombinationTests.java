package test.enums.typeeffectiveness;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import application.anatures.Anature;
import application.enums.Type;
import application.enums.TypeEffectiveness;
import test.helpers.TestObjects;

public class GenerateExplicitTypeCombinationTests
{

	public static void main(String... args) throws IOException
	{
		ArrayList<Type> typesPrimary = Type.getTypes();
		typesPrimary.remove(Type.NotSet);

		ArrayList<Type> typesSecondary = Type.getTypes();

		FileWriter bw = new FileWriter("./generatedTestLog.txt");
		PrintWriter pw = new PrintWriter(bw);

		// We want each source type
		for(Type outerType : typesPrimary)
		{
			// count to exclude the nth generated test since a combo should already exist
			// on the source type
			int firstCount = 0;
			for(Type innerTypePrimary : typesPrimary)
			{
				int secondCountcount = 0;
				for(Type innerTypeSecondary : typesSecondary)
				{
					if(secondCountcount == 0 || secondCountcount > firstCount)
					{
						if(innerTypePrimary.equals(innerTypeSecondary))
						{
							continue;
						}

						pw.println("new Object[] { s(Type." + outerType.toString() + "), t(new Type[] { Type." + innerTypePrimary.toString() + ", Type."
								+ innerTypeSecondary.toString() + " }), TypeEffectiveness."
								+ getTypeEffectiveness(outerType, innerTypePrimary, innerTypeSecondary) + " },");
					}
					secondCountcount++;
				}
				secondCountcount = 0;
				firstCount++;
			}
			firstCount = 0;
		}

		pw.close();
	}

	public static String getTypeEffectiveness(Type source, Type targetPrimary, Type targetsecondary)
	{
		Anature sourceAnature = TestObjects.getAnature().getClone().setPrimaryType(source);
		Anature targetAnature = TestObjects.getAnature().getClone().setPrimaryType(targetPrimary).setSecondaryType(targetsecondary);

		return TypeEffectiveness.typeEffectiveness(sourceAnature, targetAnature).toString().replace(" ", "");
	}

}