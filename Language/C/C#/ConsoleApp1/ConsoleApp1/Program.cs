using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int y = Convert.ToInt32(Console.ReadLine());
            if (y % 11 == 0)
            {
                Console.WriteLine("Ahoj");
            }
            Boolean failed = false;
            int x = 0;
            while (!failed)
            {
                try
                {

                    x +=Convert.ToInt32(Console.ReadLine());
                    Console.WriteLine("sum="+x);
                }
                catch (Exception)
                {
                    failed = true;
                }
            }
            Console.ReadLine();
        }
    }
}
