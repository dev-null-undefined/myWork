namespace SimpleDatabaseAplication
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.AutorPohlaviLabel = new System.Windows.Forms.Label();
            this.AutorJmenoBox = new System.Windows.Forms.ComboBox();
            this.AutorPohlaviBox = new System.Windows.Forms.ComboBox();
            this.AutorJmenoLabel = new System.Windows.Forms.Label();
            this.AutorNarozenLabel = new System.Windows.Forms.Label();
            this.AutorPrijmeniBox = new System.Windows.Forms.ComboBox();
            this.AutorPrijmeniLabel = new System.Windows.Forms.Label();
            this.AutorNarozen = new System.Windows.Forms.DateTimePicker();
            this.AutorLabel = new System.Windows.Forms.Label();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.KnihaUploadImage = new System.Windows.Forms.Button();
            this.KnihaPictureBox = new System.Windows.Forms.PictureBox();
            this.tableLayoutPanel4 = new System.Windows.Forms.TableLayoutPanel();
            this.KnihaPopis = new System.Windows.Forms.RichTextBox();
            this.KnihaNazevBox = new System.Windows.Forms.ComboBox();
            this.KnihaVydani = new System.Windows.Forms.DateTimePicker();
            this.KnihaLabel = new System.Windows.Forms.Label();
            this.DownloadButton = new System.Windows.Forms.ToolStripButton();
            this.UploadButton = new System.Windows.Forms.ToolStripButton();
            this.SyncButton = new System.Windows.Forms.ToolStripButton();
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            this.tableLayoutPanel1.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.tableLayoutPanel3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.KnihaPictureBox)).BeginInit();
            this.tableLayoutPanel4.SuspendLayout();
            this.toolStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.BackColor = System.Drawing.SystemColors.ControlDark;
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 47);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.BackColor = System.Drawing.SystemColors.ControlLightLight;
            this.splitContainer1.Panel1.Controls.Add(this.tableLayoutPanel1);
            this.splitContainer1.Panel1.Controls.Add(this.AutorLabel);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.BackColor = System.Drawing.SystemColors.ControlLightLight;
            this.splitContainer1.Panel2.Controls.Add(this.tableLayoutPanel2);
            this.splitContainer1.Panel2.Controls.Add(this.KnihaLabel);
            this.splitContainer1.Size = new System.Drawing.Size(800, 404);
            this.splitContainer1.SplitterDistance = 311;
            this.splitContainer1.TabIndex = 2;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel1.Controls.Add(this.AutorPohlaviLabel, 0, 3);
            this.tableLayoutPanel1.Controls.Add(this.AutorJmenoBox, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.AutorPohlaviBox, 1, 3);
            this.tableLayoutPanel1.Controls.Add(this.AutorJmenoLabel, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.AutorNarozenLabel, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.AutorPrijmeniBox, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.AutorPrijmeniLabel, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.AutorNarozen, 1, 2);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 30);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 4;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 24F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 24F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 24F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel1.Size = new System.Drawing.Size(311, 374);
            this.tableLayoutPanel1.TabIndex = 3;
            // 
            // AutorPohlaviLabel
            // 
            this.AutorPohlaviLabel.AutoSize = true;
            this.AutorPohlaviLabel.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.AutorPohlaviLabel.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorPohlaviLabel.Location = new System.Drawing.Point(0, 75);
            this.AutorPohlaviLabel.Margin = new System.Windows.Forms.Padding(0, 3, 0, 0);
            this.AutorPohlaviLabel.Name = "AutorPohlaviLabel";
            this.AutorPohlaviLabel.Padding = new System.Windows.Forms.Padding(3);
            this.AutorPohlaviLabel.Size = new System.Drawing.Size(50, 19);
            this.AutorPohlaviLabel.TabIndex = 7;
            this.AutorPohlaviLabel.Text = "Pohlavi";
            // 
            // AutorJmenoBox
            // 
            this.AutorJmenoBox.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.SuggestAppend;
            this.AutorJmenoBox.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.ListItems;
            this.AutorJmenoBox.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorJmenoBox.FormattingEnabled = true;
            this.AutorJmenoBox.Location = new System.Drawing.Point(53, 3);
            this.AutorJmenoBox.Name = "AutorJmenoBox";
            this.AutorJmenoBox.Size = new System.Drawing.Size(255, 21);
            this.AutorJmenoBox.TabIndex = 1;
            this.AutorJmenoBox.SelectedIndexChanged += new System.EventHandler(this.AutorJmenoBox_SelectedIndexChanged);
            this.AutorJmenoBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AutorJmenoBox_KeyDown);
            this.AutorJmenoBox.Validated += new System.EventHandler(this.AutorJmenoBox_Validated);
            // 
            // AutorPohlaviBox
            // 
            this.AutorPohlaviBox.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorPohlaviBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.AutorPohlaviBox.FormattingEnabled = true;
            this.AutorPohlaviBox.Location = new System.Drawing.Point(53, 75);
            this.AutorPohlaviBox.Name = "AutorPohlaviBox";
            this.AutorPohlaviBox.Size = new System.Drawing.Size(255, 21);
            this.AutorPohlaviBox.TabIndex = 8;
            this.AutorPohlaviBox.SelectedIndexChanged += new System.EventHandler(this.AutorPohlaviBox_SelectedIndexChanged);
            // 
            // AutorJmenoLabel
            // 
            this.AutorJmenoLabel.AutoSize = true;
            this.AutorJmenoLabel.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.AutorJmenoLabel.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorJmenoLabel.Location = new System.Drawing.Point(0, 3);
            this.AutorJmenoLabel.Margin = new System.Windows.Forms.Padding(0, 3, 0, 0);
            this.AutorJmenoLabel.Name = "AutorJmenoLabel";
            this.AutorJmenoLabel.Padding = new System.Windows.Forms.Padding(3);
            this.AutorJmenoLabel.Size = new System.Drawing.Size(50, 19);
            this.AutorJmenoLabel.TabIndex = 0;
            this.AutorJmenoLabel.Text = "Jmeno";
            // 
            // AutorNarozenLabel
            // 
            this.AutorNarozenLabel.AutoSize = true;
            this.AutorNarozenLabel.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.AutorNarozenLabel.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorNarozenLabel.Location = new System.Drawing.Point(0, 51);
            this.AutorNarozenLabel.Margin = new System.Windows.Forms.Padding(0, 3, 0, 0);
            this.AutorNarozenLabel.Name = "AutorNarozenLabel";
            this.AutorNarozenLabel.Padding = new System.Windows.Forms.Padding(3);
            this.AutorNarozenLabel.Size = new System.Drawing.Size(50, 21);
            this.AutorNarozenLabel.TabIndex = 6;
            this.AutorNarozenLabel.Text = "Narozen";
            // 
            // AutorPrijmeniBox
            // 
            this.AutorPrijmeniBox.AutoCompleteMode = System.Windows.Forms.AutoCompleteMode.SuggestAppend;
            this.AutorPrijmeniBox.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.ListItems;
            this.AutorPrijmeniBox.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorPrijmeniBox.FormattingEnabled = true;
            this.AutorPrijmeniBox.Location = new System.Drawing.Point(53, 27);
            this.AutorPrijmeniBox.Name = "AutorPrijmeniBox";
            this.AutorPrijmeniBox.Size = new System.Drawing.Size(255, 21);
            this.AutorPrijmeniBox.TabIndex = 3;
            this.AutorPrijmeniBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AutorPrijmeniBox_KeyDown);
            this.AutorPrijmeniBox.Validated += new System.EventHandler(this.AutorPrijmeniBox_Validated);
            // 
            // AutorPrijmeniLabel
            // 
            this.AutorPrijmeniLabel.AutoSize = true;
            this.AutorPrijmeniLabel.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.AutorPrijmeniLabel.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorPrijmeniLabel.Location = new System.Drawing.Point(0, 27);
            this.AutorPrijmeniLabel.Margin = new System.Windows.Forms.Padding(0, 3, 0, 0);
            this.AutorPrijmeniLabel.Name = "AutorPrijmeniLabel";
            this.AutorPrijmeniLabel.Padding = new System.Windows.Forms.Padding(3);
            this.AutorPrijmeniLabel.Size = new System.Drawing.Size(50, 19);
            this.AutorPrijmeniLabel.TabIndex = 4;
            this.AutorPrijmeniLabel.Text = "Prijmeni";
            // 
            // AutorNarozen
            // 
            this.AutorNarozen.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorNarozen.Location = new System.Drawing.Point(53, 51);
            this.AutorNarozen.Name = "AutorNarozen";
            this.AutorNarozen.Size = new System.Drawing.Size(255, 20);
            this.AutorNarozen.TabIndex = 5;
            this.AutorNarozen.ValueChanged += new System.EventHandler(this.AutorNarozen_ValueChanged);
            // 
            // AutorLabel
            // 
            this.AutorLabel.BackColor = System.Drawing.SystemColors.Control;
            this.AutorLabel.Dock = System.Windows.Forms.DockStyle.Top;
            this.AutorLabel.Font = new System.Drawing.Font("MV Boli", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.AutorLabel.Location = new System.Drawing.Point(0, 0);
            this.AutorLabel.Margin = new System.Windows.Forms.Padding(3, 3, 3, 0);
            this.AutorLabel.Name = "AutorLabel";
            this.AutorLabel.Size = new System.Drawing.Size(311, 30);
            this.AutorLabel.TabIndex = 2;
            this.AutorLabel.Text = "Autor";
            this.AutorLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.tableLayoutPanel2.ColumnCount = 2;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 29.89691F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 70.1031F));
            this.tableLayoutPanel2.Controls.Add(this.tableLayoutPanel3, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.tableLayoutPanel4, 1, 0);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 30);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 1;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.Size = new System.Drawing.Size(485, 374);
            this.tableLayoutPanel2.TabIndex = 1;
            // 
            // tableLayoutPanel3
            // 
            this.tableLayoutPanel3.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.tableLayoutPanel3.ColumnCount = 1;
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel3.Controls.Add(this.KnihaUploadImage, 0, 1);
            this.tableLayoutPanel3.Controls.Add(this.KnihaPictureBox, 0, 0);
            this.tableLayoutPanel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel3.Location = new System.Drawing.Point(3, 3);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            this.tableLayoutPanel3.RowCount = 2;
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 45F));
            this.tableLayoutPanel3.Size = new System.Drawing.Size(138, 368);
            this.tableLayoutPanel3.TabIndex = 0;
            // 
            // KnihaUploadImage
            // 
            this.KnihaUploadImage.Dock = System.Windows.Forms.DockStyle.Fill;
            this.KnihaUploadImage.Location = new System.Drawing.Point(3, 326);
            this.KnihaUploadImage.Name = "KnihaUploadImage";
            this.KnihaUploadImage.Size = new System.Drawing.Size(132, 39);
            this.KnihaUploadImage.TabIndex = 0;
            this.KnihaUploadImage.Text = "Upload Image";
            this.KnihaUploadImage.UseVisualStyleBackColor = true;
            this.KnihaUploadImage.MouseClick += new System.Windows.Forms.MouseEventHandler(this.KnihaUploadImage_MouseClick);
            // 
            // KnihaPictureBox
            // 
            this.KnihaPictureBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.KnihaPictureBox.Location = new System.Drawing.Point(3, 3);
            this.KnihaPictureBox.Name = "KnihaPictureBox";
            this.KnihaPictureBox.Size = new System.Drawing.Size(132, 317);
            this.KnihaPictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.KnihaPictureBox.TabIndex = 1;
            this.KnihaPictureBox.TabStop = false;
            // 
            // tableLayoutPanel4
            // 
            this.tableLayoutPanel4.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.tableLayoutPanel4.ColumnCount = 1;
            this.tableLayoutPanel4.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel4.Controls.Add(this.KnihaPopis, 0, 2);
            this.tableLayoutPanel4.Controls.Add(this.KnihaNazevBox, 0, 0);
            this.tableLayoutPanel4.Controls.Add(this.KnihaVydani, 0, 1);
            this.tableLayoutPanel4.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel4.Location = new System.Drawing.Point(147, 3);
            this.tableLayoutPanel4.Name = "tableLayoutPanel4";
            this.tableLayoutPanel4.RowCount = 3;
            this.tableLayoutPanel4.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 24F));
            this.tableLayoutPanel4.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 24F));
            this.tableLayoutPanel4.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel4.Size = new System.Drawing.Size(335, 368);
            this.tableLayoutPanel4.TabIndex = 1;
            // 
            // KnihaPopis
            // 
            this.KnihaPopis.Dock = System.Windows.Forms.DockStyle.Fill;
            this.KnihaPopis.Font = new System.Drawing.Font("MV Boli", 10.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.KnihaPopis.Location = new System.Drawing.Point(3, 51);
            this.KnihaPopis.MaxLength = 450;
            this.KnihaPopis.Name = "KnihaPopis";
            this.KnihaPopis.Size = new System.Drawing.Size(329, 314);
            this.KnihaPopis.TabIndex = 3;
            this.KnihaPopis.Text = "";
            this.KnihaPopis.Validated += new System.EventHandler(this.KnihaPopis_Validated);
            // 
            // KnihaNazevBox
            // 
            this.KnihaNazevBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.KnihaNazevBox.FormattingEnabled = true;
            this.KnihaNazevBox.Location = new System.Drawing.Point(0, 0);
            this.KnihaNazevBox.Margin = new System.Windows.Forms.Padding(0);
            this.KnihaNazevBox.Name = "KnihaNazevBox";
            this.KnihaNazevBox.Size = new System.Drawing.Size(335, 21);
            this.KnihaNazevBox.TabIndex = 0;
            this.KnihaNazevBox.SelectedIndexChanged += new System.EventHandler(this.KnihaNazevBox_SelectedIndexChanged);
            this.KnihaNazevBox.Validated += new System.EventHandler(this.KnihaNazevBox_Validated);
            // 
            // KnihaVydani
            // 
            this.KnihaVydani.Dock = System.Windows.Forms.DockStyle.Fill;
            this.KnihaVydani.Location = new System.Drawing.Point(3, 27);
            this.KnihaVydani.Name = "KnihaVydani";
            this.KnihaVydani.Size = new System.Drawing.Size(329, 20);
            this.KnihaVydani.TabIndex = 4;
            this.KnihaVydani.ValueChanged += new System.EventHandler(this.KnihaVydani_ValueChanged);
            // 
            // KnihaLabel
            // 
            this.KnihaLabel.BackColor = System.Drawing.SystemColors.Control;
            this.KnihaLabel.Dock = System.Windows.Forms.DockStyle.Top;
            this.KnihaLabel.Font = new System.Drawing.Font("MV Boli", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.KnihaLabel.Location = new System.Drawing.Point(0, 0);
            this.KnihaLabel.Name = "KnihaLabel";
            this.KnihaLabel.Size = new System.Drawing.Size(485, 30);
            this.KnihaLabel.TabIndex = 0;
            this.KnihaLabel.Text = "Kniha";
            this.KnihaLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // DownloadButton
            // 
            this.DownloadButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.DownloadButton.Image = ((System.Drawing.Image)(resources.GetObject("DownloadButton.Image")));
            this.DownloadButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.DownloadButton.Name = "DownloadButton";
            this.DownloadButton.Size = new System.Drawing.Size(44, 44);
            this.DownloadButton.Text = "toolStripButton1";
            this.DownloadButton.Click += new System.EventHandler(this.DownloadButton_Click);
            // 
            // UploadButton
            // 
            this.UploadButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.UploadButton.Image = ((System.Drawing.Image)(resources.GetObject("UploadButton.Image")));
            this.UploadButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.UploadButton.Name = "UploadButton";
            this.UploadButton.Size = new System.Drawing.Size(44, 44);
            this.UploadButton.Text = "toolStripButton1";
            this.UploadButton.Click += new System.EventHandler(this.UploadButton_Click);
            // 
            // SyncButton
            // 
            this.SyncButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.SyncButton.Image = ((System.Drawing.Image)(resources.GetObject("SyncButton.Image")));
            this.SyncButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.SyncButton.Name = "SyncButton";
            this.SyncButton.Size = new System.Drawing.Size(44, 44);
            this.SyncButton.Text = "toolStripButton1";
            this.SyncButton.Click += new System.EventHandler(this.SyncButton_Click);
            // 
            // toolStrip1
            // 
            this.toolStrip1.ImageScalingSize = new System.Drawing.Size(40, 40);
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.DownloadButton,
            this.UploadButton,
            this.SyncButton});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(800, 47);
            this.toolStrip1.TabIndex = 1;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 451);
            this.Controls.Add(this.splitContainer1);
            this.Controls.Add(this.toolStrip1);
            this.MinimumSize = new System.Drawing.Size(800, 490);
            this.Name = "Form1";
            this.Text = "Form1";
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel3.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.KnihaPictureBox)).EndInit();
            this.tableLayoutPanel4.ResumeLayout(false);
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.ComboBox AutorJmenoBox;
        private System.Windows.Forms.Label AutorJmenoLabel;
        private System.Windows.Forms.Label AutorLabel;
        private System.Windows.Forms.Label KnihaLabel;
        private System.Windows.Forms.ComboBox AutorPrijmeniBox;
        private System.Windows.Forms.Label AutorNarozenLabel;
        private System.Windows.Forms.DateTimePicker AutorNarozen;
        private System.Windows.Forms.Label AutorPrijmeniLabel;
        private System.Windows.Forms.ComboBox AutorPohlaviBox;
        private System.Windows.Forms.Label AutorPohlaviLabel;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel3;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel4;
        private System.Windows.Forms.ComboBox KnihaNazevBox;
        private System.Windows.Forms.ToolStripButton DownloadButton;
        private System.Windows.Forms.ToolStripButton UploadButton;
        private System.Windows.Forms.ToolStripButton SyncButton;
        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.PictureBox KnihaPictureBox;
        private System.Windows.Forms.Button KnihaUploadImage;
        private System.Windows.Forms.RichTextBox KnihaPopis;
        private System.Windows.Forms.DateTimePicker KnihaVydani;
    }
}

