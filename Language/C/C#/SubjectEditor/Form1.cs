using System;
using System.Collections.Specialized;
using System.Configuration;
using System.Data;

using System.Data.SqlClient;
using System.Drawing;
using System.Threading;
using System.Windows.Forms;

namespace SubjectEditor
{
    public partial class SubjectEditor : Form
    {
        DataTable t = new DataTable("Subject");
        private SqlConnection con;
        private int newIdShift=0;
        private NameValueCollection settings = ConfigurationManager.AppSettings;
        public SubjectEditor()
        {
            InitializeComponent();
            con = new SqlConnection("Data Source="+ settings.Get("host")+","+ settings.Get("port") + "; Database="+ settings.Get("database") + "; User ID="+ settings.Get("user") + ";password="+ settings.Get("password"));
            Download();
            if(settings.Get("id-Read-only")=="true"|| settings.Get("id-Read-only") == "1")
            {
                dataGridView1.Columns["id"].ReadOnly = true;
                ID_Value.ReadOnly = true;
            }
        }

        private void SyncButtonClick(object sender, EventArgs e)
        {
            Upload();
            Download();
        }
        private void Download()
        {
            con.Open();
            newIdShift = 0;
            SqlCommand c = new SqlCommand("SELECT * FROM "+settings.Get("table"), con);
            t.Clear();
            SqlDataAdapter a = new SqlDataAdapter(c);
            a.Fill(t);
            BindingSource bs = new BindingSource(t, "");
            dataGridView1.DataSource = bs;
            Binding bID = new Binding("Text", bs, "id");
            bID.DataSourceUpdateMode = DataSourceUpdateMode.OnPropertyChanged;
            Binding bFistName = new Binding("Text", bs, "first_name");
            bFistName.DataSourceUpdateMode = DataSourceUpdateMode.OnPropertyChanged;
            Binding bLastName = new Binding("Text", bs, "last_name");
            bLastName.DataSourceUpdateMode = DataSourceUpdateMode.OnPropertyChanged;
            Binding bSchoolClass = new Binding("Text", bs, "school_class");
            bSchoolClass.DataSourceUpdateMode = DataSourceUpdateMode.OnPropertyChanged;
            ID_Value.DataBindings.Clear();
            ID_Value.DataBindings.Add(bID);
            FirstName_Value.DataBindings.Clear();
            FirstName_Value.DataBindings.Add(bFistName);
            LastName_Value.DataBindings.Clear();
            LastName_Value.DataBindings.Add(bLastName);
            SchoolClass_Value.DataBindings.Clear();
            SchoolClass_Value.DataBindings.Add(bSchoolClass);
            con.Close();
        }
        private void Upload()
        {
            con.Open();
            SqlCommand selectCmd = new SqlCommand("SELECT * FROM "+ settings.Get("table"), con);
            SqlCommand updateCmd = new SqlCommand("UPDATE "+settings.Get("table") + " SET id=@id, first_name=@firstName, mid_name=@midName , last_name=@lastName,email=@email , school_class=@schoolClass, nick=@nick, suspend=@suspend WHERE id=@updateID;", con);
            updateCmd.Parameters.Add("@firstName", SqlDbType.NVarChar, 50, "first_name");
            updateCmd.Parameters.Add("@midName", SqlDbType.NVarChar, 50, "mid_name");
            updateCmd.Parameters.Add("@lastName", SqlDbType.NVarChar, 50, "last_name");
            updateCmd.Parameters.Add("@email", SqlDbType.NVarChar, 128, "email");
            updateCmd.Parameters.Add("@schoolClass", SqlDbType.NChar, 3, "school_class");
            updateCmd.Parameters.Add("@nick", SqlDbType.NChar, 3, "nick");
            updateCmd.Parameters.Add("@suspend", SqlDbType.Bit, 0, "suspend");
            updateCmd.Parameters.Add("@id", SqlDbType.Int, 0, "id");
            updateCmd.Parameters.Add("@updateID", SqlDbType.Int, 0, "id").SourceVersion = DataRowVersion.Original;
            SqlCommand insert = new SqlCommand("insert into "+ settings.Get("table") + " (id,first_name, mid_name, last_name, email, school_class, nick, suspend) values (@id,@first_name, @mid_name, @last_name, @email, @school_class, @nick, @suspend)", con);
            insert.Parameters.Add("@first_name", SqlDbType.NVarChar, 50, "first_name");
            insert.Parameters.Add("@mid_name", SqlDbType.NVarChar, 50, "mid_name");
            insert.Parameters.Add("@last_name", SqlDbType.NVarChar, 50, "last_name");
            insert.Parameters.Add("@email", SqlDbType.NVarChar, 128, "email");
            insert.Parameters.Add("@school_class", SqlDbType.NChar, 3, "school_class");
            insert.Parameters.Add("@nick", SqlDbType.NChar, 3, "nick");
            insert.Parameters.Add("@suspend", SqlDbType.Bit, 0, "suspend");
            insert.Parameters.Add("@id", SqlDbType.Int, 0, "id");
            SqlCommand delete = new SqlCommand("DELETE FROM "+ settings.Get("table") + " where id=@deleteID", con);
            delete.Parameters.Add("@deleteID", SqlDbType.Int, 0, "id").SourceVersion = DataRowVersion.Original;
            SqlDataAdapter a = new SqlDataAdapter();
            a.UpdateCommand = updateCmd;
            a.InsertCommand = insert;
            a.DeleteCommand = delete;
            a.Update(t);
            con.Close();
        }
        private void UploadButtonClick(object sender, EventArgs e)
        {
            Upload();
        }

        private void DownloadButtonClick(object sender, EventArgs e)
        {
            Download();
        }


        private void DefaultValuesNeeded(object sender, DataGridViewRowEventArgs e)
        {
            e.Row.Cells["suspend"].Value = false;
            e.Row.Cells["first_name"].Value = "";
            e.Row.Cells["mid_name"].Value = "";
            e.Row.Cells["last_name"].Value = "";
            e.Row.Cells["email"].Value = "";
            e.Row.Cells["school_class"].Value = "";
            e.Row.Cells["nick"].Value = "";
        }

        private void dataGridView1_UserAddedRow(object sender, DataGridViewRowEventArgs e)
        {
            Console.WriteLine("Hi");
            con.Open();
            SqlCommand cmd = new SqlCommand("SELECT max(id)+1 from "+ settings.Get("table"), con);
            dataGridView1.Rows[e.Row.Index-1].Cells["id"].Value = Convert.ToInt32(cmd.ExecuteScalar())+ newIdShift;
            con.Close();
            newIdShift++;
        }
    }
}
