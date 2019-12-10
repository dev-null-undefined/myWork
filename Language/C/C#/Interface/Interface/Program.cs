using System;
using System.Collections.Generic;

namespace Interface
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            IPaperMoney czk1 = new Czk(100);
            IPaperMoney euro = new Euro(10);
            IPaperMoney czk2 = new Czk(500);
            //100 CZK, 10 EUR, 500 CZK
            Console.WriteLine(czk1.GreatherThan(euro));
            //Console.WriteLine(euro.ConverTo("BND"));
            List<IPaperMoney> money = new List<IPaperMoney>();
            money.Add(czk2);
            money.Add(euro);
            money.Add(czk1);
        }
    }
}
