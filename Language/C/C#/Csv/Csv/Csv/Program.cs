using System;
using System.IO;

namespace Csv
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.Write("Give me collumns that u want to read (separated by ','):");
            string input=Console.ReadLine();
            string[] inputs=input.Split(",");
            int[] columnsNumbers=new int[inputs.Length];
            int x = 0;
            foreach(var text in inputs)
            {
                columnsNumbers[x]=Int32.Parse(text);
                x++;
            }
            Console.Write("Give me separator:");
            string separator = Console.ReadLine();
            Console.Write("Give me new separator:");
            string newseparator = Console.ReadLine();
            Console.Write("Give me file input file:");
            string fileInput = Console.ReadLine();
            Console.Write("Give me file outpit file:");
            string fileOut = Console.ReadLine();
            Console.Write("do you want consol log values?(YES/NO):");
            string consolLog = Console.ReadLine();

            using (StreamWriter writer = new StreamWriter(fileOut))
            {
                string[] lines = System.IO.File.ReadAllLines(fileInput);
                foreach (var line in lines)
                {
                    //System.Console.WriteLine("Contents of WriteText.txt = {0}", line);
                    string[] values = line.Split(separator);
                    foreach (var value in columnsNumbers)
                    {
                        if (consolLog.Equals("YES"))
                        {
                            Console.Write("Value number '{1}'={0},", value - 1 < values.Length - 1 && value - 1 >= 0 ? values[value - 1] : "WRONG COLLUM NUMBER B*TCH", value);
                        }
                        writer.Write("{0}{1}", value - 1 < values.Length - 1 && value - 1 >= 0 ? values[value - 1] : "WRONG COLLUM NUMBER B*TCH", newseparator);
                    }
                    writer.WriteLine("");
                }
            }
            
           
        }
    }
}
