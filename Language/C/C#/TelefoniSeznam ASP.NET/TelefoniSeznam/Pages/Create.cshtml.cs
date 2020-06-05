using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using TelefoniSeznam.Data;
using TelefoniSeznam.Models;

namespace TelefoniSeznam.Pages
{
    public class CreateModel : PageModel
    {
        private readonly TelefoniSeznam.Data.DatabaseContext _context;

        public CreateModel(TelefoniSeznam.Data.DatabaseContext context)
        {
            _context = context;
        }

        public IActionResult OnGet()
        {
            return Page();
        }

        [BindProperty]
        public c3b_kos3_seznam c3b_kos3_seznam { get; set; }

        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://aka.ms/RazorPagesCRUD.
        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                return Page();
            }

            _context.c3b_kos3_seznam.Add(c3b_kos3_seznam);
            await _context.SaveChangesAsync();

            return RedirectToPage("./Index");
        }
    }
}
