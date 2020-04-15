using System;
using System.Data;
using System.Data.SqlClient;
using System.IO;

namespace DatabaseConnection
{
    class Program
    {
        #region Zadani
        //Vytvořte třídu DataControl, která bude vytvářet „lokální“ kopii dat ze db servere dvěma způsoby.

        //1)      před DataReader + Strongly typed data

        //a.vytvoř třídu SubjectModel, která bude mít vlastnosti odpovídající datové tabulce sbj_subject (například public int ID { get; set; })

        //b.vytvoř proměnou typu List<SubjectModel>


        //c.z předchozí úlohy použij SQLConnection+SQLCommand

        //d.      z SQLCommand vytvoř DataReader a načti data do proměnné List< SubjectModel >

        //2)      před DataAdapter + DataTable

        //a.z předchozí úlohy použij SQLConnection+SQLCommand

        //b.      vytvoř DataAdapter pomocí SQLCommand

        //c.vytvoř proměnnou typu DataTable

        //d.naplň DataTable pomocí DataAdapter a metody Fill



        //Oba způsoby vyzkoušej a projekt pošli na email.



        //Odpověz na otázky:

        //-         k čemu slouží DataAdapter

        //-         k čemu slouží DataReader

        //-         k čemu slouží DataTable
        #endregion
        static void Main(string[] args)
        {
            string connstring = string.Format("Server={0}, {1}; UID={2}; password={3}; Database={4}", "193.85.203.188", "11433", "student", "StudentPV", "pv");
            using (SqlConnection connection = new SqlConnection(connstring)) {
                try
                {
                    connection.Open();
                    using (SqlCommand command = new SqlCommand())
                    {
                        command.Connection = connection;
                        command.CommandText = "select * from sbj_subject";
                        // Version 1
                        DataControl dataCopy = new DataControl(command);
                        Console.WriteLine(dataCopy);
                        foreach(SubjectModel s in dataCopy.Records) {
                            Console.WriteLine(s);
                        }
                        // Version 2
                        SqlDataAdapter adapter = new SqlDataAdapter(command);
                        DataTable set = new DataTable("Subject");
                        adapter.Fill(set);
                        using (var writer = new StringWriter())
                        {
                            set.WriteXml(writer);
                            Console.WriteLine(writer.ToString());
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