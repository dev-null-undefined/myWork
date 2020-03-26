using System;
using System.Data.SqlClient;

namespace DatabaseConnection
{
    class Program
    {
        static void Main(string[] args)
        {
            string connstring = string.Format("Server={0}, {1}; UID={2}; password={3}", "193.85.203.188", "11433", "student", "StudentPV");
            using (SqlConnection connection = new SqlConnection(connstring)) {
                try
                {
                    connection.Open();
                    connection.ChangeDatabase("pv");
                    using (SqlCommand command = new SqlCommand())
                    {
                        command.Connection = connection;
                        command.CommandText = "select count(*) as pocet from sbj_subject";
                        using (SqlDataReader l = command.ExecuteReader())
                        {

                            while (l.Read())
                            {
                                Console.WriteLine(l.GetInt32(0));
                            }
                        }
                    }
                }
                catch (SqlException e)
                {
                    Console.WriteLine(e.Message);
                }
                catch(Exception e)
                {
                    Console.WriteLine(e);
                }
            }
        }
    }
}