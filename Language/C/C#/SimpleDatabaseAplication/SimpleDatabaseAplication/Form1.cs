using MySql.Data.MySqlClient;
using System;
using System.Configuration;
using System.Data;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace SimpleDatabaseAplication
{
    public partial class Form1 : Form
    {
        
        private MySqlConnection _conn;
        private DataSet _tables;
        // Relation between talbes in dataSet
        public Form1()
        {
            _tables = new DataSet();
            _tables.Tables.Add(new DataTable("autor"));
            _tables.Tables.Add(new DataTable("pohlavi"));
            _tables.Tables.Add(new DataTable("kniha"));
            _conn = new MySqlConnection(ConfigurationManager.ConnectionStrings["server"].ConnectionString);
            InitializeComponent();
            download();
        }

        private void PridejRadekAutor()
        {
            DataRow newRow = _tables.Tables["autor"].NewRow();
            newRow["pohlavi_id"] = 1;
            newRow["datum_narozeni"] = DateTime.Today;
            _tables.Tables["autor"].Rows.Add(newRow);
        }
        private void PridejRadekKniha()
        {
            DataRow newRow = _tables.Tables["kniha"].NewRow();
            newRow["autor_id"] = 1;
            newRow["datum_vydani"] = DateTime.Today;
            _tables.Tables["kniha"].Rows.Add(newRow);
        }
        private void download()
        {
            _conn.Open();
            MySqlCommand command;
            MySqlDataAdapter adapter;

            // pohlavi
            command = new MySqlCommand("SELECT * FROM pohlavi;", _conn);
            adapter = new MySqlDataAdapter(command);
            _tables.Tables["pohlavi"].Clear();
            adapter.Fill(_tables.Tables["pohlavi"]);
            AutorPohlaviBox.DisplayMember = "nazev";
            AutorPohlaviBox.DataSource = _tables.Tables["pohlavi"];
            // autori
            command = new MySqlCommand("select * from autor;", _conn);
            adapter = new MySqlDataAdapter(command);
            _tables.Tables["autor"].Clear();
            adapter.Fill(_tables.Tables["autor"]);
            AutorJmenoBox.DisplayMember = "jmeno";
            AutorJmenoBox.ValueMember = "id";
            AutorPrijmeniBox.DisplayMember = "prijmeni";
            AutorPrijmeniBox.ValueMember = "id";
            AutorJmenoBox.DataSource = _tables.Tables["autor"];
            AutorPrijmeniBox.DataSource = _tables.Tables["autor"];
            AutorNarozen.Format = DateTimePickerFormat.Custom;
            AutorNarozen.CustomFormat = "yyyy/MM/dd";
            PridejRadekAutor();
            // knihy
            command = new MySqlCommand("select * from kniha;", _conn);
            adapter = new MySqlDataAdapter(command);
            _tables.Tables["kniha"].Clear();
            adapter.Fill(_tables.Tables["kniha"]);
            KnihaNazevBox.DisplayMember = "nazev";
            KnihaNazevBox.DataSource = _tables.Tables["kniha"];
            KnihaVydani.Format = DateTimePickerFormat.Custom;
            KnihaVydani.CustomFormat = "yyyy/MM/dd";
            PridejRadekKniha();
            selectAutorToKniha();
            selectPohlaviToAutor();
            _conn.Close();
        }
        private bool systemIndexChangePohlavi = true;
        private bool systemIndexChangeAutor = true;
        public void selectPohlaviToAutor()
        {
                int rowIndex = -1;
                var pohlaviId = _tables.Tables["autor"].Rows[AutorJmenoBox.SelectedIndex]["pohlavi_id"];
                for (int i = 0, l = _tables.Tables["pohlavi"].Rows.Count; i < l; i++)
                {
                    if (_tables.Tables["pohlavi"].Rows[i]["id"].Equals(pohlaviId))
                    {
                        rowIndex = i;
                    }
                }
                if (AutorPohlaviBox.SelectedIndex != rowIndex)
                {
                    systemIndexChangePohlavi = true;
                    AutorPohlaviBox.SelectedIndex = rowIndex;
                }            
        }
        public void selectAutorToKniha()
        {
            int rowIndex = -1;
            object autorId = _tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["autor_id"];
            for (int i = 0, l = _tables.Tables["autor"].Rows.Count; i < l; i++)
            {
                if (_tables.Tables["autor"].Rows[i].RowState != DataRowState.Detached&&_tables.Tables["autor"].Rows[i]["id"].Equals(autorId))
                {
                    rowIndex = i;
                }
            }
            if (AutorJmenoBox.SelectedIndex != rowIndex)
            {
                systemIndexChangeAutor = true;
                AutorJmenoBox.SelectedIndex = rowIndex;
            }
        }
        public void upload()
        {
            this.ValidateChildren();
            _conn.Open();
            // AUTOR
            _tables.Tables["autor"].Rows.RemoveAt(_tables.Tables["autor"].Rows.Count - 1);
            MySqlCommand insert = new MySqlCommand("INSERT INTO autor (`jmeno`, `prijmeni`, `datum_narozeni`, `pohlavi_id`) values (@jmeno, @prijmeni, @datum_narozeni, @pohlavi_id)", _conn);
            insert.Parameters.Add("@jmeno", MySqlDbType.VarChar, 45, "jmeno");
            insert.Parameters.Add("@prijmeni", MySqlDbType.VarChar, 45, "prijmeni");
            insert.Parameters.Add("@datum_narozeni", MySqlDbType.Date, 1, "datum_narozeni");
            insert.Parameters.Add("@pohlavi_id", MySqlDbType.Int32, 11, "pohlavi_id");
            MySqlCommand update = new MySqlCommand("UPDATE autor SET `jmeno` = @jmeno, `prijmeni` = @prijmeni, `datum_narozeni` = @datum_narozeni, `pohlavi_id` = @pohlavi_id WHERE ID=@ID", _conn);
            update.Parameters.Add("@jmeno", MySqlDbType.VarChar, 45, "jmeno");
            update.Parameters.Add("@prijmeni", MySqlDbType.VarChar, 45, "prijmeni");
            update.Parameters.Add("@datum_narozeni", MySqlDbType.Date, 0, "datum_narozeni");
            update.Parameters.Add("@pohlavi_id", MySqlDbType.Int32, 11, "pohlavi_id");
            update.Parameters.Add("@ID", MySqlDbType.Int32, 11, "id");
            MySqlDataAdapter adapter = new MySqlDataAdapter();
            adapter.InsertCommand = insert;
            adapter.UpdateCommand = update;
            adapter.Update(_tables.Tables["autor"]);
            // KHINA
            _tables.Tables["kniha"].Rows.RemoveAt(_tables.Tables["kniha"].Rows.Count - 1);
            insert = new MySqlCommand("INSERT INTO kniha (nazev,datum_vydani,popis,autor_id,image) values (@nazev,@datum_vydani,@popis,@autor_id,@image)", _conn);
            insert.Parameters.Add("@autor_id", MySqlDbType.Int32, 11, "autor_id");
            insert.Parameters.Add("@datum_vydani", MySqlDbType.Date, 1, "datum_vydani");
            insert.Parameters.Add("@popis", MySqlDbType.VarChar, 450, "popis");
            insert.Parameters.Add("@image", MySqlDbType.MediumBlob, 1, "image");
            insert.Parameters.Add("@nazev", MySqlDbType.VarChar, 45, "nazev");
            update = new MySqlCommand("UPDATE kniha SET `nazev` = @nazev, `datum_vydani` = @datum_vydani, `popis` = @popis, `autor_id` = @autor_id, `image` = @image WHERE ID=@ID", _conn);
            update.Parameters.Add("@ID", MySqlDbType.Int32, 11, "id");
            update.Parameters.Add("@nazev", MySqlDbType.VarChar, 45, "nazev");
            update.Parameters.Add("@autor_id", MySqlDbType.Int32, 11, "autor_id");
            update.Parameters.Add("@datum_vydani", MySqlDbType.Date, 1, "datum_vydani");
            update.Parameters.Add("@popis", MySqlDbType.VarChar, 450, "popis");
            update.Parameters.Add("@image", MySqlDbType.MediumBlob, 1, "image");
            adapter = new MySqlDataAdapter();
            adapter.InsertCommand = insert;
            adapter.UpdateCommand = update;
            adapter.Update(_tables.Tables["kniha"]);
            _conn.Close();

        }
        private void AutorJmenoBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (AutorJmenoBox.SelectedIndex != -1)
            {
                AutorNarozen.Value = Convert.ToDateTime(_tables.Tables["autor"].Rows[AutorJmenoBox.SelectedIndex]["datum_narozeni"].ToString());
                if (!systemIndexChangeAutor && KnihaNazevBox.SelectedIndex!=-1&& _tables.Tables["autor"].Rows[AutorJmenoBox.SelectedIndex]["id"]!=System.DBNull.Value)
                {
                    _tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["autor_id"] = _tables.Tables["autor"].Rows[AutorJmenoBox.SelectedIndex]["id"];
                }
                selectPohlaviToAutor();
                systemIndexChangeAutor = false;
            }
        }
        private void AutorPohlaviBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (AutorPohlaviBox.SelectedIndex != -1&&!systemIndexChangePohlavi && AutorPrijmeniBox.SelectedIndex != -1)
            {
                    _tables.Tables["autor"].Rows[AutorPrijmeniBox.SelectedIndex]["pohlavi_id"] = _tables.Tables["pohlavi"].Rows[AutorPohlaviBox.SelectedIndex]["id"];
            }
            systemIndexChangePohlavi = false;
        }

        private void AutorJmenoBox_Validated(object sender, EventArgs e)
        {
            if (AutorJmenoBox.SelectedIndex == -1)
            {
                bool pridejAutora = _tables.Tables["autor"].Rows[AutorPrijmeniBox.SelectedIndex]["jmeno"] == System.DBNull.Value;
                _tables.Tables["autor"].Rows[AutorPrijmeniBox.SelectedIndex]["jmeno"] = AutorJmenoBox.Text;
                AutorJmenoBox.SelectedIndex = AutorPrijmeniBox.SelectedIndex;
                if (pridejAutora)
                {
                    int saveIndex = AutorJmenoBox.SelectedIndex;
                    PridejRadekAutor();
                    upload();
                    download();
                    AutorJmenoBox.SelectedIndex = saveIndex;
                    if (KnihaNazevBox.SelectedIndex != -1)
                    {
                        _tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["autor_id"] = _tables.Tables["autor"].Rows[AutorJmenoBox.SelectedIndex]["id"];
                    }
                    selectAutorToKniha();
                }
            }
        }

        private void AutorPrijmeniBox_Validated(object sender, EventArgs e)
        {
            if (AutorPrijmeniBox.SelectedIndex == -1)
            {
                _tables.Tables["autor"].Rows[AutorJmenoBox.SelectedIndex]["prijmeni"] = AutorPrijmeniBox.Text;
                AutorPrijmeniBox.SelectedIndex = AutorJmenoBox.SelectedIndex;
            }
        }
        private void AutorNarozen_ValueChanged(object sender, EventArgs e)
        {
            if (AutorPrijmeniBox.SelectedIndex != -1)
            {
                _tables.Tables["autor"].Rows[AutorJmenoBox.SelectedIndex]["datum_narozeni"] = AutorNarozen.Value.ToString();
            }
        }


        
        private void loadImageFromDatabeze()
        {
            if(_tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["image"] != System.DBNull.Value)
            {
                Stream str = new MemoryStream((Byte[])_tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["image"]);
                Bitmap image = new Bitmap(str);
                KnihaPictureBox.Image = image;
            }
            else
            {
                KnihaPictureBox.Image = KnihaPictureBox.InitialImage;

            }
        }

        private void KnihaPopis_Validated(object sender, EventArgs e)
        {
            if (KnihaNazevBox.SelectedIndex != -1)
            {
                _tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["popis"] = KnihaPopis.Text;
            }
        }
        private void KnihaNazevBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (KnihaNazevBox.SelectedIndex != -1)
            {
                KnihaVydani.Value = Convert.ToDateTime(_tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["datum_vydani"].ToString());
                KnihaPopis.Text = _tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["popis"].ToString();
                loadImageFromDatabeze();
                selectAutorToKniha();
                oldIndex = KnihaNazevBox.SelectedIndex;
            }
        }
        private int oldIndex=-1;
        private void KnihaNazevBox_Validated(object sender, EventArgs e)
        {
            if (KnihaNazevBox.SelectedIndex == -1&&oldIndex!=-1)
            {
                bool pridejKnihu = _tables.Tables["kniha"].Rows[oldIndex]["nazev"] == System.DBNull.Value;
                _tables.Tables["kniha"].Rows[oldIndex]["nazev"] = KnihaNazevBox.Text;
                KnihaNazevBox.SelectedIndex = oldIndex;
                int saveIndex = oldIndex;
                if (pridejKnihu)
                {
                    PridejRadekKniha();
                    upload();
                    download();
                    KnihaNazevBox.SelectedIndex = saveIndex;
                }
            }
        }


        private void KnihaUploadImage_MouseClick(object sender, MouseEventArgs e)
        {
            if (KnihaNazevBox.SelectedIndex != -1)
            {
                using (OpenFileDialog dlg = new OpenFileDialog())
                {
                    dlg.Title = "Open Image";
                    dlg.Filter = "bmp files (*.bmp)|*.bmp";

                    if (dlg.ShowDialog() == DialogResult.OK)
                    {
                        Bitmap image = new Bitmap(dlg.FileName);
                        KnihaPictureBox.Image = image;
                        MemoryStream ms = new MemoryStream();
                        image.Save(ms, System.Drawing.Imaging.ImageFormat.Png);
                        byte[] bmpBytes = ms.GetBuffer();
                        ms.Close();
                        _tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["image"] = bmpBytes;
                    }
                }
            }
        }


        private void AutorPrijmeniBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyValue == 13)
            {
                AutorNarozen.Focus();
            }
        }

        private void AutorJmenoBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyValue == 13)
            {
                AutorPrijmeniBox.Focus();
            }
        }

        #region TopButtons
        private void DownloadButton_Click(object sender, EventArgs e)
        {
            download();
        }

        private void UploadButton_Click(object sender, EventArgs e)
        {
            upload();
        }

        private void SyncButton_Click(object sender, EventArgs e)
        {
            upload();
            download();
        }

        #endregion

        private void KnihaVydani_ValueChanged(object sender, EventArgs e)
        {
            if (KnihaNazevBox.SelectedIndex != -1)
            {
                _tables.Tables["kniha"].Rows[KnihaNazevBox.SelectedIndex]["datum_vydani"] = KnihaVydani.Value.ToString();
            }
        }
    }
}
