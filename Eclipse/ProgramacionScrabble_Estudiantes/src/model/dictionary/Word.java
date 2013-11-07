package model.dictionary;

public class Word
{
	private String value="";
	private long id = -1;
	public Word (String nValue, long nId)
	{
		this.value= nValue;
		this.id = nId;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	//operators
//	public boolean lessThan(Word arg1, Word arg2)
//	{
//		return arg1.getId()<arg2.getId();
//	}
//	public boolean greaterThan(Word arg1, Word arg2)
//	{
//		return arg1.getId()>arg2.getId();
//	}
	public boolean equals(Word arg1, Word arg2)
	{
		return arg1.getValue().equals(arg2);
	}
	public String toString()
	{
		return "["+this.id+":"+this.value+"]";
	}
}
