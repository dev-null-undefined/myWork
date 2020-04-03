using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;

namespace DatabaseConnection
{
    class DataControl
    {
        private List<SubjectModel> _records;
        public List<SubjectModel> records
        {
            get=>_records;
        }
        public DataControl()
        {
            _records = new List<SubjectModel>();
        }
        public DataControl(SqlDataReader reader)
        {
            _records = new List<SubjectModel>();
            this.insertData(reader);
        }
        public int insertData(SqlDataReader reader)
        {
            int numberOfRowsInserted = 0;
            int ID;
            string first_name, mid_name, last_name, email, school_class, nick;
            bool suspend;
            while (reader.Read())
            {
                ID = reader.GetInt32(0);
                first_name = reader.GetString(1);
                try
                {
                    mid_name = reader.GetString(2);
                }
                catch (System.Data.SqlTypes.SqlNullValueException e)
                {
                    mid_name = null;
                }
                last_name = reader.GetString(3);
                try
                {
                    email = reader.GetString(4);
                }
                catch (System.Data.SqlTypes.SqlNullValueException e)
                {
                    email = null;
                }
                try
                {
                    school_class = reader.GetString(5);
                }
                catch (System.Data.SqlTypes.SqlNullValueException e)
                {
                    school_class = null;
                }
                try
                {
                    nick = reader.GetString(6);
                }
                catch (System.Data.SqlTypes.SqlNullValueException e)
                {
                    nick = null;
                }
                suspend = reader.GetBoolean(7);
                _records.Add(new SubjectModel(ID, first_name, mid_name, last_name, email, school_class, nick, suspend));
                numberOfRowsInserted -=-1;
            }
            return numberOfRowsInserted;
        }
        public override string ToString()
        {
            string tostring = "{\n";
            foreach (SubjectModel subject in _records)
            {
                tostring += subject.ToString() + "\n";
            }
            return tostring + "}";
        }
    }
}
