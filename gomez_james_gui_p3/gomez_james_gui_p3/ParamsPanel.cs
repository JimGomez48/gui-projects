using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Xml;

namespace gomez_james_gui_p3
{
    class ParamsPanel : Grid
    {
        //Textboxes
        private TextBox xStart;
        private TextBox yStart;
        private TextBox rows;
        private TextBox columns;
        private TextBox width;
        private TextBox height;
        private TextBox maxIterations;
        private TextBox maxModulus;
        private List<TextBox> textBoxes;

        //labels
        private Label xStart_label;
        private Label yStart_label;
        private Label rows_label;
        private Label columns_label;
        private Label width_label;
        private Label height_label;
        private Label maxIterations_label;
        private Label maxModulus_label;

        private const string paramsFileString = "params.xml";
        private const string allowedRegexVals = "[^0-9]+";

        public TextBox XStart {get { return xStart; }}
        
        public TextBox YStart {get { return yStart; }}
        
        public TextBox Rows {get { return rows; }}
        
        public TextBox Columns {get { return columns; }}        

        public TextBox ImageWidth {get { return width; }}
        
        public TextBox ImageHeight {get { return height; }}        

        public TextBox MaxIterations {get { return maxIterations; }}        

        public TextBox MaxModulus {get { return maxModulus; }}        

        public ParamsPanel() {
            Width = 120;
            HorizontalAlignment = HorizontalAlignment.Left;
            VerticalAlignment = VerticalAlignment.Top;
            ShowGridLines = false;

            //Add rows  with single column to gridParams
            ColumnDefinition col0 = new ColumnDefinition();
            ColumnDefinitions.Add(col0);
            for (int i = 0; i < 16; i++) {
                RowDefinitions.Add(new RowDefinition());
            }

            createLabels();
            createTextBoxes();
            arrangeAndAddUiItems();
            loadParamsXML();
        }

        private void createLabels() {
            xStart_label = new Label();
            xStart_label.Content = "X Start";
            xStart_label.Margin = new Thickness(10, 10, 10, 0);
            yStart_label = new Label();
            yStart_label.Content = "Y Start";
            yStart_label.Margin = new Thickness(10, 10, 10, 0);
            rows_label = new Label();
            rows_label.Content = "Rows";
            rows_label.Margin = new Thickness(10, 10, 10, 0);
            columns_label = new Label();
            columns_label.Content = "Columns";
            columns_label.Margin = new Thickness(10, 10, 10, 0);
            width_label = new Label();
            width_label.Content = "Width";
            width_label.Margin = new Thickness(10, 10, 10, 0);
            height_label = new Label();
            height_label.Content = "Height";
            height_label.Margin = new Thickness(10, 10, 10, 0);
            maxIterations_label = new Label();
            maxIterations_label.Content = "Max Iterations";
            maxIterations_label.Margin = new Thickness(10, 10, 10, 0);
            maxModulus_label = new Label();
            maxModulus_label.Content = "Max Modulus";
            maxModulus_label.Margin = new Thickness(10, 10, 10, 0);
        }

        private void createTextBoxes() {
            textBoxes = new List<TextBox>();
            xStart = new TextBox();
            xStart.Margin = new Thickness(10, 0, 10, 10);
            textBoxes.Add(xStart);
            yStart = new TextBox();
            yStart.Margin = new Thickness(10, 2, 10, 10);
            textBoxes.Add(yStart);
            rows = new TextBox();
            rows.Margin = new Thickness(10, 2, 10, 10);
            textBoxes.Add(rows);
            columns = new TextBox();
            columns.Margin = new Thickness(10, 2, 10, 10);
            textBoxes.Add(columns);
            width = new TextBox();
            width.Margin = new Thickness(10, 2, 10, 10);
            textBoxes.Add(width);
            height = new TextBox();
            height.Margin = new Thickness(10, 2, 10, 10);
            textBoxes.Add(height);
            maxIterations = new TextBox();
            maxIterations.Margin = new Thickness(10, 2, 10, 10);
            textBoxes.Add(maxIterations);
            maxModulus = new TextBox();
            maxModulus.Margin = new Thickness(10, 2, 10, 10);
            textBoxes.Add(maxModulus);
        }

        private void arrangeAndAddUiItems() {
            Grid.SetRow(xStart_label, 0);
            Grid.SetColumn(xStart_label, 0);
            Grid.SetRow(xStart, 1);
            Grid.SetColumn(xStart, 0);
            Grid.SetRow(yStart_label, 2);
            Grid.SetColumn(yStart_label, 0);
            Grid.SetRow(yStart, 3);
            Grid.SetColumn(yStart, 0);
            Grid.SetRow(rows_label, 4);
            Grid.SetColumn(rows_label, 0);
            Grid.SetRow(rows, 5);
            Grid.SetColumn(rows, 0);
            Grid.SetRow(columns_label, 6);
            Grid.SetColumn(columns_label, 0);
            Grid.SetRow(columns, 7);
            Grid.SetColumn(columns, 0);
            Grid.SetRow(width_label, 8);
            Grid.SetColumn(width_label, 0);
            Grid.SetRow(width, 9);
            Grid.SetColumn(width, 0);
            Grid.SetRow(height_label, 10);
            Grid.SetColumn(height_label, 0);
            Grid.SetRow(height, 11);
            Grid.SetColumn(height, 0);
            Grid.SetRow(maxIterations_label, 12);
            Grid.SetColumn(maxIterations_label, 0);
            Grid.SetRow(maxIterations, 13);
            Grid.SetColumn(maxIterations, 0);
            Grid.SetRow(maxModulus_label, 14);
            Grid.SetColumn(maxModulus_label, 0);
            Grid.SetRow(maxModulus, 15);
            Grid.SetColumn(maxModulus, 0);

            Children.Add(xStart_label);
            Children.Add(xStart);
            Children.Add(yStart_label);
            Children.Add(yStart);
            Children.Add(rows_label);
            Children.Add(rows);
            Children.Add(columns_label);
            Children.Add(columns);
            Children.Add(width_label);
            Children.Add(width);
            Children.Add(height_label);
            Children.Add(height);
            Children.Add(maxIterations_label);
            Children.Add(maxIterations);
            Children.Add(maxModulus_label);
            Children.Add(maxModulus);
        }
        /// <summary></summary>
        /// <returns> true if write was succesful, false otherwise</returns>
        public bool writeParamsXML() {
            bool success = true;
            XmlWriterSettings settings = new XmlWriterSettings();
            settings.Indent = true;
            XmlWriter writer = XmlWriter.Create(paramsFileString, settings);

            try {
                string writeValue;
                writer.WriteStartDocument();
                writer.WriteStartElement("Params");
                writeValue = (xStart.Text == null || xStart.Text == "") ? "0" : xStart.Text;
                writer.WriteElementString("XStart", writeValue);
                writeValue = (yStart.Text == null || yStart.Text == "") ? "0" : yStart.Text;
                writer.WriteElementString("YStart", writeValue);
                writeValue = (rows.Text == null || rows.Text == "") ? "720" : rows.Text;
                writer.WriteElementString("Rows", writeValue);
                writeValue = (columns.Text == null || columns.Text == "") ? "720" : columns.Text;
                writer.WriteElementString("Columns", writeValue);
                writeValue = (width.Text == null || width.Text == "") ? "720" : width.Text;
                writer.WriteElementString("Width", writeValue);
                writeValue = (height.Text == null || height.Text == "") ? "720" : height.Text;
                writer.WriteElementString("Height", writeValue);
                writeValue = (maxIterations.Text == null || maxIterations.Text == "") ? "500" : maxIterations.Text;
                writer.WriteElementString("MaxIterations", writeValue);
                writeValue = (maxModulus.Text == null || maxModulus.Text == "") ? "500" : maxModulus.Text;
                writer.WriteElementString("MaxModulus", writeValue);
                writer.WriteEndElement();
                writer.WriteEndDocument();
                writer.Flush();
            }
            catch (Exception e) {
                Console.WriteLine(e.Data);
                success = false;
            }
            finally {
                writer.Close();
            }

            return success;
        }

        public void loadParamsXML() {
            if (!File.Exists(paramsFileString)) {
                writeParamsXML();
            }
            XmlDocument paramsDoc = new XmlDocument();
            paramsDoc.Load(paramsFileString);
            XmlNodeReader reader = new XmlNodeReader(paramsDoc);

            while (reader.Read()) {
                if (reader.NodeType == XmlNodeType.Element) {
                    switch (reader.Name) {
                        case "XStart":
                            xStart.Text = reader.ReadString();
                            break;
                        case "YStart":
                            yStart.Text = reader.ReadString();
                            break;
                        case "Rows":
                            rows.Text = reader.ReadString();
                            break;
                        case "Columns":
                            columns.Text = reader.ReadString();
                            break;
                        case "Width":
                            width.Text = reader.ReadString();
                            break;
                        case "Height":
                            height.Text = reader.ReadString();
                            break;
                        case "MaxIterations":
                            maxIterations.Text = reader.ReadString();
                            break;
                        case "MaxModulus":
                            maxModulus.Text = reader.ReadString();
                            break;
                    }
                }
            }
        }

        public bool isValid() {
            Regex regex = new Regex(allowedRegexVals);
            foreach (TextBox t in textBoxes) {
                if (regex.IsMatch(t.Text))
                    return false;
            }
            
            return true;
        }

    }

}
