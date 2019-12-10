using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml;
using System.Xml.Linq;

namespace NejakaDivnaVecNaXMLNet
{
    public partial class XmlExplorer : Form
    {
        public System.IO.Stream file;
        public XmlExplorer()
        {
            InitializeComponent();
            
        }

        private void toolStripButton1_Click(object sender, EventArgs e)
        {
            OpenFileDialog fileOpener = new OpenFileDialog();
            //fileOpener.Filter = "XML (*.xml)|All files (*.*)";
            fileOpener.Title = "Select XML File.";
            DialogResult dr = fileOpener.ShowDialog();
            if (dr == System.Windows.Forms.DialogResult.OK)
            {
                XmlDocument xmlDoc = new XmlDocument();
                xmlDoc.Load(fileOpener.FileName);
                XmlNodeList nodeList = xmlDoc.DocumentElement.SelectNodes("/");
                TreeNode root = new TreeNode(fileOpener.FileName);
                root.Tag = DateTime.Today;
                foreach (XmlNode node in nodeList)
                {
                    treeViewer.Nodes.Add(root);
                    AddNode(node, root);
                }
            }
        }

        static string GetAttributeText(XmlNode inXmlNode, string name)
        {
            XmlAttribute attr = (inXmlNode.Attributes == null ? null : inXmlNode.Attributes[name]);
            return attr == null ? null : attr.Value;
        }

        private void AddNode(XmlNode inXmlNode, TreeNode inTreeNode)
        {

            // Loop through the XML nodes until the leaf is reached.
            // Add the nodes to the TreeView during the looping process.
            if (inXmlNode.HasChildNodes)
            {
                XmlNodeList nodeList = inXmlNode.ChildNodes;
                for (int i = 0; i <= nodeList.Count - 1; i++)
                {
                    XmlNode xNode = inXmlNode.ChildNodes[i];
                    string text = GetAttributeText(xNode, "name");
                    if (string.IsNullOrEmpty(text))
                        text = xNode.Name;
                    TreeNode t = new TreeNode(text);
                    t.Tag = xNode;
                    inTreeNode.Nodes.Add(t);
                    TreeNode tNode = inTreeNode.Nodes[i];
                    AddNode(xNode, tNode);
                }
            }
            else
            {
                string text = GetAttributeText(inXmlNode, "name");
                if (string.IsNullOrEmpty(text))
                    text = (inXmlNode.OuterXml).Trim();
                if (inTreeNode.Text != text)
                    inTreeNode.Text = text;
                inTreeNode.Nodes.Clear();
            }
        }

        private void treeViewer_AfterSelect(object sender, TreeViewEventArgs e)
        {
            if (e.Node.Tag is XmlNode)
            {
                XmlNode xmlNode = (XmlNode)e.Node.Tag;
                textBox.Text =xmlNode.InnerXml;
            }
        }

      
    }
}
