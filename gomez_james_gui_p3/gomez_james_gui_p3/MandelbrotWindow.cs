using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace gomez_james_gui_p3
{
    class MandelbrotWindow: Window
    {
        private Canvas canvas;

        public MandelbrotWindow(): base()
        {
            canvas = new Canvas();
            this.Content = canvas;
        }

        [STAThread]
        public static void Main(String[] args)
        {
            MandelbrotWindow window = new MandelbrotWindow();
            window.Show();
            Application app = new Application();
            app.Run();            
        }
    }
}
