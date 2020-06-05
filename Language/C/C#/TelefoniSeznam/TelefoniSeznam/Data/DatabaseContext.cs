using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using TelefoniSeznam.Models;

namespace TelefoniSeznam.Data
{
    public class DatabaseContext : DbContext
    {
        public DatabaseContext (DbContextOptions<DatabaseContext> options)
            : base(options)
        {
        }

        public DbSet<TelefoniSeznam.Models.c3b_kos3_seznam> c3b_kos3_seznam { get; set; }
    }
}
