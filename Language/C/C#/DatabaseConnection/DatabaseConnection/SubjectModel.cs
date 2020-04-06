using System;
using System.Collections.Generic;
using System.Reflection;
using System.Text;

namespace DatabaseConnection
{
    class SubjectModel
    {
        private int _ID;
        private string _first_name;
        private string _mid_name;
        private string _last_name;
        private string _email;
        private string _school_class;
        private string _nick;
        private bool _suspend;

        public SubjectModel()
        {
        }
        public SubjectModel(int ID,string first_name, string mid_name, string last_name, string email,string school_class,string nick, bool suspend)
        {
            _ID = ID;
            _first_name = first_name;
            _mid_name = mid_name;
            _last_name = last_name;
            _email = email;
            _school_class = school_class;
            _nick = nick;
            _suspend = suspend;
        }

        public int ID { get => _ID; set => _ID = value; }
        public string First_name { get => _first_name; set => _first_name = value; }
        public string Mid_name { get => _mid_name; set => _mid_name = value; }
        public string Last_name { get => _last_name; set => _last_name = value; }
        public string Email { get => _email; set => _email = value; }
        public string School_class { get => _school_class; set => _school_class = value; }
        public string Nick { get => _nick; set => _nick = value; }
        public bool Suspend { get => _suspend; set => _suspend = value; }

        /// <summary>
        /// Basic to string for testing only.
        /// </summary>
        /// <returns>
        /// String with all Properties and theyr value.
        /// </returns>
        public override String ToString()
        {
            Type objType = this.GetType();
            PropertyInfo[] propertyInfoList = objType.GetProperties();
            StringBuilder result = new StringBuilder();
            foreach (PropertyInfo propertyInfo in propertyInfoList)
            {
                result.AppendFormat("{0}={1}, ", propertyInfo.Name, propertyInfo.GetValue(this));
            }
            return result.ToString();
        }
    }
}
