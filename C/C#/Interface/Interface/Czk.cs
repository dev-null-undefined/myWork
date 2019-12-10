using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Net;
using System.Text;

namespace Interface
{
    class Czk : IPaperMoney,ICreationInterface
    {
        private short _value;
        private int _date;

        public Czk(short value)
        {
            _value = value;
        }

        public short Value => _value;

        public string Iso => "203";

        public string Name => "Czk";

        public int CreationDate { get => _date; set => _date = value; }

        public decimal ConverTo(string curence)
        {
            string urlPattern = "http://rate-exchange-1.appspot.com/currency?from={0}&to={1}";
            string url = string.Format(urlPattern, this.Name, curence);

            using (var wc = new WebClient())
            {
                var json = wc.DownloadString(url);
                JObject jObject = JObject.Parse(json);
                decimal nasobic = (decimal)jObject.SelectToken("rate");
                return (int)this.Value * nasobic;
            }
            throw new Exception("Can not convert");
        }

        public int GreatherThan(IPaperMoney p)
        {
            string urlPattern = "http://rate-exchange-1.appspot.com/currency?from={0}&to={1}";
            string url = string.Format(urlPattern, p.Name, this.Name);

            using (var wc = new WebClient())
            {
                var json = wc.DownloadString(url);
                JObject jObject = JObject.Parse(json);
                decimal nasobic = (decimal)jObject.SelectToken("rate");
                decimal valueOfPInCzk = (int) p.Value * nasobic;
                if (valueOfPInCzk > _value)
                {
                    return -1;
                }
                if (valueOfPInCzk == _value)
                {
                    return 0;
                }
                if (valueOfPInCzk < _value)
                {
                    return 1;
                }
            }
            throw new Exception("Can not convert");
        }
    
    }
}
