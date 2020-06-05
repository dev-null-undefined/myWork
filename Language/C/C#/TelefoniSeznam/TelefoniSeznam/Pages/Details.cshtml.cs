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
    public class DetailsModel : PageModel
    {
        private readonly TelefoniSeznam.Data.DatabaseContext _context;

        public DetailsModel(TelefoniSeznam.Data.DatabaseContext context)
        {
            _context = context;
        }

        public c3b_kos3_seznam c3b_kos3_seznam { get; set; }

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            c3b_kos3_seznam = await _context.c3b_kos3_seznam.FirstOrDefaultAsync(m => m.id == id);

            if (c3b_kos3_seznam == null)
            {
                return NotFound();
            }
            return Page();
        }
    }
}
