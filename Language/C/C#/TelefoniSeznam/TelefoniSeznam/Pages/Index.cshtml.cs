using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using TelefoniSeznam.Data;
using TelefoniSeznam.Models;

namespace TelefoniSeznam.Pages
{
    public class IndexModel : PageModel
    {
        private readonly TelefoniSeznam.Data.DatabaseContext _context;

        public IndexModel(TelefoniSeznam.Data.DatabaseContext context)
        {
            _context = context;
        }

        public IList<c3b_kos3_seznam> c3b_kos3_seznam { get;set; }

        public async Task OnGetAsync()
        {
            c3b_kos3_seznam = await _context.c3b_kos3_seznam.ToListAsync();
        }
    }
}
