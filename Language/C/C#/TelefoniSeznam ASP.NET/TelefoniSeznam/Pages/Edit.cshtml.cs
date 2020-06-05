using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using TelefoniSeznam.Data;
using TelefoniSeznam.Models;

namespace TelefoniSeznam.Pages
{
    public class EditModel : PageModel
    {
        private readonly TelefoniSeznam.Data.DatabaseContext _context;

        public EditModel(TelefoniSeznam.Data.DatabaseContext context)
        {
            _context = context;
        }

        [BindProperty]
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

        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://aka.ms/RazorPagesCRUD.
        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                return Page();
            }

            _context.Attach(c3b_kos3_seznam).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!c3b_kos3_seznamExists(c3b_kos3_seznam.id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return RedirectToPage("./Index");
        }

        private bool c3b_kos3_seznamExists(int id)
        {
            return _context.c3b_kos3_seznam.Any(e => e.id == id);
        }
    }
}
