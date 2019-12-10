using System;
using System.Collections.Generic;
using System.Text;

namespace Vektory
{
    class Vektor
    {
        private int x;
        private int y;
        public Vektor(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public static Vector operator +(Vektor p, Vektor o)
        {
            return new Vektor(p.x + o.x, p.y + o.y);
        }
        public static Vector operator -(Vektor p, Vektor o)
        {
            return new Vektor(p.x - o.x, p.y - o.y);
        }
        public static bool operator ==(Vektor p, Vektor o)
        {
            bool rovnaSe = (p.x == o.x && p.y == o.y);
            return rovnaSe;
        }
        public static bool operator !=(Vektor p, Vektor o)
        {
            return !(p == o);
        }
        public override bool Equals(object obj)
        {
            return obj is Vektor vektor && vektor == this;
        }
        public override int GetHashCode()
        {
            var hasCode = -5291;
            hasCode = hasCode * -1732 + x.GetHashCode();
            hasCode = hasCode * -1232 + y.GetHashCode();
            return hasCode;
        }
        public override string ToString()
        {
            return "Souřadnice vekoru x= " + this.x + " , y = " + this.y;
        }
    }
}