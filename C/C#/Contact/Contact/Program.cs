using System;

namespace Contact
{
    class Program
    {
        static void Main(string[] args)
        {
            ContactList list = new ContactList();
            int input;
            do
            {
                Console.WriteLine("pro konec 99 vypis 1 zapis 2");
                input = Int32.Parse(Console.ReadLine());
                switch (input)
                {
                    case 1:
                        Console.WriteLine(list.ToString());
                        break;
                    case 2:
                        Console.WriteLine("Jmeno:");
                        string jmeno = Console.ReadLine();

                        Console.WriteLine("prijmeni:");
                        string prijmeni = Console.ReadLine();


                        Console.WriteLine("telefon:");
                        string telefon = Console.ReadLine();


                        Console.WriteLine("adresa:");
                        string adresa = Console.ReadLine();

                        try
                        {
                            Console.WriteLine("Succes:" + list.addContact(new Contact(jmeno, prijmeni, telefon, adresa)));
                        }catch(WrongParameter e)
                        {
                            Console.WriteLine(e.Message);
                        }
                        break;
                }
            } while (input != 99);
        }
    }
}
