using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zlomek
{
    class Zlomek
    {
        private int _citatel;
        private int _jmenovatel;
        public static Zlomek VytvorZlomek(int citatel, int jmenovatel)
        {
            if (jmenovatel == 0)
            {
                throw new Exception("Nelze delit nulou v oboru R");
            }
            return new Zlomek(citatel, jmenovatel);
        }
        public Zlomek(int citatel, int jmenovatel)
        {
            _citatel = citatel;
            _jmenovatel = jmenovatel;
        }

        public static Zlomek operator +(Zlomek a,Zlomek b)
        {
            Zlomek soucet = new Zlomek(a._citatel*b._jmenovatel+a._jmenovatel*b._citatel,a._jmenovatel*a._jmenovatel);
            soucet.Kraceni();
            return soucet;
        }
        public static Zlomek operator -(Zlomek a, Zlomek b)
        {
            Zlomek minus = new Zlomek(a._citatel * b._jmenovatel - a._jmenovatel * b._citatel, a._jmenovatel * a._jmenovatel);
            minus.Kraceni();
            return minus;
        }
        public static Zlomek operator /(Zlomek a, Zlomek b) => new Zlomek(a._jmenovatel, a._citatel) * b;
        public static bool operator ==(Zlomek a, Zlomek b)
        {
            a.Kraceni();
            b.Kraceni();
            return (a._citatel == b._citatel && a._jmenovatel == b._jmenovatel);
        }
        public static bool operator !=(Zlomek a, Zlomek b)
        {
            a.Kraceni();
            b.Kraceni();
            return !(a._citatel == b._citatel && a._jmenovatel == b._jmenovatel);
        }
        public static implicit operator double(Zlomek k) => k._citatel / k._jmenovatel;
        public static Zlomek operator *(Zlomek a, Zlomek b)
        {
            Zlomek nasob = new Zlomek(a._citatel * b._citatel, a._jmenovatel * a._jmenovatel);
            nasob.Kraceni();
            return nasob;
        }

        public override string ToString() => _citatel + "/" + _jmenovatel;

        public void Kraceni()
        {
            int a = _citatel;
            int b = _jmenovatel;
            int r;
            if (a >= 1 && b >= 1)
            {
                do
                {
                    r = a % b;
                    a = b;
                    b = r;
                } while (b != 0);
                _citatel /= a;
                _jmenovatel /= a;
            }
        }

        public override bool Equals(object obj) => obj is Zlomek zlomek && this == zlomek;

        public override int GetHashCode()
        {
            var hashCode = -1320372681;
            hashCode = hashCode * -1521134295 + _citatel.GetHashCode();
            hashCode = hashCode * -1521134295 + _jmenovatel.GetHashCode();
            return hashCode;
        }
    }
}
