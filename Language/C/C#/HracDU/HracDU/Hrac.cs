using System;
using System.Collections.Generic;
using System.Text;

namespace HracDU
{
    class Hrac
    {
        private int _zivot;
        private int _syla;
        private int _maxZivot;
        private String _jmeno;

        public Hrac(int zivot, int maxZivot, int syla, string jmeno)
        {
            _zivot = zivot;
            _maxZivot = maxZivot;
            _syla = syla;
            _jmeno = jmeno;
        }

    }
}
