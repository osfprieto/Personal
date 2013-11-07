package model.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import time.TimeMeter;

public class DictionaryQuerySolver
{
	public static final int LISTSIZE=62118;
	private Word[] wordList = new Word[LISTSIZE];
	private TimeMeter timeMeter = new TimeMeter();

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		DictionaryQuerySolver dqs = new DictionaryQuerySolver();
		dqs.loadDictionary(new File("./dic/en-Us.dic"));
		int pos = dqs.searchWordPosition("net");
		int pos2 = dqs.searchWordPosition("Net");
		System.out.println(pos);
		System.out.println(pos2);
		
	}

	public boolean loadDictionary(File dicFile)
	{
		
		try
		{
			this.timeMeter.reset();
			this.timeMeter.start();
			
			BufferedReader reader = new BufferedReader(new FileReader(dicFile));
			String line = "";
			line = reader.readLine();// fist line, words number
			int size = new Integer(line);

			for (int i = 0; i < size; i++)
			{
				line = reader.readLine();
				if (line != null)
				{
					String[] in = line.split("/");
					String word = in[0];
					Word w = new Word(word, i);
					this.wordList[i]=(w);

				}
			}
			this.timeMeter.stop();
			System.out.println(this.timeMeter.getCount()+" miliseconds loading dictionary");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
			return false;
		}
		catch (IOException e)
		{
			System.out.println("Reading error");
			return false;
		}
		System.out.println("Load Ok!");
		return true;
	}

	public int searchWordPosition(String word)
	{
		this.timeMeter.reset();
		this.timeMeter.start();
		for (int i = 0; i < LISTSIZE; i++)
		{
			if(this.wordList[i].getValue().equalsIgnoreCase(word))
			{
				this.timeMeter.stop();
				System.out.println(this.timeMeter.getCount()+" milsecs searching "+word);
				return i;
			}
		}
		this.timeMeter.stop();
		System.out.println(this.timeMeter.getCount()+" milsecs searching "+word);
		return -1;
	}
	public boolean searchWord(String word)
	{
		int res= searchWordPosition(word);
		if(res!=-1)
		{
			return true;
		}
		return false;
	}

//	public static int binarySearch(Word[] array, String value, int left, int right)
//	{
//		if (left > right)
//			return -1;
//		int middle = (left + right) / 2;
//		if (array[middle].getValue() .equals(value) )
//			return middle;
//		else if (array[middle] > value)
//			return binarySearch(array, value, left, middle - 1);
//		else
//			return binarySearch(array, value, middle + 1, right);
//	}

}
