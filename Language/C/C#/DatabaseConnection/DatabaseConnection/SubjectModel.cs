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

        public string Nick { get => _nick;  }
        public string School_class { get => _school_class; }
        public string Email { get => _email; }
        public string Last_name { get => _last_name; }
        public string Mid_name { get => _mid_name;  }
        public string First_name { get => _first_name; }
        public int ID { get => _ID; }
        public override bool Equals(object obj)
        {
            return obj is SubjectModel model &&
                   _ID == model._ID &&
                   _first_name == model._first_name &&
                   _mid_name == model._mid_name &&
                   _last_name == model._last_name &&
                   _email == model._email &&
                   _school_class == model._school_class &&
                   _nick == model._nick &&
                   _suspend == model._suspend;
        }

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
