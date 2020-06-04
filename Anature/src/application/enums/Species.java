package application.enums;

public enum Species
{
	NotSet,
	Null,
	Sardino,
	Midaqua,
	Aquatecta,
	Smoldren,
	Scorta,
	Scaldris,
	Sapron,
	Sodren,
	Arganian,
	Transgendeero,
	Transgendeera,
	Clouder,
	Cloutrain,
	Cloudoom,
	Modenine,
	Routweiler,
	Fiberwire,
	Belvidia,
	Poire;

	public String toString()
	{
		return this.name()
				.replaceAll("_", " ");
	}
}
