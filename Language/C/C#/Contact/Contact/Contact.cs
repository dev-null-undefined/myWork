using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Text;
using System.Text.RegularExpressions;

namespace Contact
{
    class Contact : IComparable<Contact>
    {
        private string _jmeno;
        private string _prijmeni;
        private string _telefon;
        private string _adress;

        public override string ToString() => "Osoba s jmenem:" + _jmeno + " " + _prijmeni + ",ma telefonic cislo:" + _telefon + ",a adresu" + _adress;
        public Contact(string jmeno, string prijmeni, string telefon, string adress)
        {
            _jmeno = jmeno;
            _prijmeni = prijmeni;
            _telefon = telefon;
            _adress = adress;
            var r = new Regex(@"([0-9]{9})$");
            if (!r.IsMatch(_telefon)){
                throw new WrongParameter("Wrong phone number");
            }
        }

        public override bool Equals(object obj)
        {
            return obj is Contact contact &&
                   _jmeno == contact._jmeno &&
                   _prijmeni == contact._prijmeni &&
                   _telefon == contact._telefon &&
                   _adress == contact._adress;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(_jmeno, _prijmeni, _telefon, _adress);
        }

        public int CompareTo(Contact other)
        {
            
            return _telefon.CompareTo(other._telefon);
        }
    }
}