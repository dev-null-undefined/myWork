using System;
using System.Collections.Generic;
using System.Text;

namespace Contact
{
    class WrongParameter : Exception
    {
        public WrongParameter(string message) : base(message)
        {
        }
    }
}
