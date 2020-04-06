using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;

namespace DatabaseConnection
{
    class DataControl
    {
        private List<SubjectModel> _records;
        public List<SubjectModel> Records
        {
            get=>_records;
        }
        /// <summary>
        /// Constructor for DataControl with emptly Record
        /// </summary>
        public DataControl()
        {
            _records = new List<SubjectModel>();
        }
        /// <summary>
        /// Constructor for DataControl
        /// </summary>
        /// <param name="command">
        /// Records from the command will be inserted using <see cref="InsertData(SqlCommand)"/>
        /// </param>
        public DataControl(SqlCommand command)
        {
            _records = new List<SubjectModel>();
            this.InsertData(command);
        }

        /// <summary>
        /// Insert data to localy stored records.
        /// </summary>
        /// <param name="command">
        /// Command must be initializated with valid Connection and CommandText 
        /// </param>
        /// <returns>
        /// Number of rows imported into local records
        /// </returns>
        public int InsertData(SqlCommand command)
        {
            using (SqlDataReader reader = command.ExecuteReader())
            {
                int numberOfRowsInserted = 0;
                while (reader.Read())
                {
                    SubjectModel model = new SubjectModel();
                    model.ID = reader.GetInt32(0);
                    model.First_name = reader.GetString(1);
                    model.Mid_name = (reader["mid_name"] == DBNull.Value) ? string.Empty : (String)reader["mid_name"];
                    model.Last_name = reader.GetString(3);
                    model.Email = (reader["email"] == DBNull.Value) ? string.Empty : (String)reader["email"];
                    model.School_class = (reader["school_class"] == DBNull.Value) ? string.Empty : (String)reader["school_class"];
                    model.Nick = (reader["nick"] == DBNull.Value) ? string.Empty : (String)reader["nick"];
                    model.Suspend = reader.GetBoolean(7);
                    _records.Add(model);
                    numberOfRowsInserted -= -1;
                }
                return numberOfRowsInserted;
            }
        }
    }
}
