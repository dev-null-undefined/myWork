using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace TelefoniSeznam.Models
{
    public class c3b_kos3_seznam
    {
        [Key]
        public int id { get; set; }
        public string Jmeno { get; set; }
        public string Prijmeni { get; set; }
        public DateTime Datum_narozeni { get; set; }
        public int Telefon { get; set; }
    }
}
