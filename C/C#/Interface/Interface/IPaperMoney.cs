using System;
using System.Collections.Generic;
using System.Text;

namespace Interface
{
    interface IPaperMoney
    {
        short Value { get; }
        string Iso { get; }

        string Name { get; }

        int GreatherThan(IPaperMoney p);

        decimal ConverTo(String curence);
    }
}
