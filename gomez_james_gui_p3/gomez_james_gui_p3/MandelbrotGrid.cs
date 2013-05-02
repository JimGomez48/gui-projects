using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gomez_james_gui_p3
{
    class MandelbrotGrid
    {
        // primary grid properties 
        double xstart, ystart, width, height;
        int rows, cols;
        int[,] data;
        int maxiterations;
        double maxmodulus;
        // secondary properties calculated from primary properties 
        int xintervals, yintervals;
        double dx, dy;

        public MandelbrotGrid(double xs, double ys, double w, 
            double h, int r, int c, int mi, double mm)
        {            
            cols = 0; rows = 0;
            
            //TODO direct assignment properties 

            // calculated properties 
            xintervals = cols - 1;
            yintervals = rows - 1;
            dx = width / xintervals;
            dy = height / yintervals;
        }
    }
}
