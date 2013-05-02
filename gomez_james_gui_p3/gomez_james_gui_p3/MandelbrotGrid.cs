using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;

namespace gomez_james_gui_p3
{
    class MandelbrotGrid
    {
        // primary grid properties 
        double xStart, yStart, width, height;
        int rows, cols;
        int[,] data;
        int maxIterations;
        double maxModulus;
        // secondary properties calculated from primary properties 
        int xIntervals, yIntervals;
        double dx, dy;

        public MandelbrotGrid(double xs, double ys, double w,
            double h, int r, int c, int mi, double mm) {
            cols = 0; rows = 0;

            //TODO direct assignment properties 

            Complex c1 = new Complex(1, 1);
            Complex c2 = new Complex(2, 4);
            Console.WriteLine(c1 + c2);

            // calculated properties 
            xIntervals = cols - 1;
            yIntervals = rows - 1;
            dx = width / xIntervals;
            dy = height / yIntervals;
        }
    }
}
