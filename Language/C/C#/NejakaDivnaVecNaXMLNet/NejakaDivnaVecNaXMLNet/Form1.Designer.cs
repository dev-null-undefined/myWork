namespace NejakaDivnaVecNaXMLNet
{
    partial class XmlExplorer
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(XmlExplorer));
            this.toolStrip = new System.Windows.Forms.ToolStrip();
            this.LoadFile = new System.Windows.Forms.ToolStripButton();
            this.statusStrip = new System.Windows.Forms.StatusStrip();
            this.FileStatus = new System.Windows.Forms.ToolStripStatusLabel();
            this.splitContainer = new System.Windows.Forms.SplitContainer();
            this.treeViewer = new System.Windows.Forms.TreeView();
            this.textBox = new System.Windows.Forms.TextBox();
            this.toolStrip.SuspendLayout();
            this.statusStrip.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer)).BeginInit();
            this.splitContainer.Panel1.SuspendLayout();
            this.splitContainer.Panel2.SuspendLayout();
            this.splitContainer.SuspendLayout();
            this.SuspendLayout();
            // 
            // toolStrip
            // 
            this.toolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.LoadFile});
            this.toolStrip.Location = new System.Drawing.Point(0, 0);
            this.toolStrip.Name = "toolStrip";
            this.toolStrip.Size = new System.Drawing.Size(800, 25);
            this.toolStrip.TabIndex = 0;
            this.toolStrip.Text = "toolStrip1";
            // 
            // LoadFile
            // 
            this.LoadFile.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.LoadFile.ForeColor = System.Drawing.SystemColors.ControlLight;
            this.LoadFile.Image = ((System.Drawing.Image)(resources.GetObject("LoadFile.Image")));
            this.LoadFile.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.LoadFile.Name = "LoadFile";
            this.LoadFile.Size = new System.Drawing.Size(23, 22);
            this.LoadFile.Text = "toolStripButton1";
            this.LoadFile.Click += new System.EventHandler(this.toolStripButton1_Click);
            // 
            // statusStrip
            // 
            this.statusStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.FileStatus});
            this.statusStrip.Location = new System.Drawing.Point(0, 428);
            this.statusStrip.Name = "statusStrip";
            this.statusStrip.Size = new System.Drawing.Size(800, 22);
            this.statusStrip.TabIndex = 1;
            this.statusStrip.Text = "statusStrip1";
            // 
            // FileStatus
            // 
            this.FileStatus.Name = "FileStatus";
            this.FileStatus.Size = new System.Drawing.Size(118, 17);
            this.FileStatus.Text = "toolStripStatusLabel1";
            // 
            // splitContainer
            // 
            this.splitContainer.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer.Location = new System.Drawing.Point(0, 25);
            this.splitContainer.Name = "splitContainer";
            // 
            // splitContainer.Panel1
            // 
            this.splitContainer.Panel1.Controls.Add(this.treeViewer);
            // 
            // splitContainer.Panel2
            // 
            this.splitContainer.Panel2.Controls.Add(this.textBox);
            this.splitContainer.Size = new System.Drawing.Size(800, 403);
            this.splitContainer.SplitterDistance = 266;
            this.splitContainer.TabIndex = 2;
            // 
            // treeViewer
            // 
            this.treeViewer.Cursor = System.Windows.Forms.Cursors.Default;
            this.treeViewer.Dock = System.Windows.Forms.DockStyle.Fill;
            this.treeViewer.Location = new System.Drawing.Point(0, 0);
            this.treeViewer.Name = "treeViewer";
            this.treeViewer.Size = new System.Drawing.Size(266, 403);
            this.treeViewer.TabIndex = 0;
            this.treeViewer.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.treeViewer_AfterSelect);
            // 
            // textBox
            // 
            this.textBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBox.Location = new System.Drawing.Point(0, 0);
            this.textBox.Multiline = true;
            this.textBox.Name = "textBox";
            this.textBox.Size = new System.Drawing.Size(530, 403);
            this.textBox.TabIndex = 0;
            // 
            // XmlExplorer
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.splitContainer);
            this.Controls.Add(this.statusStrip);
            this.Controls.Add(this.toolStrip);
            this.Name = "XmlExplorer";
            this.Text = "Form1";
            this.toolStrip.ResumeLayout(false);
            this.toolStrip.PerformLayout();
            this.statusStrip.ResumeLayout(false);
            this.statusStrip.PerformLayout();
            this.splitContainer.Panel1.ResumeLayout(false);
            this.splitContainer.Panel2.ResumeLayout(false);
            this.splitContainer.Panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer)).EndInit();
            this.splitContainer.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ToolStrip toolStrip;
        private System.Windows.Forms.ToolStripButton LoadFile;
        private System.Windows.Forms.StatusStrip statusStrip;
        private System.Windows.Forms.ToolStripStatusLabel FileStatus;
        private System.Windows.Forms.SplitContainer splitContainer;
        private System.Windows.Forms.TreeView treeViewer;
        private System.Windows.Forms.TextBox textBox;
    }
}

