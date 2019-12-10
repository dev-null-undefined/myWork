using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Net;
using System.Text;

namespace Interface
{
    class Euro : IPaperMoney,ICreationInterface
    {
        private short _value;
        private int _date;

        public Euro(short value)
        {
            _value = value;
        }

        public short Value => _value;

        public string Iso => "978";

        public string Name => "Eur";

        public int CreationDate { get => _date; set => _date=value; }

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
                decimal valueOfPInEuro = (int)p.Value * nasobic;
                if (valueOfPInEuro > _value)
                {
                    return -1;
                }
                if (valueOfPInEuro == _value)
                {
                    return 0;
                }
                if (valueOfPInEuro < _value)
                {
                    return 1;
                }
            }
            throw new Exception("Can not convert");
        }
    }
}
