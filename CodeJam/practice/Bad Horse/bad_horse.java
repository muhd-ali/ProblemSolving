import java.io.*;
import java.util.*;

public class bad_horse
{
  // Globals --------------------------------------------------------------------------------------
	public static String separater = "====================================================";
	public static Hashtable<String, Integer> group_1 = new Hashtable<String, Integer>();
	public static Hashtable<String, Integer> group_2 = new Hashtable<String, Integer>();
  // Globals --------------------------------------------------------------------------------------


	private static final void mydebug(String str)
	{
		System.out.println("----------------------->(<**" + str + "**>)");
		System.out.println("\n");
	}

	private static boolean add_entries_to_groups(String line)
	{
		String [] name = line.split(" ");
		boolean inserted = false;

		if (!group_1.containsKey(name[1]))
		{
			if (!group_1.containsKey(name[0]))
			{
				group_1.put(name[0], 100);
			}
			inserted = true;
		}
		else if (!group_2.containsKey(name[1]))
		{
			if (!group_2.containsKey(name[0]))
			{
				group_2.put(name[0], 100);
			}
			inserted = true;
		}

		if (!inserted)
		{
			return false;
		}

		inserted = false;

		if (!group_1.containsKey(name[0]))
		{
			if (!group_1.containsKey(name[1]))
			{
				group_1.put(name[1], 100);
			}
			inserted = true;
		}
		else if (!group_2.containsKey(name[0]))
		{
			if (!group_2.containsKey(name[1]))
			{
				group_2.put(name[1], 100);
			}
			inserted = true;
		}

		if (!inserted)
		{
			return false;
		}

		return true;
	}

	private static void reset_things()
	{
		group_1.clear();
		group_2.clear();
	}

	public static void main(String [] arg)
	{
		if (arg.length != 2)
		{
			System.out.println("Use as following:");
			System.out.println("java bad_horse <input_fileName.txt> <output_fileName.txt>");
			System.out.println(separater);
			return;
		}

		String input_fileName = arg[0];
		String output_fileName = arg[1];

		int num_cases;
		int case_num = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(input_fileName)))
		{
			File fp = new File(output_fileName);
			FileWriter fw = new FileWriter(fp.getName(),false);
			BufferedWriter bw = new BufferedWriter(fw);
			fw = new FileWriter(fp.getName(),true);

			String line;
			num_cases = Integer.parseInt(br.readLine());
			boolean can_be_grouped = true;

			int num_lines;
			while ((line = br.readLine()) != null)
			{
				case_num++;
				can_be_grouped = true;
				num_lines = Integer.parseInt(line);

				for (int i=0; i<num_lines; i++)
				{
					line = br.readLine();
					if (!can_be_grouped)
					{
						continue;
					}

					if (!add_entries_to_groups(line))
					{
						bw.write("Case #" + case_num + ": No\n");
						can_be_grouped = false;
					}
				}

				if (can_be_grouped)
				{
					bw.write("Case #" + case_num + ": Yes\n");
				}

				reset_things();
			}

			bw.close();
		}
		catch(IOException e)
		{
			mydebug("no file named " + input_fileName);
			System.out.println(separater);
			return;
		}
	}
}
