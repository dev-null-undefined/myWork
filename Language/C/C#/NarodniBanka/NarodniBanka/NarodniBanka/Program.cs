using System;
using System.Xml.Linq;
using SPSEJecna.OTK.CNBAPI;
namespace NarodniBanka
{
    class Program
    {
        static void Main(string[] args)
        {
            /*Console.WriteLine(Rates.GetRates(DateTime.Today));*/
            Kurz kurzEUR = new Kurz("EUR");
            Kurz kurzUSD = new Kurz("USD");
            Kurz kurzRUB = new Kurz("RUB");
           
            Console.WriteLine(kurzEUR.VratMnozstvi(DateTime.Today) + " " + kurzEUR.Mena+" - "+kurzEUR.VratKurz(DateTime.Today)+" CZK");
            Console.WriteLine(kurzUSD.VratMnozstvi(DateTime.Today) + " " + kurzUSD.Mena+" - "+kurzUSD.VratKurz(DateTime.Today)+" CZK");
            Console.WriteLine(kurzRUB.VratMnozstvi(DateTime.Today) + " " + kurzRUB.Mena+" - "+kurzRUB.VratKurz(DateTime.Today)+" CZK");


        }
    }





class Kurz:Rates
    {




   
        private static decimal GetRate (DateTime datumKurz, string currency)
        {
            decimal result=0;

            XElement seznamKurzu = Rates.GetRates(datumKurz);
            foreach( XElement x in seznamKurzu.Element("tabulka").Elements("radek"))
            {
                if (x.Attribute("kod").Value == currency)
                {
                    result = Convert.ToDecimal ( x.Attribute("kurz").Value);
                }


            }





            return result;
        }


        private static decimal GetRateMnozstvi(DateTime datumKurz, string currency)
        {
            decimal result = 0;

            XElement seznamKurzu = Rates.GetRates(datumKurz);
            foreach (XElement x in seznamKurzu.Element("tabulka").Elements("radek"))
            {
                if (x.Attribute("kod").Value == currency)
                {
                    result = Convert.ToDecimal(x.Attribute("mnozstvi").Value);
                }


            }





            return result;
        }

        String _currency;

        public Kurz (String currency)
        {
            this._currency = currency;
        }

        public String Mena
        {
            get
            {
                return _currency;
            }
            protected set
            {
                this._currency = value;
            }
            
        }
        

        public decimal VratKurz(DateTime datumKurz)
        {
            return Kurz.GetRate( datumKurz, _currency);
        }

        public decimal VratMnozstvi(DateTime datumKurz)
        {
            return Kurz.GetRateMnozstvi(datumKurz, _currency);
        }
    }

 }
