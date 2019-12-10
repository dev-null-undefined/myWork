using System;
using System.Collections.Generic;
using System.Text;

namespace Contact
{
    class ContactList
    {
        private SortedSet<Contact> _telefoniSeznam = new SortedSet<Contact>();

        public ContactList()
        {
        }

        public bool addContact(Contact c) => _telefoniSeznam.Add(c);
        public ContactList(SortedSet<Contact> telefoniSeznam)
        {
            _telefoniSeznam = telefoniSeznam;
        }

        public override string ToString()
        {
            string returning = "";
            foreach(Contact c in _telefoniSeznam)
            {
                returning += c.ToString()+"|";
            }
            return returning;
        }
    }
}
